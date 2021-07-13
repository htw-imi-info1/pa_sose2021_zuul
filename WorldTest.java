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
        goAndSee("east", "street"); //strasse2);
        goAndSee("south", "street"); //strasse4);
        goAndSee("west", "office 1"); //zeitsparkasse1);
        goAndSee("east", "street"); //strasse4);
        goAndSee("east", "street"); //strasse5);
        goAndSee("east", "street"); //strasse6);
        goAndSee("east", "street"); //strasse7);
        goAndSee("north", "street"); //strasse3);
        goAndSee("west", "amphitheater"); //amphitheater);
        goAndSee("east", "street"); //strasse3);
        goAndSee("south", "street"); //strasse7);
        goAndSee("south", "street"); //strasse9);
        goAndSee("west", "office 2"); //zeitsparkasse2);
        goAndSee("east", "street"); //strasse9);
        goAndSee("east", "street"); //strasse10);
        goAndSee("south", "street"); //strasse15);
        goAndSee("west", "street"); //strasse14);
        goAndSee("west", "street"); //strasse13);
        goAndSee("west", "street"); //strasse12);
        goAndSee("west", "street"); //strasse11);
        goAndSee("north", "street"); //strasse8);
        goAndSee("west", "office 3"); //zzeitsparkasse3);
        goAndSee("east", "street"); //strasse8);
        goAndSee("south", "street"); //strasse11);
        goAndSee("east", "street"); //strasse12);
        goAndSee("south", "street"); //strasse18);
        goAndSee("west", "street"); //strasse17);
        goAndSee("west", "street"); //strasse16);
        goAndSee("north", "office 4"); //zzeitsparkasse4);
        goAndSee("south", "street"); //strasse16);
        goAndSee("east", "street"); //strasse17);
        goAndSee("east", "street"); //strasse18);
        goAndSee("east", "street"); //strasse19);
        goAndSee("east", "street"); //strasse20);
        goAndSee("east", "office 5"); //zzeitsparkasse5);
        goAndSee("west", "street"); //strasse20);
        goAndSee("west", "street"); //strasse19);
        goAndSee("south", "street"); //strasse22);
        goAndSee("west", "street"); //strasse21);
        goAndSee("west", "Niemalsgasse"); //zniemalsgasse);
        goAndSee("backwards", "Nirgendhaus"); //znirgendhaus);

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
