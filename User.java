import java.util.ArrayList;

/**
 * Thsi class will hold all details of user
 *
 * @Iffat Sara Siddick
 * @1
 */
public class User
{
    private int currentWeight;
    private int maxWeight;
    private static ArrayList<Item>holdingItems = new ArrayList<>();

    /**
     * Constructor for objects of class User
     */
    public User(int maxWeight)
    {
        this.maxWeight = maxWeight;
        currentWeight = 0;
    }
    
    /**
     * This method returns the users maximum weight limit
     * 
     * @return maximum weight
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }
    
    /**
     * This method keeps track of the users current weight when it increases
     * 
     * @param int weight
     */
    public void setCurrentWeightIncrease(int weight)
    {
        currentWeight += weight;
    }
    
    /**
     * This method keeps track of the users current weight when it lowers
     * 
     * @param int weight
     */
    public void setCurrentWeightDecrease(int weight)
    {
        currentWeight -= weight;
    }
    
    /**
     * This method returns the users current weight
     * 
     * @return current weight
     */
    public int getCurrentWeight()
    {
        return currentWeight;
    }
    
    /**
     * This method keeps track of the users current weight when it lowers
     * 
     * @param 
     */
    public void setHoldingItems(Item item)
    {
        holdingItems.add(item);
    }
    
    /**
     * This method keeps track of the users current weight when it lowers
     * 
     * @param 
     */
    public void removeFromHoldingItems(Item item)
    {
        holdingItems.remove(item);
    }
    
    /**
     * This method returns the users current weight
     * 
     * @return current weight
     */
    public ArrayList<Item> getHoldingItems()
    {
        return holdingItems;
    }
    
    /**
     * This method checks if the user holding any item
     *
     * @return boolean
     */
    public boolean holdingAnItem(Room currentRoom)
    {   
        if(holdingItems.size() == 0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Isnt currently being used at all
     * This methods checks if the user is holding a specific item
     * 
     * @return boolean
     */
    public boolean hasItem(Item item)
    {
        boolean found = false;
        int i = 0;
        while(found == false && i < holdingItems.size()) {
            if(item == holdingItems.get(i)) {
                found = true;;
            }
            else {
                i ++;
            }
        }
        return found;
    }
}
