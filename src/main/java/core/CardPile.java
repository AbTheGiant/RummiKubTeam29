package core;

import java.util.ArrayList;

public class CardPile implements Comparable{
	protected ArrayList<Card> cards=new ArrayList<Card> ();
	public  void addCard(Card card)
	{
		cards.add(card);
	}
	public  int getSize()
	{
		return cards.size();
	}
	public void addPile(CardPile another)
	{
		for(Card card:another.cards)
		{
			addCard(card);
		}
	}
	public int getScore()
	{
		int result=0;		
		for(Card card:cards)
		{
			result+=card.getRank();
		}
		return result;
		
	}
	
	public int compareTo(Object arg0){
		CardPile other=(CardPile)arg0;
		if(this.getScore()>other.getScore())
			return 1;
		else if(this.getScore()<other.getScore())
			return -1;
		return 0;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}

}

