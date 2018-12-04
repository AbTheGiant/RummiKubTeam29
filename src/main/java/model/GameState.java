package model;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import javax.management.RuntimeErrorException;

public class GameState extends Observable implements Serializable {

	
	final Player[] players;
    private String currentPlayer;
     Deck deck;
   private String winMessage;

	final ArrayList<Meld> melds;
private boolean decideFirstPlayer; 
    
public GameState(Player[] players, String thisPlayer, Deck deck, ArrayList<Meld> melds) {
        this.players = players;
        this.setCurrentPlayerName(thisPlayer);
        this.deck = deck;
        this.melds = melds;
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getThisPlayer() {
        return getCurrentPlayerName();
    }

    public void setDeck(Deck deck) {
		this.deck = deck;
	}
    
    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Meld> getMelds() {
        return melds;
    }
	
	public Player getCurrentPlayer(){
		for(Player p: players ){
			
			if(p.getName().equals(getCurrentPlayerName())){
				
				
				return p;
			}
			
			
			
		}
		throw new RuntimeException("Could not find Player");
		
	}
public String FindNextPlayer(){
	
	for(int  i = 0; i<players.length; i++){
		
		Player p = players[i];
		
		if(p.equals(getCurrentPlayer())){
			
			i++;
			if(i>=players.length){
				
				i=0;
				
			}
			return players[i].getName();
			
		}
		
	}
	throw new RuntimeException("Could not find Player");
	
}

public GameState copy(){
	
	return fromBytes(toBytes(this));
	
	
	
	
	
}


private static byte[] toBytes(GameState gameState) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out = null;
    try {
        out = new ObjectOutputStream(bos);
        out.writeObject(gameState);
        out.flush();
        return bos.toByteArray();
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
private static GameState fromBytes(byte[] bytes) {
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

public boolean isDecideFirstPlayer() {
	return decideFirstPlayer;
}

public void setDecideFirstPlayer(boolean decideFirstPlayer) {
	this.decideFirstPlayer = decideFirstPlayer;
}

public String getWinMessage() {
	return winMessage;
}

public void setWinMessage(String winMessage) {
	this.winMessage = winMessage;
}

public String getCurrentPlayerName() {
	return currentPlayer;
}

public void setCurrentPlayerName(String currentPlayer) {
	this.currentPlayer = currentPlayer;
}

}
