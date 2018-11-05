//KYLE R NELSON
//UNIVERSITY OF KENTUCKY
//CS 499 Fall 2018

package handDetermination;
import java.util.concurrent.ThreadLocalRandom;

//While these files are in the same package, we can avoid importing Card.java
public class Hand_test 
{
	//START RANDOM HAND GENERATION
	//The following methods will generate various types of hands reliably and randomly
	//These will be used to test the evaluation methods
	
	//Generate random five of a kind hand
	static Card[] randomFiveOfAKind()
	{
		//Hand will contain the generated Five of a Kind Hand
		Card[] Hand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < Hand.length; c++)
		{
			Hand[c] = randomCard();
		}
		//now pick two different indices to be the three matching cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[Hand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, Hand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second and third indices to that of the first
		Hand[Index[4]].value = Hand[Index[3]].value = Hand[Index[2]].value = Hand[Index[1]].value = Hand[Index[0]].value;
		
		//This is guaranteed to return a hand with five of a kind
		return Hand;
	}
	
	//Generate random straight flush
	static Card[] randomStraightFlush()
	{
		//Hand will contain the generated straight flush
		Card[] Hand = randomStraight();
		
		//now change all the suits to that of the first card
		for(int c = 1; c < Hand.length; c++)
		{
			Hand[c].suit = Hand[0].suit;
		}
		
		//The returned hand is guaranteed to be a straight flush
		return Hand;
	}
	
	//Generate random four of a kind
	static Card[] randomFourOfAKind()
	{
		//Hand will contain the generated four of a kind
		Card[] Hand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < Hand.length; c++)
		{
			Hand[c] = randomCard();
		}
		//now pick two different indices to be the three matching cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[Hand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, Hand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second and third indices to that of the first
		Hand[Index[3]].value = Hand[Index[2]].value = Hand[Index[1]].value = Hand[Index[0]].value;
		
		//the hand is guaranteed to have four cards of a kind
		return Hand;
	}
	
	//Generate random Full House
	static Card[] randomFullHouse()
	{
		//Hand will contain the generated full house
		Card[] Hand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < Hand.length; c++)
		{
			Hand[c] = randomCard();
		}
		
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[Hand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, Hand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second index to that of the first
		Hand[Index[1]].value = Hand[Index[0]].value;
		//check that the third index does not have the same value as the first
		if(Hand[Index[2]].value == Hand[Index[0]].value)
		{
			//if the values are the same, increment the value on the third
			//this ensures that the values are not the same, 
			//and they will never exceed the maximum value (since a high ace is not generated randomly)
			Hand[Index[2]].value++;
		}
		//now set the value of the card at the fourth and fifth indices to that of the third
		Hand[Index[4]].value = Hand[Index[3]].value = Hand[Index[2]].value;
		
		//the returned hand is guaranteed to be a full house
		return Hand;
	}
	
	//Generate random Flush
	static Card[] randomFlush()
	{
		//Hand will contain the generated Flush
		Card[] Hand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < Hand.length; c++)
		{
			Hand[c] = randomCard();
		}
		//now change all the suits to that of the first card
		for(int c = 1; c < Hand.length; c++)
		{
			Hand[c].suit = Hand[0].suit;
		}
		
		//the hand is now guaranteed to be a flush
		return Hand;
	}
	
	//Generate random Straight
	static Card[] randomStraight()
	{
		//Hand will contain the generated Straight
		Card[] Hand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < Hand.length; c++)
		{
			Hand[c] = randomCard();
		}
		//now pick two different indices to be the three matching cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[Hand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, Hand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//check if the value of the first index is too great for a straight, and reduce it if so
		if(Hand[Index[0]].value > 10)
			//reduce by a random number between 1 and 10
			Hand[Index[0]].value -= (byte)ThreadLocalRandom.current().nextInt(1, 11);
		
		//now set the card at each subsequent index to one more than the previous
		for(int c = 1; c < Index.length; c++)
		{
			Hand[Index[c]].value = (byte)(Hand[Index[c - 1]].value + 1);
		}
		
		//The returned hand is guaranteed to be a straight
		return Hand;
	}
	
	//Generate random Three of a Kind
	static Card[] randomThreeOfAKind()
	{
		//Hand will contain the generated Three of a kind
		Card[] Hand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < Hand.length; c++)
		{
			Hand[c] = randomCard();
		}
		//now pick two different indices to be the three matching cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[Hand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, Hand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second and third indices to that of the first
		Hand[Index[2]].value = Hand[Index[1]].value = Hand[Index[0]].value;
		
		//returned hand is guaranteed at least three of a kind
		return Hand;
	}
	
	//Generate random Two Pair
	static Card[] randomTwoPair()
	{
		//Hand will contain the generated two pair
		Card[] Hand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < Hand.length; c++)
		{
			Hand[c] = randomCard();
		}
		//now pick four different indices to be the pairs of cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[Hand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, Hand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second index to that of the first
		Hand[Index[1]].value = Hand[Index[0]].value;
		//check that the third index does not have the same value as the first
		if(Hand[Index[2]].value == Hand[Index[0]].value)
		{
			//if the values are the same, increment the value on the third
			//this ensures that the values are not the same, 
			//and they will never exceed the maximum value (since a high ace is not generated randomly)
			Hand[Index[2]].value++;
		}
		//now set the value of the card at the fourth index to that of the third
		Hand[Index[3]].value = Hand[Index[2]].value;
		
		//the returned hand is guaranteed to have two pairs
		return Hand;
	}
	
	//Generate random one pair
	static Card[] randomOnePair()
	{
		//Hand will contain the generated one pair when returned
		Card[] Hand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < Hand.length; c++)
		{
			Hand[c] = randomCard();
		}
		//now pick two different indices to be the pair of cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[Hand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, Hand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second index to that of the first
		Hand[Index[1]].value = Hand[Index[0]].value;
		
		//returned hand is guaranteed to have at least one pair
		return Hand;
	}
	
	//END RANDOM HAND GENERATION
	
	//This method will generate a random card
	static Card randomCard()
	{
		byte value = randomValue();
		byte suit = randomSuit();
		
		Card randCard = new Card(value, suit);
		
		return randCard;
	}
	
	//This method will generate a random card value
	static byte randomValue()
	{
		//generates value between 0 and 13
		byte randomValue = (byte)ThreadLocalRandom.current().nextInt(1, 14);
		return randomValue;
	}
	
	//This method will generate a random card suit
	static byte randomSuit()
	{
		//generates value between 0 and 4
		byte randomSuit = (byte)ThreadLocalRandom.current().nextInt(0, 4);
		return randomSuit;
	}
	
	//The main of the testing program
	public static void main(String[] args) 
	{
		//constant to store the number of tests we conduct on each hand
		final int TEST_NUM = 15;
		
		//BEGIN TESTS
		
		//Test Five of A Kind
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomFiveOfAKind();
			if(! Hands.isFiveOfAKind(Hand))
			{
				System.out.println("Five of a Kind Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		//Test Straight Flush
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomStraightFlush();
			if(! Hands.isStraightFlush(Hand))
			{
				System.out.println("Straight Flush Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		// Test Four of a Kind
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomFourOfAKind();
			if(! Hands.isFourOfAKind(Hand))
			{
				System.out.println("Four of a Kind Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		// Test Full House
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomFullHouse();
			if(! Hands.isFullHouse(Hand))
			{
				System.out.println("Full House Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		// Test Flush
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomFlush();
			if(! Hands.isFlush(Hand))
			{
				System.out.println("Flush Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		// Test Straight
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomStraight();
			if(! Hands.isStraight(Hand))
			{
				System.out.println("Straight Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		// Test Three of a Kind
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomThreeOfAKind();
			if(! Hands.isThreeOfAKind(Hand))
			{
				System.out.println("Three of a Kind Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		// Test Two Pair
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomTwoPair();
			if(! Hands.isTwoPair(Hand))
			{
				System.out.println("Two Pair Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		// Test One Pair
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card Hand[] = randomOnePair();
			if(! Hands.isOnePair(Hand))
			{
				System.out.println("One Pair Failure!");
				for(int i = 0; i < Hand.length; i++)
				{
					Hand[i].print();
				}
			}
		}
		System.out.println("DONE");
		//END TESTS
	}

}
