/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    
    private String description;
    private Exits exits = new Exits();


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction, Room room){exits.add(direction,room);};
    
    @Deprecated
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null) {
           setExit("north",north);
        }
        if(east != null) {
            setExit("east",east);
        }
        if(south != null) {
            setExit("south",south);
        }
        if(west != null) {
            setExit("west",west);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return "You are " + description 
        +"\n" + exits.getDescription();
         
    }
    
    public Room getExit(String direction){return exits.get(direction);}

}
