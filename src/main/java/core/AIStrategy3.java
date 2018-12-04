package core;

import java.util.ArrayList;

import model.Card;
import model.Meld;
import model.Player;

public class AIStrategy3 extends AIStrategy1{
	
	public void makeMove(Game game, Player player) {
		System.out.println(player);
		int startCount = player.getSize();
 		if (player.getCurrentScore() < 30&&game.getMelds().size()>0) {
			playHand(game, player);
		} else {
			//o	If no other player has 3 fewer tiles than p3, then p3 plays only the tiles of its hand
			//that require using tiles on the table to make melds
			
			int minSize=1000;
			Player[]otherPlayers=game.getPlayers();
			for(Player other:otherPlayers)
			{
				if(other!=player)
				{
					if(minSize>other.getSize())
					{
						minSize=other.getSize();
					}
				}
			}
			
			if(minSize+3<player.getSize())
			{
				playTable(game, player, 3);
				playHand(game, player);				
			}
			else
			{
				if(game.getDeck().isEmpty())
				{
					Meld tempMeld = new Meld();
					tempMeld.addPile(player);
					ArrayList<Meld> melds =tempMeld.generateMelds(3);
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
						for (Meld meld : melds) {
							player.addMeld(meld);
						}
						playTable(game, player,3);
					}	
				}
				else
				{
					playTable(game, player, 3);
				}
			}					
		}

 		if (startCount == player.getSize() && game.getDeck().isEmpty() == false) {
			System.out.println("Player " + player.getName() + "'s draw new tile");
			game.drawCard();
		}
	}

}
