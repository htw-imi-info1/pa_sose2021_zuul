
public class Look extends Command
{
    public Look(String parameters){
        super(parameters);
    }
    public String process(GameStatus gameStatus){
        return gameStatus.getLocationDescription();
    }
}
