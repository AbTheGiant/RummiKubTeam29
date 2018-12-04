package core;

import java.util.ArrayList;
import java.util.Collections;

import model.Card;
import model.Meld;
import model.Player;
import model.Strategy;

public class AIStrategy1 extends Strategy {

	public void playTable(Game game, Player player, int minMeldSize) {
		// try each meld
		for (Meld meld : game.getMelds()) {
			Meld tempMeld = new Meld();
			tempMeld.addPile(player);
			tempMeld.addPile(meld);
			ArrayList<Meld> melds = tempMeld.generateMelds(minMeldSize);
			if (melds.isEmpty()) {
				jokerMelds(melds, player);

			}
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

					for (Card solutionCard : solution.getCards()) {

						if (solutionCard.equals(card)) {

							solutionCard.setNewCard(true);
							isNewCardPlayed = true;

						}

					}

				}
			}
			// apply solution
			if (isNewCardPlayed) {
				if(minMeldSize==3){
					
					for (Meld solution : melds) {
						
						addOneMeld(game, player, meld, solution);
					}
					
				}
				else if(minMeldSize == 2){
					
					Meld solution = melds.get(0);
					for(Card c: player.getCards()){
						
						if(c.isJoker()){
							
							solution.addCard(c);
							
						}
						
						
					}
					solution.assignJokerValue();
					addOneMeld(game, player, meld, solution);
				}
				return;

			}
		}
	}

	private void addOneMeld(Game game, Player player, Meld meld, Meld solution) {
		game.removeMeld(meld);
		game.addMeld(solution);
		// remove cards from player
		for (Card card : solution.getCards()) {
			player.removeCard(card);
		}
	}

	public void playHand(Game game, Player player) {
		Meld tempMeld = new Meld();
		tempMeld.addPile(player);
		ArrayList<Meld> melds = tempMeld.generateMelds(3);
		if (melds.isEmpty()) {
			jokerMelds(melds, player);

		}

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
				if (meld.getScore() < 30 && player.getCurrentScore() < 30) {
					// System.out.print("Minmal meld is 30 points!");
				} else {
					for (Card card : meld.getCards()) {
						card.setNewCard(true);
						game.removeCard(card);
						//player.addToCurrenScore(card.getRank());
						playedCards.add(card);
					}
					game.addMeld(meld);
					System.out.println("Placed a new meld");
					System.out.println(meld);
				}
			}
		}
	}

	private void jokerMelds(ArrayList<Meld> melds, Player player) {
		Card joker = null;
		for (Card c : player.getCards()) {
			if (c.isJoker()) {
				joker = c;

			}

		}

		if (joker == null) {
			return;

		}
		Meld tempMeld = new Meld();
		tempMeld.addPile(player);
		ArrayList<Meld> smallMelds = tempMeld.generateMelds(2);
		
		if(smallMelds.isEmpty()){
			return;
			
			
			
		}
	Meld smallMeld = smallMelds.get(0);
	smallMeld.addCard(joker);
	smallMeld.assignJokerValue();
	melds.add(smallMeld);	
		
	}

	public void makeMove(Game game, Player player) {
		System.out.println(player);
		int startCount = player.getSize();
		if (player.getCurrentScore() < 30) {
			playHand(game, player);
		} else {
			playTable(game, player, 3);
			
			playHand(game, player);
		}

		if (startCount == player.getSize() && game.getDeck().isEmpty() == false) {
			System.out.println("Player " + player.getName() + "'s draw new tile");
			game.drawCard();
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