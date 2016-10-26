package file.files;

import java.util.ArrayList;

import program.util.FileUtil;

public class PasswordAttemptsFile 
{
	private static final String fileLocation = System.getProperty("user.home") + "/Library/IDU Data/PasswordAttempts.txt";
	
	public static void createPasswordAttemptsFile()
	{
		FileUtil.createNewFile(fileLocation, "");	
	}
	
	public static void addAttempt(String attempt)
	{
		ArrayList<String> currentList = FileUtil.readSerializedArray(fileLocation);
		currentList.add(attempt);
		
		FileUtil.writeSerializedArray(fileLocation, currentList);
	}
	
	public static ArrayList<String> getAttempts()
	{
		return FileUtil.readSerializedArray(fileLocation);
	}
	
	public static void clearAttempts()
	{
		FileUtil.clearFile(fileLocation);
	}
	
	public static boolean isEmpty()
	{
		if(FileUtil.isFileEmpty(fileLocation))
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
