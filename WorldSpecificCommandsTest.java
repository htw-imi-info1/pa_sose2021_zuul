
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
        assertTrue( actual.contains("You are on a street"));
        assertTrue( actual.contains("east"));
    }

    @Test
    public void showExits(){
        game.processCommand("go east");
        String result = game.processCommand("go south");
        assertTrue(result.contains("Exits:"));
        assertTrue(result.contains("east"));
        assertTrue(result.contains("north"));
        assertTrue(result.contains("west"));
    }

}
