import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author  Barne Kleinen
 */
public class WorldTest
{
    private Game game;

    public WorldTest()
    {

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        game = new Game();
    }

    /**
     * This test should keep working
     */
    @Test
    public void goWODirectionShouldShowError()
    {
        assertEquals("Go where?", game.processCommand("go"));
    }
    /**
     * Adapt this to your new world
     */
    @Test
    public void completeWalkthrough()
    {
        goAndSee("east", "auf einem Wiesenweg"); //wiese2);
        goAndSee("east", "auf einem Wiesenweg"); //wiese3);
        goAndSee("down", "in einer grünen Röhre"); //roehre1);
        goAndSee("down", "in einer dunklen Grotte"); //grotte1);
        goAndSee("up", "in einer grünen Röhre"); //roehre1);
        goAndSee("up", "auf einem Wiesenweg"); //wiese3);
        goAndSee("east", "auf einem Wiesenweg"); //wiese4);
        goAndSee("south", "auf einem Wiesenweg"); //wiese5);
        goAndSee("down", "in einer grünen Röhre"); //roehre2);
        goAndSee("down", "in einer dunklen Grotte"); //grotte2);
        goAndSee("up", "in einer grünen Röhre"); //roehre2);
        goAndSee("up", "auf einem Wiesenweg"); //wiese5);
        goAndSee("west", "auf einem Wiesenweg"); //wiese6);
        goAndSee("west", "auf einem Wiesenweg"); //wiese7);
        goAndSee("down", "in einer grünen Röhre"); //roehre3);
        goAndSee("down", "in einer dunklen Grotte"); //grotte3);
        goAndSee("up", "in einer grünen Röhre"); //roehre3);
        goAndSee("up", "auf einem Wiesenweg"); //wiese7);
        goAndSee("south", "auf einem Wiesenweg"); //wiese8);
        goAndSee("west", "auf einem Wiesenweg"); //wiese9);
        goAndSee("west", "in einem Schloss"); //schloss);
        goAndSee("east", "auf einem Wiesenweg"); //wiese9);
        goAndSee("east", "auf einem Wiesenweg"); //wiese8);
        goAndSee("north", "auf einem Wiesenweg"); //wiese7);
        goAndSee("east", "auf einem Wiesenweg"); //wiese6);
        goAndSee("east", "auf einem Wiesenweg"); //wiese5);
        goAndSee("north", "auf einem Wiesenweg"); //wiese4);
        goAndSee("west", "auf einem Wiesenweg"); //wiese3);
        goAndSee("west", "auf einem Wiesenweg"); //wiese2);
        goAndSee("west", "auf einem Wiesenweg"); //wiese1);

    }
    /**
     * helper method for completeWalkthrough - no changes needed
     */
    private void goAndSee(String direction, String whatShouldBeContained){
        //when
        String result = game.processCommand("go "+direction);
        //then
        if (!result.contains(whatShouldBeContained))
            fail(result +" does not contain "+whatShouldBeContained);
    }



}
