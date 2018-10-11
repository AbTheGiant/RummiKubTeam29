package core;


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

	
	
	
}
