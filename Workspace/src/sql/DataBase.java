package sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class: DataBase
 * @author ZackEvans
 *
 * This class contains methods that help create and setup the programs database
 */

public class DataBase 
{
	/**
	 * Function: createDBLocation()
	 * @author ZackEvans
	 * 
	 * Check if path folder exists and if it doesent then create it.
	 */

	public void createDBLocation()
	{
		File file = new File(System.getProperty("user.home"),"Library/IDU Data"); // create file path to db
		
		// check if the file doesent exist
		if (!file.exists())
		{
			file.mkdir(); // create directory
		}
	}
	
	/**
	 * Function: createDatabase()
	 * @author ZackEvans
	 * 
	 * Create the database file
	 */
	
	public void createDatabase() 
	{
		final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // create file path to db
		Connection c = null; // create connection for the database
		
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // get connection to database
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage());
	    	System.exit(0);
	    }
	}
	
	/**
	 * Function: checkConnection()
	 * @author ZackEvans
	 * 
	 * check to see if a connection can be established to the database.
	 */
	
	public void checkConnection()
	{
		final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; 
		boolean connection = false;
		Connection c = null;
		
		try 
	    {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(dbLocation);
			connection = true;
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		
		System.out.println("database connected: " + connection);
	}
}
