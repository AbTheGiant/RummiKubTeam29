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
	
public char getColorChar() {
		
		switch(color)
		{		
		case Red:
			return 'R';	
		case Blue:
			return 'B';
		case Green:
			return 'G';
		case Orange:
			return 'O';
		}
		return ' ';
	}
	
	
	
	@Override
	public String toString() {
		String result="";
		switch(color)
		{		
		case Red:
			return "R"+rank;	
		case Blue:
			return "B"+rank;
		case Green:
			return "G"+rank;
		case Orange:
			return "O"+rank;
		}
		return "";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + rank;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (color != other.color)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Card otherCard=(Card)arg0;
		if(otherCard==null)
			return 1;
		if(this.getColorChar()>otherCard.getColorChar())
		{
			return 1;
		}
		else if(this.getColorChar()<otherCard.getColorChar())
		{
			return -1;
		}
		else if(this.getRank()>otherCard.getRank())
		{
			return 1;
		}
		else if(this.getRank()<otherCard.getRank())
		{
			return -1;
		}		
		return 0;
	}
	
	
}
