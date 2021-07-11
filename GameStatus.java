
/**
 * uncomment the implements part if you want the compiler to help
 * with the correct method signatures.
 */
public class GameStatus
{
    private Room currentRoom;
    
    public GameStatus(Room initialRoom){
        this.currentRoom = initialRoom;
    }
    public void setLocation(Room room){
        currentRoom = room;
    }
    public Room getLocation(){
        return currentRoom;
    }
    public String getLocationDescription(){
        return "You are " +currentRoom.getDescription();
    }
    
    private boolean playing = true;
    public void quit(){playing = false;}
    public boolean isPlaying(){return playing;}
    
}
