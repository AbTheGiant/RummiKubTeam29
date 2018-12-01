package view;

import java.awt.Color;
import java.awt.Graphics;

import model.Card;

public class ViewCard extends Drawing implements Comparable {

	public ViewCard(String fileName, Card c) {
		super(fileName);
		card = c; 
	}

	private final Card card;

	@Override
	public int compareTo(Object o) {
		
	ViewCard vc = (ViewCard) o;	
		
		return card.compareTo(vc.card);
	}
	
	public boolean isSelected(){
		
		return selected;
		
		
		
	}
public void paint(Graphics g){
	
	
	
	if (card.isNewCard() == true) {
		g.setColor(Color.yellow);
		int width = 2;
		g.fillRect(position.x - width, position.y - width, size.x + width * 2, size.y + width * 2);
	}
	
	super.paint(g);
	
}
	public Card getCard() {
		
		return card;
	}
}
