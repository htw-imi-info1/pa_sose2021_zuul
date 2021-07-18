
public class Item
{
    String description, name;
    int weight;
    public Item(String name, String description, int weight){
        this.name = name;
        this.description = description; 
        this.weight = weight;
    }
    public String getDescription(){
        return description +" ("+name+")";
    }
}
