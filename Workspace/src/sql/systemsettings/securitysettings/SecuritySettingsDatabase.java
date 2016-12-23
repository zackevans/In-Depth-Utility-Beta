package sql.systemsettings.securitysettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import sql.util.DatabaseUtil;

/**
 * Class: SecuritySettingsDatabase 
 * @author ZackEvans
 *
 * This class holds functions that interact with the security settings table in the database.
 */

public class SecuritySettingsDatabase 
{
	final static String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // db location

	/**
	 * Function: createSecuritySettingsTable()
	 * 
	 * This function creates all the fields needed in the security settings table. If the first row doesn't exist call a function to create it.
	 */
	
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
	    
	    if(DatabaseUtil.countItems("SECURITY_SETTINGS") == 0) // check if the table is empty
	    {
	    	createInitialRow(); // create the defult row
	    }
	}
	
	/**
	 * Function: createInitialRow()
	 * 
	 * This function creates the first row in the database and initializes values.
	 */
	
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
	    	preparedStatement.setInt(2,0);
	    	preparedStatement.setBoolean(3,false);
	    	preparedStatement.setBoolean(4,false);
	    	preparedStatement.setInt(5,0);
	    	preparedStatement.setBoolean(6,false);
	    	preparedStatement.setBoolean(7,false);
	    	preparedStatement.setInt(8,0);

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

	/**
	 * Function: getRequirePasswordValue()
	 * @return REQUIRE_PASSWORD value from db
	 * 
	 * This function gets the value REQUIRE_PASSWORD from the first row of the db.
	 */
	
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
	
	/**
	 * Function: updateRequirePasswordValue(Boolean updatedValue)
	 * @param updatedValue
	 * 
	 * Thus function updates the REQUIRE_PASSWORD value in the first row of the database.
	 */
	
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
	
	/**
	 * Function: getRequirePasswordTimeValue()
	 * @return the REQUIRE_PASSWORD_TIME value from the db
	 * 
	 * This function returns the REQUIRE_PASSWORD_TIME value from the first row of the database.
	 */
	
	public int getRequirePasswordTimeValue()
	{
		// create connections
    	Connection c = null;
        Statement stmt = null;
        // create value to be returned
        int rVal  = 0;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT REQUIRE_PASSWORD_TIME FROM SECURITY_SETTINGS WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getInt("REQUIRE_PASSWORD_TIME"); // get val from Col ID
      
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
	
	/**
	 * Function: updateRequirePasswordTimeValue(int updatedValue)
	 * @param updatedValue
	 * 
	 * This function updates the REQUIRE_PASSWORD_TIME value in the first row of the database.
	 */
	
	public void updateRequirePasswordTimeValue(int updatedValue)
	{
		Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE SECURITY_SETTINGS SET REQUIRE_PASSWORD_TIME = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
	           
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
			preparedStatement.setInt(1, updatedValue); // set first ? to list position
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
	
	/**
	 * Function: getLogFailedAttemptsValue()
	 * @return LOG_FAILED_ATTEMPTS value from db.
	 * 
	 * This function gets the LOG_FAILED_ATTEMPTS value from the first row of the table
	 */
	
	
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
	
	/**
	 * Function: updateLogFailedAttemptsValue(Boolean updatedValue)
	 * @param updatedValue
	 * 
	 * This function updates the LOG_FAILED_ATTEMPTS value in the database.
	 */
	
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
	
	/**
	 * Function: getReceiveEmailAttemptsValue()
	 * @return the RECEIVE_EMAIL_ATTEMPTS value in the databse.
	 * 
	 * This function returns the RECEIVE_EMAIL_ATTEMPTS value from the first row of the table.
	 */
	
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
	
	/**
	 * Function: updateReceiveEmailAttemptsValue(Boolean updatedValue)
	 * @param updatedValue
	 * 
	 * This function updates the RECEIVE_EMAIL_ATTEMPTS value in the first row of the table
	 */
	
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
	
	/**
	 * Function: getReceiveEmailAttemptsCount()
	 * @return RECEIVE_EMAIL_ATTEMPTS_COUNT value stored in the database.
	 * 
	 * This function gets the RECEIVE_EMAIL_ATTEMPTS_COUNT value in the first row of the table.
	 */
	
	public int getReceiveEmailAttemptsCount()
	{
		Connection c = null; // create connections
        Statement stmt = null; 
        int rVal = 0; // create value to be returned
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT RECEIVE_EMAIL_ATTEMPTS_COUNT FROM SECURITY_SETTINGS WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getInt("RECEIVE_EMAIL_ATTEMPTS_COUNT"); 
      
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
	
	/**
	 * Function: updateReceiveEmailAttemptsCount(int updatedValue)
	 * @param updatedValue
	 * 
	 * This function updates the RECEIVE_EMAIL_ATTEMPTS_COUNT value in the database.
	 */
	
	public void updateReceiveEmailAttemptsCount(int updatedValue)
	{
		Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE SECURITY_SETTINGS SET RECEIVE_EMAIL_ATTEMPTS_COUNT = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			preparedStatement.setInt(1, updatedValue); // set first ? to list position
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
	
	/**
	 * Function: getShowNotificationsValue()
	 * @return SHOW_NOTIFICATIONS value from the database
	 * 
	 * This funciton returns the SHOW_NOTIFICATIONS from the first row of the table.
	 */
	
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
	
	/**
	 * Function: updateShowNotificaitonsValue(boolean updatedValue)
	 * @param updatedValue
	 * 
	 * This function updates the SHOW_NOTIFICATIONS in the first row of the SECURITY_SETTINGS table
	 */
	
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
	
	/**
	 * Function: getEraseAppDataValue()
	 * @return ERASE_APP_DATA value
	 *
	 * This function gets the ERASE_APP_DATA value from the database.
	 */
	
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
	
	/**
	 * Function: updateEraseAppDataValue(boolean updatedValue)
	 * @param updatedValue
	 * 
	 * this function updates the ERASE_APP_DATA value in the database
	 */
	
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
	
	/**
	 * Function: getEraseAppDataCount()
	 * @return ERASE_APP_DATA_COUNT value.
	 * 
	 * This funciton updates the ERASE_APP_DATA_COUNT value in the first row of the table.
	 */
	
	public int getEraseAppDataCount()
	{
		// create connections
    	Connection c = null;
        Statement stmt = null;
        // create value to be returned
        int rVal = 0;
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT ERASE_APP_DATA_COUNT FROM SECURITY_SETTINGS WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getInt("ERASE_APP_DATA_COUNT"); // get val from Col ID
      
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
	
	/**
	 * Function: updateEraseAppDataCount(int updatedValue)
	 * @param updatedValue
	 * 
	 * This function updates the ERASE_APP_DATA_COUNT value in the database.
	 */
	
	public void updateEraseAppDataCount(int updatedValue)
	{
		Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE SECURITY_SETTINGS SET ERASE_APP_DATA_COUNT = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			preparedStatement.setInt(1, updatedValue); // set first ? to list position
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
