package view;

import java.applet.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Game;
import model.Card;
import model.Deck;
import model.GameState;
import model.Meld;
import model.Player;


 

public class GameView extends JPanel implements MouseListener, MouseMotionListener, Observer, KeyListener {
	Drawing tile;
	GameState gameState;
	List<Button> buttons;
	List<ViewCard> tiles;
	List<ViewMeld> viewMelds;
	Map<String, ActionListener> actions;
	Timer playTimer = new Timer();
	int maxTimeRemaining = 120;
	int timeRemaining = maxTimeRemaining;
	public static boolean useTimer = false;
	Font normalFont = new Font("Arial", Font.PLAIN, 12);
	Font bigFont = new Font("Arial", Font.BOLD, 22);
	Button placeButton;
	Button drawButton;
	Button doneButton;
	String rigString = "";

	public void init(GameState gs, Map<String, ActionListener> a) {
		buttons = new ArrayList<>();
		tiles = new ArrayList<>();
		viewMelds = new ArrayList<>();
		actions = a;
		Drawing.setApplet(this);
		tile = new Drawing("pics/tiles/blank.png");
		playTimer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				timeRemaining--;
				if (timeRemaining == 0 && useTimer == true) {
					if (doneButton.unavailabele == true) {

						drawTile();
					}
					triggerDone();

				}
				repaint();
			}
		}, 1000, 1000);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		update(null, gs);
		// tiles = new ArrayList<Drawing>();
		setSize(1200, 850);
		// offscreen = createImage(1020, 650);
		// offg = offscreen.getGraphics();
		requestFocus();
		// //////////////////////////////////////////////////

	}

	public void createButtons() {
		buttons.clear();

		if (!gameState.getCurrentPlayerName().equals(Game.myPlayer) && Game.myPlayer != null) {

			return;

		}
		doneButton = new Button("pics/done.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				triggerDone();
				;

			}
		});
		doneButton.unavailabele = !gameState.getCurrentPlayer().isAi();

		buttons.add(doneButton);

		drawButton = new Button("pics/drawTile.png", null);

		drawButton.unavailabele = gameState.getCurrentPlayer().isAi();

		buttons.add(drawButton);

		placeButton = new Button("pics/placeMeld.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Meld m : gameState.getMelds()) {

					m.makeStale();

				}
				Meld meld = new Meld();
				for (ViewMeld vm : viewMelds) {

					for (int i = vm.tiles.size() - 1; i >= 0; i--) {
						ViewCard vc = vm.tiles.get(i);
						if (vc.isSelected()) {

							vm.getMeld().getCards().remove(i);
							meld.addCard(vc.getCard());

						}

					}

				}
				for (ViewCard vs : tiles) {

					if (vs.isSelected()) {
						Card c = vs.getCard();
						c.setNewCard(true);
						meld.addCard(c);
						gameState.getCurrentPlayer().addToCurrenScore(c.getRank());

						Player p = gameState.getCurrentPlayer();
						for (int i = p.getSize() - 1; i >= 0; i--) {

							Card playerCard = p.getCards().get(i);
							if (playerCard.equals(c)) {
								p.getCards().remove(playerCard);
								break; // if there are duplicate tiles in the
										// player's hand, don't remove both
							}
						}
					}

				}

				meld.assignJokerValue();

				doneButton.unavailabele = false;
				drawButton.unavailabele = true;
				gameState.getMelds().add(meld);

				refreshViewItems();

			}
		});
		placeButton.unavailabele = gameState.getCurrentPlayer().isAi();

		buttons.add(placeButton);

		drawButton.action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				drawTile();

			}
		};

	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (int i = 0; i < gameState.getDeck().getNumCards(); i++) {

			tile.position.x = i * 4;
			tile.paint(g);
		}

		for (int i = 0; i < tiles.size(); i++) {
			ViewCard card = tiles.get(i);
			int cardWidth = 70;
			if (tiles.size() > 17) {

				cardWidth = (int) (70f * (17f / tiles.size()));

			}
			card.setPosition(i * cardWidth, this.getHeight() - card.size.y);

			card.paint(g);

		}
		for (int i = 0; i < buttons.size(); i++) {

			Drawing button = buttons.get(i);
			button.setPosition(this.getWidth() - button.size.x, 100 + i * 70);
			button.paint(g);

		}

		g.setColor(Color.black);
		g.setFont(normalFont);
		if (useTimer == true) {

			int minutes = timeRemaining / 60;
			int seconds = timeRemaining % 60;
			g.drawString("timeRemaining " + minutes + ":" + seconds, this.getWidth() - 120, 70);

		}

		g.drawString("deckNum" + gameState.getDeck().getNumCards(), 0, 100);
		g.drawString("CurrenPlayer" + gameState.getThisPlayer(), 600, 50);
		if (rigString.isEmpty() == false) {
			g.drawString("rig" + rigString, 600, 70);
		}
		for (int i = 0; i < viewMelds.size(); i++) {

			ViewMeld meld = viewMelds.get(i);

			meld.paint(g, 200 + i * 40);

		}
		if (gameState.getWinMessage() != null) {
			g.setFont(bigFont);
			g.setColor(Color.BLACK);
			g.drawString(gameState.getWinMessage(), 300, 150);

		}
		/*
		 * for(Image i : tiles) { g.drawImage(i, 0, 0, null); }
		 */

	}

	public void update(Observable arg0, Object arg1) {

		GameState gameState = (GameState) arg1;

		this.gameState = gameState;
		if (gameState.getWinMessage() == null) {

			createButtons();

		} else {

			buttons.clear();

		}

		refreshViewItems();
		timeRemaining = maxTimeRemaining;
	}

	public void refreshViewItems() {

		tiles.clear();
		viewMelds.clear();

		for (Card c : gameState.getCurrentPlayer().getCards()) {

			ViewCard vc = new ViewCard(c.getCardfileName(), c);

			tiles.add(vc);
		}
		Collections.sort(tiles);

		for (Meld m : this.gameState.getMelds()) {

			ViewMeld vc = new ViewMeld(m);

			viewMelds.add(vc);
		}
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		for (Drawing t : tiles) {

			if (t.mouseCollide(e.getX(), e.getY())) {

				t.selected();
				repaint();
			}

		}
		for (int i = 0; i < buttons.size(); i++) {
			Button b = buttons.get(i);
			if (b.mouseCollide(e.getX(), e.getY())) {

				b.click();
				repaint();
			}

		}

		ViewMeld selectedVm = null;
		for (ViewMeld vm : viewMelds) {

			if (vm.hasSelectedTile()) {

				selectedVm = vm;

			}

		}
		for (int i2 = viewMelds.size() - 1; i2 >= 0; i2--) {
			ViewMeld m = viewMelds.get(i2);

			if (m.mouseCollide(e.getX(), e.getY())) {
				boolean playTile = false;
				for (int i = tiles.size() - 1; i >= 0; i--) {

					ViewCard vc = tiles.get(i);

					if (vc.isSelected()) {
						playTile = true;
						doneButton.unavailabele = false;
						drawButton.unavailabele = true;

						gameState.getCurrentPlayer().getCards().remove(vc.getCard());
						m.getMeld().addCard(vc.getCard());
						vc.getCard().setNewCard(true);

					}

				}
				if (playTile == false) {
					if (selectedVm != null && selectedVm != m) {

						moveMeldTiles(selectedVm, m);

					} else {
						m.clickTile(e.getX(), e.getY());
						repaint();
					}
				} else {

					refreshViewItems();

				}
				m.getMeld().assignJokerValue();

				break;
			}

		}

	}

	private void moveMeldTiles(ViewMeld selectedVm, ViewMeld m) {

		for (int i = selectedVm.tiles.size() - 1; i >= 0; i--) {

			ViewCard vc = selectedVm.tiles.get(i);
			if (vc.isSelected()) {

				selectedVm.tiles.remove(i);
				selectedVm.getMeld().getCards().remove(vc.getCard());
				m.getMeld().getCards().add(vc.getCard());

			}

		}
		refreshViewItems();
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (Drawing t : tiles) {

			if (t.mouseCollide(e.getX(), e.getY())) {

				t.triggerHovered();
				repaint();
			} else {

				t.notHovered();

			}

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 47) {

			Button rig = new Button("pics/rig.png", new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					addRigTile();
				}
			});

			buttons.add(rig);
			repaint();
		} else if (e.getKeyCode() == 10) {
			addRigTile();

		}

		else if (e.getKeyCode() == 8) {

			rigString = "";
			repaint();

		}

		else {

			rigString += e.getKeyChar();
			repaint();

		}

		System.out.println(e.getKeyCode());
	}

	public void addRigTile() {

		String color = "" + rigString.charAt(0);
		int rank = Integer.parseInt(rigString.substring(1, rigString.length()));

		Card rigChar = new Card(Card.stringToColor(color), rank, rank == 0);
		gameState.getCurrentPlayer().addCard(rigChar);
		rigString = "";
		refreshViewItems();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void drawTile() {
		if (gameState.getDeck().isEmpty() == false) {
			System.out.println("Player " + gameState.getThisPlayer() + "s draw new tile");
			Card c = gameState.getDeck().deal();

			gameState.getCurrentPlayer().addCard(c);
			tiles.add(new ViewCard(c.getCardfileName(), c));
			doneButton.unavailabele = false;
			drawButton.unavailabele = true;
			placeButton.unavailabele = true;
		}
	}

	private void triggerDone() {
		for(int i = gameState.getMelds().size()-1; i>= 0; i--){
			
			Meld m = gameState.getMelds().get(i);
			if(m.getSize()==0){
				
				gameState.getMelds().remove(i);
				
				
			}
			
			
			
		}
		
		actions.get("Done").actionPerformed(new ActionEvent(gameState, 0, ""));
	}
}
}