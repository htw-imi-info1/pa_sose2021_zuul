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
     * # 9. Dschungelbuch
     *
     * World:
     * - Kaa's Baum (B)
     * - Übungsplatz der Elefantenkompanie (Ü)
     * - Balus Ufer (U)
     * - Baghiras Höhle (H)
     * - Affenstadt (A)
     * - Berg (Bg)
     * - Pfad (P)
     *
     *        U           Ü
     *        |           |
     * -> P - P - P - P - P
     *                    |
     *            P - P - P - B
     *            |
     *        H - P - Bg - A

     * Welt mit nummern:
     *      U              Ü
     *      |              |
     * -> P1 - P2 - P3 - P4 - P5
     *                     |
     *           P8 - P7 - P6 - B
     *           |
     *       H - P9 - Bg - A
     *
     *
     *
     * Goal: Bananen von King Louis aus der Affenstadt klauen - davor genügend Stöcke und Steine sammeln für Ablenkung für die Affen
     *
     * up/down: den Berg zur Affenstadt hinauf klettern
     *
     * Items:
     * - Bananen
     * - Stock
     * - Steine
     * - Kaa's alte Haut
     * - Beeren
     * - Blätter
     */
    private void createRooms()
    {
        welcomeString =
        "Welcome to the World of Zuul!\n"+
        "World of Zuul is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n";


        Room kaasBaum, uebungsplatzElefanten, balusUfer, baghirasHoehle, affenstadt, berg,
        pfad1, pfad2, pfad3, pfad4, pfad5, pfad6, pfad7, pfad8, pfad9;


        // create the rooms
        kaasBaum = new Room("unter Kaas Baum");
        uebungsplatzElefanten = new Room("auf dem Uebungsplatz der Elefantenkompanie");
        balusUfer = new Room("an Balus Ufer");
        baghirasHoehle = new Room("in Baghiras Höhle");
        affenstadt = new Room("in der Affenstadt");
        berg = new Room("auf einem Berg");
        pfad1 = new Room("auf einem schmalen Pfad");
        pfad2 = new Room("auf einem schmalen Pfad");
        pfad3 = new Room("auf einem schmalen Pfad");
        pfad4 = new Room("auf einem schmalen Pfad");
        pfad5 = new Room("auf einem schmalen Pfad");
        pfad6 = new Room("auf einem schmalen Pfad");
        pfad7 = new Room("auf einem schmalen Pfad");
        pfad8 = new Room("auf einem schmalen Pfad");
        pfad9 = new Room("auf einem schmalen Pfad");

        // public void setExits(Room north, Room east, Room south, Room west)
        // initialise room exits
        pfad1.setExit("east", pfad2);
        pfad2.setExit("west", pfad1);
        pfad2.setExit("north", balusUfer);
        balusUfer.setExit("south", pfad2);
        pfad2.setExit("east", pfad3);
        pfad3.setExit("west", pfad2);
        pfad3.setExit("east", pfad4);
        pfad4.setExit("west", pfad3);
        pfad4.setExit("east", pfad5);
        pfad5.setExit("west", pfad4);
        pfad5.setExit("north", uebungsplatzElefanten);
        uebungsplatzElefanten.setExit("south", pfad5);
        pfad5.setExit("south", pfad6);
        pfad6.setExit("north", pfad5);
        pfad6.setExit("east", kaasBaum);
        kaasBaum.setExit("west", pfad6);
        pfad6.setExit("west", pfad7);
        pfad7.setExit("east", pfad6);
        pfad7.setExit("west", pfad8);
        pfad8.setExit("east", pfad7);
        pfad8.setExit("south", pfad9);
        pfad9.setExit("north", pfad8);
        pfad9.setExit("west", baghirasHoehle);
        baghirasHoehle.setExit("east", pfad9);
        pfad9.setExit("up", berg);
        berg.setExit("down", pfad9);
        berg.setExit("east", affenstadt);
        affenstadt.setExit("west", berg);

        gameStatus = new GameStatus(pfad1);  // start game in the amphitheater
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
