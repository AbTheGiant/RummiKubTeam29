package core;

import core.Card.Color;
import junit.framework.TestCase;
import java.util.Comparator;

public class CardComparatorTest extends TestCase{
	public void testCompare() {
		Card c1 = new Card(Color.Red,12);
		Card c2 = new Card(Color.Blue,10);
		
		assertEquals(1,compare(c1,c2));
		
		Card c3 = new Card(Color.Blue,12);
		Card c4 = new Card(Color.Red,12);
		
		assertEquals(-1,compare(c3,c4));
	
	}

}
