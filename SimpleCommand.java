/**
 * A SimpleCommand just returns its output,
 * The gameStatus is never changed.
 */
public class SimpleCommand extends Command
{
    String output;
    public SimpleCommand(String parameters, String output){
        super(parameters);
        this.output = output;
    }
    public String process(GameStatus _gameStatus){
        return output;
    }
}
