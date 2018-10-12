package core;

import core.Card.Color;

public class Card implements Comparable {
	public enum Color{Red ,Blue,Green,Orange}
	private Color color;
	private int rank;
	private boolean newCard;
	private boolean movedCard;
	public Card(Color color, int rank) {
		super();
		this.color = color;
		this.rank = rank;
		newCard=false;
		movedCard=false;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
