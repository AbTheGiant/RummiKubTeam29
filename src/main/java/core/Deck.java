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

	
}