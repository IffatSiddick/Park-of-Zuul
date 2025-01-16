/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] forCodeCommands = {
        "go", "quit", "help", "take", "leave", "give", "check"
    };
    private static final String[] forUserCommands = {
        "go [direction]", "go back", "take [item]", "leave [item]", "give [character] [item]", "check phone/myself/[item]",
        "quit", "help"};
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < forCodeCommands.length; i++) {
            if(forCodeCommands[i].equals(aString))
                return true;
        }
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        //need to change here for check OR chmage code to check whole line
        for(String command: forUserCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
