package network;



import model.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


public class Network {

    private static final int port = 80;
    private static final List<PrintWriter> handlers = new ArrayList<>();
    private static Socket socket = null;
    private static BufferedReader br = null;
    private static PrintWriter out = null;
    private static AtomicBoolean runningServer = new AtomicBoolean(true);
    private static ActionListener serverUpdate;

    public static void createServer(final GameState gameState, ActionListener serverUpdate) {
        Network.serverUpdate = serverUpdate;
        try {
            final ServerSocket serverSocket = new ServerSocket(port);
            //create new thread to look for clients
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lookForClients(serverSocket, gameState);
                }
            }).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//this is the reactor function which react to IO events in a separate threads
    private static void lookForClients(ServerSocket serverSocket, final GameState gameState) {
        while (runningServer.get()) {
            try {
                final Socket connectionSocket = serverSocket.accept();
                //create new thread to handle the clients
                Thread clientThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handleClient(connectionSocket, gameState);
                    }
                });
                clientThread.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ConnectionResponse connectToServer(String server, ActionListener listener) {
        final String host = server;
        System.out.println("Creating socket to '" + host + "' on port " + port);
        try {
            socket = new Socket(host, port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            int playerNum = Integer.parseInt(br.readLine());

            

            System.out.println("Successfully read from server, player: " + playerNum);

            ConnectionResponse cr = new ConnectionResponse();
            String gameStateString = br.readLine();
            cr.gameState = gameStateFromBytes(gameStateString);
            cr.playerIndex = playerNum;
          
            handlers.add(out);
            startReadLoop(br, listener);
            return cr;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
    }

    private static void startReadLoop(final BufferedReader br, final ActionListener al) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String gameStateString = br.readLine();
                        al.actionPerformed(
                                new ActionEvent(gameStateFromBytes(gameStateString),
                                        0, "update")
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public static void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
            }
            if (br != null) {
                br.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static GameState gameStateFromBytes(String base64) {
        byte[] bytes = DatatypeConverter.parseBase64Binary(base64);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            return (GameState) o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

    }

    static void handleClient(Socket connectionSocket, GameState gameState) {
        //Try connect to the server on an unused port eg 9991. A successful connection will return a socket
        try {
            //Create Input&Outputstreams for the connection
            InputStream recieveStream = connectionSocket.getInputStream();
            OutputStream sendStream = connectionSocket.getOutputStream();

            Scanner recieveScanner = new Scanner(recieveStream, "UTF-8");
            PrintWriter SendPrinter = new PrintWriter(new OutputStreamWriter(sendStream, "UTF-8"), true);

            // output player number
            SendPrinter.println(handlers.size()+1);
            SendPrinter.println(toBytes(gameState));
            handlers.add(SendPrinter);
            while (recieveScanner.hasNext()) {
                String readResult = recieveScanner.nextLine();
                GameState resultGameState = gameStateFromBytes(readResult);
                serverUpdate.actionPerformed(new ActionEvent(resultGameState, 0, ""));
                
                //dispatch gamestate to all handlers
                for (PrintWriter subscriber : handlers) {
                    if (subscriber != SendPrinter) {
                    	subscriber.println(toBytes(resultGameState));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toBytes(GameState gameState) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(gameState);
            out.flush();
            byte[] bytes = bos.toByteArray();
            return DatatypeConverter.printBase64Binary(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    public static void notifySubscribers(GameState gameState) {
        for(PrintWriter pr : handlers) {
            pr.println(toBytes(gameState));
        }
    }
}