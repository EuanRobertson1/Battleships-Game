import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Contains methods for the user to fire a shot. Also checks if ships have been sunk
 *
 * @author Euan Robertson
 * @version 30/07/21
   */
public class userShot
{
    //instance variables
    FileOutputStream outputStream;
    PrintWriter printWriter;
    FileReader fileReader;
    BufferedReader reader;

    /**
     *  getX()
     *  gets an X value from user and returns it. If X value is invalid it promts the user to enter again
     *  
     *  @param none
     *  
     *  @return x
     */
    public int getX()
    {
        int x;
        boolean validChoice = false;
        do
            {
            System.out.println("Please enter an X coordinate");
            
             x = Genio.getInteger();
            if(x < 0 || x > 9)
            {
                System.out.println("Invalid X coordiante!");
            }else
            {
                validChoice = true;
            }
            
        }while(!validChoice);
        return x;
    }
    
    /**
     *  getY()
     *  gets an Y value from user and returns it. If Y value is invalid it promts the user to enter again
     *  
     *  @param none
     *  
     *  @return y
     */
    public int getY()
    {
        int y;
        boolean validChoice = false;
        do
            {
            System.out.println("Please enter an Y coordinate");
            
             y = Genio.getInteger();
            if(y < 0 || y > 9)
            {
                System.out.println("Invalid Y coordiante!");
            }else
            {
                validChoice = true;
            }
            
        }while(!validChoice);
        return y;
        
    }
    
    /**
     *   sunkShips()
     *  Takes the numbers that were counted in the position class and uses them in a series of if statements to determine if any ships have sunk. The many if statements for the cruiser and the destroyer types
     *  are due to there being multiple of those ships and them both taking up multiple spaces which results in different combinations of those ships being destroyed having to be compensated for.
     *  
     *  This method also updates and prints the sunk ship numbers 
     *  
     *  
     *  @param int bNumber, int c3Number, int c4Number, int d0Number, int d1Number, int d2Number, int sNumber
     *  
     *  @return none
     */
    public void sunkShips(int bNumber, int c3Number, int c4Number, int d0Number, int d1Number, int d2Number, int sNumber)
    {
                int bCount = 0;
                int cCount = 0;
                int dCount = 0;
                int sCount = 0;
                
                
                                
                if(bNumber == 0)
                {
                    bCount = 1;
                }
                
                //mutiple outcoumes depending on the combination of ships destroyed
                if (c3Number == 0 & c4Number > 0)
                {
                    //cruiser 333 is destroyed
                    cCount = 1;
                }
                else if (c4Number == 0 & c3Number > 0)
                {
                    //cruiser 444 is destroyed
                    cCount = 1;
                }else if(c3Number == 0 & c4Number == 0)
                {
                    //they are both destroyed
                    cCount = 2;
                }
                
                //mutiple outcoumes depending on the combination of ships destroyed
                if(d0Number == 0 & d1Number > 0 & d2Number > 0 )
                {
                    //destroyer 00 is destroyed
                    dCount = 1;
                }else if(d1Number == 0 & d0Number > 0 & d2Number > 0 )
                {
                    //destroyer 11 is destroyed
                    dCount = 1;
                }else if(d2Number == 0 & d1Number > 0 & d0Number > 0 )
                {
                    //destroyer 22 is destroyed
                    dCount = 1;
                }else if(d0Number == 0 & d1Number == 0 & d2Number > 0 )
                {
                    //destroyer 00 and 11 are destroyed
                    dCount = 2;
                }else if(d0Number == 0 & d2Number == 0 & d1Number > 0 )
                {
                    //destroyer 00 and 22 are destroyed
                    dCount = 2;
                }else if(d1Number == 0 & d2Number == 0 & d0Number > 0 )
                {
                    //destroyer 11 and 22 are destroyed
                    dCount = 2;
                }else if(d1Number == 0 & d1Number == 0 & d2Number == 0 )
                {
                    //destroyer 11 and 22 are destroyed
                    dCount = 3;
                }
                
                
                
               
                //change sCount value depending on number of submarines sunk
                if (sNumber ==  2)
                {
                   sCount = 1;
                }else if (sNumber == 1)
                {
                    sCount = 2;
                }else if(sNumber == 0)
                {
                    sCount = 3;
                }
                
                
                //print number of ships sunk for each type
                System.out.println("Battleship sunk: " + bCount);
                System.out.println("Cruiser(s) sunk: " + cCount);
                System.out.println("Destroyer(s) sunk: " + dCount);
                System.out.println("Submarine(s) sunk: " + sCount);
                System.out.println(" ");
                
                
                
    }
    
    /**
     *  checkAllSunk()
     *  
     *  This method will return a boolean as true if all the ships are sunk, or false if they aren't. It determines this by checking if the letter and number counts are all 0
     *  
     *  @param int bNumber, int c3Number, int c4Number, int d0Number, int d1Number,int  d2Number,int sNumber
     *  
     *  @return checkAllSunk
     */
    public boolean checkAllSunk(int bNumber, int c3Number, int c4Number, int d0Number, int d1Number,int  d2Number,int sNumber )
    {
        boolean checkAllSunk;
        
        if (bNumber == 0 & c3Number == 0 & c4Number == 0 & d0Number == 0 & d1Number == 0 & d2Number == 0 & sNumber == 0)
        {
            checkAllSunk = true;
        }else
        {
            checkAllSunk = false;
        }
        
        return checkAllSunk;
    }
    
    /**
     *  saveNumbersToFile()
     *  
     *  This method will save all the ship count numbers to a  text file
     *  
     *  @param int bNumber, int c3Number, int c4Number, int d0Number, int d1Number,int  d2Number,int sNumber
     *  
     *  @return none
     */
    public void saveNumbersToFile(int bNumber, int c3Number, int c4Number, int d0Number, int d1Number,int  d2Number, int sNumber)
    {
        //save bNumber
         try
        {
            //create instance of FileOutputStream class
            outputStream = new FileOutputStream("Numbers.txt");
            //create instance of PrintWriter class
            printWriter = new PrintWriter(outputStream);
        
           
            printWriter.println(bNumber);
            printWriter.println(c3Number);
            printWriter.println(c4Number);
            printWriter.println(d0Number);
            printWriter.println(d1Number);
            printWriter.println(d2Number);
            printWriter.println(sNumber);
            
             
            //flush and close the PrintWriter object
            printWriter.flush();
            printWriter.close();            
        }catch(IOException e) {
            //if there is an IO Exception then this message is printed 
            System.out.println("An error occurred, Please Try again");
            e.printStackTrace();
        } 
        
        
    }

        /**
     *  checkShot()
     *  
     *  This method checks if a shot was a hit or miss. It does this by using the value returned from the getChar() method in the position class which is then passed to this method from the main class.
     *  This value is then checked, if the value is a tilde then a M is returned otherwise a H is returned.
     *  
     *  @param char char1      
     *  @return hitOrMiss
     */
    public char checkShot(char char1)
    {
        char hitOrMiss;
        if (char1 == '~')
        {
            
             hitOrMiss = 'M';
        }else
        {
            
             hitOrMiss = 'H';
        }
        
        return hitOrMiss;
    }

    
}
