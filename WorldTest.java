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


      goAndSee("south", "in einem schmalen Korridor"); //korridor2);
      goAndSee("west", "Quartier"); //quartier1);
      goAndSee("east", "in einem schmalen Korridor"); //korridor2);
      goAndSee("east", "in einem schmalen Korridor"); //korridor3);
      goAndSee("east", "in einem schmalen Korridor"); //korridor4);
      goAndSee("east", "in einem schmalen Korridor"); //korridor5);
      goAndSee("north", "Toilette"); //toilette1);
      goAndSee("south", "in einem schmalen Korridor"); //korridor5);
      goAndSee("east", "Zentrale"); //zentrale);
      goAndSee("west", "in einem schmalen Korridor"); //korridor5);
      goAndSee("south", "in einem schmalen Korridor"); //korridor6);
      goAndSee("east", "Quartier"); //quartier2);
      goAndSee("west", "in einem schmalen Korridor"); //korridor6);
      goAndSee("west", "in einem schmalen Korridor"); //korridor7);
      goAndSee("west", "in einem schmalen Korridor"); //korridor8);
      goAndSee("south", "in einem schmalen Korridor"); //korridor9);
      goAndSee("west", "Toilette"); //toilette2);
      goAndSee("east", "in einem schmalen Korridor"); //korridor9);
      goAndSee("east", "in einem schmalen Korridor"); //korridor10);
      goAndSee("east", "in einem schmalen Korridor"); //korridor11);
      goAndSee("east", "Quartier"); //quartier3);
      goAndSee("west", "in einem schmalen Korridor"); //korridor11);
      goAndSee("south", "in einem schmalen Korridor"); //korridor12);
      goAndSee("west", "in einem schmalen Korridor"); //korridor13);
      goAndSee("down", "Maschinenraum"); //maschinenraum);
      goAndSee("up", "in einem schmalen Korridor"); //korridor13);
      goAndSee("east", "in einem schmalen Korridor"); //korridor12);
      goAndSee("north", "in einem schmalen Korridor"); //korridor11);
      goAndSee("west", "in einem schmalen Korridor"); //korridor10);
      goAndSee("west", "in einem schmalen Korridor"); //korridor9);
      goAndSee("north", "in einem schmalen Korridor"); //korridor8);
      goAndSee("east", "in einem schmalen Korridor"); //korridor7);
      goAndSee("east", "in einem schmalen Korridor"); //korridor6);
      goAndSee("north", "in einem schmalen Korridor"); //korridor5);
      goAndSee("west", "in einem schmalen Korridor"); //korridor4);
      goAndSee("west", "in einem schmalen Korridor"); //korridor3);
      goAndSee("west", "in einem schmalen Korridor"); //korridor2);
      goAndSee("north", "in einem schmalen Korridor"); //korridor1);

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
