---
layout: default
---

<html> 

<head> 
<title> Williams </title>
</head>

<body bgcolor="white" text="black">

<h1> Williams </h1>

16SEP2018 - Today I wrote the System Model, User Interaction, and Functional Requirements for the Requirements Specification (totaling 603 words) and also designed the team logo.

30SEP2018 - Today I created the class diagram and wrote its description for the architecture assignment.  In addition to that description I wrote the problems discovered for that section of the code review.  These two components of the architecture assignment contained 535 words total.  This brings my running word count up to 1138 words.

07OCT2018 - Today I began implementation of a test app to get the ARCore portion of our app up and running. I was able to get the test app to be openable and present a system message "Hello, World!"

10OCT2018 - I started adding the ARCore components into our test app, I was able to get the camera view to come up and start updating real time, I have been attempting to get the ARCore demo app where you tap on a surface and an object (the android mascot) appears on it.

12OCT2018 - I am still attempting to implement the ARCore demo within our test app.  I haven't yet been able to get it up and running on my emulator.  My emulator is having trouble with Google Play, I am having difficulties getting it to sign in to the Google Play Store to download the ARCore app.

17OCT2018 - I was able to get the ARCore demo up and running on my android emulator on my laptop.  It will be a lot more helpful to have a test device.  I will also email Dr. Haye's in regards to her android tablet that she said I could borrow.  Now I just need to figure out how to rip a still frame from the AR environment in preparation for labeling with the MLKit component.

30OCT2018 - Today I followed along with several android app development tutorials, one specifically about an augmented images app that could play video over pictures recognized in a newspaper.  I'm noticing that the images that the image augmentor pulls from are stored in an image augmentation database.  It looks straightforward enough to add images to that dynamically, but I'll look into it further once we get to that portion of development.  For now I'm focusing on getting an image of the scene to send to the machine learning algorithm.

1930EST 01NOV2018 - Today I am attempting to get our AR app to take pictures at regular intervals to be labeled in "real-time" by the Machine Learning portion of the app.  My current goal is to edit the existing Google ARCore augmented image demo and see where I can get as far as ripping an image directly from that.  I will be attempting to get it to save an image to a locally available location once per second (adjustable easily as 1 variable)

2000EST 01NOV2018 - Taking a look at the Augmented Image API, it seems that it would be easier to accomplish what I'm trying to do by having a separate Android Activity taking pictures in the background, while a Unity based ARCore is running in the foreground.  I will play around with this and see where I get.  My inexperience with android development is posing a little more of a challenge than I thought it would, but I've found some helpful tutorials on how to create a custom camera app and will try to work from those.

2130EST 01NOV2018 - Wasn't able to figure out the separate camera activity.  And through further research I have determined that two activities cannot share the camera at the same time.  Also, the Unity ARCore is in C# and I can't imagine it very easy to bridge the gap between Unity and the rest of the project in Android Studio.  Instead I will resume my efforts to rip a still frame of the AR environment (including AR objects) from the AR Sceneview.  This should definitely prove easier to integrate with the other pieces of our program.

0100EST 02NOV2018 - Through testing with the Java Timer class I've been able to get a test app to take pictures at regular intervals of 1 second.  The only problem that I'm running into is that the best place (or at least, the only one I can figure out) to send the pictures is the phone's photo gallery on external memory.  With that being said, it also continuously adds those pictures for as long as the app is running, this is an immense waste of memory space and I'll try to figure out a way to plug this leak as I continue.  For now though, I'm turning my attention towards implementing this camera timer within the AR framework.

0330EST 02NOV2018 - I've found an interesting stackexchange post in regards to pulling images from ARScenes.  Hopefully this pans out.  It's intended for a different AR application, but I should hopefully be able to adapt it to our purposes.

0530EST 02NOV2018 - EUREKA! After reviewing a lot of documentation on the ARCore stuff going on in the demo, I've noticed that it deals a lot in "GLES20" objects.  This is the image of the current scene view just in a different form, I can capture that using a method from the stack exchange post I found, and transform that object into a BitMap, which can then be converted into a .png file.  It's a bit more roundabout than I was hoping for, but it should work.  I'll get at it.

0645EST 02NOV2018 - Hopefully my last update, I've theoretically managed to get the app to take pictures and save them to the gallery under a special "PokerHands" folder.  I'm still running into the problem of the app continuously taking pictures and not doing anything about the old ones, theoretically we would need no more than 3-5, or possibly even just the most recent image.  I'm attempting to figure out the best way to ensure only a certain number of the most recent images are saved.  I think once the image file reaches a certain size (number of images) I can delete the oldest one, and then add the new one after that.

0717EST 02NOV2018 - While untested, I theoretically have an augmented images app, that takes pictures of it's view every second, stores only the 3 most recently taken pictures in a folder in the gallery, and deletes the oldest image as it takes more pictures.  I'm uploading it to our github repository so that Kyle Defler can test it on his phone.  I'll also attempt to get my emulator up and running with my webcam to see if it works on that.  Hopefully all goes well and this part can be put to bed.

