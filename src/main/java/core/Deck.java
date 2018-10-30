package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Deck {
	ArrayList<Card> deck;
	Deck()
	{
		deck=new ArrayList<Card>();
		for(int i=1;i<=13;i++)
		{
		    Card.Color  []colors={Card.Color.Green,Card.Color.Red,
		    		Card.Color.Orange,Card.Color.Blue,
		    		Card.Color.Green,Card.Color.Red,
		    		Card.Color.Orange,Card.Color.Blue};
			for(Card.Color color:colors)
			{
				deck.add(new Card(color,i));				
			}			
		}
	}
	
	
	Deck(int size)
	{
		deck=new ArrayList<Card>();
		for(int i=1;i<=13;i++)
		{
			
		    Card.Color  []colors={Card.Color.Green,Card.Color.Red,
		    		Card.Color.Orange,Card.Color.Blue,
		    		Card.Color.Green,Card.Color.Red,
		    		Card.Color.Orange,Card.Color.Blue};
			for(Card.Color color:colors)
			{
				if(size<=0)
					return;
				deck.add(new Card(color,i));
				size--;				
			}			
		}
	}
	
	//shuffle method
	public void shuffle() {
		Random random=new Random();
		for(int i=0;i<deck.size();i++)
		{
			int swap=random.nextInt(52);
			Card temp=deck.get(i);
			deck.set(i, deck.get(swap));
			deck.set(swap, temp);
		}
	}
	
	
	public boolean isEmpty() {
		
		return deck.isEmpty();
	}



	public Card deal() {
		
		Card card=deck.get(deck.size()-1);
		deck.remove(deck.size()-1);
		return card;
	}
	
	
	public int getNumCards() {
		return deck.size();
	}
	

	
}