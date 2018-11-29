package core;

import java.util.ArrayList;

import junit.framework.TestCase;
import model.Card;
import model.CardPile;
import model.Card.Color;
public class CardPileTest extends TestCase {

	
	protected ArrayList<Card> cards=new ArrayList<Card> ();
	
	
	public void testAddCard()
    {
    	try
    	{
    	Card testercard = new Card(Color.Red,2);
    	cards.add(testercard);
    	assertEquals(testercard, cards.get(0));
    	


    	
    	}

    	catch (Exception e) {

            assertTrue(false);
		}

    }
	
	
	public void testGetScore() {
		
	
		
		
		try
    	{
			CardPile tester = new CardPile();
			
			Card testercard = new Card(Color.Red,11);
			Card testercard2= new Card(Color.Blue,11);
			Card testercard3 = new Card(Color.Green,11);
			
			tester.addCard(testercard);
			tester.addCard(testercard2);
			tester.cards.add(testercard3);
			
			assertEquals(33, tester.getScore());

    	
    	}

    	catch (Exception e) {

            assertTrue(false);
		}

		
	}
	
	
	public void testCompareTo() {
		
		
		
		
		try
    	{
			CardPile tester = new CardPile();
			Card testercard = new Card(Color.Red,11);
			Card testercard2= new Card(Color.Blue,11);
			Card testercard3 = new Card(Color.Green,11);
			tester.cards.add(testercard);
			tester.cards.add(testercard2);
			tester.cards.add(testercard3);
			
			
			CardPile tester2 = new CardPile();
			Card testercard4 = new Card(Color.Orange,11);
			Card testercard5= new Card(Color.Blue,11);
			Card testercard6 = new Card(Color.Green,11);
			tester2.cards.add(testercard4);
			tester2.cards.add(testercard5);
			tester2.cards.add(testercard6);
			
			
			CardPile tester3 = new CardPile();
			Card testercard7 = new Card(Color.Orange,12);
			Card testercard8= new Card(Color.Blue,11);
			Card testercard9 = new Card(Color.Green,14);
			tester3.cards.add(testercard7);
			tester3.cards.add(testercard8);
			tester3.cards.add(testercard9);
			
			assertTrue(tester.compareTo(tester2)==0);
			assertTrue(tester.compareTo(tester3)==-1);
			//assertTrue(tester.compareTo(testerCard2)==0

    	
    	}

    	catch (Exception e) {

            assertTrue(false);
		
	}
	
	
	
	
	

}
	
		public void testGetCards() {
			
			
			try
	    	{
				CardPile tester = new CardPile();
				
				Card testercard = new Card(Color.Red,11);
				Card testercard1= new Card(Color.Blue,11);
				Card testercard2 = new Card(Color.Green,11);
				
				tester.cards.add(testercard);
				tester.cards.add(testercard1);
				tester.cards.add(testercard2);
				
				Object arrayholder = tester.getCards().get(0);
				 
				assertEquals(arrayholder, tester.cards.get(0));
				

	    	
	    	}

	    	catch (Exception e) {

	            assertTrue(false);
			}
		}
	
}
