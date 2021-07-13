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
        goAndSee("east", "auf einem schmalen Pfad"); //pfad2);
        goAndSee("north", "an Balus Ufer"); //balusUfer);
        goAndSee("south", "auf einem schmalen Pfad"); //pfad2);
        goAndSee("east", "auf einem schmalen Pfad"); //pfad3);
        goAndSee("east", "auf einem schmalen Pfad"); //pfad4));
        goAndSee("east", "auf einem schmalen Pfad"); //pfad5);
        goAndSee("north", "auf dem Uebungsplatz der Elefantenkompanie"); //uebungsplatzElefanten);
        goAndSee("south", "auf einem schmalen Pfad"); //pfad5);
        goAndSee("south", "auf einem schmalen Pfad"); //pfad6);
        goAndSee("east", "unter Kaas Baum");//kaasBaum);
        goAndSee("west", "auf einem schmalen Pfad"); //pfad6);
        goAndSee("west", "auf einem schmalen Pfad"); //pfad7);
        goAndSee("west", "auf einem schmalen Pfad"); //pfad8);
        goAndSee("south", "auf einem schmalen Pfad"); //pfad9);
        goAndSee("west", "in Baghiras Höhle"); //baghirasHoehle);
        goAndSee("east", "auf einem schmalen Pfad"); //pfad9);
        goAndSee("up", "auf einem Berg"); //berg);
        goAndSee("east", "in der Affenstadt"); //affenstadt);
        goAndSee("west", "auf einem Berg"); //berg);
        goAndSee("down", "auf einem schmalen Pfad"); //pfad9);
        goAndSee("north", "auf einem schmalen Pfad"); //pfad8);
        goAndSee("east", "auf einem schmalen Pfad"); //pfad7);
        goAndSee("east", "auf einem schmalen Pfad"); //pfad6);
        goAndSee("north", "auf einem schmalen Pfad"); //pfad5);
        goAndSee("west", "auf einem schmalen Pfad"); //pfad4);
        goAndSee("west", "auf einem schmalen Pfad"); //pfad3);
        goAndSee("west", "auf einem schmalen Pfad"); //pfad2);
        goAndSee("west", "auf einem schmalen Pfad"); //pfad1);


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
