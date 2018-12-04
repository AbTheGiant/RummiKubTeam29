package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Card;
import model.Meld;

public class ViewMeld {

	private Meld meld;
	List<ViewCard> tiles;

	public ViewMeld(Meld m) {
		tiles = new ArrayList<>();
		meld = m;

		for (Card c : m.getCards()) {

			ViewCard vc = new ViewCard(c.getCardfileName(), c);

			tiles.add(vc);
		}
		Collections.sort(tiles);

	}

	public void paint(Graphics g, int y) {

		for (int i = 0; i < tiles.size(); i++) {
			ViewCard card = tiles.get(i);
			card.setPosition(i * 70, y);

			card.paint(g);

		}

	}

	// check to see if meld is clicked
	public boolean mouseCollide(int x, int y) {
		for (Drawing t : tiles) {

			if (t.mouseCollide(x, y)) {

				return true;

			}

		}

		return false;
	}

	public Meld getMeld() {

		return meld;

	}

	public void clickTile(int x, int y) {

		for (ViewCard t : tiles) {

			if (t.mouseCollide(x, y)) {

				t.selected();

			}

		}

	}

	public boolean hasSelectedTile() {
		
		for (ViewCard t : tiles) {
			
			if(t.isSelected()){
				
				
				return true;
			}
			
			
		}
		
		
		return false;
	}

}
