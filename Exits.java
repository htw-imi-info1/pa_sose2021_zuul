import java.util.*;
public class Exits 
{
    Map<String,Room> exits = new HashMap<>();
    public void add(String direction, Room room){exits.put(direction,room);}
    public Room get(String direction){return exits.get(direction);}
    public String getDescription(){
        String result = "Exits: ";
        for(String d : exits.keySet()){
               result += d + " ";    
            }    
        return result;
    }
}
