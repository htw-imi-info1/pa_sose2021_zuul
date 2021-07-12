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
    World:
    - Staubwüste
    - Krater
    - Berg
    - Mauseloch

     * -> s - s - s    - s
     *    |   |   |      |
     *    s - K - B    - s
     *    |   |   |      |
     *    s - s - s(M) - B
     *    |   |   |      |
     *    s - B - s(R) - s

    Goal: Käse finden und zur Rakete bringen bevor ihn die Mondmäuse finden

    up/down: Rakete  (über Staubwüste s*, nicht über Krater oder Berg), Mauseloch

    Items:
    - Schweizer Käse
    - Gouda
    - Edammer
    - Gogonzola
    - Mausefalle

     */
    private void createRooms()
    {
        // START_WORLD
        welcomeString =   
        "Welcome to 'Maus im Mond'!\n"+
        "Maus im Mond is a new, incredibly boring adventure game.\n"+
        "Type 'help' if you need help.\n"; 
        String[] row1 = {"in einer Staubwüste 1","in einer Staubwüste 2","in einer Staubwüste 3","in einer Staubwüste 4"};
        String[] row2 = {"in einer Staubwüste 5","in einem Krater","auf einem Berg","in einer Staubwüste 6"};
        String[] row3 = {"in einer Staubwüste 7","in einer Staubwüste 8","in einer Staubwüste 9","auf einem Berg"};
        String[] row4 = {"in einer Staubwüste 10","Berg","in einer Staubwüste 11","in einer Staubwüste 12"};
        Room firstRoom = buildRow(null, row1 );
        Room initialRoom = firstRoom;

        firstRoom = buildRow(firstRoom, row2);
        firstRoom = buildRow(firstRoom, row3);
        firstRoom = buildRow(firstRoom, row4);
       
        // hier ist das mauseloch
        Room location = initialRoom.getExit("east").getExit("east").getExit("south").getExit("south");
        Room newRoom = new Room("in einem kleinen Mauseloch");
        location.setExit("down",newRoom);
        newRoom.setExit("up",location);

        // hier ist die rakete

        location = location.getExit("south");

        newRoom = new Room("in einer silbernen Rakete");
        location.setExit("up",newRoom);
        newRoom.setExit("down",location);


        // END_WORLD

        gameStatus = new GameStatus(initialRoom);
    }

    private Room buildRow(Room upperRoom,String[]row){
        Room newRoom = null, cursor = upperRoom, lastRoom,firstRoom = null;

        for (int i = 0;i<row.length;i++){
            lastRoom = newRoom;
            newRoom = new Room(row[i]);
            if (i==0) {
                firstRoom = newRoom;
            }
            else{
                lastRoom.setExit("east",newRoom);
                newRoom.setExit("west",lastRoom);
            }
            if (cursor != null){
                // invariant: cursor has to be north of room[i]
                newRoom.setExit("north",cursor);
                cursor.setExit("south",newRoom);
                cursor = cursor.getExit("east");
            }
        }
        return firstRoom;
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
