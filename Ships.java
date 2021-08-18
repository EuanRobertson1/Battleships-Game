
/**
 * Contains accessor methods used to use the ship arrays in the position class
 *
 * @author Euan Robertson
 * @version 30/07/21
 */
public class Ships
{ 
    // instance variables 
    private char [] battleship = {'B','B','B','B'};
    private char [] cruiser = {'3','3','3'};
    private char [] destroyer = {'0','0'};
    private char [] submarine = {'S'};
    
    
    
    /**
     *  getBattleship() 
     *  Accessor method for array 'battleship'
     *  
     *  @param none
     *  
     *  @return battleship
     */
    public char [] getBattleship()
    {
        return battleship;
    }
    
    /**
     *  getBattleship() 
     *  Accessor method for array 'cruiser'
     *  
     *  @param none
     *  
     *  @return cruiser
     */
    public char [] getCruiser()
    {
        return cruiser;
    }
    
    /**
     *  getBattleship() 
     *  Accessor method for array 'destroyer'
     *  
     *  @param none
     *  
     *  @return destroyer
     */
    public char [] getDestroyer()
    {
        return destroyer;
    }
    
    /**
     *  getBattleship() 
     *  Accessor method for array 'submarine'
     *  
     *  @param none
     *  
     *  @return submarine
     */
    public char [] getSubmarine()
    {
        return submarine;
    }
    
}
