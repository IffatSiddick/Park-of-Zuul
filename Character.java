import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * This a NPC, a non playable character.
 *
 * @Iffat
 * @1
 */
public class Character
{
    private String name;
    private String description;
    private Room currentRoom;
    private Item item;
    private boolean itemOwned;
    private static ArrayList<Character>characters = new ArrayList<>();

    /**
     * Constructor for objects of class Character
     */
    public Character()
    {
        itemOwned = false;
    }
    
    public void addProperties(String name, String description, Item item, Room room)
    {
        this.name = name;
        this.description = description;
        this.item = item;
        //sets characters start room
        currentRoom = room;
        characters.add(this);
    }
    
    /**
     * This method returns the name of the character
     * 
     * @return String name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * This method returns the description of the character
     * 
     * @return String name
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * This method returns the description of the character for the game
     * 
     * @return String name
     */
    public void getLongDescription()
    {   
        if(item == null) {
            System.out.println("There is a " + description + ". Their name is " + name + ".");
        }
        else if(itemOwned) {
            System.out.println(name + " looks happy with their " + item.getName() + ".");
        }
        else {
            System.out.println("There is a " + description + ". Their name is " + name + ".");
            System.out.println(name + " looks unhappy and you hear them say they are looking for their " + item.getName() + ".");
        }
    }
    
    /**
     * This method returns the characters item
     * 
     * @return Item of Character
     */
    public Item getItem()
    {
        return item;
    }
    
    /**
     * This method sets the value of ItemOwned to true when the Character has their Item
     * 
     * @return String name
     */
    public void setItemOwned()
    {
        itemOwned = true;
    }

    /**
     * This method returns the current room of the character
     * 
     * @return Room room
     */
    public boolean getItemOwned()
    {
        return itemOwned;
    }
    
    /**
     * This method changes the current room of the character
     * 
     * @return String name
     */
    public void setRoom(Room room)
    {
        currentRoom = room;
    }

    /**
     * This method returns the current room of the character
     * 
     * @return Room room
     */
    public Room getRoom()
    {
        return currentRoom;
    }
    
    /**
     * This method returns the name of the character
     * 
     * @return String name
     */
    public static ArrayList<Character> getCharacters()
    {
        return characters;
    }
     
    /**
     * This method checks is the character is in a room
     * 
     * @return boolean
     */
    public boolean characterInRoom(Room room, Character person)
    {   
        if(person.currentRoom == room) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * This method returns the character in the room
     * 
     * @return Character
     */
    public Character getCharacterInRoom(Room room, Character person)
    {   
        if(person.currentRoom == room) {
            return person;
        }
        else {
            return null;
        }
    }
    
    /**
     * This method checks is the character has their item
     * 
     * @return boolean
     **/
    
    public boolean characterHasItem()
    {   
        if(itemOwned) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * This method returns if the given input is a Character
     */
    public boolean inputIsCharacter(String input)
    {
        boolean found = false;
        int i = 0;
        while(found == false && i < characters.size()) {
            if(input.equals(characters.get(i).getName())) {
                found = true;
            }
            else {
                i ++;
            }
        }
        return found;
    }
    
    /**
     * This method returns a specific character in the array
     * 
     * @return item
     */
    public Character getCharacter(String input)
    {
        boolean found = false;
        int i = 0;
        while(found == false && i < characters.size()) {
            if(input.equals(characters.get(i).getName())) {
                found = true;
            }
            else {
                i ++;
            }
        }
        return characters.get(i);
    }
    
    /**
     * This method changes the room the character is in. allowing them to move
     */
    public Room changeRoom(Room room)
    {
        Random randomInt = new Random();
        
        //picks a random number from the amount of exits a room has
        //needs gto be for specific room
        ArrayList<String> exits = room.getExits();
        int integer = randomInt.nextInt(exits.size());
        String direction = exits.get(integer);
        
        currentRoom = room.getExit(direction);
        
        return currentRoom;
    }
}
