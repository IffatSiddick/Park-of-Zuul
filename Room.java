import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    //properties of Room
    private String name;
    private String description;
    private boolean visited;
    
    // stores all rooms in the game
    private static ArrayList<Room> rooms = new ArrayList<>();
    // stores exits of this room.
    private HashMap<String, Room> exits;
    // stores all rooms that the user has visited
    private static ArrayList<Room> visitedRooms = new ArrayList<>();

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room() 
    {
        exits = new HashMap<>();
        visited = false;
    }

    /**
     * This method sets the properties of a room
     */
    public void addProperties(String description, String name)
    {
        this.name = name;
        this.description = description;
    }
    
    /**
     * This method returns the name of the room
     * 
     * @return String name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public void getLongDescription(Room room)
    {
        Item item = new Item(room);
        System.out.println();
        System.out.println("You are " + description + "."); 
        item.getItemInRoom(room);
    }
    
    /**
     * This method changes the value of the visited variable
     * Which represents if a room has been visited or not
     */
    public void setVisited(Room room)
    {
        visited = true;
        setVisitedRooms(room);
    }
    
    /**
     * This method returns the value of the visited variable
     * Which represents if a room has been visited or not
     * 
     * @return boolean
     */
    public boolean getVisited()
    {
        return visited;
    }
    
     /**
     * This method adds the Rooms in the game to the ArrayList
     */
    public void setRooms(Room room)
    {
        rooms.add(room);
    }
    
    /**
     * This method returns all the Rooms in the game to the ArrayList
     */
    public ArrayList<Room> getRooms()
    {
        return rooms;
    }
    
    /**
     * This method returns all the size of the array rooms
     */
    public int roomsSize()
    {
        return rooms.size();
    }
    
    /**
     * 
     */
    public Room getRoom(String roomName)
    {   
        //will be null for some cases
        boolean found = false;
        int i = 0;
        while(found == false && i < roomsSize()) {
            if(roomName.equals(rooms.get(i).name)) {
                found = true;
            }
            else {
                i ++;
            }
        }
        return rooms.get(i);
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * 
     */
    public ArrayList<String> getExits()
    {
        ArrayList <String> directions = new ArrayList<>();
        Set<String> roomExits = exits.keySet();
        for(String exit: roomExits) {
            directions.add(exit);
        }
        return directions;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     **/
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * This method adds the Rooms in the game to the ArrayList
     */
    public static void setVisitedRooms(Room room)
    {
        visitedRooms.add(room);
    }
    
    /**
     * This method returns all the Rooms in the game to the ArrayList
     */
    public ArrayList<Room> getVisitedRooms()
    {
        return visitedRooms;
    }
    
    /**
     * This method returns all the size of the array visitedRooms
     */
    public int visitedRoomsSize()
    {
        return visitedRooms.size();
    }
    
    /**
     * This method will return a specific room
     * 
     * @return Room
     */
    public Room getPreviousRoom(Room currentRoom)
    {   
        int index = visitedRoomsSize()-2;
        if(index >= 0) {
            Room newRoom = visitedRooms.get(index);
            //removes room they are leaving from list
            visitedRooms.remove(currentRoom);
            return newRoom;
        }
        else {
            //signals no new room to go back to
            return null;
        }
    }
    
    /**
     * This method changes the room the character is in. allowing them to move
     */
    public Room randomRoom()
    {
        Random randomInt = new Random();
        int integer = randomInt.nextInt(rooms.size());
        Room newRoom = rooms.get(integer);
        
        return newRoom;
    }
}

