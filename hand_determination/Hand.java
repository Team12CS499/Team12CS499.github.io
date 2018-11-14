//KYLE R NELSON
//UNIVERSITY OF KENTUCKY
//CS 499 Fall 2018

package handDetermination;

//This class will Determine the type of a given hand of five cards
//and store this as a Hand object
public class Hand
{
	//These constants will correspond to each hand type
	//The number associated with each constant is the rank of that type of hand
	//Lower ranks trump higher ranks, in this way they may be better referred to as tiers
	//but standard poker terminology is "rank", so rank it is
	public static final byte FiveOfAKind = 0;
	public static final byte StraightFlush = 1;
	public static final byte FourOfAKind = 2;
	public static final byte FullHouse = 3;
	public static final byte Flush = 4;
	public static final byte Straight = 5;
	public static final byte ThreeOfAKind = 6;
	public static final byte TwoPair = 7;
	public static final byte OnePair = 8;
	public static final byte HighCard = 9;
	
	//These are the private variables that identify the hand
	//Cards is an array of cards that represent the hand's literal contents
	Card[] Cards = new Card[5];
	//Rank is the rank of the hand, also referred to as the hand's type
	byte Rank;
	
	//set and get for the private variables
	public Card[] getCards() {
		return Cards;
	}
	//TODO use identifier functions to determine rank
	public void setCards(Card[] cards) {
		Cards = cards;
		Rank = determineRank(cards);
	}

	public byte getRank() {
		return Rank;
	}

	//The constructor takes an array of Card objects and then calculates the rank accordingly
	public Hand(Card[] cards) 
	{
		Cards = cards;
		Rank = determineRank(cards);
	}
	
	//IMPORTANT NOTE:
	//The following methods determine if a hand classifies at that type of hand INCLUSIVELY
	//i.e. the method may return true even if the hand could classify as a higher value hand type
	//e.g. the hand "A A 2 2 7" may return true for both isOnePair() and isTwoPair()
	
	//Furthermore, these methods are for use on any array of Card objects,
	//not just the private Cards of a Hand object
	
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
		
		//now we see if we have an instance of every value from lowest to lowest + hand.length - 1
		for(int c = 0; c < Hand.length; c++)
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
	
	//This method returns the byte corresponding to the rank of a given hand,
	//as represented by an array of Card objects
	static byte determineRank(Card[] Hand)
	{
		//the default value is High Card, since that is what the 
		byte rank = HighCard;
		
		if(isFiveOfAKind(Hand))
			rank = FiveOfAKind;
		else if (isStraightFlush(Hand))
			rank = StraightFlush;
		else if (isFourOfAKind(Hand))
			rank = FourOfAKind;
		else if (isFullHouse(Hand))
			rank = FullHouse;
		else if (isFlush(Hand))
			rank = Flush;
		else if (isStraight(Hand))
			rank = Straight;
		else if (isThreeOfAKind(Hand))
			rank = ThreeOfAKind;
		else if (isTwoPair(Hand))
			rank = TwoPair;
		else if (isOnePair(Hand))
			rank = OnePair;
		
		return rank;
	}
}
