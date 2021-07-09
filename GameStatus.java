
/**
 * uncomment the implements part if you want the compiler to help
 * with the correct method signatures.
 */
public class GameStatus // implements GameStatusInterface
{ 
    private Room currentRoom;
    private Room lastRoom;
    
    public GameStatus(Room initialRoom){
        currentRoom = initialRoom;
    }
    
    public String handleBackCommand(Command command){
        if (lastRoom == null) return "I don't remember where you came from!";
        currentRoom = lastRoom; lastRoom = null;
        return currentRoom.getDescription();
    }
    public String handleGoCommand(Command command){
    
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
      
        String result = "";
        if (nextRoom == null) {
            result += "There is no door!";
        }
        else {
            lastRoom = currentRoom;
            currentRoom = nextRoom;
            result += currentRoom.getDescription();
        }
        return result + "\n";
    }
    public String getLocationDescription(){return currentRoom.getDescription();}
}
