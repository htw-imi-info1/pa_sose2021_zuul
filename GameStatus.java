
/**
 * uncomment the implements part if you want the compiler to help
 * with the correct method signatures.
 */
public class GameStatus implements GameStatusInterface
{
    private Room currentRoom;
    public GameStatus(Room initialRoom){
        this.currentRoom = initialRoom;
    }
    public void setLocation(Room room){
        currentRoom = room;
    }
    public String getLocationDescription(){
        return "You are " +currentRoom.getDescription();
    }
    public String handleGoCommand(Command command){
    if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            return"There is no door!";
        }
        else {
            currentRoom = nextRoom;
            return getLocationDescription();
           
        }
    }
}
