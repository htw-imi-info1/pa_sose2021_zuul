
import static org.junit.Assert.*;
import org.junit.Before;
public class ZuulTest
{
    Game game;
    
    @Before
    public void setUp()
    {
        game = new Game();
    }
     /**
     * helper method for completeWalkthrough - no changes needed
     */
     void goAndSee(String direction, String whatShouldBeContained){
        //when
        String result = game.processCommand("go "+direction);
        //then
        if (!result.contains(whatShouldBeContained))
            fail(result +" does not contain "+whatShouldBeContained);
    }
}
