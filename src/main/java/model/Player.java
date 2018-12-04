package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import core.Game;
import core.HumanStrategy;

public class Player extends CardPile implements Observer {
	private Strategy strategy;
	private int currentScore;
	private String name;

	public Player(Strategy strategy) {
		// TODO Auto-generated constructor stub
		this.strategy = strategy;
	}

	public void removeCard(Card card) {
		currentScore += card.getRank();
		cards.remove(card);
	}

	public Card getCard(String cardName) {
		for (Card card : cards) {
			if (card.toString().equals(cardName)) {
				return card;
			}
		}
		return null;
	}

	public void update(Observable arg0, Object arg1) {

		Game game = (Game) arg0;
		GameState gs = (GameState) arg1;
		if (this.getName().equals(gs.getThisPlayer())) {

			int startCount = getSize();
			if (game.isStopGame())
				return;
			System.out.println("Player " + name + "'s turn");
			strategy.makeMove(game, this);
			// If you didn't play any of your tiles in your turn, then you have
			// to draw another tile from the
			// stock.
			
			if (getSize() == 0 && gs.isDecideFirstPlayer()==false)
				game.setStopGame(true);

		}

	}

	public void addMeld(Meld anotherMeld) {
		for (Card card : anotherMeld.getCards()) {
			addCard(card);
		}
	}

	public void addPlayer(Player player) {
		for (Card card : player.cards) {
			addCard(card);
		}
	}

	@Override
	public String toString() {
		Collections.sort(cards);
		String result = "";
		for (Card card : cards) {
			if (result.length() > 0) {
				result += ",";
			}
			result += card.toString();
		}
		return "{" + result + "}";
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean validateMeld (Meld m) {
		
		if(m.getScore()<30 && this.getCurrentScore()<30)
		{
			return false;					
		}
		if(m.isValid()==false){
			
			return false;
			
		}
		return true;
	}
	
	public void addToCurrenScore(int scoreAmount){
		
		currentScore += scoreAmount;
		
		
		
		
	}
	
	public boolean isAi(){
		
		if(strategy instanceof HumanStrategy){
			
			return false;
			
			
		}
		else{
			
			
			return true;
		}
		
		
	}

	public boolean hasJoker() {
		for(Card c: this.cards){
			
			if(c.isJoker()){
				
				return true;
				
			}
			
			
		}
		return false;
	}
	
	
}
