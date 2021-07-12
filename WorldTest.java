
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

    final String Ko = "Korridor";
    final String Kl = "Klassenzimmer";
    final String W = "Raum der Wünsche";
    final String G = "Gemeinschaftsraum";
    final String GH = "Große Halle";
    final String TM = "Toilette der Maulenden Myrthe";
    final String B = "Bibliothek";
    final String T = "Treppe";
    /**
     * Adapt this to your new world
     */
    @Test
    public void testBranch1()
    {
        String[][] path = {{"east",Ko},
                {"east",Ko},{"east",Ko},
                {"east",Ko},
                {"south",Ko},{"south",Ko},{"south",Ko},

                {"east",G}};
        walkBranch("branch 1",path);
    }

    @Test
    public void testBranch2()
    {   String[][] path = {{"east",Ko},
                {"east",Ko},{"east",Ko},
                {"east",Ko},
                {"south",Ko},   
                {"east",TM}};

        walkBranch("branch 2",path);
    }

    @Test
    public void testBranch3()
    { 
        String[][] path = {{"east",Ko},
                {"east",Ko},{"east",Ko},
                {"east",Ko},

                {"up",T},{"up",Ko},{"east",W},
            };
        walkBranch("branch 3",path);
    }

    @Test
    public void testBranch4()
    {
        String[][] path = {{"east",Ko},
                {"east",Ko},{"east",Ko},
                {"east",Ko},

                {"up",T},{"east",B},
            };
        walkBranch("branch 4",path);
    }

    @Test
    public void testBranch5()
    {
        String[][] path = {{"east",Ko},
                {"east",Ko},{"north",GH}
            };
        walkBranch("branch 5",path);
    }

    @Test
    public void testBranch6()
    {
        String[][] path = {{"east",Ko},
                {"south",Kl}
            };
        walkBranch("branch 6",path);
    }

    private void walkBranch(String name, String[][]path){
        for(int i= 0;i<path.length; i++){
            goAndSee(name+" - step "+i+" ",path[i][0],  path[i][1]);
        }
        for(int i = path.length-1;i>0; i--){
            goAndSee(name+" - step "+i+" backwards",Room.reverse(path[i][0]),  path[i-1][1]);
        }}

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
