
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
    public void completeWalkthroughRowsManual()
    {
        goAndSee("1","east",  "wasteland");
        goAndSee("2","east",  "wasteland");
        goAndSee("3","east",  "wasteland");
        goAndSee("4","east",  "no door");
        goAndSee("5","south",  "wasteland");
        goAndSee("6","west",  "wasteland");
        goAndSee("7","west",  "wasteland");
        goAndSee("8","west",  "wasteland");
        goAndSee("9","south",  "wasteland");
        goAndSee("10","east",  "wasteland");
        goAndSee("11","east",  "wasteland");
        goAndSee("12","east",  "wasteland");
    }

    @Test
    public void completeWalkthroughColsManual()
    {
        goAndSee("1","south",  "wasteland");
        goAndSee("2","south",  "wasteland");
        goAndSee("3","south",  "no door");
        goAndSee("4","east",  "wasteland");
        goAndSee("5","north",  "wasteland");
        goAndSee("6","north",  "wasteland");
        goAndSee("7","north",  "no door");
        goAndSee("8","east",  "wasteland");
        goAndSee("9","south",  "wasteland");
        goAndSee("10","south",  "wasteland");
    }

    @Test
    public void findRocket()
    {
        /*
         *   -> W - W - W  - W
         *      |   |   |    |
         *      W - W - W* - W
         *      |   |   |    |
         *      W - W - W  - W
         */
        goAndSee("1","south",  "wasteland");
        goAndSee("4","east",  "wasteland");
        goAndSee("4","east",  "wasteland");
        goAndSee("5","up",  "rocket");
        goAndSee("2","down",  "wasteland");
        goAndSee("4","east",  "wasteland");
        goAndSee("4","south",  "wasteland");

        //should be in the southweast corner now; check by checking the exits
        String output = game.processCommand("look");
        assertTrue(output.contains("north"));
        assertTrue(output.contains("west"));
        assertFalse(output.contains("south"));
        assertFalse(output.contains("east"));

    }

    /**
     * helper method for completeWalkthrough - no changes needed
     */
    private void goAndSee(String direction, String whatShouldBeContained){
        goAndSee("",direction,whatShouldBeContained);
    }

    private void goAndSee(String marker,String direction, String whatShouldBeContained){
        //when
        String result = game.processCommand("go "+direction);
        //then
        if (!result.contains(whatShouldBeContained))
            fail(marker+result +" does not contain "+whatShouldBeContained);
    }

}
