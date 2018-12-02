//KYLE R NELSON
//UNIVERSITY OF KENTUCKY
//CS 499 Fall 2018

package hand_determination;
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
		//testHand will contain the generated Five of a Kind testHand
		Card[] testHand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = randomCard();
		}
		//now pick two different indices to be the three matching cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[testHand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testHand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second and third indices to that of the first
		testHand[Index[4]].value = testHand[Index[3]].value = testHand[Index[2]].value = testHand[Index[1]].value = testHand[Index[0]].value;
		
		//This is guaranteed to return a hand with five of a kind
		return testHand;
	}
	
	//Generate random straight flush
	static Card[] randomStraightFlush()
	{
		//testHand will contain the generated straight flush
		Card[] testHand = randomStraight();
		
		//now change all the suits to that of the first card
		for(int c = 1; c < testHand.length; c++)
		{
			testHand[c].suit = testHand[0].suit;
		}
		
		//The returned hand is guaranteed to be a straight flush
		return testHand;
	}
	
	//Generate random four of a kind
	static Card[] randomFourOfAKind()
	{
		//testHand will contain the generated four of a kind
		Card[] testHand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = randomCard();
		}
		//now pick two different indices to be the three matching cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[testHand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testHand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second and third indices to that of the first
		testHand[Index[3]].value = testHand[Index[2]].value = testHand[Index[1]].value = testHand[Index[0]].value;
		
		//the hand is guaranteed to have four cards of a kind
		return testHand;
	}
	
	//Generate random Full House
	static Card[] randomFullHouse()
	{
		//testHand will contain the generated full house
		Card[] testHand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = randomCard();
		}
		
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[testHand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testHand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second index to that of the first
		testHand[Index[1]].value = testHand[Index[0]].value;
		//check that the third index does not have the same value as the first
		if(testHand[Index[2]].value == testHand[Index[0]].value)
		{
			//if the values are the same, increment the value on the third
			//this ensures that the values are not the same, 
			//and they will never exceed the maximum value (since a high ace is not generated randomly)
			testHand[Index[2]].value++;
		}
		//now set the value of the card at the fourth and fifth indices to that of the third
		testHand[Index[4]].value = testHand[Index[3]].value = testHand[Index[2]].value;
		
		//the returned hand is guaranteed to be a full house
		return testHand;
	}
	
	//Generate random Flush
	static Card[] randomFlush()
	{
		//testHand will contain the generated Flush
		Card[] testHand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = randomCard();
		}
		//now change all the suits to that of the first card
		for(int c = 1; c < testHand.length; c++)
		{
			testHand[c].suit = testHand[0].suit;
		}
		
		//the hand is now guaranteed to be a flush
		return testHand;
	}
	
	//Generate random Straight
	static Card[] randomStraight()
	{
		//testHand will contain the generated Straight
		Card[] testHand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = randomCard();
		}
		//now pick two different indices to be the three matching cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[testHand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testHand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//check if the value of the first index is too great for a straight, and reduce it if so
		if(testHand[Index[0]].value > 10)
			//reduce by a random number between 1 and 10
			testHand[Index[0]].value -= (byte)ThreadLocalRandom.current().nextInt(1, 11);
		
		//now set the card at each subsequent index to one more than the previous
		for(int c = 1; c < Index.length; c++)
		{
			testHand[Index[c]].value = (byte)(testHand[Index[c - 1]].value + 1);
		}
		
		//The returned hand is guaranteed to be a straight
		return testHand;
	}
	
	//Generate random Three of a Kind
	static Card[] randomThreeOfAKind()
	{
		//testHand will contain the generated Three of a kind
		Card[] testHand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = randomCard();
		}
		//now pick two different indices to be the three matching cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[testHand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testHand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second and third indices to that of the first
		testHand[Index[2]].value = testHand[Index[1]].value = testHand[Index[0]].value;
		
		//returned hand is guaranteed at least three of a kind
		return testHand;
	}
	
	//Generate random Two Pair
	static Card[] randomTwoPair()
	{
		//testHand will contain the generated two pair
		Card[] testHand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = randomCard();
		}
		//now pick four different indices to be the pairs of cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[testHand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testHand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second index to that of the first
		testHand[Index[1]].value = testHand[Index[0]].value;
		//check that the third index does not have the same value as the first
		if(testHand[Index[2]].value == testHand[Index[0]].value)
		{
			//if the values are the same, increment the value on the third
			//this ensures that the values are not the same, 
			//and they will never exceed the maximum value (since a high ace is not generated randomly)
			testHand[Index[2]].value++;
		}
		//now set the value of the card at the fourth index to that of the third
		testHand[Index[3]].value = testHand[Index[2]].value;
		
		//the returned hand is guaranteed to have two pairs
		return testHand;
	}
	
	//Generate random one pair
	static Card[] randomOnePair()
	{
		//testHand will contain the generated one pair when returned
		Card[] testHand = new Card[5];
		
		//populate hand with random cards
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = randomCard();
		}
		//now pick two different indices to be the pair of cards
		//start by creating an array of indices and populating it accordingly
		int Index[] = new int[testHand.length];
		for(int c = 0; c < Index.length; c++)
		{
			Index[c] = c;//the index will contain the number of its index initially
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the indices
		for(int i = 0; i < Index.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testHand.length);
			int temporary = Index[i];
			Index[i] = Index[j];
			Index[j] = temporary;
		}
		//the Index array should now contain a shuffled list of indices
		
		//now set the value of the card at the second index to that of the first
		testHand[Index[1]].value = testHand[Index[0]].value;
		
		//returned hand is guaranteed to have at least one pair
		return testHand;
	}
		
	//This method will generate a random hand
	static Card[] randomHand()
	{
		//testDeck will contain all possible cards
		Card[] testDeck = new Card[52];
		byte testValue = 0;
		byte testSuit = 0;
		for(byte c = 0; c < testDeck.length; c++)
		{
			testValue++;
			if(testValue > Card.KING)
			{
				testValue = 1;
				testSuit++;
			}
			if(testSuit > 3)
				testSuit = 0;
			testDeck[c] = new Card(testValue, testSuit);
		}
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the deck
		for(int i = 0; i < testDeck.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testDeck.length);
			Card temporary = new Card(testDeck[i].value, testDeck[i].suit);
			testDeck[i] = new Card(testDeck[j].value, testDeck[j].suit);
			testDeck[j] = new Card(temporary.value, temporary.suit);
		}
		
		//testHand will contain the generated one pair when returned
		Card[] testHand = new Card[5];

		//populate hand with cards drawn from the deck
		for(int c = 0; c < testHand.length; c++)
		{
			testHand[c] = testDeck[c];
		}
		return testHand;
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
	
	//The poker test method
	public static void PokerTest() 
	{
		//constant to store the number of tests we conduct on each hand
		final int TEST_NUM = 1000;
		
		//BEGIN TESTS FOR FALSE NEGATIVES
		
		//Test Five of A Kind
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomFiveOfAKind();
			if(! Hand.isFiveOfAKind(testHand))
			{
				System.out.println("Five of a Kind Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		//Test Straight Flush
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomStraightFlush();
			if(! Hand.isStraightFlush(testHand))
			{
				System.out.println("Straight Flush Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		// Test Four of a Kind
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomFourOfAKind();
			if(! Hand.isFourOfAKind(testHand))
			{
				System.out.println("Four of a Kind Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		// Test Full House
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomFullHouse();
			if(! Hand.isFullHouse(testHand))
			{
				System.out.println("Full House Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		// Test Flush
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomFlush();
			if(! Hand.isFlush(testHand))
			{
				System.out.println("Flush Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		// Test Straight
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomStraight();
			if(! Hand.isStraight(testHand))
			{
				System.out.println("Straight Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		// Test Three of a Kind
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomThreeOfAKind();
			if(! Hand.isThreeOfAKind(testHand))
			{
				System.out.println("Three of a Kind Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		// Test Two Pair
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomTwoPair();
			if(! Hand.isTwoPair(testHand))
			{
				System.out.println("Two Pair Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		// Test One Pair
		for(int c = 0; c < TEST_NUM; c++)
		{
			Card testHand[] = randomOnePair();
			if(! Hand.isOnePair(testHand))
			{
				System.out.println("One Pair Possible False Positive");
				for(int i = 0; i < testHand.length; i++)
				{
					testHand[i].print();
				}
			}
		}
		System.out.println("FALSE NEGATIVES DONE\n");
		//END TESTS FOR FALSE NEGATIVES
		
		//BEGIN TESTS FOR FALSE POSITIVES
		
		//numSuit will record the number of cards of each suit generated,
		//this can be used as a baseline to determine if the RNG is responsible for any discrepancies
		int numSuit[] = new int[4];
		
		//numRank will store the number of hands of each rank generated
		int numRank[] = new int[10];
		
		//create a simulated deck with one instance of each possible card
		Card[] testDeck = new Card[52];
		byte testValue = 0;
		byte testSuit = 0;
		for(byte c = 0; c < testDeck.length; c++)
		{
			testValue++;
			if(testValue > Card.KING)
			{
				testValue = 1;
				testSuit++;
			}
			if(testSuit > 3)
				testSuit = 0;
			testDeck[c] = new Card(testValue, testSuit);
		}
		
		//now perform the Durstenfeld version of the Fisher-Yates shuffle on the deck
		for(int i = 0; i < testDeck.length - 1; i++)
		{
			int j = ThreadLocalRandom.current().nextInt(i, testDeck.length);
			Card temporary = new Card(testDeck[i].value, testDeck[i].suit);
			testDeck[i] = new Card(testDeck[j].value, testDeck[j].suit);
			testDeck[j] = new Card(temporary.value, temporary.suit);
		}
		
		//store the total number of possible hands generated
		int TESTNUM = 0;
		
		for(int first = 0; first < testDeck.length; first++)
		{
			for(int second = first + 1; second < testDeck.length; second++)
			{
				for(int third = second + 1; third < testDeck.length; third++)
				{
					for(int fourth = third + 1; fourth < testDeck.length; fourth++)
					{
						for(int fifth = fourth + 1; fifth < testDeck.length; fifth++)
						{
							//count the number of hands generated, to ensure we got them all
							TESTNUM++;
							//make a hand of the cards at these iterating indices
							Card[] handArray = {testDeck[first], testDeck[second],
									testDeck[third], testDeck[fourth], testDeck[fifth]};
							Hand testHand = new Hand(handArray);
							
							//add the number of cards of each suit to numSuit
							for(int i = 0; i < testHand.Cards.length; i++)
							{
								numSuit[testHand.Cards[i].suit]++;
							}
							
							//add one to the entry in field corresponding to the rank of the generated hand
							numRank[testHand.Rank]++;
						}
					}
				}
			}
		}

		/*OBSOLETE RANDOM TEST METHOD
		 * 
		//generate TESTNUM hands, classify them, and record the number in each classification
		final int TESTNUM = 10000;
		for(int c = 0; c < TESTNUM; c++)
		{
			Hand testHand = new Hand(randomHand());
			
			//add the number of cards of each suit to numSuit
			for(int i = 0; i < testHand.Cards.length; i++)
			{
				numSuit[testHand.Cards[i].suit]++;
			}
			
			//add one to the entry in field corresponding to the rank of the generated hand
			numRank[testHand.Rank]++;
		}
		*
		*/
		
		System.out.println(TESTNUM + " Hands Generated\n");
		
		//print the actual and expected number of the suits as a control group
		System.out.printf("%-15s : %-10s : %-10s\n", "Suit", "Expected", "Actual");
		for(int c = 0; c < 4; c++)
			System.out.printf("%-15s : %-10s : %-10s\n", c, ((double)TESTNUM * 5) / 4, numSuit[c]);
		
		System.out.println("");
		
		//print the actual and expected number for each hand rank
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Rank", "Expected", "Actual");
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Five of a Kind", 0, numRank[Hand.FiveOfAKind]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Straight Flush", 40, numRank[Hand.StraightFlush]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Four of a Kind", 624, numRank[Hand.FourOfAKind]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Full House", 3744, numRank[Hand.FullHouse]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Flush", 5108, numRank[Hand.Flush]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Straight", 10200, numRank[Hand.Straight]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Three of a Kind", 54912, numRank[Hand.ThreeOfAKind]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "Two Pair", 123552, numRank[Hand.TwoPair]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "One Pair", 1098240, numRank[Hand.OnePair]);
		System.out.printf("%-15s : %-10.8s : %-10s\n", "High Card", 1302540, numRank[Hand.HighCard]);
		
		System.out.println("FALSE POSITIVES DONE");
		//END TEST FOR FALSE POSITIVES
	}
	
	public static void EuchreTest()
	{
		//we will generate a random hand as our trick, print it,
		//and print what the Euchre trick method thinks is the winning card
		for (int c = 0; c < 15; c++)
		{
			System.out.println("Trick number " + c + ":");
			
			Card[] Trick = randomHand();
			for(int i = 0; i < Trick.length; i++)
			{
				Trick[i].print();
			}
			byte trumpSuit = randomSuit();
			int winningIndex = Euchre.trickWinner(Trick, trumpSuit);
			System.out.println("Winner with Trump " + trumpSuit + ":");
			Trick[winningIndex].print();
			System.out.println("");
		}
	}
	
	public static void main(String[] args) 
	{
		//comment or uncomment these test method calls to test the desired code
		
		//PokerTest();
		EuchreTest();
	}

}
