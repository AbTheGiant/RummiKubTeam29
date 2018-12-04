package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import core.Game;
import model.Deck;

public class MainMenuView extends JPanel implements MouseListener {

	List<Button> buttons = new ArrayList();
	ActionListener gameStart;

	public void paint(Graphics g) {

		for (int i = 0; i < buttons.size(); i++) {

			Button button = buttons.get(i);
			button.setPosition(this.getWidth() - button.size.x, 100 + i * 95);
			button.paint(g);

		}
		if(buttons.size()==7){
			g.setColor(Color.black);
			g.drawString("Do you want to use the timer?", this.getWidth() - 400, 470);
			g.drawString("Do you want to set initial hands of players?", this.getWidth() - 450, 670);
		}
		
		repaint();

	}

	public void init(ActionListener gameStart) {
		this.gameStart = gameStart;
		Button createLocal = new Button("pics/local.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				local();
			}
		});
		buttons.add(createLocal);
		this.addMouseListener(this);

	}

	public void local() {

		Button twoplayers = new Button("pics/2players.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				createButtonTwoPlayers();

			}
		});
		buttons.add(twoplayers);

		Button threeplayers = new Button("pics/3players.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				createButtonThreePlayers();
			}
		});
		buttons.add(threeplayers);

		Button fourplayers = new Button("pics/4players.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				createButtonFourPlayers();
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
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "2p0ai"));

			}
		});
		buttons.add(zeroais);

		Button oneai = new Button("pics/1ai.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "2p1ai"));
			}
		});
		buttons.add(oneai);

	}

	public void createButtonFourPlayers() {

		Button zeroais = new Button("pics/0ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "4p0ai"));
			}
		});
		buttons.add(zeroais);

		Button oneai = new Button("pics/1ai.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "4p1ai"));
			}
		});

		buttons.add(oneai);

		Button twoais = new Button("pics/2ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "4p2ai"));
			}
		});
		buttons.add(twoais);

		Button threeais = new Button("pics/3ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "4p3ai"));
			}
		});
		buttons.add(threeais);

	}

	public void createButtonThreePlayers() {

		Button zeroais = new Button("pics/0ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "3p0ai"));

			}
		});
		buttons.add(zeroais);

		Button oneai = new Button("pics/1ai.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "3p1ai"));
			}
		});
		buttons.add(oneai);

		Button twoais = new Button("pics/2ais.png", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttons.clear();
				gameStart.actionPerformed(new ActionEvent(this, 0, "3p2ai"));
			}
		});
		buttons.add(twoais);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		for (int i = 0; i < buttons.size(); i++) {

			Button button = buttons.get(i);
			if (button.mouseCollide(e.getX(), e.getY())) {

				button.click();
				repaint();

			}

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
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

}
