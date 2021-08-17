
# Programming Assignment 1

## Step0: Download the Project

First, you need to download the Project:
it is based on the baad_zuul_with_tests project, with some additions:
https://github.com/htw-imi-info1/pa_sose2021_zuul

- there is a simplified WorldTest that just tests the "go" command -this should be easier to adapt to your new world
- for testing the refactored exit display, there is a ShowExitTest - this needs to be adapted to your world.
- There are interfaces with method signatures that you may use to guide you through your implementation.

##Step1: Create the World

Start by adapting the World to your theme.
Adapt the 5 rooms already present in the example - no need to add the whole world just yet. Choose a part of the world that uses the four directions already present.
If you want to use the tests for the refactoring, don't forget to adapt the tests. (This doesn't affect the grading, but will make the refactoring easier). Describe what you did in your report, and include a screenshot of your World creation in your report.

#Step2: Refactor Room Exits

Refactor how exits are handled to
- avoid code duplication
- make the additions of new directions easier

as described in the book (8.4-8.7)
With a mayor change: instead of adding the exit logic to Room, create a new class Exits.
Exit should implement this methods which are then used by Room:

    public void add(String direction, Room room);
    public Room get(String direction);
    public String getDescription();

https://github.com/htw-imi-info1/pa_sose2021_zuul/blob/main/ExitsInterface.java

As always, include a description of what you did in your report together with some screenshots.


# Step3: Add new directions

Add some more rooms to your world, especially one that makes use of new directions which should now be easier to implement.
(You don't need to implement the whole world if your's is rather large, add that if there is time left in the end)
As always, include a description of what you did in your report together with some screenshots.

# Step4:  Refactor Game

Now, refactor Game (if not already done during the refactoring of Rooms/Exits) to use the refactored Room Exit handling.

To do so, encapsulate the Game Status in a new class GameStatus which for now only holds the Player's location as a field (the class is already created).

How should the initial Room be handed to GameStatus? Explain your decision in your report.

As always, include a description of what you did in your report together with some screenshots.


# Step5:  Add new commands

Finally, add two new commands to Game:
1) look - prints out the currentRoom description again
2) any other command matching your theme that just prints out an appropriate text message. e.g.
    >speak
    "You say: hi!"
   > 
As always, include a description of what you did in your report together with some screenshots.


# Programming Assignment 2


You start with a version of zuul where the Refactoring of the Commands has already been done for you.
It differs from the one in the book: the main difference is that the commands are implemented in subclasses of "Command".
CommandWords has the responsibility to "know" the valid command words as in the original version.

Commands in Zuul do two things:
- they generate an output string
- they may (or may not) change the state of the Game ("gameStatus") which is currently only the current location of the player (but might be extended by items carried, hitpoints etc.)

All Command Subclasses implement a method "public string process(GameStatus gameStatus)"
It takes the gameStatus as a parameter, which may be modified (e.g. when the Room changes).
They return the String that should be printed as output.


1. The implementation of the "help" command was moved to the Help class, but not modified yet. Modify it to print out the complete list of valid commands which can be obtained by calling

CommandWords.INSTANCE.getValidCommands()

2. Extend your project so that a room can contain any number of items. Items have descriptions and weights.
   When creating rooms and setting their exits, items for this game should also be created.

3. When a player enters a room, all items should be shown to the player:
   Before you start to implement it, think about these questions and put the answers in your report:
   How should the information about an item present in a room be produced? Which class should produce the string describing the item? Which class should print it? Why?

4. Implement a “back” command that does not have a second word and takes the player back into the previous location.
   Where should the previous location be stored?
   Test this! What happens if you enter back twice?

5. add a beamer to your game: "A beamer is a device
   that can be charged and fired. When you charge the beamer, it memorizes the current room. When you fire the beamer, it transports you immediately back to the room it was charged in." (S.292)
   (this includes a new command with a parameter, e.g. beemer charge and beemer fire)


# Programming Assignment 3

You start with a version of Zuul with my solution of PA_2 with Items, a Beamer and a Back command.

Answer the questions in your Report. These include programming hints; so it's best to answer them - or at least think about them - before you start programming.

1. Implement a field to store the items currently carried by the player.
   Hint: my solution contains a class "Items" that already represents a Collection of Item-s. The best place to put that field is GameStatus.
2. Implement a command “take” that has the name of the item as the second parameter.
   a. What happens if the item to be taken is not in the room?
   b. How is the maximum weight determined? Does “take” know how to deal with this? You will probably need a method to ask if the current item can be taken by the player.
3. Implement the command “drop” to get rid of an item. “drop all” should do just that, drop all items currently carried.
4. Congratulate the player if they have visited/seen all Rooms in your World. Where do you track the rooms already visited? Which is the best Collection type for that?
   How and where do you track which rooms have already been visited? Which method and which Command should check for that?
5. Implement the game goal, which is either collecting a certain set of items or dropping one or several items in a certain room. Which Command should check for success in your case?
   Congratulate the player on winning the game!
