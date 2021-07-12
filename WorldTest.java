
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
     *   Metzger   Saloon
     *   |         |
     *-> S -- S -- S -- S -- S* -- Sonnenuntergang
     *        |
     *        Bank

     */
    @Test
    public void completeWalkthrough()
    {
        goAndSee("1","north",  "Metzger");
        goAndSee("2","south",  "Strasse");
        goAndSee("3","east",  "Strasse");
        goAndSee("4","south",  "Bank");
        goAndSee("5","north",  "Strasse");
        goAndSee("6","east",  "Strasse");
        goAndSee("7","north",  "Saloon");
        goAndSee("8","south",  "Strasse");
        goAndSee("9","east",  "Strasse");
        goAndSee("10","east",  "Strasse");
        goAndSee("11","down",  "Bergwerk");
        goAndSee("12","up",  "Strasse");
        goAndSee("13","east",  "Sonnenuntergang");
        goAndSee("west",  "Strasse");
        goAndSee("west",  "Strasse");
        goAndSee("west",  "Strasse");
        goAndSee("west",  "Strasse");
        goAndSee("west",  "Strasse");
        goAndSee("west",  "no door");
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
