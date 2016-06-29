package program.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class: NetworkUtil
 * @author ZackEvans
 *
 * This class contains metods that help manage and check network protocols
 */

public class NetworkUtil 
{
	public static boolean isNetworkAvailable() 
	{                                                                                                                                                                                                 
	    try 
	    {                                                                                                                                                                                                                                 
	        final URL url = new URL("http://www.google.com");                                                                                                                                                                                 
	        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
	        conn.connect();                                                                                                                                                                                                                   
	        return true;                                                                                                                                                                                                                      
	    } 
	    
	    catch (IOException e) 
	    {                                                                                                                                                                                                             
	        return false;                                                                                                                                                                                                                     
	    }                                                                                                                                                                                                                                     
	}
}
