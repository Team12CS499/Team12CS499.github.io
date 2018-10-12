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
I also wrote the testing portion of the presentation. Defler and I collaborated on the exact testing requirements, with him writing the long winded version for the document, and I the short version for the slides. This accounts for 124 words. An addition 101 words come from the review and defects section.

<h2>DevNote 10/12</h2>
<p></p>
The presentation went well last Friday, and our evaluation sheets reflect that. I have been busy gathering and labeling reference images for the ARCore software. Currently I only have one set of images. There are 5 pictures of each card in this set, all on a high contrast black background. The first angle is flat to the cards face, the second is at an approximately 45 degree angle, the third is at an extreme approximately 25 degree angle, the fourth and fifth are both taken with the card diagonal to the camera. Each image is named starting with the number of letter corrisponding to its value ("2" for a 2, "A" for an ace, etc.) and the suit seperated by an underscore. All letters are capitalized. For example, the Ace of Spades pictures are labeled "A_SPADES" plus a number to differentiate them. I will wait to see if ARCore requires more samples before gather other sets. I will also have to determine if Windows' automatic numbering system, which makes use of spaces in the filenames, will cause problems in parsing the filenames.

I've floated the possible stretch goal of inserting Gin Rummy as a non-Poker stretch goal. This of course depends on the comming weeks. In the coming week I will either continue to add to the library of reference images, or create the data structures necessary to operate the hand classifying algorithm. This depends on whether ARCore requires more images or not. This marks the end of Scrumm Cycle ONE.

</body>

</html>
