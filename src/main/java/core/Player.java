package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public class Player  extends CardPile implements Observer {
   
    private int currentScore;
    private String name;

	
	
	
	
	public  void removeCard(Card card)
	{
		currentScore+=card.getRank();
		cards.remove(card);
	}
	
	
	public Card getCard(String cardName)
	{
		for(Card card:cards)
		{
			if(card.toString().equals(cardName))
			{
				return card;
			}			
		}
		return null;
	}
	
	public void addMeld(Meld anotherMeld)
	{
		for(Card card:anotherMeld.getCards())
		{
			addCard(card);
		}
	}
	
	public void addPlayer(Player player)
	{
		for(Card card:player.cards)
		{
			addCard(card);
		}
	}
	
	
	@Override
	public String toString() {
		Collections.sort(cards);		
		String result="";
		for(Card card:cards)
		{
			if(result.length()>0)
			{
				result+=",";
			}
			result+=card.toString();		
		}
		return "{"+result+"}";
	}


	public int getCurrentScore() {
		return currentScore;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

 
	
}
