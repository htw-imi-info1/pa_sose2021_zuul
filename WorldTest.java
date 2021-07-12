
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
        // first row
        goAndSee("11","east",  "Staubwüste");
        goAndSee("12","east",  "Staubwüste");
        goAndSee("13","east",  "Staubwüste");
        goAndSee("1x","east",  "no door");

        goAndSee("12_2","west",  "Staubwüste");
        goAndSee("11_2","west",  "Staubwüste");
        goAndSee("10_2","west",  "Staubwüste");
        goAndSee("1x_2","west",  "no door");

        // second row
        goAndSee("20","south",  "Staubwüste");
        goAndSee("21","east",  "Krater");
        goAndSee("22","east",  "Berg");
        goAndSee("23","east",  "Staubwüste");
        goAndSee("2x","east",  "no door");

        goAndSee("21_2","west",  "Berg");
        goAndSee("21_2","west",  "Krater");
        goAndSee("20_2","west",  "Staubwüste");
        goAndSee("2x_2","west",  "no door");

        // third row
        goAndSee("30","south",  "Staubwüste");
        goAndSee("31","east",  "Staubwüste");
        goAndSee("32","east",  "Staubwüste");
        goAndSee("32_1","down",  "Mauseloch");
        goAndSee("32_2","up",  "Staubwüste");
        goAndSee("33","east",  "Berg");
        goAndSee("3x","east",  "no door");

        goAndSee("32_2","west",  "Staubwüste");
        goAndSee("31_2","west",  "Staubwüste");
        goAndSee("30_2","west",  "Staubwüste");
        goAndSee("3x_2","west",  "no door");

        // forth row
        goAndSee("40","south",  "Staubwüste");
        goAndSee("41","east",  "Berg");
        goAndSee("42","east",  "Staubwüste");
        goAndSee("42_r","up",  "Rakete");
        goAndSee("42_2","down",  "Staubwüste");
        goAndSee("43","east",  "Staubwüste");
        goAndSee("4x","east",  "no door");

        goAndSee("42_2","west",  "Staubwüste");
        goAndSee("41_2","west",  "Berg");
        goAndSee("40_2","west",  "Staubwüste");
        goAndSee("4x_2","west",  "no door");

    }

    /**
     * helper method for completeWalkthrough - no changes needed
     */
    private void goAndSee(String marker,String direction, String whatShouldBeContained){
        //when
        String result = game.processCommand("go "+direction);
        //then
        if (!result.contains(whatShouldBeContained))
            fail(marker + result +" does not contain "+whatShouldBeContained);
    }

}
