package program.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class: NetworkUtil
 * @author ZackEvans
 *
 * This class contains methods that help manage and check network protocols
 */

public class NetworkUtil 
{
	/**
	 * Function: isNetworkAvailable() 
	 * @return if there is a network connection or not.
	 * 
	 * This function checks if the computer if connected to the Internet.
	 */
	
	public static boolean isNetworkAvailable() 
	{                                                                                                                                                                                                 
	    try // try to connect to google.com
	    {                                                                                                                                                                                                                                 
	        final URL url = new URL("http://www.google.com");                                                                                                                                                                                 
	        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
	        conn.connect();                                                                                                                                                                                                                   
	        return true; // if the URL connects then there is a internet connection -> return true                                                                                                                                                                                                             
	    } 
	    
	    catch (IOException e)  // if connection fails
	    {                                                                                                                                                                 
	        return false; // return false                                                                                                                                                                           
	    }                                                                                                                                                                                                                                     
	}
}
