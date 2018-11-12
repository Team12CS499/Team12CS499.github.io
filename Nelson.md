---
layout: default
---

<html> 

<head> 
<title> Nelson </title>
</head>

<body bgcolor="white" text="black">

<h1>Kyle Nelson Developer Notes</h1>
<p></p>
<h2>DevNote 9/16</h2>
<p></p>
Last Wednesday, Defler and I met and created the majority of the project plan together. We submitted it to our customer for tentative approval. My portion totaled 105 words exactly.
<p></p>
Today I wrote the Title Page, Table of Contents, Nonfunctional Requirements, Feasibility, and Conclusion sections of the Requirements Specification document. This totaled 658 words exactly. The Metrics document I wrote counted for an additional 138 words exactly. This brings the running tally for the word count, including the project plan, to 901 words exactly.
<p></p>
We are assuming that the ARCore image identification software will be insufficient on its own, and will require use of the MLKit in order to provide timely and accurate answers. Marrying these two tools in order to identify the cards being scanned will likely comprise the hardest portion of the project. As such we have allocated plenty of time for these sections in case of unexpected difficulty.
<p></p>

<h2>DevNote 9/30</h2>
<p></p>
My primary contributions over the previous weeks have been visual elements. I constructed the domain model diagram, after consulting online resources regarding proper implimentation. The description for the domain model diagram is 136 words. I also constructed a mockup of the user interface for the program. I have also been reviewing the metrics we initially stipulated, and found that while the approximate manhour breakdown is still feasible, the user story format for the product size estimate is no longer viable. In stead we have switched to a method approximation approach. These revised metrics account for 104 words.
<p></p>
I also wrote the testing portion of the presentation. Defler and I collaborated on the exact testing requirements, with him writing the long winded version for the document, and I the short version for the slides. This accounts for 124 words. An addition 101 words come from the review and defects section. This brings the running total to 1366 words thus far (not including this document, which will be counted at the end).

<h2>DevNote 10/12</h2>
<p></p>
The presentation went well last Friday, and our evaluation sheets reflect that. I have been busy gathering and labeling reference images for the ARCore software. Currently I only have one set of images. There are 5 pictures of each card in this set, all on a high contrast black background. The first angle is flat to the cards face, the second is at an approximately 45 degree angle, the third is at an extreme approximately 25 degree angle, the fourth and fifth are both taken with the card diagonal to the camera. Each image is named starting with the number of letter corrisponding to its value ("2" for a 2, "A" for an ace, etc.) and the suit seperated by an underscore. All letters are capitalized. For example, the Ace of Spades pictures are labeled "A_SPADES" plus a number to differentiate them. I will wait to see if ARCore requires more samples before gather other sets. I will also have to determine if Windows' automatic numbering system, which makes use of spaces in the filenames, will cause problems in parsing the filenames.

I've floated the possible stretch goal of inserting Gin Rummy as a non-Poker stretch goal. This of course depends on the comming weeks. In the coming week I will either continue to add to the library of reference images, or create the data structures necessary to operate the hand classifying algorithm. This depends on whether ARCore requires more images or not. This marks the end of Scrumm Cycle ONE.

<h2>DevNote 10/26</h2>
<p></p>
The past few weeks have been spent writing the skeleton for the hand determination method. This has been pushed up in the schedule due to delays in the card identification software, which make it impractical for me to expand our machine learning training image library.

With the basic outline complete, my focus has now shifted to creating a robust unit test for the method, so that I can test it as I write. So far I have succeeded in creating a method to generate random cards, but what we need is to generate hands that we know have certain properties. That is to say, that we want to be able to create a hand that we know is a flush or a straight, etc. as needed for testing. This has proven somewhat more time consuming for straights and matching kinds, as we don't want all of the relivant cards to necessarily be adjacent one another.

An interesting side note is that I have devised a simple way to make aces high or low as needed. Whenever using the ACE constant we can add (13 * ACE_HIGH), where ACE_HIGH is either 1 if it is high, or 0 if not. This would provide a simple method of accomidating the two styles of play.

