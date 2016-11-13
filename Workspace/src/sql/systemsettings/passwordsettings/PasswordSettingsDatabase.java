package sql.systemsettings.passwordsettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import program.util.security.Encryption;
import sql.util.DatabaseUtil;

public class PasswordSettingsDatabase 
{
	final static String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // db location
	
	public void createPasswordTable()
	{
		Connection c = null; // create connection var
	    Statement stmt = null; // create statement var
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // connect to database

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE if not exists PASSWORD_SETTINGS" + // sql code to create the system settings table in db
                  "(ID INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                  "PASSWORD         varchar                )";
	      
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
	    
	    if(DatabaseUtil.countItems("PASSWORD_SETTINGS") == 0)
	    {
	    	createInitialRow();
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
	  
	    	String sql = "INSERT INTO PASSWORD_SETTINGS (PASSWORD)" + // sql code to create a new row in db
	    				 "VALUES (?);"; 
	      			   	    
	    	PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement for database
	    	preparedStatement.setBytes(1,Encryption.encryptString("")); // set password blank

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
	
	public void updatePassword(String password)
	{
		Connection dbConnection = null;
		
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection(dbLocation);
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE PASSWORD_SETTINGS SET PASSWORD = ? WHERE ID = ?";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			
			preparedStatement.setBytes(1, Encryption.encryptString(password));
			preparedStatement.setInt(2, 1);
			
			dbConnection.commit();
			preparedStatement.executeUpdate();
			preparedStatement.close();
			dbConnection.setAutoCommit(true);
			dbConnection.close();
	    } 
		
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
		
		System.out.println("Updated Password successfully");
	}
	
	public String getPassword()
	{
		Connection c = null;
	    Statement stmt = null;
	    String name = "";
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation);
	    	c.setAutoCommit(false);

	    	stmt = c.createStatement();
	    	ResultSet rs = stmt.executeQuery( "SELECT PASSWORD FROM PASSWORD_SETTINGS WHERE ID = 1" );
	    
	    	name = Encryption.decryptString(rs.getBytes("PASSWORD"));
	     	
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    return name;
	}
	
	public boolean doesPasswordExist()
	{
		String password = getPassword();
		
		if(password.length() >0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
