
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

    final String KK = "Krossen Krabbe";
    final String AE = "Abfalleimer";
    final String PH = "Patricks Haus";
    final String TH = "Thaddäus Haus";
    final String SA = "Spongebobs Ananas";
    final String ST = "Sandys Treedome";
    final String SSA = "Schattige Shoals Altersheim";
    final String S = "Straße";
    /**
     * Adapt this to your new world
     */
    @Test
    public void testBranch1()
    {
        String[][] path = {
                {"east",S},{"east",S},{"east",S},{"east",S},{"east",S},
                {"north",TH}
            };
        walkBranch("branch 1",path);
    }

    @Test
    public void testBranch2()
    {   String[][] path = {
                {"east",S},{"east",S},{"east",S},{"east",S},{"east",S},{"east",S},
                {"north",SA}

            };
        walkBranch("branch 2",path);
    }

    @Test
    public void testBranch3()
    { 
        String[][] path = {
                {"east",S},{"east",S},{"east",S},{"east",S},
                {"north",PH}
            };
        walkBranch("branch 3",path);
    }

    @Test
    public void testBranch4()
    {
        String[][] path = {
                {"east",S},
                {"north",KK}
            };
        walkBranch("branch 4",path);
    }

    @Test
    public void testBranch5()
    {
        String[][] path = {
                {"east",S},
                {"south",AE}
            };
        walkBranch("branch 5",path);
    }

    @Test
    public void testBranch6()
    {
        String[][] path = {
                {"east",S},{"east",S},{"east",S},{"south",S},
                {"up",ST}
            };
        walkBranch("branch 6",path);
    }

    @Test
    public void testBranch7()
    {
        String[][] path = {
                {"east",S},{"east",S},{"east",S},{"south",S},{"south",S},
                {"east",S},{"east",S},{"east",S},
                {"east",SSA}
            };
        walkBranch("branch 7",path);
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
