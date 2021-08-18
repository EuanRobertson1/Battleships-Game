
/**
 * contains a method that displays the menu options and reuturns the user's choice
 *
 * @author Euan Robertson
 * @version 30/07/21
 */
public class Menu
{
    
    
    /**
     *  displayMenu()
     *  
     *  displays menu options and current high score. Returns the user's choice 
     *  
     *  @param int highScore     
     *  @return selection
     */
    public int displayMenu(int highScore)
    {
       int selection;
       
       //create intsance of score class
       Score score = new Score();
       
       
        
      //menu Options are printed 
      System.out.println("------Welcome to Battleships------");
      System.out.println("Current High Score: " + highScore + " shots taken to sink all the ships");
      System.out.println("Please Choose from these 4 Options");
      System.out.println("----------------------------------");
      System.out.println("1- Start New Game");
      System.out.println("2- Load Saved Game");
      System.out.println("3- Reset High Score");
      System.out.println("4- Quit");
      System.out.println("-----------------------------------");
       
      selection = Genio.getInteger();
      
      return selection;
      
    }
    
   
    
    
   
}
