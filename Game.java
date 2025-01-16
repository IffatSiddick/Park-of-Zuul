import java.util.ArrayList;
import java.util.Random;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes and Iffat Siddick
 * @version 1
 */

public class Game 
{
    private Parser parser;
    //the Users currentRoom needs to stay consistant throughout the game
    private Room currentRoom;
    //user has to consistant throughout whole game
    private User user;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createItems();
        createCharacters();
        parser = new Parser();
        //this sets the users max weight limit to 15 throughout the whole game
        user = new User(15);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Character character = new Character();
        
        Room entrance, babys_area, kids_area, skate_area, gym_area, central_area, open_area;

        entrance = new Room();
        entrance.addProperties("outside the park. There is a sign reading 'Welcome to Zuul Park'", "entrance");
        entrance.setRooms(entrance);
        
        babys_area = new Room();
        babys_area.addProperties("in the young children playground. There is a small slide, some baby swings and rocking horses",
        "young childrens playground");
        babys_area.setRooms(babys_area);
        
        kids_area = new Room();
        kids_area.addProperties("in the older childrens playground. There is a jungle gym, large slide and balance beams", 
        "older childrens playground");
        kids_area.setRooms(kids_area);
        
        central_area = new Room();
        central_area.addProperties("in the middle of the park. There is a beautiful fountain in front of you and lots of benches", 
        "middle of the park");
        central_area.setRooms(central_area);
        
        skate_area = new Room();
        skate_area.addProperties("in the skate section. There are ramps and whatever where people are skateboarding", 
        "skate section");
        skate_area.setRooms(skate_area);
        
        gym_area = new Room();
        gym_area.addProperties("in the gym section. There is a bike, treadmill and bicep curl machine", "gym section");
        gym_area.setRooms(gym_area);
        
        open_area = new Room();
        open_area.addProperties("in an open field of grass. There are many groups of people there picnicking", 
        "open field of grass");
        open_area.setRooms(open_area);
        
        // initialise room exits
        entrance.setExit("north", central_area);
        entrance.setExit("east", skate_area);
        entrance.setExit("west", babys_area);

        babys_area.setExit("north", kids_area);
        babys_area.setExit("east", entrance);
        
        kids_area.setExit("east", central_area);
        kids_area.setExit("south", babys_area);
        
        skate_area.setExit("north", gym_area);
        skate_area.setExit("west", entrance);
        
        gym_area.setExit("south", skate_area);
        gym_area.setExit("west", central_area);

        central_area.setExit("north", open_area);
        central_area.setExit("east", gym_area);
        central_area.setExit("south", entrance);
        central_area.setExit("west", kids_area);
        
        open_area.setExit("south", central_area);
        
