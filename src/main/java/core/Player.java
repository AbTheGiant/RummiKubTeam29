package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public class Player  extends CardPile implements Observer {
    private Strategy strategy;
    private int currentScore;
    private String name;

	
	public Player(Strategy strategy) {
		// TODO Auto-generated constructor stub
		this.strategy=strategy;
	}
	
	
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
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub		
		Game game=(Game)arg0;
		int startCount=getSize();
		if(game.isStopGame())
			return;
		System.out.println("Player "+name+"�s turn");
		strategy.makeMove(game,this);
		//If you didn't play any of your tiles in your turn, then you have to draw another tile from the
		//stock.
		if(startCount==getSize()&&game.getDeck().isEmpty()==false)
		{
			System.out.println("Player "+name+"�s draw new tile");			
			addCard(game.getDeck().deal());
		}	
		if(getSize()==0)
			game.setStopGame(true);

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


