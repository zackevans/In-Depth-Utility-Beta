package file.files;

import java.util.ArrayList;

import program.util.FileUtil;
import program.util.security.Encryption;

public class PasswordAttemptsFile 
{
	private static final String fileLocation = System.getProperty("user.home") + "/Library/IDU Data/PasswordAttempts.txt";
	
	public static void createPasswordAttemptsFile()
	{
		FileUtil.createNewFile(fileLocation, "");	
	}
	
	public static void addAttempt(String attempt)
	{
		ArrayList<String> currentList = getAttempts();
		currentList.add(attempt);
		
		Encryption.writeEncryptedSerializedObject(currentList, fileLocation);
	}
	
	public static ArrayList<String> getAttempts()
	{
		ArrayList<String> returnList = new ArrayList<String>();
		
		if(!FileUtil.isFileEmpty(fileLocation))
		{
			return returnList = (ArrayList<String>) Encryption.readDecryptedSerializedArray(fileLocation);
		}
		
		else
		{
			return returnList;
		}
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
