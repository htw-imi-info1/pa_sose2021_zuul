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
     * # 7. Star Wars
     *
     * World:
     * - Korridor (K)
     * - Toilette (T)
     * - Zentrale (Z)
     * - Quartier (Q)
     * - Maschinenraum (M)
     *
     *     -> K           T
     *        |           |
     *    Q - K - K - K - K - Z
     *                    |
     *            K - K - K - Q
     *            |
     *        T - K - K - K - Q
     *                |
     *    M - K - K - K
     *
     * Welt mit Nummern:
     *     -> K1             T1
     *        |              |
     *   Q1 - K2 - K3 - K4 - K5 - Z
     *                       |
     *             K8 - K7 - K6 - Q1
     *             |
     *        T2 - K9 - K10 - K11 - Q1
     *                         |
     *              M - K13 - K12
     *
     *
     * Goal: Sprengsatz in den maschinenraum bringen um Todesstern ausschalten zu können, dann wieder zum startpunkt
     *
     * up/down: in den maschinenraum klettern und wieder heraus
     *
     * Items:
     * - Schraubenschlüssel
     * - Sormtrooper Helm
     * - Blaster
     * - Lichtschwertkristalle
     *
     */
    private void createRooms()
    {
        welcomeString =
        "Welcome to the World of Zuul!\n"+
        "World of Zuul is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n";


        Room korridor1, korridor2, korridor3, korridor4, korridor5, korridor6, korridor7, korridor8, korridor9, korridor10, korridor11, korridor12, korridor13,
        toilette1, toilette2, quartier1, quartier2, quartier3,
        zentrale, maschinenraum;

        // create the rooms
        korridor1 = new Room("in einem schmalen Korridor");
        korridor2 = new Room("in einem schmalen Korridor");
        korridor3 = new Room("in einem schmalen Korridor");
        korridor4 = new Room("in einem schmalen Korridor");
        korridor5 = new Room("in einem schmalen Korridor");
        korridor6 = new Room("in einem schmalen Korridor");
        korridor7 = new Room("in einem schmalen Korridor");
        korridor8 = new Room("in einem schmalen Korridor");
        korridor9 = new Room("in einem schmalen Korridor");
        korridor10 = new Room("in einem schmalen Korridor");
        korridor11 = new Room("in einem schmalen Korridor");
        korridor12 = new Room("in einem schmalen Korridor");
        korridor13 = new Room("in einem schmalen Korridor");
        toilette1 = new Room("in einer ziemlich schmutzigen Toilette");
        toilette2 = new Room("in einer schön sauberen Toilette");
        quartier1 = new Room("in einem sehr ordentlichen Quartier");
        quartier2 = new Room("in einem unaufgeräumten Quartier");
        quartier3 = new Room("in einem schön dekorierten Quartier");
        zentrale = new Room("in der Zentrale");
        maschinenraum = new Room("im dampfenden Maschinenraum");

        // public void setExits(Room north, Room east, Room south, Room west)
        // initialise room exits
        korridor1.setExit("south", korridor2);
        korridor2.setExit("west", quartier1);
        quartier1.setExit("east", korridor2);
        korridor2.setExit("east", korridor3);
        korridor3.setExit("east", korridor4);
        korridor4.setExit("east", korridor5);
        korridor5.setExit("north", toilette1);
        toilette1.setExit("south", korridor5);
        korridor5.setExit("east", zentrale);
        zentrale.setExit("west", korridor5);
        korridor5.setExit("south", korridor6);
        korridor6.setExit("east", quartier2);
        quartier2.setExit("west", korridor6);
        korridor6.setExit("west", korridor7);
        korridor7.setExit("west", korridor8);
        korridor8.setExit("south", korridor9);
        korridor9.setExit("west", toilette2);
        toilette2.setExit("east", korridor9);
        korridor9.setExit("east", korridor10);
        korridor10.setExit("east", korridor11);
        korridor11.setExit("east", quartier3);
        quartier3.setExit("west", korridor11);
        korridor11.setExit("south", korridor12);
        korridor12.setExit("west", korridor13);
        korridor13.setExit("down", maschinenraum);
        maschinenraum.setExit("up", korridor13);
        korridor13.setExit("east", korridor12);
        korridor12.setExit("north", korridor11);
        korridor11.setExit("west", korridor10);
        korridor10.setExit("west", korridor9);
        korridor9.setExit("north", korridor8);
        korridor8.setExit("east", korridor7);
        korridor7.setExit("east", korridor6);
        korridor6.setExit("north", korridor5);
        korridor5.setExit("west", korridor4);
        korridor4.setExit("west", korridor3);
        korridor3.setExit("west", korridor2);
        korridor2.setExit("north", korridor1);

        gameStatus = new GameStatus(korridor1);  // start game in the amphitheater
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
