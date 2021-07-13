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
     *
     * # 8. Mario
     *
     * World:
     * - Röhre (R)
     * - Wiese (W)
     * - Grotte (G)
     * - Schloss (S)
     *
     *                G
     *                |
     *                R
     *                |
     *     -> W - W - W - W
     *                    |
     *    G - R - W - W - W - R - G
     *            |
     *    S - W - W
     *
     * World with Numbers:
     *                  G1
     *                  |
     *                  R1
     *                  |
     *     -> W1 - W2 - W3 - W4
     *                       |
     *   G3 - R3 - W7 - W6 - W5 - R2 - G2
     *             |
     *    S - W9 - W8
     *
     *
     * Goal: Münzen aus Grotten einsammeln und zum Schloss gelangen
     *
     * up/down: Röhren
     *
     * Items:
     * - Münze
     * - Pilz
     * - Stern
     * - Panzer
     * - Feuerblume
     *
     */
    private void createRooms()
    {
        welcomeString =
        "Welcome to the World of Zuul!\n"+
        "World of Zuul is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n";


        Room roehre1, roehre2, roehre3, roehre4,
        grotte1, grotte2, grotte3, grotte4,
        wiese1, wiese2, wiese3, wiese4, wiese5, wiese6, wiese7, wiese8, wiese9,
        schloss;

        // create the rooms
        roehre1 = new Room("in einer grünen Röhre");
        roehre2 = new Room("in einer grünen Röhre");
        roehre3 = new Room("in einer grünen Röhre");
        grotte1 = new Room("in einer dunklen Grotte");
        grotte2 = new Room("in einer dunklen Grotte");
        grotte3 = new Room("in einer dunklen Grotte");
        wiese1 = new Room("auf einem Wiesenweg");
        wiese2 = new Room("auf einem Wiesenweg");
        wiese3 = new Room("auf einem Wiesenweg");
        wiese4 = new Room("auf einem Wiesenweg");
        wiese5 = new Room("auf einem Wiesenweg");
        wiese6 = new Room("auf einem Wiesenweg");
        wiese7 = new Room("auf einem Wiesenweg");
        wiese8 = new Room("auf einem Wiesenweg");
        wiese9 = new Room("auf einem Wiesenweg");
        schloss = new Room("in einem Schloss");

        // public void setExits(Room north, Room east, Room south, Room west)
        // initialise room exits
        wiese1.setExit("east", wiese2);
        wiese2.setExit("east", wiese3);
        wiese3.setExit("down", roehre1);
        roehre1.setExit("down", grotte1);
        grotte1.setExit("up", roehre1);
        roehre1.setExit("up", wiese3);
        wiese3.setExit("east", wiese4);
        wiese4.setExit("south", wiese5);
        wiese5.setExit("down", roehre2);
        roehre2.setExit("down", grotte2);
        grotte2.setExit("up", roehre2);
        roehre2.setExit("up", wiese5);
        wiese5.setExit("west", wiese6);
        wiese6.setExit("west", wiese7);
        wiese7.setExit("down", roehre3);
        roehre3.setExit("down", grotte3);
        grotte3.setExit("up", roehre3);
        roehre3.setExit("up", wiese7);
        wiese7.setExit("south", wiese8);
        wiese8.setExit("west", wiese9);
        wiese9.setExit("west", schloss);
        schloss.setExit("east", wiese9);
        wiese9.setExit("east", wiese8);
        wiese8.setExit("north", wiese7);
        wiese7.setExit("east", wiese6);
        wiese6.setExit("east", wiese5);
        wiese5.setExit("north", wiese4);
        wiese4.setExit("west", wiese3);
        wiese3.setExit("west", wiese2);
        wiese2.setExit("west", wiese1);

        gameStatus = new GameStatus(wiese1);  // start game in the amphitheater
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

}
