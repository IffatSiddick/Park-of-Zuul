import java.util.ArrayList;

/**
 * This will instantiate an item of the game
 * Some items can be picked up while some can't.
 *
 * @Iffat Siddick
 * @1.1
 */
public class Item
{   
    private String name;
    private static ArrayList<Item>items = new ArrayList<>();
    private boolean pickable;
    private boolean pickedup;
    private int weight;
    private Room currentRoom;
    
    /**
     * Constructor for objects of class Item
     * Creates an object
     * 
     * @param
     */
    public Item(Room startRoom)
    {
        boolean pickedup = false;
        currentRoom = startRoom;
    }
    
    /**
     * Adds item to room
     * will need to change this
     */
    public void setItemProperties(String name, boolean pickedup, int weight) 
    /** Character character **/
    {
        this.name = name;
        setPickable(pickedup);
        this.weight = weight;
        setItems(this);
    }
    
    /**
     * This method sets the array
     */
    public static void setItems(Item item)
    {
        items.add(item);
    }
    
    /**
     * This method returns array of item names
     */
    public static ArrayList getItems()
    {
        return items;
    }
    
    /**
     * This method returns a specific item in the array
     * 
     * @return item
     */
    public Item getItem(String input)
    {
        boolean found = false;
        int i = 0;
        while(found == false && i < getItems().size()) {
            if(input.equals(items.get(i).name)) {
                found = true;
            }
            else {
                i ++;
            }
        }
        return items.get(i);
    }
    
    /**
     * This method defines the name of the item 
     * and adds it to the list of items in the game
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * This method returns the name of the item
     * 
     * @return name of item
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * This method sets whether the item can be picked up by the user or not
     */
    public void setPickable(boolean choice)
    {
        pickable = choice;
    }
    
    /**
     * This returns is the item can be picked up or not
     *
     * @return value of variable pickable
     */
    public boolean getPickable()
    {
        return pickable;
    }
    
    /**
     * This method allows the user to pick up item
     * If it is pickable
     * 
     * Should probably be split into 2 methods
     * 
     * @ returns boolean that represents if user has picked up the item or not
     */
    public void setPickedup()
    {
        if (pickable) {
            pickedup = !pickedup;
        }
    }
    
    /**
     * This method returns if the item has been picke up or not
     * 
     * @return boolean representation of if item has been picked up or not
     */
    public boolean getPickedup()
    {
        return pickedup;
    }
    
    /**
     * This method sets whether the weight of the item
     */
    public void setWeight(int weight)
    {
        this.weight = weight;
    }
    
    /**
     * This returns is the weight of the item
     *
     * @return value of variable weight
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * This method tells us the room where the item is
     * It starts where the item is found
     * If picked up then room becomes current room as user moves around
     * Needs to becalled each time user moves room with an item
     */
    public void setRoom(Room currentRoom)
    {
         this.currentRoom = currentRoom; 
    }
     
    /**
     * This method tells us the room where the item is
     * It starts where the item is found
     * If picked up then room becomes current room as user moves around
     */ 
    public Room getRoom()
    {
         return currentRoom; 
    }
     
    /**
     * This method returns if the item is in a specific room
     * 
     * @return boolean
     */
    public boolean itemInRoom(Room currentRoom)
    {
        if(getRoom() == currentRoom) {
            return true;
        }
        return false;
    }
    
    /**
     * This method returns if the item is in a specific room
     * 
     * @return boolean
     */
    public void getItemInRoom(Room currentRoom)
    {   
        for(Item item:items) {
            if(item.getRoom() == currentRoom) {
                System.out.println("There is a " + item.getName() + ".");
            }
        }
    }
    
    /**
     * This method returns if the gievn input is an item
     */
    public boolean inputIsItem(String input)
    {
        boolean found = false;
        int i = 0;
        while(found == false && i < items.size()) {
            if(input.equals(items.get(i).name)) {
                found = true;
            }
            else {
                i ++;
            }
        }
        return found;
    }
}
