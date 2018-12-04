package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import core.Game;
import model.Deck;

public class MainMenuView extends JPanel implements MouseListener, KeyListener {

	List<Button> buttons = new ArrayList();
	ActionListener gameStart;
	String serverip = "localhost";
	boolean remote = false;
	boolean playerPick = false;

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 0; i < buttons.size(); i++) {

			Button button = buttons.get(i);
			button.setPosition(this.getWidth() - button.size.x, 100 + i * 95);
			button.paint(g);

		}
		g.setColor(Color.black);
		if (playerPick) {
			g.drawString("Do you want to use the timer?", this.getWidth() - 400, 470);
			g.drawString("Do you want to set initial hands of players?", this.getWidth() - 450, 670);
		}

		if (remote == true) {

			g.drawString("Server:" + serverip, this.getWidth() - 350, 150);

		}
		repaint();

	}

	public void init(ActionListener gameStart) {
		this.gameStart = gameStart;
		Button createLocal = new Button("pics/local.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				playerPick = true;
				local();
			}
		});
		buttons.add(createLocal);
		this.addMouseListener(this);
		this.addKeyListener(this);

		Button createRemote = new Button("pics/remote.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				remote();
				remote = true;
			}
		});
		buttons.add(createRemote);

	}

	protected void remote() {
		Button connect = new Button("pics/done.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "Remote:" + serverip));
			}
		});
		buttons.add(connect);

	}

	public void local() {

		Button twoplayers = new Button("pics/2players.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				createButtonTwoPlayers();
				playerPick = false;
			}
		});
		buttons.add(twoplayers);

		Button threeplayers = new Button("pics/3players.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				createButtonThreePlayers();
				playerPick = false;
			}
		});
		buttons.add(threeplayers);

		Button fourplayers = new Button("pics/4players.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				createButtonFourPlayers();
				playerPick = false;
			}
		});
		buttons.add(fourplayers);

		final Button yTimer = new Button("pics/yes.png", null);
		yTimer.action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				yTimer.selected();
				GameView.useTimer = true;
			}
		};
		buttons.add(yTimer);

		final Button nTimer = new Button("pics/no.png", null);
		nTimer.action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nTimer.selected();
				GameView.useTimer = false;
			}
		};
		buttons.add(nTimer);

		final Button yRig = new Button("pics/yes.png", null);
		yRig.action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				yRig.selected();
				Game.rig = true;
			}
		};
		buttons.add(yRig);

		final Button nRig = new Button("pics/no.png", null);
		nRig.action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nRig.selected();
				Game.rig = false;
			}
		};
		buttons.add(nRig);

	}

	public void createButtonTwoPlayers() {

		Button zeroais = new Button("pics/0ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "2p0ai"));

			}
		});
		buttons.add(zeroais);

		AIButton oneai = new AIButton("pics/1ai.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "2p1ai" + getStrategies()));
			}
		}, getAIButtons());
		buttons.add(oneai);

		AIButton twoais = new AIButton("pics/2ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "2p2ai" + getStrategies()));
			}
		}, getAIButtons());
		buttons.add(twoais);

	}

	public void createButtonFourPlayers() {

		Button zeroais = new Button("pics/0ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "4p0ai"));
			}
		});
		buttons.add(zeroais);

		AIButton oneai = new AIButton("pics/1ai.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "4p1ai" + getStrategies()));
			}
		}, getAIButtons());

		buttons.add(oneai);

		AIButton twoais = new AIButton("pics/2ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "4p2ai" + getStrategies()));
			}
		}, getAIButtons());
		buttons.add(twoais);

		AIButton threeais = new AIButton("pics/3ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "4p3ai" + getStrategies()));
			}
		}, getAIButtons());
		buttons.add(threeais);

	}

	public void createButtonThreePlayers() {

		Button zeroais = new Button("pics/0ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "3p0ai"));

			}
		});
		buttons.add(zeroais);

		AIButton oneai = new AIButton("pics/1ai.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "3p1ai" + getStrategies()));
			}
		}, getAIButtons());
		buttons.add(oneai);

		AIButton twoais = new AIButton("pics/2ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "3p2ai" + getStrategies()));
			}
		}, getAIButtons());
		buttons.add(twoais);

		AIButton threeais = new AIButton("pics/3ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gameStart.actionPerformed(new ActionEvent(this, 0, "3p3ai" + getStrategies()));
			}
		}, getAIButtons());
		buttons.add(threeais);

	}

	protected String getStrategies() {
		String retString = "";

		for (Button b : buttons) {
			if (b instanceof AIButton) {
				AIButton aiB = (AIButton) b;
				int i = 1;
				for (Button ai : aiB.getAiButtons()) {

					if (ai.isSelected()) {

						retString += ";" + i;

					}
					i++;

				}
			}

		}
		return retString;
	}

	public List<Button> getAIButtons() {
		List<Button> aiButtons = new ArrayList<>();
		final Button strat1 = new Button("pics/strat1.png", null);
		strat1.setActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strat1.selected();
			}
		});
		aiButtons.add(strat1);
		final Button strat2 = new Button("pics/strat2.png", null);
		strat2.setActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strat2.selected();
			}
		});
		aiButtons.add(strat2);
		final Button strat3 = new Button("pics/strat3.png", null);
		strat3.setActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strat3.selected();
			}
		});
		aiButtons.add(strat3);
		return aiButtons;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		for (int i = 0; i < buttons.size(); i++) {

			Button button = buttons.get(i);
			if (button.mouseCollide(e.getX(), e.getY())) {

				button.click();
				repaint();

			}
			if (button instanceof AIButton) {
				AIButton aiB = (AIButton) button;

				for (int j = 0; j < aiB.getAiButtons().size(); j++) {

					Button aiButton = aiB.getAiButtons().get(j);
					if (aiButton.mouseCollide(e.getX(), e.getY())) {

						aiButton.click();
						repaint();

					}

				}
			}

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			gameStart.actionPerformed(new ActionEvent(this, 0, "Remote:" + serverip));

		}

		else if (e.getKeyCode() == 8) {

			serverip = "";
			repaint();

		}

		else {

			serverip += e.getKeyChar();
			repaint();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
