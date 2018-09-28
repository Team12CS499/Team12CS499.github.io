---
layout: default
---

<html> 

<head> 
<title> Requirments </title>
</head>

<body bgcolor="white" text="black">

<h1> Requirments </h1>
<p></p>

<p><strong>Functional Requirements</strong></p>
<p>This section describes the functional requirements of the application.</p>
<ul>
<li>The application loads and displays the augmented reality overlay with instructions to aim the camera at a hand of cards.</li>
</ul>
<ol>
<li>The application loads the overlay and connects to any cloud components</li>
<ol>
<li>The user exits the application while it&rsquo;s loading</li>
</ol>
<li>The application displays the augmented reality overlay and instructions for use of the application</li>
</ol>
<ul>
<li>The application recognizes the cards and displays the best hand possible from the cards available</li>
</ul>
<ol>
<li>The application captures an image of the cards</li>
<ol>
<li>The application cannot recognize the cards to be captured</li>
</ol>
<li>The application sends images of the cards to the image recognition component</li>
<ol>
<li>The application cannot establish a connection with the image recognition component</li>
</ol>
<li>The image recognition component returns the card values and suits to the application</li>
<ol>
<li>The image recognition component cannot recognize the cards</li>
</ol>
<li>The card values and suits are overlaid onto the cards, and the best hand possible from those cards is overlaid onto the screen below the cards.</li>
</ol>
<p><strong>Essential Nonfunctional Requirements:</strong></p>
<ol>
<li>The application must run within the latest Android version (9.0 &ldquo;Pie&rdquo; as of August 6, 2018)</li>
<li>The application must be able to identify hands of exactly size five (5)</li>
<li>The application must return accurate results for the hand&rsquo;s content and category within five (5) seconds of startup</li>
<li>No individual case may return inaccurate results in more than eighty percent (80%) of tests</li>
<li>The application must return accurate results for hand content and category in ninety-five percent (95%) of all tests</li>
<li>The application shall not exceed 1GB of local memory</li>
</ol>
<p><strong>Optional Nonfunctional Objectives:</strong></p>
<ol>
<li>The application is ported to function in iOS</li>
<li>The application returns accurate results in fully one-hundred percent (100%) of tests</li>
<li>The application is able to recognize hands of size greater than two (2) and up to seven (7), in order to accommodate other types of poker</li>
</ol>
