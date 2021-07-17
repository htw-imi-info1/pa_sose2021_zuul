
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.Before;

public class ItemsTest 
{
    Game game;

    @Before
    public void setUp(){
        game = new Game();
    }

    @Test
    public void testMilkInThePub()
    {
        String output = game.processCommand("go west");
        //   assertTrue(output.contains("a bottle of milk")); // milk in the pub
        //  assertTrue(output.contains("cacao")); // cacao in the pub
    }

    @Test
    public void testNothing()
    {

        game.processCommand("go west");
        String output = game.processCommand("go east");
        //     assertFalse(output.contains("Item"));  // no empty item list
    }

    @Test
    public void testManualInTheLab()
    {
        //   goAndSee("south", "manual of the Java"); // manual in the office

    }
}
