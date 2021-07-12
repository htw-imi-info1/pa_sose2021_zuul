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

    # 6. Spongebob

    World:
    - Krosse Krabbe (KK)
    - Der Abfalleimer (AE)
    - Patricks haus (PH)
    - Thaddäus haus (TH)
    - Spongebobs Ananas (SA)
    - Sandys Treedome (ST)
    - Schattige Shoals Altersheim (SSA)
    - Straße (S)

     *        KK          PH  TH  SA
     *        |           |   |   |
     * -> S - S - S - S - S - S - S
     *        |       |        
     *        AE      S - ST
     *                |
     *                S - S - S - S - SSA   

     *           KK(12)               PH(13) TH(14) SA(15)
     *           |                    |      |      |
     * -> S(0) - S(1) - S(2) - S(3) - S(4) - S(5) - S(6)
     *           |             |        
     *          AE(16)         S(7) - ST(17)
     *                         |
     *                         S(8) - S(9) - S(10) - S(11) - SSA(18)                

    Goal: Zutaten Einsammeln und zu Mr. Crabs in die Krosse Krabbe bringen

    up/down: Sandys Tree Dome als Luftschleuse hin und her

    Items:
    - Boulette
    - Brötchen
    - Salat
    - Zwiebeln
    - Tomaten
    - Käse
    - Eingelegte Gürkchen
    - Senf
    - Ketchup
    - Geheimzutat
    - Eine Priese Liebe

    -----
     */
    private void createRooms()
    {
        // START_WORLD
        welcomeString =   
        "Welcome to Sponge Bob!\n"+
        "This is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n"; 
        final String KK = "bei der Krossen Krabbe";
        final String AE = "beim Abfalleimer";
        final String PH = "in Patricks Haus";
        final String TH = "in Thaddäus Haus";
        final String SA = "bei Spongebobs Ananas";
        final String ST = "in Sandys Treedome";
        final String SSA = "im 'Schattige Shoals Altersheim'";
        final String S = "auf der Straße";
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

        Room[] room = new Room[19];
        for(int i=0;i<= 11;i++)room[i] = new Room(S+" ("+i+")");

        room[12] = new Room(KK);
        room[13] = new Room(PH);
        room[14] = new Room(TH);
        room[15] = new Room(SA);
        room[16] = new Room(AE);
        room[17] = new Room(ST);
        room[18] = new Room(SSA);

        // strassen

        room[0].connect("east",room[1]);
        room[1].connect("east",room[2]);
        room[2].connect("east",room[3]);
        room[3].connect("east",room[4]);
        room[4].connect("east",room[5]);
        room[5].connect("east",room[6]);

        room[3].connect("south",room[7]);
        room[7].connect("south",room[8]);

        room[8].connect("east",room[9]);
        room[9].connect("east",room[10]);
        room[10].connect("east",room[11]);

        // andere Räume

        room[1].connect("north",room[12]);
        room[4].connect("north",room[13]);
        room[5].connect("north",room[14]);
        room[6].connect("north",room[15]);
        
        
        room[1].connect("south",room[16]);
        
        room[7].connect("up",room[17]);
        room[11].connect("east",room[18]);

        // END_WORLD
        gameStatus = new GameStatus(room[0]);
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
