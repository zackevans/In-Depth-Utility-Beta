package sql.system.settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SystemDatabase 
{
	public void createSystemTable()
	{
		Connection c = null;
	    Statement stmt = null;
	    
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE if not exists SYSTEM_SETTINGS" + 
                  "(ID INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                  "PASSWORD           varchar              ," +
                  "PASS_EXIST         boolean              )";
	      
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
	    System.out.println("System Settings Table created successfully");
	}
	
	
	

}
