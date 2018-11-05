//KYLE R NELSON
//UNIVERSITY OF KENTUCKY
//CS 499 Fall 2018

package handDetermination;

//This class will Determine the type of a given hand of five cards
public class Hands
{
	//IMPORTANT NOTE:
	//The following methods determine if a hand classifies at that type of hand INCLUSIVELY
	//i.e. the method may return true even if the hand could classify as a higher value hand type
	//e.g. the hand "A A 2 2 7" may return true for both isOnePair() and isTwoPair()
	
	//BEGIN CARD CLASSIFICATION
	
	//Determines if a hand is five of a kind
	static boolean isFiveOfAKind(Card[] Hand)
	{
		for(int c = 0; c < Hand.length; c++)
		{
			for(int i = c + 1; i < Hand.length; i++)
			{
				for(int j = i + 1; j < Hand.length; j++)
				{ 
					for(int k = j + 1; k < Hand.length; k++)
					{
						for(int l = k + 1; l < Hand.length; l++)
						{
							if( (Hand[c].value == Hand[i].value) 
							&& (Hand[i].value == Hand[j].value)
							&& (Hand[j].value == Hand[k].value)
							&& (Hand[k].value == Hand[l].value))
							{
								return true;
							}
						}
					}
				}
			} 
		}
		return false;
	}
	
	//Determines if a hand is straight flush
	static boolean isStraightFlush(Card[] Hand)
	{
		return isStraight(Hand) && isFlush(Hand);
	}
	
	//Determines if a hand is four of a kind
	static boolean isFourOfAKind(Card[] Hand)
	{
		for(int c = 0; c < Hand.length; c++)
		{
			for(int i = c + 1; i < Hand.length; i++)
			{
				for(int j = i + 1; j < Hand.length; j++)
				{ 
					for(int k = j + 1; k < Hand.length; k++)
					{
						if( (Hand[c].value == Hand[i].value) 
						&& (Hand[i].value == Hand[j].value)
						&& (Hand[j].value == Hand[k].value))
						{
							return true;
						}
					}
				}
			} 
		}
		
		return false;
	}
	
	//Determines if a hand is a full house
	static boolean isFullHouse(Card[] Hand)
	{
		int setThree[] = {-1, -1, -1};
		//int pairTwo[] = {-1, -1};

		boolean threeFound = false;
		//first find the set of three
		for(int c = 0; c < Hand.length && !threeFound; c++)
		{
			for(int i = c + 1; i < Hand.length && !threeFound; i++)
			{
				for(int j = i + 1; (j < Hand.length) && !threeFound; j++)
				{
					//if three matching cards are found
					if(( (Hand[c].value == Hand[i].value) 
							&& (Hand[i].value == Hand[j].value) ))
					{
						threeFound = true;
						setThree[0] = c;
						setThree[1] = i;
						setThree[2] = j;
					}
				}
			} 
		}
		//if there was no set of three
		if(! threeFound)
		{
			return false;
		}
		//now find the pair and make sure it's different		
		for(int c = 0; c < Hand.length; c++)
		{
			for(int i = c + 1; i < Hand.length; i++)
			{
				//if we find a pair different from the set
				if( (Hand[c].value == Hand[i].value)  && (Hand[c].value != Hand[setThree[0]].value) )
				{
					return threeFound;
				}
			} 
		}

		return false;
	}
	
	//Determines if a hand is Flush
	static boolean isFlush(Card[] Hand)
	{
		boolean isFlush = true;
		
		//simply go through each card and make sure they have the same suit as the first
		for(int c = 1; c < Hand.length; c++)
		{
			isFlush = isFlush && (Hand[c].suit == Hand[0].suit);
		}
		
		return isFlush;
	}
	
	//Determines if a hand is Straight
	static boolean isStraight(Card[] Hand)
	{
		boolean isStraight = true;
		//first find lowest card value in the hand
		int lowest = 15;
		for(int c = 0; c < Hand.length; c++)
		{
			if(Hand[c].value < lowest)
			{
				lowest = Hand[c].value;
			}
		}
		
		//now we see if we have an instance of every value from lowest to lowest + 4
		for(int c = 0; c < 5; c++)
		{
			boolean instanceFound = false;
			for(int i = 0; i < Hand.length; i++)
			{
				instanceFound = instanceFound || (Hand[i].value == (lowest + c));
			}
			//we must find an instance of each
			isStraight = isStraight && instanceFound;
		}
		
		return isStraight;
	}
	
	//Determines if a hand is three of a kind
	static boolean isThreeOfAKind(Card[] Hand)
	{
		//boolean threeFound = false;
		
		for(int c = 0; c < Hand.length; c++)
		{
			for(int i = c + 1; i < Hand.length; i++)
			{
				for(int j = i + 1; j < Hand.length; j++)
				{ 
						if( (Hand[c].value == Hand[i].value) 
						&& (Hand[i].value == Hand[j].value) )
						{
							return true;
						}
				}
			} 
		}
		
		return false;
	}
	
	//Determines if a hand is two pair
	static boolean isTwoPair(Card[] Hand)
	{
		int pairOne[] = {-1, -1};
		//int pairTwo[] = new int[2];

		//run through each possible pair in the hand
		for(int c = 0; c < Hand.length; c++)
		{
			for(int i = c + 1; i < Hand.length; i++)
			{
				if(Hand[c].value == Hand[i].value)
				{
					//if we haven't found a first pair
					if(pairOne[0] < 0)
					{
						pairOne[0] = c;
						pairOne[1] = i;
					}
					//if we have found the first pair and this pair is different
					else if(pairOne[0] != c && pairOne[1] != c && pairOne[0] != i && pairOne[1] !=i)
					{
						return true;
					}
				}
			} 
		}
		//if two pairs are not found
		return false;
	}
	
	//Determines if a hand is one pair
	static boolean isOnePair(Card[] Hand)
	{
		//boolean pairFound = false;
		
		for(int c = 0; c < Hand.length; c++)
		{
			for(int i = c + 1; i < Hand.length; i++)
			{
				if(Hand[c].value == Hand[i].value)
					return true;
			} 
		}
		
		return false;
	}
	
	//END CARD CLASSIFICATION
}
