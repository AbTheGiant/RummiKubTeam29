package core;

import java.util.ArrayList;

import model.Card;
import model.Meld;
import model.Player;

public class AIStrategy2 extends AIStrategy1{
	@Override
	public void makeMove(Game game, Player player) {
		System.out.println(player);
		int startCount = player.getSize();
 		if (player.getCurrentScore() < 30) {
			playHand(game, player);
		} else {		
			Meld tempMeld = new Meld();
			tempMeld.addPile(player);
			ArrayList<Meld> melds =tempMeld.generateMelds(3);
			for (Meld meld : melds) {
				for (Card card : meld.getCards()) {
					player.removeCard(card);					
				}				
			}			
			if(player.getSize()==0)
			{
				for (Meld meld : melds) {
					player.addMeld(meld);
				}
				playHand(game, player);			
			}
			else
			{
				playTable(game, player, 3);
				for (Meld meld : melds) {
					player.addMeld(meld);
				}
			}
		}
 		if (startCount == player.getSize() && game.getDeck().isEmpty() == false) {
			System.out.println("Player " + player.getName() + "'s draw new tile");
			game.drawCard();
		}
	}
}
