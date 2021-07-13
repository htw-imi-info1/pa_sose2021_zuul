
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
        assertTrue(actual+" did not contain ", actual.contains("in einem schmalen Korridor"));
        assertTrue( actual.contains("south"));
    }

    @Test
    public void showExits(){
        String result = game.processCommand("go south");
        assertTrue(result.contains("Exits:"));
        assertTrue(result.contains("east"));
        assertTrue(result.contains("west"));
        assertTrue(result.contains("north"));
    }

}
