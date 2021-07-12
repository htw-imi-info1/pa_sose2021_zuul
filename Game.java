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
     *# 5. Harry Potter

    World:
    - Korridor (Ko)
    - Klassenzimmer (Kl)
    - Raum der Wünsche (W)
    - Gemeinschaftsraum (G)
    - Große Halle (GH)
    - Toilette der Maulenden Myrthe (TM)
    - Bibliothek (B)
    - Treppe (T)

     *
     *                       Ko - W
     *                       |
     *             GH        T  - B
     *             |         |
     *-> Ko - Ko - Ko - Ko - Ko
     *        |              |
     *        Kl             Ko - TM
     *                       |
     *                       Ko
     *                       |
     *                       Ko - G
     *                       
    Nummerierte Räume für das Array:

     *                           Ko6 - W(10)
     *                           |up
     *                GH(11)     T  - B(12)
     *                |          |up
     *-> Ko1 - Ko2 - Ko3 - Ko4 - Ko5
     *           |               |
     *           Kl(13)          Ko7 - TM(14)
     *                           |
     *                           Ko8
     *                           |
     *                           Ko9 - G(15)

    Goal: Loona Lovegoods Sachen zurück in den Gemeinschaftsraum bringen

    up/down: Treppe

    Items:
    - Spulenwurzeln
    - Lenkpflaumen
    - Radischen Ohrringe
    - Butterbierkorken-Halsband
    - Klitterer
    - Löwenhut
     */
    private void createRooms()
    {
        // START_WORLD
        welcomeString =   
        "Welcome to Hogwarts!\n"+
        "This is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n"; 
        final String Ko = "im Korridor";
        final String Kl = "im Klassenzimmer";
        final String W = "im Raum der Wünsche";
        final String G = "im Gemeinschaftsraum";
        final String GH = "in der Große Halle";
        final String TM = "in der Toilette der Maulenden Myrthe";
        final String B = "in der Bibliothek";
        final String T = "auf der Treppe";
        /*
         *                           Ko6 - W(10)
         *                           |
         *                GH(11)     T(0)  - B(12)
         *                |          |
         *-> Ko1 - Ko2 - Ko3 - Ko4 - Ko5
         *           |               |
         *           Kl(13)          Ko7 - TM(14)
         *                           |
         *                           Ko8
         *                           |
         *                           Ko9 - G(15)
         *                           */

        Room[] room = new Room[16];
        for(int i=1;i<= 9;i++)room[i] = new Room(Ko+" ("+i+")");
        room[10] = new Room(W);
        room[11] = new Room(GH);
        room[12] = new Room(B);
        room[13] = new Room(Kl);
        room[14] = new Room(TM);
        room[15] = new Room(G);
        room[0] = new Room(T);

        room[1].connect("east",room[2]);
        room[2].connect("east",room[3]);
        room[2].connect("south",room[13]);
        room[3].connect("east",room[4]);
        room[3].connect("north",room[11]);
        room[4].connect("east",room[5]);

        room[5].connect("up",room[0]);
        room[0].connect("up",room[6]);
        room[0].connect("east",room[12]);
        room[6].connect("east",room[10]);

        room[5].connect("south",room[7]);
        room[7].connect("east",room[14]);
        room[7].connect("south",room[8]);

        room[8].connect("south",room[9]);

        room[9].connect("east",room[15]);

        // END_WORLD
        gameStatus = new GameStatus(room[1]);
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
