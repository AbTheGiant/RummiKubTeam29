package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.swing.JFrame;

import model.Card;
import model.Deck;
import model.GameState;
import model.Meld;
import model.Player;
import view.GameView;

public class Game extends Observable {
	public static boolean rig = false;
	private ArrayList<Meld> melds = new ArrayList<Meld>();
	// private Player[] players = new Player[4];
	private Deck deck = new Deck();
	private GameView view = new GameView();
	private boolean stopGame;
	private GameState gameState;
	private Map<String, ActionListener> actions = new HashMap<>();

	Game() {
		/*
		 * players[3] = new Player(new HumanStrategy());
		 * players[3].setName("human"); players[0] = new Player(new
		 * AIStrategy3()); players[0].setName("p3"); players[1] = new Player(new
		 * AIStrategy2()); players[1].setName("p2"); players[2] = new Player(new
		 * AIStrategy1()); players[2].setName("p1"); deck.shuffle();
		 * 
		 * for (int i = 0; i < 4; i++) { this.addObserver(players[i]); for (int
		 * i1 = 0; i1 < 14; i1++) { players[i].addCard(deck.deal()); }
		 * 
		 * } gameState = new GameState(players, players[0].getName(), deck,
		 * melds);
		 * 
		 * view.init(gameState, getActions()); view.setVisible(true);
		 */

	}

