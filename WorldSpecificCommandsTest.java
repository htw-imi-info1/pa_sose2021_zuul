
import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author  Barne Kleinen
 */
public class WorldSpecificCommandsTest
{
    private Game game;

    @Before
    public void setUp()
    {
        game = new Game();
    }

    @Test
    public void testLook()
    {
        String actual = game.processCommand("look");
        assertTrue( actual.contains("You are outside the main entrance"));
        assertTrue( actual.contains("west"));
    }

    @Test
    public void showExits(){
        game.processCommand("go south");
        String result = game.processCommand("go north");
        assertTrue(result.contains("Exits:"));
        assertTrue(result.contains("east"));
        assertTrue(result.contains("south"));
        assertTrue(result.contains("west"));
        assertFalse(result.contains("north"));
    }

}
