import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
 
/**
 * Contains methods based around displaying the user's grid to them
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Display
{
    // instance variables
    
    private static final int size = 10;
    private char[][] userGrid = new char[size][size];
    FileOutputStream outputStream;
    PrintWriter printWriter;
    FileReader fileReader;
    BufferedReader reader;
    
    
    
       /**
     *  fillUserGrid()
     *  
     *  Used to fill the 'userGrid' 2D array with tildes to represent the ocean
     *  
     *  @param none     
     *  @return none
     */
    public void fillUserGrid()
    {
        for (int i = 0; i < userGrid[0].length; i++)
        {
            for (int j = 0; j < userGrid[0].length; j++)
            {
                userGrid[i][j] = '~';
            }
        }
    }
    
    
      /**
     *  fillUserGrid()
     *  
     *  This method will print 'userGrid'
     *  
     *  @param none     
     *  @return none
     */
    public void showGrid()
    {
            
        
          
        //x axis markers   
                  
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
                        System.out.print(userGrid[i][j]);  
                        System.out.print(" ");
                    }
                    System.out.println(" ");
                    g++;
                }
        
       
    }
    
      /**
     *  saveUserGridToFile()
     *  
     *  This method will save 'userGrid' to a text file
     *  
     *  @param none     
     *  @return none
     */
    public void saveUserGridToFile()
        {
            try
        {
            //create instance of FileOutputStream class
            outputStream = new FileOutputStream("UserGrid.txt");
            //create instance of PrintWriter class
            printWriter = new PrintWriter(outputStream);
        
            //print grid to file
                for (int i = 0; i < userGrid[0].length; i++)
            {
            for (int j = 0; j < userGrid[0].length; j++)
            {
                printWriter.println(userGrid[i][j]);  
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
    
        /**
     *  updateUserGrid()
     *  
     *  This method will update user grid with a H or M character depending on what was returned to the main class by the checkShot() method in the userShot class
     *  
     *  @param int xCoord, int yCoord, char marker     
     *  @return none
     */
    public void updateUserGrid(int xCoord, int yCoord, char marker)
    {
        userGrid[yCoord][xCoord] = marker;
    }
}
