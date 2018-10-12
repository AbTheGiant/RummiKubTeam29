package core;

import core.Card.Color;
import junit.framework.TestCase;
public class CardTest  extends TestCase {
	
	
	public void testColor()
    {
    	try
    	{
    	Card card = new Card(Color.Red,2);
    	assertTrue(card.getColor().equals(Color.Red));
    	card= new Card(Color.Blue, 12);
    	assertTrue(card.getColor().equals(Color.Blue));
    	
    	}
    	catch (Exception e) {

            assertTrue(false);
		}
    }

	public void testRank()
    {
    	try
    	{
    	Card card = new Card(Color.Red,2);
    	assertEquals(2,card.getRank());
    	card= new Card(Color.Blue, 12);
    	assertEquals(12,card.getRank());
    	
    	
    	}
    	catch (Exception e) {

            assertTrue(false);
		}
    }

	
	public void testCardToString()
    {
    	try
    	{
    	Card testerCard = new Card(Color.Blue,5);
    	assertEquals("B5", testerCard.toString());
    	}

    	catch (Exception e) {

            assertTrue(false);
		}

    }
	
	public void testCompare()
    {
    	try
    	{
    	Card testerCard= new Card(Color.Blue,5);

    	Card testerCard2= new Card(Color.Blue,5);

    	Card testerCard3 = new Card(Color.Green,6);
    	Card testerCard4 = new Card (Color.Orange, 7);
    	assertTrue(testerCard.compareTo(testerCard2)==0);

    	assertTrue(testerCard3.compareTo(testerCard4)==-1);
    	}


    	catch (Exception e) {

            assertTrue(false);
		}

    }
	
	
	
}
