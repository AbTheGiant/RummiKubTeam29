package core;

import java.util.ArrayList;
import core.Card.Color;
import junit.framework.TestCase;

public class MeldTest extends TestCase {
	
	
	public void testSameRankDifferentColour() {
		int count = 5;
		try 
		{
		Card c1 = new Card (Color.Red,12);
		Card c2 = new Card (Color.Green,12);
		Card c3 = new Card (Color.Blue,12);
		Card c4 = new Card (Color.Orange,12);
		
		if((c1.getRank()== c2.getRank()) &&(c2.getRank()==c3.getRank()) && (c3.getRank() == c1.getRank())) {
			if((c1.getColor() != c2.getColor()) && (c2.getColor() != c3.getColor()) && c3.getColor() != c1.getColor()) {
				count++;
			 }
		}
		assertTrue(count > 5);
		}
		catch(Exception e) {
			assertTrue(false);
		}
		
	}
	public void testSameColorIncreasing() {
		try
		{
			CardPile p = new CardPile();
			CardPile newPile = new CardPile();
		    String firstColor = " ";
		    int i = 0;
		    int count = 0;
		    int counter = 0;
		    firstColor = p.get(i);
			for(int j = 0; j<p.getSize(); j++) {
				if(p.get(j) == firstColor) {
					count++;
				}
			p.addPile(newPile);	
			}
			for(int k = 0; k < p.getSize(); k++) {
				if(p.get(k) == firstColor) {
					counter++;
				}
			}
		assertFalse(counter < count);	
		}
		catch(Exception e) {
			assertTrue(false);
		}
		
	}
	public void testNotSameColor() {
		try
		{
		Card card = new Card(Color.Red,5);
		Card card1 = new Card(Color.Blue,9);
		assertEquals(false,card.getColor().equals(card1.getColor()));
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}
	public void testIsValid() {
		
	}
	public void testToString() {	
	}
	public void testAddMeld() {
		
	}
	public void testGenerateMeld() {
		
	}
	
}
