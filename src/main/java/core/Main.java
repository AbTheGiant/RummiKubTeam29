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
import view.MainView;
import view.GameView;
import view.MainMenuView;

public class Main {
	static MainView view = new MainView();

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

		int playerNum = Integer.parseInt("" + actionCommand.charAt(0));
		int aiNum = Integer.parseInt("" + actionCommand.charAt(2));

		List<Player> players = new ArrayList<>();
		for (int i = 0; i < playerNum; i++) {
			if (i < 4 - aiNum) {
				Player humanPlayer = new Player(new HumanStrategy());
				humanPlayer.setName("Human " + i);
				players.add(humanPlayer);
			} else {
				Player aiPlayer = new Player(new AIStrategy1());
				aiPlayer.setName("AI " + i);
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

	private static void commandFork(ActionEvent e) {
		String command = (e.getActionCommand());
		GameState gameState = fetchGameState(command);

		GameView gameView = new GameView();
		Game game = new Game(gameState, gameView);
		for (Player p : gameState.getPlayers()) {

			game.addObserver(p);

		}
		// game.addObserver(gameView);
		gameView.init(gameState, game.getActions());
		// game.update();
		view.init(gameView);
		game.update();

	}
}
