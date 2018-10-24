package core;

import core.Card.Color;
import junit.framework.TestCase;


public class CardComparatorTest extends TestCase{
	public void testCompare() {
	//	Card c1 = new Card(Color.Red,12);
	//	Card c2 = new Card(Color.Blue,10);
	//	CardPile p = new CardPile();
	//	p.addCard(c1);
	//	p.addCard(c2);
		
	//	assertEquals(1,compare(p.get(0).getRank(),c2));
		
	//	Card c3 = new Card(Color.Blue,12);
	//	Card c4 = new Card(Color.Red,12);
		
	//	assertEquals(-1,compare(c3,c4));
		
		
		
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
		
	
	}

}
