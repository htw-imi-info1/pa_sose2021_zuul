
public class Go extends Command
{
    public Go(String parameter){
        super(parameter);
    }

    @Override
    public String process(GameStatus gameStatus){

        if(!hasParameter()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";
        }

        String direction = getParameter();

        // Try to leave current room.
        Room nextRoom = gameStatus.getLocation().getExit(direction);

        if (nextRoom == null) {
            return"There is no door!";
        }
        else {
            gameStatus.setLocation(nextRoom);
            return gameStatus.getLocationDescription();
        }
    }
}
