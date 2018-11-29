package core;

import junit.framework.TestCase;
import model.Card;
import model.Deck;

public class DeckTest extends TestCase {


	public void testShuffle()
    {
    	try
    	{
    	Deck deck1 = new Deck();
    	Deck deck2 = new Deck();

    	deck2.shuffle();

    	int count = 0;

    	for(int i=0;i<104;i++)
    	{
    		Card c1 = deck1.deal();
    		Card c2 = deck2.deal();


    		if( c1.getRank()==c2.getRank() && c1.getColor().equals(c2.getColor())) {
    			count++;
    		}


    	}


    	assertTrue(count < 5);
    	}

    	catch (Exception e) {

            assertTrue(false);
		}

    }
	
	
	public void testDeal()
    {
    	try
    	{
       Deck testerDeck = new Deck();
       Card c = testerDeck.deal();
       assertTrue(c != null);
       assertEquals(103, testerDeck.getNumCards());
    	}

    	catch (Exception e) {

            assertTrue(false);
		}
    }
	
	
	public void testNumberOfCards()	{
		try {
		Deck testerDeck = new Deck();
		assertEquals(104,testerDeck.getNumCards());

		}

		catch (Exception e) {

            assertTrue(false);
		}

	}


	
	 
    
}