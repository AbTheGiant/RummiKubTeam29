package core;

import java.util.Comparator;

import model.Card;

public class CardComparator implements Comparator<Card> {

	public int compare(Card arg0, Card arg1) {
		// TODO Auto-generated method stub
		Card thisCard=(Card)arg0;
		Card otherCard=(Card)arg1;
		
		if(otherCard==null)
			return 1;
		if(thisCard.getRank()>otherCard.getRank())
		{
			return 1;
		}
		else if(thisCard.getRank()<otherCard.getRank())
		{
			return -1;
		}		
		else if(thisCard.getColorChar()>otherCard.getColorChar())
		{
			return 1;
		}
		else if(thisCard.getColorChar()<otherCard.getColorChar())
		{
			return -1;
		}
		else 
			return 0;
	}

}
