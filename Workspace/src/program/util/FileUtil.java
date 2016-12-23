package program.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class: FileUtill 
 * @author ZackEvans
 *
 * This class holds functions that help with files.
 */

public class FileUtil 
{
	/**
	 * Function: doesFileExist(String filePath)
	 * @param filePath
	 * @return If the file exists.
	 * 
	 * This function checks if a file exists. If it does it returns true.
	 */
	
	public static boolean doesFileExist(String filePath)
	{
		File f = new File(filePath);
		
		if(f.exists() && !f.isDirectory()) 
		{ 
		    return true;
		}
		
		else
		{
			return false;
		}
	}
	
	/**
	 * Function: isFileEmpty(String path)
	 * @param path
	 * @return If there content in the file.
	 * 
	 * This function returns true if there is content in the file.
	 */
	
	public static boolean isFileEmpty(String path)
	{
		boolean rVal = false;
		
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			if (br.readLine() == null) 
			{
			   rVal = true;
			}
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		
		return rVal;
	}
	
	/**
	 * Function: createNewFile(String fileLocation,String fileText)
	 * @param fileLocation
	 * @param fileText
	 * 
	 * This function creates a new file in memory.
	 */
	
	public static void createNewFile(String fileLocation,String fileText)
	{
		File exportFile = new File(fileLocation);
		
		try 
		{
			exportFile.createNewFile();
			
			FileWriter fileWriter = new FileWriter(fileLocation, true);
			fileWriter.write(fileText);
			fileWriter.close();
		} 
		
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Function: overWriteFile(String fileLocation, String fileText)
	 * @param fileLocation
	 * @param fileText
	 * 
	 * This function replaces the data in a file.
	 */
	
	public static void overWriteFile(String fileLocation, String fileText)
	{
		File exportFile = new File(fileLocation);
		
		try 
		{
			exportFile.createNewFile();
			
			FileWriter fileWriter = new FileWriter(fileLocation, true);
			clearFile(fileLocation);
			fileWriter.write(fileText);
			fileWriter.close();
		} 
		
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Function: clearFile(String fileLocation)
	 * @param fileLocation
	 * 
	 * This function removes all the data from the funciton.
	 */
	
	public static void clearFile(String fileLocation)
	{
		PrintWriter writer = null;
		try 
		{
			writer = new PrintWriter(fileLocation);
			writer.print("");
			writer.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
