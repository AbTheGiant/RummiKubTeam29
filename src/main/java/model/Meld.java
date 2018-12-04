package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import core.CardComparator;

public class Meld extends CardPile implements Serializable {
	// sort cards by
	public boolean sameRankDiffentColor() {
		Collections.sort(cards, new CardComparator());
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getRank() != cards.get(i + 1).getRank())
				return false;
			if (cards.get(i).getColor() == cards.get(i + 1).getColor())
				return false;
		}
		return true;
	}

	public void assignJokerValue() {

		Card joker = null;

		for (int i = cards.size() - 1; i >= 0; i--) {

			Card c = cards.get(i);

			if (c.isJoker()) {

				joker = c;
				cards.remove(i);

			}

		}

		if (joker == null) {

			return;

		}

		Collections.sort(cards);

		if (this.sameColorIncreasing()) {

			Card firstCard = cards.get(0);

			if (firstCard.getRank() == 1) {
				Card lastCard = cards.get(cards.size() - 1);

				joker.setRank(lastCard.getRank() + 1);
				joker.setColor(lastCard.getColor());

			} else {

				joker.setRank(firstCard.getRank() - 1);
				joker.setColor(firstCard.getColor());

			}

		} else if (sameRankDiffentColor()) {

			List<Card.Color> colorList = new ArrayList<>(Arrays.asList(Card.Color.values()));

			for (int i = cards.size() - 1; i >= 0; i--) {

				Card c = cards.get(i);

				colorList.remove(c.getColor());

			}
			joker.setColor(colorList.get(0));
			joker.setRank(cards.get(0).getRank());

		}

		else {

			for (int i = 0; i < cards.size() - 1; i++) {
				if (cards.get(i).getRank() != cards.get(i + 1).getRank() - 1) {

					joker.setRank(cards.get(i).getRank() + 1);
					joker.setColor(cards.get(i).getColor());

				}

			}
		}
		cards.add(joker);
	}
	
	public boolean sameColorIncreasing() {

		Collections.sort(cards);

		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getRank() != cards.get(i + 1).getRank() - 1)
				return false;
			if (cards.get(i).getColor() != cards.get(i + 1).getColor())
				return false;
		}
		return true;
	}

	public boolean notSameColor() {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getColor() == cards.get(i + 1).getColor())
				return false;
		}
		return true;
	}

	public boolean isValid() {
		return (sameColorIncreasing() || sameRankDiffentColor()) && cards.size() > 2;
	}
	
	public boolean isValid(int minSize) {
		return (sameColorIncreasing() || sameRankDiffentColor()) && cards.size() >= minSize;
	}
	@Override
	public String toString() {
		String result = "";
		for (Card card : cards) {
			if (result.length() > 0) {
				result += ",";
			}
			result += card.toString();
		}
		return "{" + result + "}";
	}

	public void addMeld(Meld anotherMeld) {
		addPile(anotherMeld);
	}

	public ArrayList<Meld> generateMelds(int minSize) {
		ArrayList<Meld> newMelds = new ArrayList<Meld>();
		int jokersRemoved = 0;
		for (int i = cards.size() - 1; i >= 0; i--) {

			if (cards.get(i).isJoker()) {

				cards.remove(i);
				jokersRemoved++;
			}

		}
		Collections.sort(cards);
		Meld currentMeldSolution = new Meld();
		for (int i = 0; i < cards.size() - 1; i++) {

			// check if the current tiles are not sequential
			if (cards.get(i).getRank() != cards.get(i + 1).getRank() - 1
					|| cards.get(i).getColor() != cards.get(i + 1).getColor()) {
				if (cards.get(i).getRank() == cards.get(i + 1).getRank()
						&& cards.get(i).getColor() == cards.get(i + 1).getColor()) {
					;
				} else {
					if (currentMeldSolution.getSize() > 0) {
						// we have found the end of the meld solution, save it
						// in the new meld list
						if (currentMeldSolution.isValid(minSize))
							newMelds.add(currentMeldSolution);
						currentMeldSolution = new Meld();
					}
				}

			} else {// the current tiles are sequential
				if (currentMeldSolution.getSize() > 0)
					currentMeldSolution.addCard(cards.get(i + 1));
				else {
					currentMeldSolution.addCard(cards.get(i + 1));
					currentMeldSolution.addCard(cards.get(i));
				}
			}
		}
		
		if (currentMeldSolution.isValid(minSize))
			newMelds.add(currentMeldSolution);
		// by rank
		currentMeldSolution = new Meld();
		Collections.sort(cards, new CardComparator());
		for (int i = 0; i < cards.size() - 1; i++) {

			if (cards.get(i).getRank() == cards.get(i + 1).getRank()
					&& cards.get(i).getColor() != cards.get(i + 1).getColor()) {
				if (currentMeldSolution.getSize() > 0)
					currentMeldSolution.addCard(cards.get(i + 1));
				else {
					currentMeldSolution.addCard(cards.get(i + 1));
					currentMeldSolution.addCard(cards.get(i));
				}
			} else {
				if (cards.get(i).getRank() == cards.get(i + 1).getRank()
						&& cards.get(i).getColor() == cards.get(i + 1).getColor()) {
					;
				} else {
					if (currentMeldSolution.getSize() > 0) {
						if (currentMeldSolution.isValid(minSize))
							newMelds.add(currentMeldSolution);
						currentMeldSolution = new Meld();
					}
				}
			}
		}
		if (currentMeldSolution.getSize() >= minSize)
			newMelds.add(currentMeldSolution);
		for(int i = 0; i < jokersRemoved; i++){
			
			cards.add(new Card(Card.Color.Red, 0, true));
			
		}
		return newMelds;
	}

	public int getScore() {
		if (!isValid())
			return 0;
		else
			return super.getScore();
	}

	public void makeStale() {
		for (Card c : cards) {

			c.setNewCard(false);

		}

	}
}
