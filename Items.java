import java.util.*;
import java.util.stream.Collectors;
public class Items
{
    List<Item> items = new ArrayList<>();
    public void add(Item i){items.add(i);};
   
      public String getDescription(){
        if (items.isEmpty()) return "";
        return "Items:\n" + items.stream().map(i -> " - "+i.getDescription()).collect(Collectors.joining("\n"));
    }
}