	public Game(GameState gs, final GameView gv) {

		deck = gs.getDeck();
		view = gv;
		gameState = gs;
		final Game me = this;

		this.addObserver(gv);

		getActions().put("Done", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GameState newGameState = (GameState) e.getSource();
				if (validTable(newGameState) && firstMeldCheck(newGameState)) {

					me.gameState = newGameState;
					createObservers();
				}

				else {
					Player p = gameState.getCurrentPlayer();
					p.addCard(deck.deal());
					p.addCard(deck.deal());
					p.addCard(deck.deal());

				}
				checkGameFinished();

				boolean resolved = resolveDecideFirstPlayer();
				endTurn(gv, me, resolved);

			}

		});
		actions.put("Place Meld", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Meld m = (Meld) e.getSource();
				Player p = gameState.getCurrentPlayer();

				if (p.validateMeld(m) == false) {

				} else {

					addMeld(m);

				}

				me.setChanged();
				me.notifyObservers(gameState.copy());

			}
		});
		actions.put("Draw Tile", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

	}

	protected void checkGameFinished() {

		if (this.gameState.getDeck().isEmpty() == true) {

			int lowestScore = Integer.MAX_VALUE;
			String lowestPlayerName = "";
			String firstLowestScore = null;

			for (Player p : gameState.getPlayers()) {

				if (p.getScore() < lowestScore) {

					lowestScore = p.getScore();
					lowestPlayerName = p.getName();
					firstLowestScore = p.getName();
				} else if (p.getScore() == lowestScore) {

					lowestPlayerName += " and " + p.getName();

				}
			}
			stopGame = true;
			this.gameState.setWinMessage(lowestPlayerName + " wins with the score of " + lowestScore);
			this.gameState.setCurrentPlayerName(firstLowestScore);
		}

	}

	protected void createObservers() {
		this.deleteObservers();
		for (Player p : gameState.getPlayers()) {

			this.addObserver(p);

		}
		this.addObserver(view);

	}

	private boolean resolveDecideFirstPlayer() {

		if (gameState.isDecideFirstPlayer()) {

			boolean allHasOneTile = true;
			Player HighestTilePlayer = gameState.getPlayers()[0];
			Card secondHighestCard = null;
			// find out if all players has one tile
			// also find player with highest tile
			for (int i = 1; i < gameState.getPlayers().length; i++) {
				Player p = gameState.getPlayers()[i];
				if (p.getSize() == 0) {

					allHasOneTile = false;

				} else {
					Card c = p.getCards().get(0);
					Card highestCard = HighestTilePlayer.getCards().get(0);
					if (c.getRank() > highestCard.getRank()) {

						HighestTilePlayer = p;

					} else if (c.getRank() == highestCard.getRank()) {
						secondHighestCard = c;

					}
				}

			}

			if (allHasOneTile == true) {

				// check if their were duplicate highest cards
				if (secondHighestCard != null
						&& secondHighestCard.getRank() == HighestTilePlayer.getCards().get(0).getRank()) {
					for (Player p : gameState.getPlayers()) {

						p.getCards().clear();

					}
				} else {

					gameState.setCurrentPlayerName(HighestTilePlayer.getName());
					gameState.setDecideFirstPlayer(false);
					if (rig == true) {

						rigPlayerHand();

					} else {
						for (Player p : gameState.getPlayers()) {

							p.getCards().clear();
							for (int i1 = 0; i1 < 14; i1++) {
								p.addCard(gameState.getDeck().deal());

							}
						}
					}
					if (rig == true) {
						int cardNum = 0;
						for (Player p : gameState.getPlayers()) {

							cardNum += p.getSize();

						}
						gameState.setDeck(new Deck(106 - cardNum));

					} else {

						gameState.setDeck(new Deck());

					}

					gameState.getDeck().shuffle();
					return true;
				}

			}

		}

		return false;

	}

	private void rigPlayerHand() {

		for (Player p : gameState.getPlayers()) {
			p.getCards().clear();
			try {

				BufferedReader br = new BufferedReader(new FileReader(p.getName() + ".txt"));
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < 14; i++) {

					String line = br.readLine();
					String color = "" + line.charAt(0);
					int rank = Integer.parseInt(line.substring(1, line.length()));

					Card rigChar = new Card(Card.stringToColor(color), rank, rank == 0);
					p.addCard(rigChar);

				}

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

	}

	protected boolean firstMeldCheck(GameState newGameState) {
		if (gameState.getMelds().size() < newGameState.getMelds().size()) {

			Meld m = newGameState.getMelds().get(gameState.getMelds().size());
			if (gameState.getCurrentPlayer().validateMeld(m)) {

				return true;

			}
		} else {

			// we detected that the player did not place a meld
			return true;

		}

		return false;
	}

	Game(Deck deck, Player[] players) {
		/*
		 * this.players = players; this.deck = deck; for (int i = 0; i < 4; i++)
		 * { this.addObserver(players[i]); }
		 */
	}

	public void update() {

		this.setChanged();
		this.notifyObservers(this.gameState.copy());
		view.update(this, this.gameState.copy());
	}

	private void endTurn(final GameView gv, final Game me, boolean resolved) {
		if (!resolved && this.stopGame == false) {

			gameState.setCurrentPlayerName(gameState.FindNextPlayer());

		}

		me.setChanged();
		me.notifyObservers(gameState.copy());
		gv.update(me, gameState.copy());
	}

	public void addMeld(Meld meld) {
		for (Meld m : gameState.getMelds()) {

			m.makeStale();

		}
		gameState.getMelds().add(meld);

	}

	public boolean validTable(GameState gameState) {
		for (Meld meld : gameState.getMelds()) {
			if (!meld.isValid())
				return false;
		}
		return true;
	}

	public void play() {
		/*
		 * while (!stopGame) { setChanged(); this.notifyObservers(this); }
		 * System.out.println(this); for (int i = 0; i < 4; i++) { if
		 * (players[i].getSize() == 0) { System.out.println("Player " +
		 * players[i].getName() + " win"); } }
		 */
	}

	@Override
	public String toString() {
		String result = "";
		for (Meld meld : melds) {
			result += meld.toString() + "\n";
		}
		return result;
	}

	public ArrayList<Meld> getMelds() {
		return gameState.getMelds();
	}

	// public static void main(String []args)
	// {
	// Game game=new Game();
	// game.play();
	// }

	public Deck getDeck() {
		return deck;
	}

	public boolean isStopGame() {
		return stopGame;
	}

	public void setStopGame(boolean stopGame) {
		this.stopGame = stopGame;
	}

	public Player[] getPlayers() {
		return gameState.getPlayers();
	}

	public Map<String, ActionListener> getActions() {
		return actions;
	}

	public void setActions(Map<String, ActionListener> actions) {
		this.actions = actions;
	}

	public void removeMeld(Meld meld) {
		for (int i = gameState.getMelds().size() - 1; i >= 0; i--) {

			Meld m = gameState.getMelds().get(i);
			if (meld.equals(m)) {

				gameState.getMelds().remove(i);

			}
		}

	}

	public void drawCard() {
		gameState.getCurrentPlayer().addCard(gameState.getDeck().deal());

	}

	public void removeCard(Card card) {

		Card c = gameState.getCurrentPlayer().getCard(card.toString());
		gameState.getCurrentPlayer().removeCard(c);

	}

}
