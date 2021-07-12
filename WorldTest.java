
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
    private final String WI = "Wiese";
    private final String WA = "Wald";
    private final String MW = "magischen Wald";
    private final String RE = "Reisfeld";
    private final String TB = "Totoros Baum";
    /**
     * Adapt this to your new world
     */
    @Test
    public void testBranch1()
    {
        String[][] path = {{"east",WI},
                {"east",WI},
                {"east",WA},
                {"south",MW},
                {"west",WA}};
        walkBranch("branch 1",path);
    }

    @Test
    public void testBranch2()
    {
        String[][] path = {{"south",RE},
                {"east",WI},
                {"south",RE},
                {"east",MW},
                {"east",MW}};
        walkBranch("branch 2",path);
    }

    @Test
    public void testBranch3()
    {
        String[][] path = {{"south",RE},
                {"east",WI},
                {"south",RE},
                {"west",RE},
                {"south",RE}};
        walkBranch("branch 3",path);
    }

    @Test
    public void testBranch4()
    {
        String[][] path = {{"south",RE},
                {"east",WI},
                {"south",RE},
                {"east",MW},
                {"south",MW},
                {"west",WA}
            };
        walkBranch("branch 4",path);
    }

    @Test
    public void testBranch5()
    {
        String[][] path = {{"south",RE},
                {"east",WI},
                {"south",RE},
                {"east",MW},
                {"south",MW},
                {"east",MW},   {"up",TB}
            };
        walkBranch("branch 5",path);
    }

    private void walkBranch(String name, String[][]path){
        for(int i= 0;i<path.length; i++){
            goAndSee(name+" - step "+i,path[i][0],  path[i][1]);
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
