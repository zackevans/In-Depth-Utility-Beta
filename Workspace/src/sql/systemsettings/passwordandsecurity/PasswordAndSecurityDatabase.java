package sql.systemsettings.passwordandsecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import program.util.security.Encryption;

public class PasswordAndSecurityDatabase 
{
	final static String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // db location
	
	public void createPasswordAndSecurityTable()
	{
		Connection c = null; // create connection var
	    Statement stmt = null; // create statement var
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // connect to database

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE if not exists PASSWORD_AND_SECURITY_SETTINGS" + // sql code to create the system settings table in db
                  "(ID INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                  "PASSWORD           varchar              )";
	      
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
	    
	    if(checkIfInitialRowExists() == false)
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
	  
	    	String sql = "INSERT INTO PASSWORD_AND_SECURITY_SETTINGS (PASSWORD)" + // sql code to create a new row in db
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
	
	public boolean checkIfInitialRowExists()
	{
		boolean rVal = false; // create val to be returned
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	Connection c = DriverManager.getConnection(dbLocation); // create connection to db
	    	c.setAutoCommit(false);
	    	
	    	Statement stmt = c.createStatement(); // create a sql statement
	    	
	    	ResultSet rs = stmt.executeQuery("SELECT ID FROM PASSWORD_AND_SECURITY_SETTINGS WHERE ID = 1"); // get the result set for items in db
	    	
	    	while (rs.next()) // run through all results
	    	{
	    		int i = rs.getInt("ID"); // get id
	   	
	    		if (i == 1) // if the initial row exists
	    		{
	    			rVal = true; // set the return value to true
	    		}
	    	}   
	    	
	    	// close all connections
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    return rVal; // return it
	}
	
	public void updatePassword(String password)
	{
		Connection dbConnection = null;
		
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection(dbLocation);
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE PASSWORD_AND_SECURITY_SETTINGS SET PASSWORD = ? WHERE ID = ?";
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
	    	ResultSet rs = stmt.executeQuery( "SELECT PASSWORD FROM PASSWORD_AND_SECURITY_SETTINGS WHERE ID = 1" );
	    
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
