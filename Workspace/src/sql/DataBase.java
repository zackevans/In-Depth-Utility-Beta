package sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase 
{
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; 

	public void createDBLocation()
	{
		File file = new File(System.getProperty("user.home"),"Library/IDU Data");
		
		if (!file.exists())
		{
			file.mkdir();
			System.out.println("File IDU_Files created");
		}
		else 
		{
			System.err.println("File IDU_Files already exists");
		}
	}
	
	public void createDatabase() 
	{
		Connection c = null;
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(dbLocation);
	    } 
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
	
	public void checkConnection()
	{
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
	      System.exit(0);
	    }
		
		System.out.println("database connected: " + connection);
	}
}
