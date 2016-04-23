package program.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
	    catch (MalformedURLException e) 
	    {                                                                                                                                                                                                   
	        throw new RuntimeException(e);                                                                                                                                                                                                    
	    } 
	    catch (IOException e) 
	    {                                                                                                                                                                                                             
	        return false;                                                                                                                                                                                                                     
	    }                                                                                                                                                                                                                                     
	}
}
