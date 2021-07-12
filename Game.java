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
     * # 4. Totoro

    World:
    - Wiese (wi)
    - Wald (wa)
    - Reisfeld (R)
    - magischer Wald (mWa)
    - Totoros Baum (TB)

     * -> Wi - Wi - Wi - Wa
     *    |              |
     *    R  - Wi   Wa - mWa
     *         |           
     *    R  - R  - mWa - mWa
     *    |         |     
     *    R    Wa - mWa - TB

    Goal: Rußmännchen einsammeln und zu Totoro bringen

    up/down: Totoros Baum (im magischen Wald)

    Items:
    - Rußmännchen
    - Blatt
    - Eimer
    - Schaufel
    -----
     */
    private void createRooms()
    {
        // START_WORLD
        welcomeString =   
        "Welcome to 'Maus im Mond'!\n"+
        "Maus im Mond is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n"; 
        final String WI = "auf einer Wiese";
        final String WA = "im Wald";
        final String R = "im Reisfeld";
        final String MW = "im magischen Wald";
        final String TB = "auf Totoros Baum.";

        String[][]names = {
                {WI,WI,WI,WA},
                {R,WI,WA,MW},
                {R,R,MW,MW},
                {R,WA,MW,MW}};

        Room[][] room = buildRooms(names);
        /* j----0   1    2    3
         * i
         * 0 -> Wi - Wi - Wi - Wa
         *      |              |
         * 1    R  - Wi   Wa - mWa
         *           |           
         * 2    R  - R  - mWa - mWa
         *      |         |     
         * 3    R    Wa - mWa - TB
         */

        room[0][0].connect("east",room[0][1]);
        room[0][1].connect("east",room[0][2]);
        room[0][2].connect("east",room[0][3]);
        
        room[0][0].connect("south",room[1][0]);
        room[0][3].connect("south",room[1][3]);
        
        room[1][0].connect("east",room[1][1]);
        room[1][2].connect("east",room[1][3]);
        
        room[1][1].connect("south",room[2][1]);
        
        room[2][0].connect("east",room[2][1]);
        room[2][1].connect("east",room[2][2]);
        room[2][2].connect("east",room[2][3]);
        
        room[2][0].connect("south",room[3][0]);
        room[2][2].connect("south",room[3][2]);
        
        room[3][1].connect("east",room[3][2]);
        room[3][2].connect("east",room[3][3]);
        
        Room totorosBaum = new Room(TB);
        room[3][3].connect("up",totorosBaum);
        
        
        
        
        
        // END_WORLD
        gameStatus = new GameStatus(room[0][0]);
    }

    private Room[][] buildRooms(String[][]names){
        Room[][] result = new Room[names.length][];
        for(int i = 0;i<names.length; i++){
            result[i] = new Room[names[i].length];
            for(int j=0;j<names[i].length;j++){
                result[i][j] = new Room(names[i][j]);
            }
        }
        return result;
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

    public static void playInstantly(){new Game().play();}

}
