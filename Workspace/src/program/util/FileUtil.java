package program.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil 
{
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