<h2>DevNote 11/4</h2>
<p></p>
I feel I should start this entry by mentioning the reason for assigning ACE the default value of 1, rather than 0 or 14. While it may not be relivant in most types of poker, the value of ACE at 1 rather than 0 is important to the scoring of other card games such as Gin Rummy. As for not setting ACE high by default, it is easier in my mind to remember to add 13 when ace is high rather than subtract when it is low. Additionally, many other games rely on ace being low by default including variants of poker.

On to the stuff I haven't reported before:
I was pleasantly surprised to find that Java has created a much more convenient way to generate random numbers since I last used that function back in High School. Now I don't have to do all the explicit casting shinanigans just to get an integer! 

I have decided to consider a Royal Flush as a form of Straight Flush. If we wish to differentiate them later, we will make the distinction based on the highest card. This is better, considering that the definition of Royal Flush changes depending on whether ACE is high or low.

Creating methods to generate random hands without either A. simulating a full deck or B. introducing unpredictable runtime as random numbers are compared against one another rapidly was difficult. For testing purposes I settled on using the Durstenfeld version of the Fisher-Yates shuffle only on the potential indices of the hand, and then manually changing the cards within these indices as needed. I managed to avoid having to do a shuffle on the cards in the 2 pair and full house generations by simply incrimenting one of the values in the case the pairs were equal. This works since the highest value, 14 for ACE_HIGH, is not randomly generated.

Generally, rather than take shortcuts assuming the hand will always be size five, I left room in the random hand generation methods to allow for changes in hand size if necessary later. Currently the size is hard coded at five, but this approach means that making the generation methods more flexible will be relatively simple.

<h2>DevNote 11/8</h2>
<p></p>
I have decided that having a proper hand class to use and store the hand type is probably the best option in the long term. For this I have created byte constants similar to those for the card suit and value that shall store the rank. Unlike the value for the cards, the rank is inverted so that the lower the number the greater the rank.

I have consolidated the determination methods, so now there is a single method that must be called to determine the rank of a given hand as expressed as an array of cards. It is this method that I will have to use to test for potentially dangerous false positives. I doubt I will come across any, as the absence of any false negatives makes it extremely difficult for a potential false positive to reach further methods. That is to say that a greater value hand will be immediately identified as such, so the methods for identifying other hands will not be called to test it.

I've yet to find a way to fully automate the search for false positives, so I have settled for randomly generating hands and manually checking any matches that come up for each possible hand rank. This can be a tedious process but the only other way I could see to create a reliable automated test would be to have some sort of oracle set to check against. Said set could not encompass a significant portion of the possible arrangements of cards, so would be even less robust than the current system. I should have a final version of this portion of the project tomorrow, whose test results I will record, and then submit a wordcount on the comments.

<h2>DevNote 11/9</h2>
<p></p>
I have come up with a satisfactory way to test for false positives. Cards are randomly drawn from a virtual deck to make a hand. This hand is tested to determine its rank/type. We repeat this process, counting the number of instances of each hand rank that occur. If there is a large discrepency between the number of hands of a given value, and the statistically expected number we can surmise that there are false positives. This process can be repeated many times to ensure adequate samples.

<h2>DevNote 11/11</h2>
<p></p>
After increasing the number of randomly generated tests for false positives as indicated above, I realized that I could actually run an exhaustive test of all possible hands. After running the exhaustive tests I was alarmed to realize that there were a number of false negatives on the Straight recongnition. After some reasearch, I realized that the number of possible straights as calculated by so many mathematicians was based off the idea that an Ace could be alternatively low or high depending on player preference. This meant that when I tested with Aces fixed to low, the straights which required Aces to be high would return as not being straights. This is an acceptable feature for this low level. We will simply have to remember to have the higher levels of the software test both the case where Ace is low and the case where Ace is high.


I estimate there to be a total of 1045 words in the comments to the code I have written for the hand Determination Algorithm, at an average of 5 word per comment line and 209 lines of comments. The accompanying README.txt is an additional 202 words. My contributions to the Coding assignment come up to 448 words. Including the 500 words each from the Ethics assignment and the Resume asssignment, this brings us to a total of 4061 words, not including this document. This document, up until this point is a total of 1818 words. This brings the GRAND TOTAL to 5879.

</body>

</html>
