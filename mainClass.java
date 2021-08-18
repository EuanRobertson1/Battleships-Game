
/**
 * Main Class for Battleships program, deals with calling methods 
 *
 * @author Euan Robertson
 * @version 30/07/21
 */
public class mainClass
{
    // instance variables 
    public int userChoice;
    boolean choiceBool;
    public int highScore;

    /**
     * Constructor for objects of class mainClass
     */
    public mainClass()
    {
        // initialise instance variables
        choiceBool = false;
    }
    
    /**
     * main method
     */
    public static void main(String [] args)
    {
        mainClass mainClass1 = new mainClass();
        
        
        mainClass1.director();
    }
    
    
    
    
    
    /**
     *  director 
     *  The director method
     *  
     *  @param none
     *  
     *  @return none 
     */ 
    public void director()
    {
        //create intances of classes
            Position p = new Position();
            Display d = new Display();
            userShot s = new userShot();
            Score score = new Score();
            Menu menu = new Menu();
            
            //define integer variable used to count the number of shots a user has made
                int shotsCount = 0;
                
            //get current high score from file
            highScore = score.getHighScore();
            
            
            
            
            
        
        //clear terminal
        System.out.print('\u000C');
                
        
        //do while loop for displaying main menu
        do{
            //get current high score from file
            highScore = score.getHighScore();
        
            //call method to get user choice
            userChoice = menu.displayMenu(highScore);
            
            //1- Start New Game
            if(userChoice == 1)
            {
                //define integer variables  used for checking if ships have sunk
                int bNumber;
                int c3Number;
                int c4Number;
                int d0Number;
                int d1Number;
                int d2Number;
                int sNumber;
                
                
                //set start boolean to false
                boolean start = false;
                
                //explain how game works to user and promt them to continue
                 do
                 {
                    //clear terminal 
                    System.out.print('\u000C');
                    
                    System.out.println("How to play:");
                    System.out.println("There are 2 Cruisers, 3 Destroyers, 3 submarines, and a Battleship for you to destroy!");
                    System.out.println("You will be asked to enter an X and Y coordinate, this will be where you fire your shot");
                    System.out.println("You will then see on your Grid if you have missed or Hit");
                    System.out.println(" ");
                    System.out.println("Good Luck!");
                     System.out.println(" ");
                    
                    System.out.println("Type C to continue...");
                    char C = Genio.getCharacter();
                    
                    if(C == 'C')
                    {
                        start = true;
                    }
                }while(!start);
                
                
                
                
                
                
                //clear terminal 
                 System.out.print('\u000C');
                
                //set boolean to true
                choiceBool = true;
                
                //set boolean to false 
                boolean allShipsSunk = false;
                
                
                                
                
                //call methods to fill the enemy grid, fill the user grid, and get enemy ship positions 
                p.fillGrid();
                d.fillUserGrid();
                p.getBattleshipPos();
                p.getCruiserPos();
                p.getDestroyerPos();
                p.getSubmarinePos();
                
               
                
                
                //loop that loops until all ships are sunk
                do 
                {
                //diplay enemy grid for testing 
                //p.testPrintEnemyShips();
                
               
                
                
                
                //check for sunken battleship
                 bNumber = p.checkBSunk();
                 
                 //check for sunken cruisers
                 c3Number= p.checkC3Sunk();
                 c4Number= p.checkC4Sunk();
                 
                 //check for sunken destroyers
                 d0Number= p.checkD0Sunk();
                 d1Number= p.checkD1Sunk();
                 d2Number = p.checkD2Sunk();
                 
                 //check for sunken submarines
                 sNumber = p.checkSSunk();
                 
                 
                 //display number of shots fired by the user
                 score.showScore(shotsCount);
                 
                 //pass ship numbers to userShot class
                 s.sunkShips(bNumber, c3Number, c4Number, d0Number, d1Number, d2Number, sNumber);
                 
                 //check if all the ships are sunk
                allShipsSunk = s.checkAllSunk(bNumber, c3Number, c4Number, d0Number, d1Number, d2Number, sNumber);
                
                //exit loop if all the ships are sunk
                if(allShipsSunk)
                {
                    break;
                }
                 
                //display user's board
                d.showGrid();
                
                int options;
                boolean boolOption = false;
                
                //display options and loop until user chooses an option
                do 
                {
                System.out.println("Please choose an option");
                System.out.println("1 - Enter Coordinates");
                System.out.println("2 - Save and Quit Game"); 
                System.out.println("3 - Quit Game"); 
                
                options = Genio.getInteger();
                
                //1 - enter coordinates
                if(options == 1)
                {
                    boolOption = true;
                }else if(options == 2)//2 - Save Game
                {
                    //call methods to save and quit 
                     p.saveGridToFile();
                     d.saveUserGridToFile();
                     s.saveNumbersToFile(bNumber, c3Number, c4Number, d0Number, d1Number, d2Number, sNumber);
                     score.saveShotsFiredToFile(shotsCount);
                     
                    //clear terminal 
                    System.out.print('\u000C');
                     
                     
                     //save highscore to file
                     score.saveHighScore(highScore);
         
                     //quit the program
                     System. exit(0);
                }else if(options == 3)//3 - quit game
                {
                    
                  //clear terminal 
                    System.out.print('\u000C');
                 
                 //save highscore to file
                 score.saveHighScore(highScore);
         
                 //quit the program
                 System. exit(0);
                } 
                }while(!boolOption);
                
                
                
                //call method to get coordinates for firing a shot
                int userX = s.getX();
                int userY = s.getY();
                
                //call method to add 1 to the number of shots taken
                shotsCount = score.shotsFired(shotsCount);
                
                //call method that returns the character from the enemy 2D array in the position chosen by the user
                char shotChar = p.getChar(userX, userY);
                
                //call method that tests if shot was a hit or miss 
                char hitOrMiss = s.checkShot(shotChar);
                
                //call method that checks if position was already fired upon
                boolean valid = p.validShot(userX, userY);
                
                //clear terminal 
                 System.out.print('\u000C');
                
                //if already fired at, return to top of loop
                if(!valid)
                {
                 boolean Continue = false;
                 
                    //clear terminal 
                 System.out.print('\u000C');
                 
                 //Tell user they have already fired there and prompt them to type a letter to continue
                 do
                 {
                    System.out.println("You have already fired a shot at this position!");
                    System.out.println("Type C to continue");
                    char C = Genio.getCharacter();
                    
                    if(C == 'C')
                    {
                        Continue = true;
                    }
                }while(!Continue);
                    continue;
                }
                
                
                //update user grid with a hit or miss
                d.updateUserGrid(userX, userY, hitOrMiss);
                
                //update enemy grid by marking where the user fired
                p.updateGrid(userX, userY);
                
                 
                
                            
                
            }while(!allShipsSunk);
                
                //clear terminal
                System.out.print('\u000C');
                
                //print message 
                System.out.println("Congratulations you have sunk all the ships with " + shotsCount + " shots!");
                
                //check if high score was beaten
                score.checkHighScore( highScore , shotsCount);
                
                //set boolean to false
                choiceBool = false;
            }
            
            //2- Load Saved Game
            if(userChoice == 2)
            {
                //set boolean to true
                choiceBool = true;
                
                
                
                
                
                //set boolean to false
                choiceBool = false;
            }
            
            //3- Reset High Score
            if(userChoice == 3)
            {
                //set boolean to true
                choiceBool = true;
                
                
                
                score.resetHighScore();
                
                //clear terminal
                System.out.print('\u000C');
                
                //set boolean to false
                choiceBool = false;
            }
            
            //4- Quit
            if(userChoice == 4)
            {
                //set boolean to true
                choiceBool = true;
                
                //clear terminal 
             System.out.print('\u000C');
             
             //save highscore to file
             score.saveHighScore(highScore);
     
             //quit the program
             System. exit(0);
     
                
                //set boolean to false
                choiceBool = false;
            }
            
            
        }while(choiceBool = false || userChoice !=4);
        
    }
    
    

    
}
