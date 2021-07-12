/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
import java.util.*;
public class Game
{
    private Parser parser;
    private GameStatus gameStatus;
    private String welcomeString;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
    # Lucky Luke - Ma Dalton

    frei nach https://www.youtube.com/watch?v=Fw6us1ipC-k

    World:

    - Bank
    - Metzger
    - Saloon
    - Strasse

    Goal: Ma Dalton geht das Abendessen klauen.
     *   Metzger   Saloon
     *   |         |
     *-> S -- S -- S -- S -- S* -- Sonnenuntergang
     *        |
     *        Bank

    up/down: Bergwerk

    Items:
    - Lamb Chops
    - Money
    - Ratanplan
    - Suppe
    - Jolly Jumper (kann nicht aufgehoben werden)

     */
    private void createRooms()
    {
        // START_WORLD
        welcomeString =
        "Welcome to Lucky Luke!\n"+
        "Lucky Luke is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n";

        Room room1 = new Room("Strasse");

        gameStatus = new GameStatus(room1);
        Room room2 = new Room("Metzger");
        room1.setExit("north",room2);
        room2.setExit("south",room1);

        room2 = new Room("Strasse");
        room1.setExit("east",room2);
        room2.setExit("west",room1);

        room1 = room2; // move down the street
        room2 = new Room("Bank");
        room1.setExit("south",room2);
        room2.setExit("north",room1);

        room2 = new Room("Strasse");
        room1.setExit("east",room2);
        room2.setExit("west",room1);

        room1 = room2; // move down the street
        room2 = new Room("Saloon");
        room1.setExit("north",room2);
        room2.setExit("south",room1);

        room2 = new Room("Strasse");
        room1.setExit("east",room2);
        room2.setExit("west",room1);

        room1 = room2; // move down the street
        room2 = new Room("Strasse");
        room1.setExit("east",room2);
        room2.setExit("west",room1);

        room1 = room2; // move down the street
        room2 = new Room("Sonnenuntergang");
        room1.setExit("east",room2);
        room2.setExit("west",room1);

        room2 = new Room("Bergwerk");
        room1.setExit("down",room2);
        room2.setExit("up",room1);

        // END_WORLD
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        while (gameStatus.isPlaying()) {
            Command command = parser.getCommand();
            String output = command.process(gameStatus);
            System.out.println(output);
        }

    }

    /**
     * This is a further method added by BK to
     * provide a clearer interface that can be tested:
     * Game processes a commandLine and returns output.
     * @param commandLine - the line entered as String
     * @return output of the command
     */
    public String processCommand(String commandLine){
        Command command = parser.getCommand(commandLine);
        return command.process(gameStatus);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println(welcomeString);

        System.out.println(gameStatus.getLocationDescription());
        System.out.println();
    }

    public static void playImmediately(){
        new Game().play();
    }

}
