package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.Deck;
import model.GameState;
import model.Meld;
import model.Player;
import network.ConnectionResponse;
import network.Network;
import view.MainView;
import view.GameView;
import view.MainMenuView;

public class Main {
	static MainView view = new MainView();
	static boolean isServer = false;
	static Game game = null;

	public static void main(String[] args) {

		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenuView mmv = new MainMenuView();
		mmv.init(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandFork(e);

			}

		});
		view.init(mmv);

		view.setVisible(true);

	}

	private static GameState fetchGameState(String actionCommand) {

		if (actionCommand.contains(":")) {
			return connectToRemote(actionCommand);

		}

		isServer = true;
		int playerNum = Integer.parseInt("" + actionCommand.charAt(0));
		int aiNum = Integer.parseInt("" + actionCommand.charAt(2));
		String[] strategies = actionCommand.split(";");

		List<Player> players = new ArrayList<>();
		int strategyNum = 1;
		for (int i = 0; i < playerNum; i++) {
			if (i < playerNum - aiNum) {
				Player humanPlayer = new Player(new HumanStrategy());
				humanPlayer.setName("Human " + i);
				players.add(humanPlayer);
			} else {
				Player aiPlayer = null;

				if (strategies[strategyNum].equals("1")) {

					aiPlayer = new Player(new AIStrategy1());

				} else if (strategies[strategyNum].equals("2")) {

					aiPlayer = new Player(new AIStrategy2());

				} else if (strategies[strategyNum].equals("3")) {

					aiPlayer = new Player(new AIStrategy3());

				}
				aiPlayer.setName("AI " + i + " Strategy" + strategies[strategyNum]);
				strategyNum++;

				players.add(aiPlayer);
			}
		}

		GameState gameState = new GameState(players.toArray(new Player[players.size()]), players.get(0).getName(),
				new Deck(), new ArrayList<Meld>());

		gameState.getDeck().shuffle();
		gameState.setDecideFirstPlayer(true);

		/*
		 * for (Player p : players) { for (int i1 = 0; i1 < 14; i1++) {
		 * p.addCard(gameState.deck.deal()); } }
		 */

		return gameState;
	}

	private static GameState connectToRemote(String actionCommand) {

		String[] split = actionCommand.split(":");

		String ip = split[1];
		ConnectionResponse r = Network.connectToServer(ip, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.update((GameState) e.getSource());

			}
		});
		Game.myPlayer = r.getGameState().getPlayers()[r.getPlayerIndex()].getName();
		return r.getGameState();
	}

	private static void commandFork(ActionEvent e) {
		String command = (e.getActionCommand());
		GameState gameState = fetchGameState(command);

		GameView gameView = new GameView();
		game = new Game(gameState, gameView);
		for (Player p : gameState.getPlayers()) {

			game.addObserver(p);

		}
		// game.addObserver(gameView);
		gameView.init(gameState, game.getActions());
		// game.update();
		view.init(gameView);
		game.update(gameState);
		if (isServer == true) {
			Network.createServer(gameState, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					game.update((GameState) e.getSource());

				}
			});
		}
	}
}
