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
     * # Momo
     *
     * World:
     * - Amphitheater (A)
     * - Zeitsparkasse (Z)
     * - Nirgendhaus (NH)
     * - Niemalsgasse (NG)
     * - Strasse (S)
     *
     *  -> S - S       A - S
     *         |           |
     *     Z - S - S - S - S
     *                     |
     *     Z - S       Z - S - S
     *         |               |
     *     Z   S - S - S - S - S
     *     |       |
     *     S - S - S - S - S - Z
     *                 |
     *   NH - NG - S - S
     *
     * // World with numbers:
     * -> S1 - S2             A - S3
     *         |                   |
     *    Z1 - S4  -  S5 -  S6 -  S7
     *                            |
     *    Z3 - S8            Z2 - S9 - S10
     *          |                       |
     *    Z4   S11 - S12 - S13 - S14 - S15
     *     |          |
     *    S16 - S17 - S18 - S19 - S20 - Z5
     *                       |
     *     NH - NG  - S21 - S22
     *
     *
     * Goal: Stundenblumen und Zeit-Zigarren einsammeln und ins Nirgendhaus bringen
     *
     * up/down: Niemalsgasse rückwärts lang gehen um zum Nirgendhaus zu gelangen
     *
     * Items:
     * - Stundenblumen
     * - Zeit-Zigarren
     */
    private void createRooms()
    {
        welcomeString =
        "Welcome to the World of Zuul!\n"+
        "World of Zuul is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n";

        Room amphitheater,
        zeitsparkasse1, zeitsparkasse2, zeitsparkasse3, zeitsparkasse4, zeitsparkasse5,
        nirgendhaus,
        niemalsgasse,
        strasse1, strasse2, strasse3, strasse4, strasse5, strasse6, strasse7, strasse8, strasse9,
        strasse10, strasse11, strasse12, strasse13, strasse14, strasse15, strasse16, strasse17, strasse18, strasse19,
        strasse20, strasse21, strasse22;

        // create the rooms
        amphitheater = new Room("in Momos amphitheater");
        zeitsparkasse1 = new Room("in office 1 of the Zeitspaarkasse");
        zeitsparkasse2 = new Room("in office 2 of the Zeitspaarkasse");
        zeitsparkasse3 = new Room("in office 3 of the Zeitspaarkasse");
        zeitsparkasse4 = new Room("in office 4 of the Zeitspaarkasse");
        zeitsparkasse5 = new Room("in office 5 of the Zeitspaarkasse");
        nirgendhaus = new Room("in the Nirgendhaus");
        niemalsgasse = new Room("on the Niemalsgasse");
        strasse1 = new Room("on a street");
        strasse2 = new Room("on a street");
        strasse3 = new Room("on a street");
        strasse4 = new Room("on a street");
        strasse5 = new Room("on a street");
        strasse6 = new Room("on a street");
        strasse7 = new Room("on a street");
        strasse8 = new Room("on a street");
        strasse9 = new Room("on a street");
        strasse10 = new Room("on a street");
        strasse11 = new Room("on a street");
        strasse12 = new Room("on a street");
        strasse13 = new Room("on a street");
        strasse14 = new Room("on a street");
        strasse15 = new Room("on a street");
        strasse16 = new Room("on a street");
        strasse17 = new Room("on a street");
        strasse18 = new Room("on a street");
        strasse19 = new Room("on a street");
        strasse20 = new Room("on a street");
        strasse21 = new Room("on a street");
        strasse22 = new Room("on a street");
        // public void setExits(Room north, Room east, Room south, Room west)
        // initialise room exits
        strasse1.setExit("east",strasse2);
        strasse2.setExit("west",strasse1);
        strasse2.setExit("south",strasse4);
        strasse4.setExit("north",strasse2);
        strasse4.setExit("west",zeitsparkasse1);
        zeitsparkasse1.setExit("east",strasse4);
        strasse4.setExit("east",strasse5);
        strasse5.setExit("west",strasse4);
        strasse5.setExit("east",strasse6);
        strasse6.setExit("west",strasse5);
        strasse6.setExit("east",strasse7);
        strasse7.setExit("west",strasse6);
        strasse7.setExit("north",strasse3);
        strasse3.setExit("south",strasse7);
        strasse7.setExit("south",strasse9);
        strasse9.setExit("north",strasse7);
        strasse3.setExit("west",amphitheater);
        amphitheater.setExit("east",strasse3);
        strasse9.setExit("west",zeitsparkasse2);
        zeitsparkasse2.setExit("east",strasse9);
        strasse9.setExit("east",strasse10);
        strasse10.setExit("west",strasse9);
        strasse10.setExit("south",strasse15);
        strasse15.setExit("north",strasse10);
        strasse15.setExit("west",strasse14);
        strasse14.setExit("east",strasse15);
        strasse14.setExit("west",strasse13);
        strasse13.setExit("east",strasse14);
        strasse13.setExit("west",strasse12);
        strasse12.setExit("east",strasse13);
        strasse12.setExit("west",strasse11);
        strasse11.setExit("east",strasse12);
        strasse11.setExit("north",strasse8);
        strasse8.setExit("south",strasse11);
        strasse8.setExit("west",zeitsparkasse3);
        zeitsparkasse3.setExit("east",strasse8);
        strasse12.setExit("south",strasse18);
        strasse18.setExit("north",strasse12);
        strasse18.setExit("west",strasse17);
        strasse17.setExit("east",strasse18);
        strasse17.setExit("west",strasse16);
        strasse16.setExit("east",strasse17);
        strasse16.setExit("north",zeitsparkasse4);
        zeitsparkasse4.setExit("south",strasse16);
        strasse18.setExit("east",strasse19);
        strasse19.setExit("west",strasse18);
        strasse19.setExit("east",strasse20);
        strasse20.setExit("west",strasse19);
        strasse20.setExit("east",zeitsparkasse5);
        zeitsparkasse5.setExit("west",strasse20);
        strasse19.setExit("south",strasse22);
        strasse22.setExit("north",strasse19);
        strasse22.setExit("west",strasse21);
        strasse21.setExit("east",strasse22);
        strasse21.setExit("west",niemalsgasse);
        niemalsgasse.setExit("east",strasse21);
        niemalsgasse.setExit("backwards",nirgendhaus);
        nirgendhaus.setExit("backwards",niemalsgasse);

        gameStatus = new GameStatus(strasse1);  // start game in the amphitheater
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
