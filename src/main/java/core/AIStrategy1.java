package core;

import java.util.ArrayList;
import java.util.Collections;

public class AIStrategy1 extends Strategy {
	
	public void playTable(Game game, Player player) {
		// try each meld
		for (Meld meld : game.getMelds()) {
			Meld tempMeld = new Meld();
			tempMeld.addPile(player);
			tempMeld.addPile(meld);
			ArrayList<Meld> melds = tempMeld.generateMelds();
			// select melds with table melds.
			boolean removed;
			do {
				removed = false;
				for (Meld solution : melds) {
					boolean containsTableCards = false;
					for (Card card : meld.getCards()) {
						if (solution.getCards().contains(card)) {
							containsTableCards = true;
						}
					}
					if (!containsTableCards) {
						melds.remove(solution);
						removed = true;
						break;
					}
				}
			} while (removed);
			Collections.sort(melds);
			// remove all duplicate solutions
			do {
				removed = false;
				for (int i = 0; i < melds.size() && (!removed); i++) {
					for (int i1 = i + 1; i1 < melds.size() && (!removed); i1++) {

						for (Card card : melds.get(i).getCards()) {
							if (melds.get(i1).getCards().contains(card)) {
								melds.remove(i1);
								removed = true;
								break;
							}
						}
					}
				}
			} while (removed);
			boolean complete = false;
			for (Card card : meld.getCards()) {
				complete = false;
				for (Meld solution : melds) {
					if (solution.getCards().contains(card)) {
						complete = true;
					}
				}
				if (!complete)
					break;
			}
			if (!complete)
				continue;
			boolean isNewCardPlayed = false;
			for (Card card : player.getCards()) {
				for (Meld solution : melds) {
					if (solution.getCards().contains(card)) {
						isNewCardPlayed = true;
						break;
					}
				}
			}
			// apply solution
			if (isNewCardPlayed) {				
				for (Meld solution : melds) {
					game.addMeld(solution);
					// remove cards from player
					for (Card card : solution.getCards()) {
						player.removeCard(card);
					}
				}
				return;
				
			}
		}
	}
	public void playHand(Game game, Player player) {
		Meld tempMeld = new Meld();
		tempMeld.addPile(player);		
		ArrayList<Meld> melds = tempMeld.generateMelds();
		Collections.sort(melds);
		ArrayList<Card> playedCards = new ArrayList<Card>();
		for (Meld meld : melds) {
			boolean notPlayed = true;
			for (Card card : meld.getCards()) {
				if (playedCards.contains(card)) {
					notPlayed = false;
					break;
				}
			}
			if (notPlayed) {
				if(meld.getScore()<30&&player.getCurrentScore()<30)
				{
					//System.out.print("Minmal meld is 30 points!");					
				}
				else
				{
					for (Card card : meld.getCards()) {
						player.removeCard(card);
						playedCards.add(card);
					}
					game.addMeld(meld);
					System.out.println("Placed a new meld");
					System.out.println(meld);
				}
			}
		}
	}

	public void makeMove(Game game, Player player) {
		System.out.println(player);
		if (player.getCurrentScore() < 30) {
			playHand(game, player);
		} else {
			playTable(game, player);
			playHand(game, player);
		}
		/*
		 * Meld tempMeld = new Meld * (); tempMeld.addPile(player);
		 * System.out.println(player); ArrayList<Meld> melds =
		 * tempMeld.generateMelds(); Collections.sort(melds); ArrayList<Card>
		 * playedCards = new ArrayList<Card>(); for (Meld meld : melds) {
		 * boolean notPlayed = true; for (Card card : meld.getCards()) { if
		 * (playedCards.contains(card)) { notPlayed = false; break; } } if
		 * (notPlayed) { for (Card card : meld.getCards()) {
		 * player.removeCard(card); playedCards.add(card); } game.addMeld(meld);
		 * System.out.println(meld); } }
		 */
	}
}