07NOV2018 - As discussed at status meeting today I have begun to work on getting images into the Augmented Images database for the app to recognize.  The pictures of the cards that Kyle Nelson took are, unfortunately, not quite straight on enough for the pre-built demo to recognize.  As such, I have found some stock images of playing cards that I have begun to crop and use for the image database.  Without the Machine Learning portion ready yet, as a proof of concept for the augmented reality and hand recognition portion of our app we have decided to modify the augmented image demo to recognize the cards, augment them, and decide what kind of hand it is.  Once the Machine Learning portion is ready to go we will integrate it from there.

08NOV2018 - Discussed with Kyle Nelson today about finalizing image database, will continue to crop images of the playing cards. 

09NOV2018 - Team meeting today: As suggested by Kyle Defler I have added set the camera to auto-focus to reduce blurriness in the view of the AR environment.  This will not cause any foreseeable issues with the image recognition. I have also started to prepare the Augmented Image database for integration with the app.  Also, for the coding assignment I contributed to the Testing and User's Manual portions, a total of 406 words.  Between that assignment and this dev-log my total word count is now: 2824

11NOV2018 - Now that I have successfully found, cropped, and named all of the playing card images according to a parse-able standard, the images are ready to be loaded into an augmented image database. After our team meeting on Discord I have managed to get the database created, but I am having difficulty getting some of the images to populate into the database.  I get errors for most of the lower value cards that they don't have enough "Feature Points" to be valid for augmentation.  I will look further into this. In addition, I added code to the Augmented Image app to render 3D objects for each of the cards suits above the card itself.  In addition I designed the 3D objects in ONSHAPE, and converted them to .obj files so they can be rendered in the 3D environment.  When the cards are augmented a bright green picture frame will appear around them, and the cards respective suit will appear above each of them.

12NOV2018 - Today we worked on the Coding Assignment presentation.  I made the Current State, Demo!, and Next Steps slides.  

13NOV2018 - I figured out what's going on with the Augmented Image Database.  Due to the fact that some of the lower cards are too "plain" (they don't have enough distinguishable features to be consistently recognized in the environment) they can't be added to the database.  As such, we will only have certain cards available to choose from for the demo. All of the face cards are recognizable, as are pretty much all cards above 7 except for those NOT listed in the file in our team repository.  This will not be a problem once Kyle Defler finishes the ML Kit stuff and I can get that integrated into the app. BUT, the database is loaded into the app now and it's ready for Kyle Defler to test on his phone.  I'm also going to try and get my emulator up and running, but have not had much success getting the ARCore apk installed on it.  My group understands this and our primary test device remains Kyle Defler's phone.

1041EST 16NOV2018 - Kyle Defler discovered that the images in the database are not being detected, and the camera overlay is remaining on the screen. We figured out this was due to the fact that none of the cards being used for testing were actually in the database.  After using cards in the database we had some small measure of success. While the images in the database are being detected, they are not being augmented by the app with the 3D graphics.  I think this could possibly be due to the fact that the database needs to determine the size of the image within the environment in order to augment it to scale.  There is a way to add an expected size of the image to the database, I will add this and hope that it resolves the issue.

1419EST 16NOV2018 - I was able to get the sizes added into the augmented image database and made an unfortunate discovery.  It did not resolve our error due to the fact that the Augmented Images demo is intended to recognize at least a 300 pixel by 300 pixel image that appears in 15cm by 15cm real life dimensions.  This should not prove to be an issue as the demo was simply a proof of concept for the overlay.  The overlay objects themselves work in the "Hello AR" demo when placed with a tap, and so the only portion of the app that isn't working is the part that recognizes the playing cards in the environment.  Once we get the machine learning portion integrated this will prove to be a non-issue.

20NOV2018 - We got out twist! We're doing euchre instead of poker!  Updated Word Count Before Thanksgiving Break (not including comments from code): 3441

28DEC2018 - Since we don't have an ML portion yet we decided at our last team meeting to enable the app user to point out cards to the app by tapping on them.  The user will be able to select the current trump suit (for euchre), the suit of the card they're about to tap, and the value of the card they're about to tap from drop down menus at the top of the screen.  Once all of the cards in the trick are selected they just have to press the submit button and the Euchre winner formula, written by Kyle Nelson, will tell us which card won the trick.  I will be writing the app while Kyle Defler continues to work on the ML portion.  If we get the ML up and running then I'll be able to integrate it into the app, if not at least we'll have something!

02DEC2018 - Today I finished the ARCore portion of our final app and integrated Kyle Nelson's Hand Recognition module.  Since the twist we've added Euchre functionality, and since we weren't able to get the MLKit working we have instead decided to really on User input to find cards in the AR Environment and label them with value and suit.  As described previously the screen displays three dropdown menus across the top of the screen, from which the user can select the trumpSuit, the suit of the card about to be tapped, and the value of the card about to be tapped.  From there, the user taps on a card in the environment to mark it and add it to the euchre trick.  The order in which the cards are marked is important to euchre, so I'll explain in the user's manual to tap them in the order in which they were played.  We'll be working on the Maintenance Assignment, and our final presentations in the next few days.  My word count from comments in our code is 532, bring my current total word count up to 4305 
</body>

</html>
