package sql.systemsettings.securitysettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                  "REQUIRE_PASSWORD          		Boolean      , " +
                  "REQUIRE_PASSWORD_TIME    		Integer      , " +
                  "LOG_FAILED_ATTEMPTS       		Boolean      , " +
                  "RECEIVE_EMAIL_ATTEMPTS    		Boolean      , " +
                  "RECEIVE_EMAIL_ATTEMPTS_COUNT     Integer      , " +
                  "SHOW_NOTIFICATIONS        		Boolean      , " +
                  "ERASE_APP_DATA            		Boolean      , " + 
                  "ERASE_APP_DATA_COUNT             Integer       )";
	    	
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
	  
	    	String sql = "INSERT INTO SECURITY_SETTINGS (REQUIRE_PASSWORD,REQUIRE_PASSWORD_TIME,LOG_FAILED_ATTEMPTS,RECEIVE_EMAIL_ATTEMPTS,"
	    			   + "RECEIVE_EMAIL_ATTEMPTS_COUNT,SHOW_NOTIFICATIONS,ERASE_APP_DATA,ERASE_APP_DATA_COUNT)" +  // sql code to create a new row in db
	    				 "VALUES (?,?,?,?,?,?,?,?);"; 
	      			   	    
	    	PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement for database
	    	preparedStatement.setBoolean(1,false);
	    	preparedStatement.setInt(2,5);
	    	preparedStatement.setBoolean(3,false);
	    	preparedStatement.setBoolean(4,false);
	    	preparedStatement.setInt(5,5);
	    	preparedStatement.setBoolean(6,false);
	    	preparedStatement.setBoolean(7,false);
	    	preparedStatement.setInt(8,5);

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
	

	public boolean getRequirePasswordValue()
	{
		// create connections
    	Connection c = null;
        Statement stmt = null;
        // create value to be returned
        boolean rVal = false;
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT REQUIRE_PASSWORD FROM SECURITY_SETTINGS WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getBoolean("REQUIRE_PASSWORD"); // get val from Col ID
      
            // close everything
            rs.close();
            stmt.close();
            c.close();
        } 
        
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return rVal; // return ID
	}
	
	public void updateRequirePasswordValue(Boolean updatedValue)
	{
		Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE SECURITY_SETTINGS SET REQUIRE_PASSWORD = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
	           
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
			preparedStatement.setBoolean(1, updatedValue); // set first ? to list position
			preparedStatement.setInt(2, 1); // set second ? to id
	           
			preparedStatement.executeUpdate(); // execute update to db
	           
			dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
			preparedStatement.close();
			dbConnection.close();
		} 
		
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
	public boolean getLogFailedAttemptsValue()
	{
		// create connections
    	Connection c = null;
        Statement stmt = null;
        // create value to be returned
        boolean rVal = false;
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT LOG_FAILED_ATTEMPTS FROM SECURITY_SETTINGS WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getBoolean("LOG_FAILED_ATTEMPTS"); // get val from Col ID
      
            // close everything
            rs.close();
            stmt.close();
            c.close();
        } 
        
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return rVal; // return ID
	}
	
	
	public void updateLogFailedAttemptsValue(Boolean updatedValue)
	{
		Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE SECURITY_SETTINGS SET LOG_FAILED_ATTEMPTS = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			preparedStatement.setBoolean(1, updatedValue); // set first ? to list position
			preparedStatement.setInt(2,1);
			
			preparedStatement.executeUpdate(); // execute update to db
			
			dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
			
			preparedStatement.close(); // close connections
			dbConnection.close();
			
		} 
		
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
	public boolean getReceiveEmailAttemptsValue()
	{
    	Connection c = null; // create connections
        Statement stmt = null; 
        boolean rVal = false; // create value to be returned
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT RECEIVE_EMAIL_ATTEMPTS FROM SECURITY_SETTINGS WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getBoolean("RECEIVE_EMAIL_ATTEMPTS"); // get val from Col ID
      
            // close everything
            rs.close();
            stmt.close();
            c.close();
        } 
        
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return rVal; // return ID
	}
	
	public void updateReceiveEmailAttemptsValue(Boolean updatedValue)
	{
		Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE SECURITY_SETTINGS SET RECEIVE_EMAIL_ATTEMPTS = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			preparedStatement.setBoolean(1, updatedValue); // set first ? to list position
			preparedStatement.setInt(2,1);
			
			preparedStatement.executeUpdate(); // execute update to db
			
			dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
			
			preparedStatement.close(); // close connections
			dbConnection.close();
		} 
		
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public boolean getShowNotificationsValue()
	{
		// create connections
    	Connection c = null;
        Statement stmt = null;
        // create value to be returned
        boolean rVal = false;
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT SHOW_NOTIFICATIONS FROM SECURITY_SETTINGS WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getBoolean("SHOW_NOTIFICATIONS"); // get val from Col ID
      
            // close everything
            rs.close();
            stmt.close();
            c.close();
        } 
        
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return rVal; // return ID
	}
	
	public void updateShowNotificaitonsValue(boolean updatedValue)
	{
		Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE SECURITY_SETTINGS SET SHOW_NOTIFICATIONS = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			preparedStatement.setBoolean(1, updatedValue); // set first ? to list position
			preparedStatement.setInt(2,1);
			
			preparedStatement.executeUpdate(); // execute update to db
			
			dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
			
			preparedStatement.close(); // close connections
			dbConnection.close();
		} 
		
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public boolean getEraseAppDataValue()
	{
		// create connections
    	Connection c = null;
        Statement stmt = null;
        // create value to be returned
        boolean rVal = false;
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT ERASE_APP_DATA FROM SECURITY_SETTINGS WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getBoolean("ERASE_APP_DATA"); // get val from Col ID
      
            // close everything
            rs.close();
            stmt.close();
            c.close();
        } 
        
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return rVal; // return ID
	}
	
	public void updateEraseAppDataValue(boolean updatedValue)
	{
		Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE SECURITY_SETTINGS SET ERASE_APP_DATA = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			preparedStatement.setBoolean(1, updatedValue); // set first ? to list position
			preparedStatement.setInt(2,1);
			
			preparedStatement.executeUpdate(); // execute update to db
			
			dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
			
			preparedStatement.close(); // close connections
			dbConnection.close();
		} 
		
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
}
