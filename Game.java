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
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        pub.addItem(new Item("warm milk",300));
        lab = new Room("in a computing lab");
        lab.addItem(new Item("univac manual",1000));
        office = new Room("in the computing admin office");
        Room cellar = new Room("in a dark damp cellar");

        // initialise room exits
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);
        pub.setExit("down", cellar);
        cellar.setExit("up", pub);
        
        gameStatus = new GameStatus(outside);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            String output = processCommand(command);
            //String output = command.process(gameStatus);
            finished = (null == output);
            if (!finished)
            {
                System.out.println(output);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
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
        return processCommand(command);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(gameStatus.getLocationDescription());
       
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private String processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            return "I don't know what you mean...";       
        }
        String result = null;
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            result = printHelp();
        }
        else if (commandWord.equals("go")) {
            result = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            result = quit(command);
        }
        else if (commandWord.equals("look")) {
            result = gameStatus.getLocationDescription();
        }
        else if (commandWord.equals("read")) {
            result = "You are reading The House in the Cerulean Sea.";
        }
        return result ;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private String printHelp() 
    {
        return "You are lost. You are alone. You wander"
        +"\n"
        + "around at the university."
        +"\n"
        +"\n"
        +"Your command words are:"
        +"\n"
        +"   go quit look read help"
        +"\n";
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private String goRoom(Command command) 
    {
        return gameStatus.handleGoCommand(command);
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private String quit(Command command) 
    {
        if(command.hasSecondWord()) {
            return "Quit what?";
        }
        else {
            return null;  // signal that we want to quit
        }
    }
}
