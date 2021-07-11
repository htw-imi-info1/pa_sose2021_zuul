
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandTest
{
    Game game ;
    /**
     * Default constructor for test class CommandTest
     */
    public CommandTest()
    {
        game = new Game();    
    }

    @Test
    public void testReadCommand(){
        String result = game.processCommand("read");
        assertEquals("You are reading a book\n",result);
    }

    @Test
    public void testLook()
    {
        String actual = game.processCommand("look");
        assertTrue( actual.contains("You are outside the main entrance"));
        assertTrue( actual.contains("west"));
    }

    @Test
    public void testHelp()
    {
        String actual = game.processCommand("help");
        assertTrue( actual.contains("Your command words are:"));
        assertTrue( actual.contains("go quit help"));

    }

    @Test
    public void testUnknown(){
        assertEquals("I don't know what you mean...\n",game.processCommand("murks"));
    }

    @Test
    public void testQuit(){

        assertEquals("Quit what?",game.processCommand("quit not really"));
        assertEquals("Thank you for playing.  Good bye.\n",game.processCommand("quit"));
    }
}

