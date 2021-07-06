

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
        java.lang.String expected = game.processCommand("look");
        assertTrue( expected.contains("You are outside the main entrance"));
         assertTrue( expected.contains("west"));
    }
}

