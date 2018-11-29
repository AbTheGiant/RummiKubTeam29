package core;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import model.Card;
import model.Deck;
import model.Meld;
import model.Player;

public class StrategyTests extends TestCase{
	
	
	
	@Test
	public void testStartGameFor30()
	{
		Card []cards2=new Card[]
				{new Card(Card.Color.Blue, 13),new Card(Card.Color.Orange, 13),new Card(Card.Color.Red, 13),new Card(Card.Color.Blue, 13),new Card(Card.Color.Blue, 11),
					new Card(Card.Color.Blue, 12),new Card(Card.Color.Blue, 10)			
					};
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 10),new Card(Card.Color.Green, 10),
					new Card(Card.Color.Orange, 10),new Card(Card.Color.Blue, 11)};
		Card []cards3=new Card[]
				{new Card(Card.Color.Red, 13),new Card(Card.Color.Red, 11),
					new Card(Card.Color.Red, 12),new Card(Card.Color.Red, 10)};
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		Meld meld=new Meld();
		for(Card card:cards)
		{
			players[3].addCard(card);			
		}
		/*for(Card card:cards3)
		{
			players[0].addCard(card);
			//players[1].addCard(card);
		}
		
		for(Card card:cards2)
		{
			//players[0].addCard(card);
			players[1].addCard(card);
		}*/
		System.out.println("Requirement 4a1");
		Game game=new Game(new Deck(0),players);
		//players[1].update(game,null);
		//players[2].update(game,null);
		players[3].update(game,null);
		//players[3].update(game,null);
		//assertTrue(game.isStopGame());
		//assertEquals(game.getMelds().size(),1);
		assertEquals(game.getMelds().get(0).getScore(),30);	
		//assertEquals(game.getMelds().get(2).getScore(),33);	
	}
	//@Test
	/*public void testNotStartGameFromBelow30()
	{
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 13),new Card(Card.Color.Orange, 13),new Card(Card.Color.Red, 13)				
					};
		Card []cards3=new Card[]
				{new Card(Card.Color.Blue, 5),new Card(Card.Color.Orange, 5),new Card(Card.Color.Red, 5)				
					};
		
		Card []cards2=new Card[]
				{new Card(Card.Color.Blue, 13),new Card(Card.Color.Blue, 11),
					new Card(Card.Color.Blue, 12),new Card(Card.Color.Blue, 10)};
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		Meld meld=new Meld();
		for(Card card:cards)
		{
			players[3].addCard(card);			
		}
		for(Card card:cards2)
		{
			players[0].addCard(card);
			players[2].addCard(card);			
		}
		for(Card card:cards3)
		{
			players[1].addCard(card);			
		}		
		Game game=new Game(new Deck(0),players);
		players[1].update(game,null);
		assertFalse(game.isStopGame());
		assertEquals(game.getMelds().size(),0);				
	}	
	@Test
	public void testGameFromBelow30()
	{
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 13),new Card(Card.Color.Orange, 13),new Card(Card.Color.Red, 13)				
					};
		Card []cards3=new Card[]
				{new Card(Card.Color.Blue, 5),new Card(Card.Color.Orange, 5),new Card(Card.Color.Red, 5)				
					};
		
		Card []cards2=new Card[]
				{new Card(Card.Color.Blue, 13),new Card(Card.Color.Blue, 11),
					new Card(Card.Color.Blue, 12),new Card(Card.Color.Blue, 10)};
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		Meld meld=new Meld();
		for(Card card:cards)
		{
			players[3].addCard(card);			
		}
		for(Card card:cards2)
		{
			players[0].addCard(card);
			players[2].addCard(card);			
		}
		for(Card card:cards3)
		{
			players[1].addCard(card);			
		}		
		Game game=new Game(new Deck(0),players);
		players[1].update(game,null);
		assertFalse(game.isStopGame());
		assertEquals(game.getMelds().size(),0);				
	}
	
	public void testGameOver()
	{
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 13),new Card(Card.Color.Orange, 13),new Card(Card.Color.Red, 13)				
					};
		Card []cards3=new Card[]
				{new Card(Card.Color.Blue, 5),new Card(Card.Color.Orange, 5),new Card(Card.Color.Red, 5)				
					};
		
		Card []cards2=new Card[]
				{new Card(Card.Color.Blue, 13),new Card(Card.Color.Blue, 11),
					new Card(Card.Color.Blue, 12),new Card(Card.Color.Blue, 10)};
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		Meld meld=new Meld();
		for(Card card:cards)
		{
			players[3].addCard(card);			
		}
		for(Card card:cards2)
		{
			players[0].addCard(card);
			players[2].addCard(card);			
		}
		for(Card card:cards3)
		{
			players[1].addCard(card);			
		}		
		Game game=new Game(new Deck(0),players);
		players[1].update(game,null);
		assertFalse(game.isStopGame());
		assertEquals(game.getMelds().size(),0);
		players[2].update(game,null);	
		
		assertTrue(game.isStopGame());
		
	}
	*/
	
	public void testStartGameForAdd30()
	{
		
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 11),new Card(Card.Color.Green, 11),
					new Card(Card.Color.Orange, 11),new Card(Card.Color.Red, 2),new Card(Card.Color.Blue, 2),new Card(Card.Color.Green, 2),
					new Card(Card.Color.Orange, 2),new Card(Card.Color.Red, 1)};
		
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		
		for(Card card:cards)
		{
			players[0].addCard(card);			
		}
		
		System.out.println("Requirement 4b2");
		Game game=new Game(new Deck(0),players);
	
		players[0].update(game,null);
		players[0].update(game,null);
		
		assertEquals(game.getMelds().get(0).getScore(),33);	
		assertEquals(game.getMelds().get(1).getScore(),8);	
	}
	
	public void testStartGameForSeveralRuns()
	{
		
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 11),new Card(Card.Color.Blue, 10),
					new Card(Card.Color.Blue, 9),new Card(Card.Color.Blue, 12),new Card(Card.Color.Red, 2),new Card(Card.Color.Red, 3),
					new Card(Card.Color.Orange, 2),new Card(Card.Color.Red, 1)};
		
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		
		for(Card card:cards)
		{
			players[0].addCard(card);			
		}
		
		System.out.println("Requirement 8C");
		Game game=new Game(new Deck(0),players);
	
		players[0].update(game,null);
		players[0].update(game,null);
		players[0].getStrategy().makeMove(game, players[0]);
		
		assertEquals(game.getMelds().get(0).getScore(),42);	
		assertEquals(game.getMelds().get(1).getScore(),6);	
	}
	
	
	public void testStartGameForSeveralSets()
	{
		
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 11),new Card(Card.Color.Red, 11),
					new Card(Card.Color.Green, 11),new Card(Card.Color.Blue, 1),new Card(Card.Color.Red, 1),new Card(Card.Color.Red, 3),
					new Card(Card.Color.Orange, 1),new Card(Card.Color.Red, 5)};
		
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		
		for(Card card:cards)
		{
			players[0].addCard(card);			
		}
		
		System.out.println("Requirement 8D");
		Game game=new Game(new Deck(0),players);
	
		players[0].update(game,null);
		players[0].update(game,null);
		players[0].getStrategy().makeMove(game, players[0]);
		assertEquals(game.getMelds().get(0).getScore(),33);	
		assertEquals(game.getMelds().get(1).getScore(),3);	
	}
	
	
	public void testStartGameForMixture()
	{
		
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 11),new Card(Card.Color.Red, 11),
					new Card(Card.Color.Green, 11),new Card(Card.Color.Blue, 11),new Card(Card.Color.Blue, 12),new Card(Card.Color.Blue, 13),
					};
		
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		
		for(Card card:cards)
		{
			players[0].addCard(card);			
		}
		
		System.out.println("Requirement 8E");
		Game game=new Game(new Deck(0),players);
	
		players[0].update(game,null);
		players[0].update(game,null);
		players[0].getStrategy().makeMove(game, players[0]);
		assertEquals(game.getMelds().get(0).getScore(),33);	
		assertEquals(game.getMelds().get(1).getScore(),36);	
	}
	
	public void testStartGameForSeveralMeldsP1()
	{
		
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 11),new Card(Card.Color.Red, 11),
					new Card(Card.Color.Green, 11),new Card(Card.Color.Blue, 10),new Card(Card.Color.Green, 10),new Card(Card.Color.Red, 10),
					};
		
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		
		for(Card card:cards)
		{
			players[3].addCard(card);			
		}
		
		System.out.println("Requirement 10B");
		Game game=new Game(new Deck(0),players);
	
		players[3].update(game,null);
		players[3].update(game,null);
		players[3].getStrategy().makeMove(game, players[0]);
		
		
		assertEquals(game.getMelds().get(0).getScore(),33);	
		assertEquals(game.getMelds().get(1).getScore(),30);	
	}
	
	
	public void testStartGameOverP3()
	{
		
		Card []cards=new Card[]
				{new Card(Card.Color.Blue, 11),new Card(Card.Color.Red, 11),
					new Card(Card.Color.Green, 11)};
		Card []cards2=new Card[]
				{new Card(Card.Color.Blue, 4),new Card(Card.Color.Red, 7),
new Card(Card.Color.Green, 9)};
		
		
		Player[] players=new Player[4]; 
		players[3]=new Player(new AIStrategy1());
		players[3].setName("p1");
		players[0]=new Player(new AIStrategy3());
		players[0].setName("p3");		 
		players[1]=new Player(new AIStrategy2());
		players[1].setName("p2");
		players[2]=new Player(new AIStrategy1());
		players[2].setName("p1");
		
		
		for(Card card:cards)
		{
			players[0].addCard(card);			
		}
		for(Card card:cards2)
		{
			players[1].addCard(card);			
		}
		
		
		System.out.println("Requirement 12A");
		;
		Game game=new Game(new Deck(0),players);
		
		
		players[1].update(game,null);
		
		
		players[0].update(game,players[0]);
			
		
		assertEquals(players[0].getSize(),0);
		
		assertTrue(game.isStopGame());
	}
}
	
