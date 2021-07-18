
/**
 * uncomment the implements part if you want the compiler to help
 * with the correct method signatures.
 */
import java.util.*;
public class GameStatus
{
    private Room currentRoom;
    private Room lastRoom;
    private Room beamer;
    private Set<Room> visited;
    private int numberOfRooms;
    private boolean seenAll = false;

    public GameStatus(Room initialRoom){
        setLocation(initialRoom);
    }

    public void setLocation(Room room){
        lastRoom = currentRoom;
        currentRoom = room;
    }

    public Room getLocation(){
        return currentRoom;
    }

    public boolean goBack(){
        if (lastRoom == null)
            return false;
        currentRoom = lastRoom;
        lastRoom = null;
        return true;
    }

    public void chargeBeamer(){
        beamer = currentRoom;
    }

    public boolean fireBeamer(){
        if (beamer == null) return false;
        setLocation(beamer);
        beamer = null;
        return false;
    }

    public String getLocationDescription(){
        return "Du bist " +currentRoom.getDescription();
    }

    private boolean playing = true;
    public void quit(){playing = false;}

    public boolean isPlaying(){return playing;}

}
