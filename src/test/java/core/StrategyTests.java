
	package core;
	import java.util.ArrayList;

	import org.junit.Test;

	import core.Card;
	import core.Meld;
	import junit.framework.TestCase;

	public class TestStrategy1 extends TestCase{
		
		@Test
		public void testStartGameFrom30()
		{
			Card []cards=new Card[]
					{new Card(Card.Color.Blue, 13),new Card(Card.Color.Orange, 13),new Card(Card.Color.Red, 13)				
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
			for(Card card:cards)
			{
				players[0].addCard(card);
				players[1].addCard(card);
			}
			Game game=new Game(new Deck(0),players);
			players[1].update(game,null);
			assertTrue(game.isStopGame());
			assertEquals(game.getMelds().size(),1);
			assertEquals(game.getMelds().get(0).getScore(),39);		
		}

}
