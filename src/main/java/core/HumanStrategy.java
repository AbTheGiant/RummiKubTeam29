package core;
import java.util.ArrayList;
import java.util.Scanner;


public class HumanStrategy extends Strategy {
	
	
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
}
