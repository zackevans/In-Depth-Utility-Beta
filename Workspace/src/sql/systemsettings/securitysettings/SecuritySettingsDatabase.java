package sql.systemsettings.securitysettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import sql.util.DatabaseUtil;

public class SecuritySettingsDatabase 
{
	final static String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // db location

	public void createSecuritySettingsTable()
	{
		Connection c = null; // create connection var
	    Statement stmt = null; // create statement var
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // connect to database

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE if not exists SECURITY_SETTINGS" + // sql code to create the system settings table in db
                  "(ID INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                  "REQUIRE_PASSWORD          Boolean      , " +
                  "LOG_FAILED_ATTEMPTS       Boolean      , " +
                  "RECEIVE_EMAIL_ATTEMPTS    Boolean      , " +
                  "SHOW_NOTIFICATIONS        Boolean      , " +
                  "ERASE_APP_DATA            Boolean      )";
	    	
	    	stmt.executeUpdate(sql); // execute sql code
	    	// close connections
	    	stmt.close();
	    	c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println();
	    
	    if(DatabaseUtil.countItems("SECURITY_SETTINGS") == 0) // check if the table is empty
	    {
	    	createInitialRow(); // create the defult row
	    }
	}
	
	public static void createInitialRow()
	{
		Connection c = null; // create connection
	    
	    try 
	    {  
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // connect to db
	    	c.setAutoCommit(false);
	  
	    	String sql = "INSERT INTO SECURITY_SETTINGS (REQUIRE_PASSWORD,LOG_FAILED_ATTEMPTS,RECEIVE_EMAIL_ATTEMPTS,SHOW_NOTIFICATIONS,ERASE_APP_DATA)" + // sql code to create a new row in db
	    				 "VALUES (?,?,?,?,?);"; 
	      			   	    
	    	PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement for database
	    	preparedStatement.setBoolean(1, false);
	    	preparedStatement.setBoolean(2, false);
	    	preparedStatement.setBoolean(3, false);
	    	preparedStatement.setBoolean(4, false);
	    	preparedStatement.setBoolean(5, false);

	    	preparedStatement.executeUpdate(); // execute statement
	    	
	    	// close connections
	    	preparedStatement.close();
	    	c.commit();
	    	c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    	System.exit(0);
	    }
	}
	
	
	
	
}