        currentRoom = entrance;  // start game outside the park
        currentRoom.setVisited(currentRoom);
    }
    
    /**
     * This method will create the items for the game
     */
    private void createItems()
    {
        Room room = new Room();
        Character character = new Character();
        
        Item ball = new Item(room.getRoom("young childrens playground"));
        ball.setItemProperties("ball",false, 3);
        
        Item scarf = new Item(room.getRoom("older childrens playground"));
        scarf.setItemProperties("scarf", true, 7);
        
        Item skateboard = new Item(room.getRoom("gym section"));
        skateboard.setItemProperties("skateboard", true, 10);
        
        Item kite = new Item(room.getRoom("middle of the park"));
        kite.setItemProperties("kite", false, 2);
    }
    
    /**
     * This method creates charcters in the game
     */
    private void createCharacters()
    {
        Item item = new Item(currentRoom);
        Room room = new Room();
        
        Character eliza = new Character();
        //passses null as Item
        eliza.addProperties("Eliza", "young woman with her family", item.getItem("scarf"), 
        room.getRoom("older childrens playground"));
        
        Character gus = new Character();
        gus.addProperties("Gus", "middle aged man wearing knee and hand guards", item.getItem("skateboard"), 
        room.getRoom("skate section"));
        
        Character benjamin = new Character();
        benjamin.addProperties("Benjamin", "man wearing a large trenchcoat and is holding a map", null, 
        room.getRoom("entrance"));
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println();
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly fun adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        
        currentRoom.getLongDescription(currentRoom);
        
        characterInRoom();
        
        System.out.println(currentRoom.getExitString());
    }
    
    /**
     * This method checks if a chracter is in the users current room
     */
    private void characterInRoom()
    {
        Character npc = new Character();
        ArrayList<Character>characters = npc.getCharacters();
        for(Character person: characters) {
            if(npc.characterInRoom(currentRoom, person)) { 
                npc = npc.getCharacterInRoom(currentRoom, person);
                npc.getLongDescription();
            }   
            person.changeRoom(currentRoom);
        }
    }
    
    /**
     * This method ensures items the user is holding move with them
     */
    private void itemInRoom()
    {   
        Item item = new Item(currentRoom);
        ArrayList<Item>list = item.getItems();
        if(user.holdingAnItem(currentRoom)) {
            for(Item object:user.getHoldingItems()) {
                object.setRoom(currentRoom);
            }
        }
    }
    
    /**
     * 
     */
    private void changingRoom(Room nextRoom)
    {
        currentRoom = nextRoom;   
        currentRoom.getLongDescription(currentRoom);
        
        itemInRoom();
        characterInRoom();
    }
    
    /**
     * This method is the for the magic transporter room
     */
    private Room roomRandomiser()
    {
        Character character = new Character();
        currentRoom = character.changeRoom(currentRoom);
        return currentRoom;
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean endGame = false;
        
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getFirstWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            endGame = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            endGame = quit(command);
        }
        else if (commandWord.equals("take")) {
            takeItem(command);
        }
        else if (commandWord.equals("leave")) {
            leaveItem(command);
        }
        else if (commandWord.equals("check")) {
            check(command);
        }
        else if (commandWord.equals("give")) {
            give(command);
        }
            
        return endGame;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println();
        System.out.println("You are lost and alone. You need to find your friends."); 
        System.out.println("You wander around at the park trying to find them.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private boolean goRoom(Command command) 
    {
        boolean wonGame = false;
        if(!command.hasSecondWord()) {
            //if there is no second word, we don't where to go
            System.out.println("Go where?");
        }
        else {
            String direction = command.getSecondWord();
            Room nextRoom = currentRoom.getExit(direction);
            if (direction.equals("back")) {
                //user going to previous room
                //with roomRandomiser!
                goBack();
            }
            else if (nextRoom == null) {
                //user did not input back or a valid direction
                System.out.println("There is no door!");
            }
            else if(nextRoom == nextRoom.getRoom("skate section")) {
                currentRoom = nextRoom;

                currentRoom.getLongDescription(currentRoom);
                currentRoom.setVisited(currentRoom);
                
                System.out.println();
                System.out.println("Whoa! Something weird is going on.");
                System.out.println("The air starts shimmering and you feel the ground spinning beneath your feet.");
                System.out.println("Suddenly you're in a different room! You didn't realise this was the MAGIC park of Zuul.");
                
                //could make this the same as other parts
                nextRoom = roomRandomiser();
                
                changingRoom(nextRoom);
                currentRoom.setVisited(currentRoom);
            }
            else {
                //going to a new room
                changingRoom(nextRoom);
                currentRoom.setVisited(currentRoom);
            }
            
            if(won(currentRoom)) {
                wonGame = true;
            }
            else {
                System.out.println(currentRoom.getExitString());
            }
        }
        //if won, doesnt show exit string
        return wonGame;
    }
    
    /**
     * This method allows the user to go back to their previous room
     */
    private void goBack()
    {   
        //roomRandomiser
        //if goes to skate
        Room newRoom = currentRoom.getPreviousRoom(currentRoom);
        if (newRoom == null) {
            System.out.println("You cannot go back any further.");
        }
        else if(newRoom == newRoom.getRoom("skate section")) {
            currentRoom = newRoom;
            currentRoom.getLongDescription(currentRoom);
            
            System.out.println();
            System.out.println("Oh look! Your back in the magic skate section of the Park of Zuul.");
            System.out.println("The air starts shimmering again and you feel the ground spinning beneath your feet.");
            System.out.println("Suddenly you're in a different room!");
            
            newRoom = roomRandomiser();
                
            changingRoom(newRoom);
            //newRoom.setVisited();
        }
        else {
            changingRoom(newRoom);
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * This method does what happens when you take an item
     */
    private void takeItem(Command command)
    {   
        if(!command.hasSecondWord()) { //has no second word
            System.out.println("What do you want to take?");
        }
        else {
            String itemName = command.getSecondWord();
            Item item = new Item(currentRoom);
            
            if(item.inputIsItem(itemName)) {
                item = item.getItem(itemName);  
                if(item.itemInRoom(currentRoom)) { 
                    if(item.getPickable()) { 
                        //checks user hasn't picked the item up already
                        if(!item.getPickedup()) { 
                            //checks user can pick up item dpending on their maximum weight limit
                            int possibleWeight = user.getCurrentWeight() + item.getWeight();
                            if(possibleWeight < user.getMaxWeight()) {
                                item.setPickedup();
                                user.setHoldingItems(item);
                                System.out.println("You picked up the " + item.getName() + ".");
                                user.setCurrentWeightIncrease(item.getWeight());
                            }
                            else {
                                System.out.println("You cannot pick up this item as otherwise you will be over your " +  
                                "maximum weight limit, " + user.getMaxWeight() + ".");
                            }
                        }
                        else {
                            System.out.println("You have already picked up this item.");
                        }
                    }
                    else {
                        System.out.println("This item cannot be picked up.");
                    }
                }
                else {
                    System.out.println("The item is not in this room.");
                }
            }
            else {
                System.out.println("This is not an item.");
            }
        }  
    }
    
    /**
     * This method des what happens when yu put down an item
     */
    private void leaveItem(Command command)
    {
        if(user.holdingAnItem(currentRoom)){
            if(!command.hasSecondWord()) { 
                //checks user has specified item they want tp put down
                System.out.println("Leave what?");
            }
            else {
                Item item = new Item(currentRoom);
                String itemName = command.getSecondWord();
                //checks user has input an Items name
                if(item.inputIsItem(itemName)) {
                    item = item.getItem(itemName);
                    //checks user is holding the item they want to put down
                    if(item.getPickedup()) {
                        item.setPickedup();
                        item.setRoom(currentRoom);
                        user.removeFromHoldingItems(item);
                        user.setCurrentWeightDecrease(item.getWeight());
                        System.out.println("You put down the " + item.getName() + " at the " + currentRoom.getName());
                    }
                    else {
                        System.out.println("You do not have this item to put down.");
                    }
                }
                else {
                System.out.println("That is not an item.");
                }
            }
        }
        else {
            System.out.println("You do not have an item to put down");
        }
    }
    
    /**
     * This method allows the user to check their phone
     */
    private void check(Command command)
    {
        System.out.println();
        String secondWord = command.getSecondWord();
        Item item = new Item(currentRoom);
        
        if(secondWord == null) {
            System.out.println("You can check your phone, yourself or an item.");
        }
        else if(secondWord.equals("phone")) {
            System.out.println("You take out your phone.");
            System.out.println("The screen is black and when you press the power button an empty battery shows on the screen.");
            System.out.println("Your phone is dead.");
            System.out.println("The last message you got from your friends was that they had arrived at the park and were waiting for you.");
            System.out.println("You need to find them.");
        }
        else if(secondWord.equals("myself")) {
            System.out.println("Your are in the " + currentRoom.getName() + ".");
            System.out.println("Your current weight is " + user.getCurrentWeight() + ".");
            System.out.println("Your maximum weight is " + user.getMaxWeight() + ".");
            System.out.println("Your need to find your friends.");
        }
        else if(item.inputIsItem(secondWord)) {
            item = item.getItem(secondWord);
            if(item.itemInRoom(currentRoom)) {
                System.out.println("This " + item.getName() + " has a weight of " + item.getWeight() + ".");
            }
            else {
                System.out.println("This item is not in the room with you to check its details.");
            }
        }
        else {
            System.out.println("You can check your phone, yourself or an item.");
        }
    }
    
    /**
     * This method implements the give command to allow the user to give a character a item
     */
    private void give(Command command)
    {      
        Character character = new Character();
        Item item = new Item(currentRoom);
        
        //checks user has entered a Characters name
        if(!command.hasSecondWord() || !character.inputIsCharacter(command.getSecondWord())) {
            System.out.println("You must specify who you want to give to.");
        }
        else {
            character = character.getCharacter(command.getSecondWord());
            
            if(!command.hasThirdWord() || !item.inputIsItem(command.getThirdWord())) { 
                System.out.println("You must specify what you want to give.");
            }
            else {
                item = item.getItem(command.getThirdWord());
                
                if(!item.getPickedup()) {
                    System.out.println("You are not carrying this item to give away.");
                }
                else {
                    if(!command.getThirdWord().equals(character.getItem().getName()) || 
                    command.getThirdWord().equals(character.getItem() == null)) {
                        System.out.println("You tried to give " + character.getName() + " the " + item.getName() + ".");
                        System.out.println(character.getName() + " said 'Sorry, but that's not mine. " + 
                        "I think I saw the owner nearby.'");
                    }
                    else {
                        System.out.println("You gave " + character.getName() + " the " + item.getName() + ".");
                        character.setItemOwned();
                        item.setPickedup();
                        user.removeFromHoldingItems(item);
                        user.setCurrentWeightDecrease(item.getWeight());
                    }
                }
            }
        }
    }
    
    /**
     * This method checks if the user has won the game
     * 
     * @return boolean
     */
    private boolean won(Room currentRoom)
    {  
        boolean endGame = false;
        if(currentRoom == currentRoom.getRoom("open field of grass") && currentRoom.getRoom("older childrens playground").getVisited() && 
        currentRoom.getRoom("middle of the park").getVisited()) {
            endGame = true;
            System.out.println("In the distance you see your friends setting out their things. They see you and wave.");
            System.out.println("You wave back with a smile and make your way over to them.");
        }
        return endGame;
    }
}
