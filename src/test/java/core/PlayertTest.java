package core;
import java.util.ArrayList;

import core.Card.Color;
import junit.framework.TestCase;


public class PlayertTest extends TestCase {

		
	
		
	public void testRemoveCard(){
	
	
	try {
		
	Player player1 = new Player();
	//CardPile tester = new CardPile();
	Card testerOne = new Card(Color.Blue, 1);
	//Card testOne = new Card(Color.Blue, 1);
	Card testTwo = new Card(Color.Red, 2);
	Card testThree= new Card (Color.Red, 1);
	player1.addCard(testerOne);
	player1.addCard(testTwo);
	player1.addCard(testThree);
	player1.removeCard(testThree);
	
	
	assertEquals(2, player1.cards.size());
	
	
	}
	
		catch (Exception e) {

        assertTrue(false);
		}
	
	}
	
	public void testToString(){
		
		
		try {
			
		Player player1 = new Player();
		//CardPile tester = new CardPile();
		Card testerOne = new Card(Color.Blue, 1);
		//Card testOne = new Card(Color.Blue, 1);
		Card testTwo = new Card(Color.Red, 2);
		Card testThree= new Card (Color.Red, 1);
		player1.addCard(testerOne);
		player1.addCard(testTwo);
		player1.addCard(testThree);
		
		
		assertEquals("{B1,R1,R2}", player1.toString());
		//assertTrue(player1.toString().equals("{B1,R2,R1}"));
		}
		
		catch (Exception e) {

	        assertTrue(false);
		}
		
		}
	
public void testGetCard(){
		
		
		try {
			
		Player player1 = new Player();
		//CardPile tester = new CardPile();
		Card testerOne = new Card(Color.Blue, 1);
		//Card testOne = new Card(Color.Blue, 1);
		Card testTwo = new Card(Color.Red, 2);
		Card testThree= new Card (Color.Red, 1);
		player1.addCard(testerOne);
		player1.addCard(testTwo);
		player1.addCard(testThree);
		
		
		assertEquals(testTwo, player1.getCard("R2"));
		
		}
		
		catch (Exception e) {

	        assertTrue(false);
		}
		
		}
	
	
	
	
}