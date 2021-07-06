
import java.util.*;
import java.util.stream.Collectors;
public class Exits implements ExitsInterface
{
    private Map<String,Room> exits = new HashMap<>();
    public String getDescription(){
        return exits.keySet().stream().collect(Collectors.joining(","));
    }

    public Room get(String d){
        return exits.get(d);
    }

    public void add(String d, Room r){exits.put(d,r);}

}
