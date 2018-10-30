package core;

import java.util.ArrayList;

public class AIStrategy2 extends AIStrategy1{
	public void makeMove(Game game, Player player) {
		System.out.println(player);
 		if (player.getCurrentScore() < 30) {
			playHand(game, player);
		} else {		
			Meld tempMeld = new Meld();
			tempMeld.addPile(player);
			ArrayList<Meld> melds =tempMeld.generateMelds();
			for (Meld meld : melds) {
				for (Card card : meld.getCards()) {
					player.removeCard(card);					
				}				
			}			
			if(game.getDeck().isEmpty()&&player.getSize()==0)
			{
				for (Meld meld : melds) {
					player.addMeld(meld);
				}
				playHand(game, player);			
			}
			else
			{
				playTable(game, player);
				for (Meld meld : melds) {
					player.addMeld(meld);
				}
			}
		}
	}
}
