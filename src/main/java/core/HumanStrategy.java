package core;

import java.util.ArrayList;
import java.util.Scanner;

import model.Card;
import model.Meld;
import model.Player;
import model.Strategy;

public class HumanStrategy extends Strategy{
	private static Scanner input=new Scanner(System.in);
	
	public Meld addTile(Game game,Player player)
	{
		String cardName;
		Meld newMeld=new Meld();
		System.out.print("Enter card name or press enter to stop: ");
		cardName=input.nextLine();		
		while(cardName.length()>0)
		{
			Card card=player.getCard(cardName);
			if(card==null)
			{
				System.out.println("Invalid card!");
			}
			else
			{
				newMeld.addCard(card);
			}
			System.out.print("Enter card name or press enter to stop: ");
			cardName=input.nextLine();
		}
		if(newMeld.isValid())
		{
			if(newMeld.getScore()<30&&player.getCurrentScore()<30)
			{
				System.out.print("Minmal meld is 30 points!");
				return null;
			}
			else
			{			
				for(Card card:newMeld.getCards())
				{
					player.removeCard(card);				
				}
				return newMeld;
			}
		}
		else
		{
			System.out.println("Invalid meld");
			return null;
		}
		
	}
	
	public void useTableTiles(Game game,Player player)
	{
		// select melds		
		ArrayList<Meld> melds=game.getMelds();
		ArrayList<Meld> playerMelds=new ArrayList<Meld>();
		int choice;
		Player newPlayer= new Player(null);
		do
		{
			int count=1;
			for(Meld meld:melds)
			{
				System.out.println(""+(count++)+meld.toString());							
			}			
			System.out.println("0 -Done");	
			choice=input.nextInt();
			if(!(choice<1||choice>melds.size()))
			{
				if(!playerMelds.contains(melds.get(choice-1)))
				{
					playerMelds.add(melds.get(choice-1));
					newPlayer.addPile(melds.get(choice-1));
				}
			}
		}while(choice!=0);
		//add cards to new fake player
		ArrayList<Meld> newMelds=new ArrayList<Meld>();		
		newPlayer.addPlayer(player);		
		Meld playedCards=new Meld();
		do
		{
			System.out.println(newPlayer);
			System.out.println("1. Place new Meld");
			System.out.println("2. --------------");			
			System.out.println("3. Done");			
			choice=input.nextInt();
			input.nextLine();
			if(choice==1)
			{
				Meld meld=addTile(game, newPlayer);
				if(meld!=null)
				{
					newMelds.add(meld);
					playedCards.addMeld(meld);
					newPlayer.addMeld(meld);
				}
			}
		}while(choice!=3);
		// checking if all
		boolean allCardGone=true;		
		for(Meld meld:playerMelds)
		{
			for(Card card:meld.getCards())
			{
				if(!playedCards.getCards().contains(card))
				{
					allCardGone=false;
					break;
				}
			}								
		}		
		if(allCardGone)
		{
			for(Meld meld:playerMelds)
			{
				melds.remove(meld);								
			}
			for(Meld meld:newMelds)
			{
				melds.add(meld);				
			}			
		}
		else
		{
			System.out.println("You must play all table cards!");
		}
	}
	
	
	public void makeMove(Game game,Player player)
	{
		System.out.println(game);
		int choice;		 
		do
		{
			System.out.println(player);
			System.out.println("1. Place new Meld");
			System.out.println("2. Add card to existing Meld");
			System.out.println("3. Draw tile");
			System.out.println("4. Done");			
			choice=input.nextInt();
			input.nextLine();
			if(choice==1)
			{
				Meld meld=addTile(game, player);
				if(meld!=null)
				{
					game.addMeld(meld);
				}
			}
			else if(choice==2&&player.getCurrentScore()>=30)
			{
				useTableTiles(game, player);
			}
			else if(choice==3&&game.getDeck().isEmpty()==false)
			{				
				player.addCard(game.getDeck().deal());
			}
		}while(choice!=4);
	}
	
	
}
