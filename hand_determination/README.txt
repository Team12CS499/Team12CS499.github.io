This package contains the means to create an Object representing a hand of playing cards.

To create a card, call the constructor for the Card object, which takes bytes representing the value and suit of the card.
These bytes are stored as constants in the Card class. Numbered values of cards can be referred to simply by there number.
	e.g. 
	To create the Ace of Spades you would call "new Card(Card.ACE, Card.SPADES);"
	To create the Three of Clubs you would call "new Card(3, Card.CLUBS);"
	and so forth
	
To create a Poker Hand of Cards, first create an array of Cards of the appropriate size for a hand.
This array is the only argument necessary to call the constructor for the Hand Object.
	e.g. "new Hand(cardArray);"
The rank (type) of hand will be automatically assessed when the Hand is created or modified.
To reference the Rank, simply use the "getRank()" method.

To in stead assess a Euchre trick, first create an array of Cards of the appropriate size for the trick.
Use this array, and the byte constant corresponding to the trump suit to call the Euchre.trickWinner() method.
	e.g. "Euchre.trickWinner(cardArray, trumpSuit)"
The method will return the index of the winning card in the array as an integer.

IMPORTANT NOTE:
You must decide ahead of time whether Aces will be high or low. 
If you want aces to be High, use the ACE_HIGH constant.
If you want aces to be Low, use the ACE constant.
DO NOT MIX THESE CONSTANTS! It will lead to false negatives on the Rank determination methods.