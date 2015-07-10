package sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase 
{
	public void createDBLocation()
	{
		File file = new File("IDU_Files");
		
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
	      c = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
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
		Connection c = null;
		try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
	    } 
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		
		System.out.println("database connected successfully");
	}
}
