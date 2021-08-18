import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
 
/**
 * Contains methods based around keeping track of the user's score and generating a high score
 *
 * @author Euan Robertson
 * @version 30/07/21
 */
public class Score
{
    
    FileOutputStream outputStream;
    PrintWriter printWriter;
    FileReader fileReader;
    BufferedReader reader;
    
    
      /**
     *  showScore()
     *  
     *  Used to print the current number of shots the user has taken
     *  
     *  @param none     
     *  @return none
     */
    public void showScore(int shot)
    {
        //display current value of 'shot'
        System.out.println("Shots Fired: " + shot);
    }
    
     /**
     *  shotsFired()
     *  
     *  Used to update the number of shots the user has taken
     *  
     *  @param int shot     
     *  @return none
     */
    public int shotsFired(int shot)
    {
        
        
        //add 1 to userShot
        shot++;
        
        
        return shot;
    }
    
    /**
     *  saveShotsFiredToFile()
     *  
     *  Used to update save number of shots the user has taken to a text file
     *  
     *  @param int shots     
     *  @return none
     */
    public void saveShotsFiredToFile(int shots)
    {
        try
        {
            //create instance of FileOutputStream class
            outputStream = new FileOutputStream("ShotsFired.txt");
            //create instance of PrintWriter class
            printWriter = new PrintWriter(outputStream);
        
            //print HighScore to file
             printWriter.println(shots);
             
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
     *  checkHighScore()
     *  
     *  this method will check if the high score was beaten and upated it if it was. Calls the saveHighScore() method even if the high score didn't change
     *  
     *  @param int highScore, int shot    
     *  @return none
     */
    public void checkHighScore(int highScore, int shot)
    {
        //update highScore if the user shots was less (less shots taken, the better)
        if(shot < highScore || highScore == 0)
        {
            highScore = shot;
        }
        
        saveHighScore(highScore);
    }
    
    /**
     *  saveHighScore()
     *  
     *  this method will save the highscore to a text file
     *  
     *  @param int highScore  
     *  @return none
     */
    public void saveHighScore(int highScore )
    {
       try
        {
            //create instance of FileOutputStream class
            outputStream = new FileOutputStream("HighScore.txt");
            //create instance of PrintWriter class
            printWriter = new PrintWriter(outputStream);
        
            //print HighScore to file
             printWriter.println(highScore);
             
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
     *  resetHighScore()
     *  
     *  this method will reset the saved high score to 0
     *  
     *  @param none 
     *  @return none
     */
    public void resetHighScore()
    {
        try
        {
            //create instance of FileOutputStream class
            outputStream = new FileOutputStream("HighScore.txt");
            //create instance of PrintWriter class
            printWriter = new PrintWriter(outputStream);
        
            //print HighScore to file
             printWriter.println(0);
             
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
     *  getHighScore()
     *  
     *  this method reads the saved highscore in from a text file 
     *  
     *  @param none 
     *  @return none
     */
    public int getHighScore( )
    {
        int fileHighScore = 0;
        try
        {
            //create instance of FileReader class
            fileReader = new FileReader("HighScore.txt");
            //create instance of BufferedReader class
            reader = new BufferedReader(fileReader);
            
             
            fileHighScore = Integer.parseInt(reader.readLine());
            
            reader.close();
        }catch(IOException e) {
            //if there is an IO Exception then this message is printed 
            System.out.println("An error occurred, Please Try again");
            e.printStackTrace();
        }
        
        
        return fileHighScore;
        
    }
    
}
