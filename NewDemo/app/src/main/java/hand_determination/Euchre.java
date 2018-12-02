package hand_determination;

public class Euchre 
{
	//this method should return the index of the winning card of a given Euchre trick
	//if there is no winner, return -1
	public static int trickWinner(Card[] Trick, byte trumpSuit)
	{
		//holds default value just in case
		int winIndex = -1;
		
		//in Euchre a trick is won by
		//the Jack of the trump Suit (right bower)
		//then the Jack of the Suit of the same color as the trump suit (left bower)
		//then the highest card of the trump suit (called trumps)
		//then the highest card of the led suit (which we will designate by the card in index 0)
		//if no cards fit these criteria, there is no winner (but since the lead card is of the led suit this should never happen)
		
		//first we check for the right bower
		for (int c = 0; c < Trick.length; c++)
		{
			if( (Trick[c].suit == trumpSuit) && (Trick[c].value == Card.JACK) )
				return c;
		}
		
		//then we look for the left bower
		for (int c = 0; c < Trick.length; c++)
		{
			//partnerSuit should give the suit of the same color as the trump suit
			//this is the suit of the left bower
			if( (Trick[c].suit == partnerSuit(trumpSuit)) && (Trick[c].value == Card.JACK) )
				return c;
		}
		
		//check for other cards of the trump suit (called trumps)
		boolean areTrumps = false;
		for (int c = 0; c < Trick.length; c++)
		{
			if (Trick[c].suit == trumpSuit)
				areTrumps = true;
		}
		
		//if trumps are present, return the highest trump
		if(areTrumps)
		{
			//highIndex will store the index of the highest trump
			int highIndex = -1;
			//we must ensure that highIndex starts out as the index of a card of the trump suit
			for (int c = 0; (c < Trick.length) && (highIndex < 0); c++)
			{
				if(Trick[c].suit == trumpSuit)
					highIndex = c;
			}
			
			for (int c = 0; c < Trick.length; c++)
			{
				//check that the card is of the trump suit, and designate it as high if it has higher value than the previous
				if ( (Trick[c].suit == trumpSuit) && (Trick[c].value > Trick[highIndex].value) )
					highIndex = c;
			}
			return highIndex;
		}
		
		//NOTE: the left bower is considered part of the trump suit, and so effects the led suit
		//NOTE: but since the left bower wins over any led cards, this shouldn't matter here
		//TODO: HANDLE LED SUIT CARDS
		byte ledSuit = Trick[0].suit;
		//winIndex will be used to store the current highest of the led suit
		winIndex = 0;
		//now we see if there is a higher card of the led suit than the first card
		for (int c = 1; c < Trick.length; c++)
		{
			//check that the card is of the led suit,
			//and designate it as high if it has higher value than the previous high
			if ( (Trick[c].suit == ledSuit) && (Trick[c].value > Trick[winIndex].value) )
				winIndex = c;
		}
		
		return winIndex;
	}
	
	//this method should return the byte corresponding to 
	//the suit of the same color as the one provided
	public static byte partnerSuit(byte inputSuit)
	{
		byte partnerSuit = -1;
		
		switch(inputSuit)
		{
			case Card.SPADES:
				partnerSuit = Card.CLUBS;
				break;
			case Card.CLUBS:
				partnerSuit = Card.SPADES;
				break;
			case Card.HEARTS:
				partnerSuit = Card.DIAMONDS;
				break;
			case Card.DIAMONDS:
				partnerSuit = Card.HEARTS;
				break;
		}
		
		return partnerSuit;
	}
}
