package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import core.CardComparator;

public class Meld extends CardPile{
	//sort cards by	
	public boolean sameRankDiffentColor()
	{
		Collections.sort(cards,new CardComparator());
		for(int i=0;i<cards.size()-1;i++)
		{
			if(cards.get(i).getRank()!=cards.get(i+1).getRank())
				return false;
			if(cards.get(i).getColor()==cards.get(i+1).getColor())
				return false;
		}		
		return true;		
	}
	
	public boolean sameColorIncreasing()
	{
		Collections.sort(cards);
		for(int i=0;i<cards.size()-1;i++)
		{
			if(cards.get(i).getRank()!=cards.get(i+1).getRank()-1)
				return false;
			if(cards.get(i).getColor()!=cards.get(i+1).getColor())
				return false;
		}		
		return true;		
	}
	
	public boolean notSameColor()
	{
		for(int i=0;i<cards.size()-1;i++)
		{
			if(cards.get(i).getColor()==cards.get(i+1).getColor())
				return false;
		}		
		return true;		
	}
	
	public boolean isValid()
	{		
		return (sameColorIncreasing()||sameRankDiffentColor())&&cards.size()>2;		
	}
	
	@Override
	public String toString() {
		String result="";
		for(Card card:cards)
		{
			if(result.length()>0)
			{
				result+=",";
			}
			result+=card.toString();		
		}
		return "{"+result+"}";
	}
	
	public void addMeld(Meld anotherMeld)
	{
		addPile(anotherMeld);
	}
	
	public ArrayList<Meld> generateMelds() 
	{	
		ArrayList<Meld> newMelds=new ArrayList<Meld>(); 
		Collections.sort(cards);
		Meld lastMeld=new Meld();
		for(int i=0;i<cards.size()-1;i++)
		{
			if(cards.get(i).getRank()!=cards.get(i+1).getRank()-1
					||cards.get(i).getColor()!=cards.get(i+1).getColor())
			{
				if(cards.get(i).getRank()==cards.get(i+1).getRank()&&
						cards.get(i).getColor()==cards.get(i+1).getColor())
				{
					;					
				}
				else
				{
					if(lastMeld.getSize()>0)
					{
						if(lastMeld.isValid())
							newMelds.add(lastMeld);
						lastMeld=new Meld();
					}
				}
			}
			else
			{
				if(lastMeld.getSize()>0)
					lastMeld.addCard(cards.get(i+1));
				else
				{
					lastMeld.addCard(cards.get(i+1));
					lastMeld.addCard(cards.get(i));
				}				
			}
		}	
		// by rank
		Collections.sort(cards,new CardComparator());
		for(int i=0;i<cards.size()-1;i++)
		{
			
			if(cards.get(i).getRank()==cards.get(i+1).getRank()
					&&cards.get(i).getColor()!=cards.get(i+1).getColor())
			{
				if(lastMeld.getSize()>0)
					lastMeld.addCard(cards.get(i+1));
				else
				{
					lastMeld.addCard(cards.get(i+1));
					lastMeld.addCard(cards.get(i));
				}
			}
			else
			{
				if(cards.get(i).getRank()==cards.get(i+1).getRank()&&
						cards.get(i).getColor()==cards.get(i+1).getColor())
				{
					;					
				}
				else
				{
					if(lastMeld.getSize()>0)
					{
						if(lastMeld.isValid())
							newMelds.add(lastMeld);
						lastMeld=new Meld();
					}
				}
			}
		}
		if(lastMeld.getSize()>2)
			newMelds.add(lastMeld);
		return newMelds;
	}

	public int getScore()
	{
		if(!isValid())
			return 0;
		else
			return super.getScore();
	}
}

