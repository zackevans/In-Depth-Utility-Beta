package file.files;

import java.util.ArrayList;

import program.util.FileUtil;
import program.util.security.Encryption;

/**
 * Class: PasswordAttemptsFile 
 * @author ZackEvans
 *
 * This class holds methods to deals with password attempts files
 */

public class PasswordAttemptsFile 
{
	private static final String fileLocation = System.getProperty("user.home") + "/Library/IDU Data/PasswordAttempts.txt"; // create the file location 
	
	/**
	 * Function: createPasswordAttemptsFile()
	 * 
	 * This function creates the path for the file.
	 */
	
	public static void createPasswordAttemptsFile()
	{
		FileUtil.createNewFile(fileLocation, "");	
	}
	
	/**
	 * Function: addAttempt(String attempt)
	 * @param attempt
	 * 
	 * This method takes in a string and adds it to the file.
	 */
	
	public static void addAttempt(String attempt)
	{
		ArrayList<String> currentList = getAttempts();
		currentList.add(attempt);
		
		Encryption.writeEncryptedSerializedObject(currentList, fileLocation);
	}
	
	/**
	 * Function: ArrayList<String> getAttempts()
	 * @return an arraylist that has all the password attempts from the file.
	 */
	
	public static ArrayList<String> getAttempts()
	{
		ArrayList<String> returnList = new ArrayList<>();
		
		if(!FileUtil.isFileEmpty(fileLocation))
		{
			return returnList = (ArrayList<String>) Encryption.readDecryptedSerializedArray(fileLocation);
		}
		
		else
		{
			return returnList;
		}
	}
	
	/**
	 * Function: clearAttempts()
	 * 
	 * This method clears all data in the files
	 */
	
	public static void clearAttempts()
	{
		FileUtil.clearFile(fileLocation);
	}
	
	/**
	 * Function: isEmpty()
	 * 
	 * @return if the file has data in it or not.
	 */
	
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
