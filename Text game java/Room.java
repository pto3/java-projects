import java.util.HashMap;
/**
 * Write a description of class Room here.
 * Parker O'Brien
 * 
 */
public class Room
{
    
    private String description;
    private Item roomItem;
    private HashMap <String, Room> movements;

    /**
     * Constructor for objects of class Room
     */
    public Room(String d, Item i)
    {
        this.description = d;
        this.roomItem = i;
        movements = new HashMap<String, Room>();
    }
    
    
    // get & set methods
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String d)
    {
        this.description = d;
    }

    public Item getItem()
    {
        return roomItem;
    }

    public HashMap getMovements()
    {
        return movements;
    }

    public void addItem(Item i)
    {
        this.roomItem = i;
    }

    // check to see if a room has an item
    public boolean hasItem()
    {
        if (roomItem != null){
            return true;
        }
        else {
            return false;
        }
    }

    //neighbouring rooms
    public void addNeighbor(String dir, Room r)
    {
        this.movements.put(dir, r);
    }

    public Room getNeighbor(String dir)
    {
        Room neighbor = this.movements.get(dir);
        return neighbor;
    }

    public Item removeItem()
    {
        this.roomItem = null;
        return roomItem;
    }

    //shows a rooms description. Received help from success center.
    public String getLongDescription()
    {
        if (hasItem()){
            String longDescription = "You are in" + description + ". You see " + roomItem.getDescription() + ".";
            return longDescription + "\n";
        }
        else {
            String longDescription = "You are in" + description + ".";
            return longDescription + "\n";
        }
    }
}
