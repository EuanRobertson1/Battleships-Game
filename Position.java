import java.util.Random;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
 

/**
 * Contains methods for creating the enemy grid and generating the random positions of the ships
 *
 * @author Euan Robertson
 * @version 30/07/21
 */
public class Position
{
        // instance variables 
        public char [] [] grid = new char [10][10];
        FileOutputStream outputStream;
        PrintWriter printWriter;
        FileReader fileReader;
        BufferedReader reader;
    
    
    /**
     *  fillGrid() 
     *  Fills the 'grid' 2D array with tilde characters which represent the ocean
     *  
     *  @param none
     *  
     *  @return none
     */ 

    public void fillGrid()
    {
        for (int i = 0; i < grid[0].length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = '~';
            }
        }
    }
    
    /**
     *  getBattleshipPos()
     *  Generates a random position for the battlship ship type and adds to the grid. This method is called first so there is no precations for ship overlaping  
     *  
     *  @param none
     *  
     *  @return none
     */
    public void getBattleshipPos()
    {
        //create instance of Random class
        Random r = new Random();
        
        //create instance of ships class
        Ships s = new Ships();
        
        //get the battleship array from the ship class
        char [] battleship = s.getBattleship(); 
        
        //get a random orientation (0 is horizontal, 1 = vertical)
        int orientation = r.nextInt(2);
        
        //defines the range and stops ships from going off the edge of the board. 
        int rangeX = 7 + 3 * orientation;
        int rangeY = 10 - 3 * orientation;
        
        //get a random x and y starting coord
        int randX = r.nextInt(rangeX);
        int randY = r.nextInt(rangeY);
        
        //used to count length of ship
        int shipCount = 0;
        
        //either prints vertically or horizontially based on 'orientation' value 
        if(orientation == 1)
        {
            //for loop until ship count reaches 4 as this is the length of the battleship ship
            for (int i = randY; shipCount < 4; i++)
            {
                //prints vertically by adding to the Y coord, X coord stays the same
                grid[i][randX] = battleship[shipCount];
                shipCount++;
            }
        }else if(orientation == 0)
        {
            //for loop until ship count reaches 4 as this is the length of the battleship ship
            for (int i = randX; shipCount < 4; i++)
            {
                //prints horizontially by adding to the X coord, Y coord stays the same
                grid[randY][i] = battleship[shipCount];
                shipCount++;
            }
        }
           
    }
    
    /**
     *  getCruiserPos()
     *  Generates a random position for the cruiser ship type and adds to the grid 
     *  
     *  @param none
     *  
     *  @return none
     */
    public void getCruiserPos()
    {
            //create instance of Random class
            Random r = new Random();
            
            
            //used to differentiate multiple ships of the same type *Explanation below*
            int count1 = 3;
            
            //create instance of ships class
            Ships s = new Ships();
            
            //get the cruiser array from the ship class
            char [] cruiser = s.getCruiser(); 
            
            //for loop as there needs to be two cruisers 
            for(int num = 0; num < 2; num++)
            {
            
            //get a random orientation (0 is horizontal, 1 = vertical)
            int orientation = r.nextInt(2);
            
            //defines the range and stops ships from going off the edge of the board. 
            int rangeX = 7 + 3 * orientation;
            int rangeY = 10 - 3 * orientation;
            
            //get a random x and y starting coord
            int randX = r.nextInt(rangeX);
            int randY = r.nextInt(rangeY);
            
            // define and intialise freeSpace, this will be used when checking for free space
            boolean freeSpace = true;
            
            //used to count length of ship (when checking for free space)
            int spaceCount = 0;
            
            //check for free space and avoid overlapping ships by scanning each index for '~'
            if(orientation == 1)
            {
                for (int i = randY; spaceCount < 3; i++)
                {
                    //vertical check for space (if any spaces don't contain a tilde then this means a ship is there so freeSpace boolean is set to false)
                    if(grid[i][randX] != '~') 
                    {
                         freeSpace = false;
                         break;
                    }
                     
                    spaceCount++;
                }
            }
            else if(orientation == 0)
            {
                for (int i = randX; spaceCount < 3; i++)
                {
                    //horizontial check for space (if any spaces don't contain a tilde then this means a ship is there so freeSpace boolean is set to false)
                    if(grid[randY][i] != '~')
                    {
                       freeSpace = false;
                         break; 
                    }
                    
                    spaceCount++;
                }
            }
            
            //if no free space is found , restart loop iteration
            if (!freeSpace)
            {
                num--;
                continue;
            }
            
            //used to count length of ship (when printing ships)
            int shipCount = 0;
            
            //either prints vertically or horizontially based on 'orientation' value 
            if(orientation == 1)
            {
                //for loop until ship count reaches 3 as this is the length of the cruiser ship
                for (int i = randY; shipCount < 3; i++)
                {
                    //prints vertically by adding to the Y coord, X coord stays the same
                    grid[i][randX] = cruiser[shipCount];
                    shipCount++;
                }
            }else if(orientation == 0)
            {
                //for loop until ship count reaches 3 as this is the length of the cruiser ship
                for (int i = randX; shipCount < 3; i++)
                {
                    //prints horizontially by adding to the X coord, Y coord stays the same
                    grid[randY][i] = cruiser[shipCount];
                    shipCount++;
                }
            } 
            
            //this code below adds 1 to the cruiser array at the '0'  '1' and '2' index so that the multiple cruisers can be differentiated when checking if they have been sunk e.g. C00 , C11....
            count1++;
            
            char temp=(char)(count1 + '0');
            
            cruiser[0] = temp;       
            cruiser[1] = temp;
            cruiser[2] = temp;
                        
            
        }  
        }
      
         /**
     *  getDestroyerPos()

     *  Generates a random position for the Destroyer ship type and adds to the grid 
     *  
     *  @param none
     *  
     *  @return none
     */
     public void getDestroyerPos()
     {
            //create instance of Random class
            Random r = new Random();
            
            //used to differentiate multiple ships of the same type *Explanation below*
            int count1 = 0;
            
            //create instance of ships class
            Ships s = new Ships();
        
            //get the cruiser array from the ship class
            char [] destroyer = s.getDestroyer(); 
        
            //for loop as there needs to be three destroyers  
                for(int num = 0; num < 3; num++)
                {
                
                //get a random orientation (0 is horizontal, 1 = vertical)
                int orientation = r.nextInt(2);
                
                //defines the range and stops ships from going off the edge of the board. 
                int rangeX = 7 + 3 * orientation;
                int rangeY = 10 - 3 * orientation;
                
                //get a random x and y starting coord
                int randX = r.nextInt(rangeX);
                int randY = r.nextInt(rangeY);
                
                // define and intialise freeSpace, this will be used when checking for free space
                boolean freeSpace = true;
                
                //used to count length of ship (when checking for free space)
                int spaceCount = 0;
                
                //check for free space and avoid overlapping ships by scanning each index for '~'
                if(orientation == 1)
                {
                    for (int i = randY; spaceCount < 2; i++)
                    {
                        //vertical check for space (if any spaces don't contain a tilde then this means a ship is there so freeSpace boolean is set to false)
                        if(grid[i][randX] != '~') 
                        {
                             freeSpace = false;
                             break;
                        }
                         
                        spaceCount++;
                    }
                }
                else if(orientation == 0)
                {
                    for (int i = randX; spaceCount < 2; i++)
                    {
                        //horizontial check for space (if any spaces don't contain a tilde then this means a ship is there so freeSpace boolean is set to false)
                        if(grid[randY][i] != '~')
                        {
                           freeSpace = false;
                             break; 
                        }
                        
                        spaceCount++;
                    }
                }
                
                //if no free space is found,restart loop
                if (!freeSpace)
                {
                    num--;
                    continue;
                }
                
                //used to count length of ship (when printing ships)
                int shipCount = 0;
                
                //either prints vertically or horizontially based on 'orientation' value 
                if(orientation == 1)
                {
                    //for loop until ship count reaches 2 as this is the length of the destroyer ship
                    for (int i = randY; shipCount < 2; i++)
                    {
                        //prints vertically by adding to the Y coord, X coord stays the same
                        grid[i][randX] = destroyer[shipCount];
                        shipCount++;
                    }
                }else if(orientation == 0)
                {
                    //for loop until ship count reaches 2 as this is the length of the destroyer ship
                    for (int i = randX; shipCount < 2; i++)
                    {
                        //prints horizontially by adding to the X coord, Y coord stays the same
                        grid[randY][i] = destroyer[shipCount];
                        shipCount++;
                    }
                } 
                
                //this code below adds 1 to the destroyer array at the '0' and '1' index so that the multiple destroyers can be differentiated when checking if they have been sunk e.g. 00 , 11....
                count1++;
                char temp=(char)(count1 + '0');
                
                destroyer[0] = temp;
                destroyer[1] = temp;
                
            }
               
            
         

        }
     
            /**
     *  getSubmarinePos()

     *  Generates a random position for the Submarine ship type and adds to the grid 
     *  
     *  @param none
     *  
     *  @return none
     */
        public void getSubmarinePos()
        {
            //create instance of Random class
            Random r = new Random();
        
            //create instance of ships class
            Ships s = new Ships();
        
            //get the cruiser array from the ship class
            char [] sub = s.getSubmarine(); 
        
            //for loop as there needs to be three submarines
                for(int num = 0; num < 3; num++)
                {
                
                //get a random orientation (0 is horizontal, 1 = vertical)
                int orientation = r.nextInt(2);
                
                //defines the range and stops ships from going off the edge of the board. 
                int rangeX = 7 + 3 * orientation;
                int rangeY = 10 - 3 * orientation;
                
                //get a random x and y starting coord
                int randX = r.nextInt(rangeX);
                int randY = r.nextInt(rangeY);
                
                // define and intialise freeSpace, this will be used when checking for free space
                boolean freeSpace = true;
                
                //used to count length of ship (when checking for free space)
                int spaceCount = 0;
                
                //check for free space and avoid overlapping ships by scanning each index for '~'
                if(orientation == 1)
                {
                    for (int i = randY; spaceCount < 1; i++)
                    {
                        //vertical check for space (if any spaces don't contain a tilde then this means a ship is there so freeSpace boolean is set to false)
                        if(grid[i][randX] != '~') 
                        {
                             freeSpace = false;
                             break;
                        }
                         
                        spaceCount++;
                    }
                }
                else if(orientation == 0)
                {
                    for (int i = randX; spaceCount < 1; i++)
                    {
                        //horizontial check for space (if any spaces don't contain a tilde then this means a ship is there so freeSpace boolean is set to false)
                        if(grid[randY][i] != '~')
                        {
                           freeSpace = false;
                             break; 
                        }
                        
                        spaceCount++;
                    }
                }
                
                //if no free space is found,restart loop
                if (!freeSpace)
                {
                    num--;
                    continue;
                }
                
                //used to count length of ship (when printing ships)
                int shipCount = 0;
                
                //either prints vertically or horizontially based on 'orientation' value 
                if(orientation == 1)
                {
                    //for loop until ship count reaches 1 as this is the length of the submarine 
                    for (int i = randY; shipCount < 1; i++)
                    {
                        //prints vertically by adding to the Y coord, X coord stays the same
                        grid[i][randX] = sub[shipCount];
                        shipCount++;
                    }
                }else if(orientation == 0)
                {
                    //for loop until ship count reaches 1 as this is the length of the submarine 
                    for (int i = randX; shipCount < 1; i++)
                    {
                        //prints horizontially by adding to the X coord, Y coord stays the same
                        grid[randY][i] = sub[shipCount];
                        shipCount++;
                    }
                } 
            }
               
            
            
             
        }
        
              /**
     *  testPrintEnemyShips()

     *  This method prints the 'grid' 2D array which contains the enemy ship positions. It is only used for testing and should be commented out of the main class when not needed
     *  
     *  @param none
     *  
     *  @return none
     */
    
        public void testPrintEnemyShips()
        {
                   //x axis markers   
            System.out.println("Enemy Ships");        
            System.out.println("  0 1 2 3 4 5 6 7 8 9");
            //y axis markers 
            int g = 0;
                
                for (int i = 0; i < 10; i++)
                {
                    
                    for (int j = 0; j< 10; j++)
                    {
                        if(j == 0)
                        {
                            System.out.print(g);
                            System.out.print(" ");
                            
                        }
                        System.out.print(grid[i][j]);  
                        System.out.print(" ");
                    }
                    
                    System.out.println(" ");
                    g++;
                }
        }
      
                 /**
     *  updateGrid()

     *  updates the 'grid' 2D array using the values that are passed into it, the x that is added to 'grid' represents where the user has fired 
     *  
     *  @param int xCoord, int yCoord
     *  
     *  @return none
     */
    
        public void updateGrid(int xCoord, int yCoord)
    {
        grid[yCoord][xCoord] = 'x';
    }
        
    
                    /**
     *  checkBSunk()

     *   this method counts the number of Bs that are on the 'grid' 2D array, the resulting number is then used in a separate method to determine if the battleship has sunk 
     *  
     *  @param none
     *  
     *  @return count
     */
        public int checkBSunk()
        {
            int count = 0;
            for (int i = 0; i < 10; i++)
                { 
                    
                    for (int j = 0; j< 10; j++)
                    {
                        if(grid[i][j] == 'B')
                        {
                            count++; 
                        }  
                    }    
                }
                return count;
        }
        
        
                      /**
     *  checkC3Sunk()

     *   this method counts the number of 3s that are on the 'grid' 2D array, the resulting number is then used in a separate method to determine if the cruiser represented by 3s has sunk 
     *  
     *  @param none
     *  
     *  @return count
     */
        public int checkC3Sunk()
        {
            int count = 0;
            for (int i = 0; i < 10; i++)
                { 
                    
                    for (int j = 0; j< 10; j++)
                    {
                        if(grid[i][j] == '3')
                        {
                            count++; 
                        }  
                    }    
                }
                return count;
        }
        
                        /**
     *  checkC4Sunk()

     *   this method counts the number of 4s that are on the 'grid' 2D array, the resulting number is then used in a separate method to determine if the crusier represented by 4s has sunk 
     *  
     *  @param none
     *  
     *  @return count
     */
        public int checkC4Sunk()
        {
            int count = 0;
            for (int i = 0; i < 10; i++)
                { 
                    
                    for (int j = 0; j< 10; j++)
                    {
                        if(grid[i][j] == '4')
                        {
                            count++; 
                        }  
                    }    
                }
                return count;
        }
        
       
                        /**
     *  checkD0Sunk()

     *   this method counts the number of 0s that are on the 'grid' 2D array, the resulting number is then used in a separate method to determine if the destroyer represented by 0s has sunk 
     *  
     *  @param none
     *  
     *  @return count
     */
        public int checkD0Sunk()
        {
            int count = 0;
            for (int i = 0; i < 10; i++)
                { 
                    
                    for (int j = 0; j< 10; j++)
                    {
                        if(grid[i][j] == '0')
                        {
                            count++; 
                        }  
                    }    
                }
                return count;
        }
        
                          /**
     *  checkD1Sunk()

     *   this method counts the number of 1s that are on the 'grid' 2D array, the resulting number is then used in a separate method to determine if the destroyer represented by 1s has sunk 
     *  
     *  @param none
     *  
     *  @return count
     */
        public int checkD1Sunk()
        {
            int count = 0;
            for (int i = 0; i < 10; i++)
                { 
                    
                    for (int j = 0; j< 10; j++)
                    {
                        if(grid[i][j] == '1')
                        {
                            count++; 
                        }  
                    }    
                }
                return count;
        }
        
                          /**
     *  checkD2Sunk()

     *   this method counts the number of 2s that are on the 'grid' 2D array, the resulting number is then used in a separate method to determine if the destroyer represented by 2s has sunk 
     *  
     *  @param none
     *  
     *  @return count
     */
        public int checkD2Sunk()
        {
            int count = 0;
            for (int i = 0; i < 10; i++)
                { 
                    
                    for (int j = 0; j< 10; j++)
                    {
                        if(grid[i][j] == '2')
                        {
                            count++; 
                        }  
                    }    
                }
                return count;
        }
        
                             /**
     *  checkSSunk()

     *   this method counts the number of Ss that are on the 'grid' 2D array, the resulting number is then used in a separate method to determine if the submarine has sunk 
     *  
     *  @param none
     *  
     *  @return count
     */
        public int checkSSunk()
        {
            int count = 0;
            for (int i = 0; i < 10; i++)
                { 
                    
                    for (int j = 0; j< 10; j++)
                    {
                        if(grid[i][j] == 'S')
                        {
                            count++; 
                        }  
                    }    
                }
                return count;
        }
     
                                /**
     *   validShot()

     *   this method checks if the passed in coordinates have already been fired upon by the user and returns a boolean value
     *  
     *  @param int x , int y
     *  
     *  @return valid
     */
        public boolean validShot(int x , int y)
    {
        boolean valid = true;
        if(grid[y][x] == 'x')
        {
           valid = false; 
        }
        return valid;
    }
    
        
                    /**
     *   getChar()

     *   this method returns the character in the 'grid' 2D array in the position of the coordinates that are passed in,( i.e returns the character in grid[4][3] if userY = 4 and userX = 3 )
     *  
     *  @param int userX, int userY
     *  
     *  @return char1
     */
        public char getChar(int userX, int userY)
        {
            
            char char1 = grid[userY][userX];
            
            return char1;
        }
        
                       /**
     *   saveGridToFile()

     *   this method prints the contents of 'grid' to a text file
     *  @param none
     *  
     *  @return none
     */
        public void saveGridToFile()
        {
            try
        {
            //create instance of FileOutputStream class
            outputStream = new FileOutputStream("Grid.txt");
            //create instance of PrintWriter class
            printWriter = new PrintWriter(outputStream);
        
            //print grid to file
                for (int i = 0; i < grid[0].length; i++)
            {
            for (int j = 0; j < grid[0].length; j++)
            {
                printWriter.println(grid[i][j]);  
            }
            }
        
             
             
            //flush and close the PrintWriter object
            printWriter.flush();
            printWriter.close();            
        }catch(IOException e) {
            //if there is an IO Exception then this message is printed 
            System.out.println("An error occurred, Please Try again");
            e.printStackTrace();
        } 
        }
        
        
        
        
    }

    

