package file.filemanager;

import file.files.PasswordAttemptsFile;

/**
 * Class: FileManager
 * @author ZackEvans
 *
 * This class contains methods to manage the external file of a system.
 */

public class FileManager 
{
	/**
	 * Function: createFiles()
	 * 
	 * This function calls methods to create individual files
	 */
	
	public static void createFiles()
	{
		PasswordAttemptsFile.createPasswordAttemptsFile();		
	}
}
