package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Deck implements Serializable {
	ArrayList<Card> deck;
	public static boolean rig = false;

	public Deck() {
		deck = new ArrayList<Card>();
		for (int i = 1; i <= 13; i++) {
			Card.Color[] colors = { Card.Color.Green, Card.Color.Red, Card.Color.Orange, Card.Color.Blue,
					Card.Color.Green, Card.Color.Red, Card.Color.Orange, Card.Color.Blue };
			for (Card.Color color : colors) {
				deck.add(new Card(color, i));

			}

		}
		deck.add(new Card(Card.Color.Red, 0, true));
		deck.add(new Card(Card.Color.Green, 0, true));

	}

	public Deck(int size) {
		deck = new ArrayList<Card>();
		for (int i = 1; i <= 13; i++) {

			Card.Color[] colors = { Card.Color.Green, Card.Color.Red, Card.Color.Orange, Card.Color.Blue,
					Card.Color.Green, Card.Color.Red, Card.Color.Orange, Card.Color.Blue };
			for (Card.Color color : colors) {
				if (size <= 0)
					return;
				deck.add(new Card(color, i));
				size--;
			}
		}
	}

	// shuffle method
	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < deck.size(); i++) {
			int swap = random.nextInt(deck.size()-1);
			Card temp = deck.get(i);
			deck.set(i, deck.get(swap));
			deck.set(swap, temp);
		}
	}

	public boolean isEmpty() {

		return deck.isEmpty();
	}

	public Card deal() {

		Card card = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		System.out.println("card dealt" + card);
		return card;
	}

	public int getNumCards() {
		return deck.size();
	}

}