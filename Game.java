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

    World:
    the world consists of a wide area of Wasteland (about 3x3 squares)
    and a rocket. (climb up/down) sitting above one of the wasteland fields

    (all rooms are wasteland, the one marked with * has the rocket)

     *   -> W - W - W  - W
     *      |   |   |    |
     *      W - W - W* - W
     *      |   |   |    |
     *      W - W - W  - W

    Goal: Collect Radioactive Bunnies to gain enough Radiation

    Items:

    Nuke Winter +7
    Basic Bunny +4
    Radiogenic +3
    Exposeya -2
    Karrotjuice +4
    Corrosion -4
    Dirty-Bomb - Doubles the total radiation level on your hand

     */
    private void createRooms()
    {
        // START_WORLD
        welcomeString =
        "Welcome to Radioactive Bunnies!\n"+
        "Radioactive Bunnies is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n";

        final int ROWS=3,COLS=4;
        Room[][] wasteland = new Room[ROWS][COLS];

        // create all rooms
        for(int row = 0; row<ROWS; row++){
            for (int col = 0; col < COLS; col++){
                wasteland[row][col] = new Room("a wide area of wasteland");            
            }
        }

        // for all but the last col, add east/west connections to the east
        for(int row = 0; row<ROWS; row++){
            for (int col = 0; col < COLS-1; col++){
                wasteland[row][col].setExit("east",wasteland[row][col+1]);
                wasteland[row][col+1].setExit("west",wasteland[row][col]); 
            }
        }

        // for all but the last row, add south/north connections to the south
        for(int row = 0; row<ROWS-1; row++){
            for (int col = 0; col < COLS; col++){
                wasteland[row][col].setExit("south",wasteland[row+1][col]);
                wasteland[row+1][col].setExit("north",wasteland[row][col]); 
            }
        }
        Room rocket = new Room("a silver shining rocket and have a perfect overview over a wide area of wasteland");
        wasteland[1][2].setExit("up",rocket);
        rocket.setExit("down",wasteland[1][2]);
        gameStatus = new GameStatus(wasteland[0][0]);  // start game outside
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
