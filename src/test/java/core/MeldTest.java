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

			Meld tester = new Meld();

			Card one = new Card (Color.Blue, 10);

			Card two = new Card ( Color.Blue, 11);

			Card three = new Card( Color.Blue, 12);

			tester.addCard(one);

			tester.addCard(two);

			tester.addCard(three);

			assertEquals(true, tester.sameColorIncreasing());

			

			Meld testerTwo = new Meld();

			Card four = new Card (Color.Red, 8);

			Card five = new Card ( Color.Red, 10);

			Card six = new Card( Color.Red, 12);

			testerTwo.addCard(four);

			testerTwo.addCard(five);

			testerTwo.addCard(six);

			assertEquals(false, testerTwo.sameColorIncreasing());

 			

			

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

		

		Meld tester = new Meld();

		Card one = new Card (Color.Blue, 10);

		Card two = new Card ( Color.Blue, 11);

		Card three = new Card( Color.Blue, 12);

		tester.addCard(one);

		tester.addCard(two);

		tester.addCard(three);

		assertEquals(true, tester.isValid());

		

		Meld testerTwo = new Meld();

		Card four = new Card (Color.Red, 8);

		Card five = new Card ( Color.Red, 10);

		Card six = new Card( Color.Red, 12);

		testerTwo.addCard(four);

		testerTwo.addCard(five);

		testerTwo.addCard(six);

		assertEquals(false, testerTwo.isValid());

		

	}

	public void testToString() {

		Meld tester = new Meld();

		Card one = new Card (Color.Blue, 10);

		Card two = new Card ( Color.Blue, 11);

		Card three = new Card( Color.Blue, 12);

		tester.addCard(one);

		tester.addCard(two);

		tester.addCard(three);

		assertEquals("{B10,B11,B12}", tester.toString());

		

	

		

	}

	

	

}