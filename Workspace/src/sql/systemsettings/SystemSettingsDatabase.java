package sql.systemsettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class: SystemSettingsDatabase 
 * @author ZackEvans
 *
 * Class contains all functions that act on the system settings db
 */

public class SystemSettingsDatabase 
{
	final static String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // db location
	
	/**
	 * Function: createSystemSettingsTable()
	 * @author ZackEvans
	 * 
	 * Function creates the system settings table in the db
	 */
	
	public void createSystemSettingsTable()
	{
		Connection c = null; // create connection var
	    Statement stmt = null; // create statement var
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // connect to database

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE if not exists SYSTEM_SETTINGS" + // sql code to create the system settings table in db
                  "(ID INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                  "PASSWORD           varchar              ," +
                  "PASS_EXIST         boolean              )";
	      
	    	stmt.executeUpdate(sql); // execute sql code
	    	
	    	// close connections
	    	stmt.close();
	    	c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    
	    System.out.println("System Settings Table created successfully");
	    
	    
	    if(checkIfRowExists() == false) // if the initial row in system settings doesn't exist, then create it.
	    {
	    	createInitialRow(); // create the initial row in the db table
	    }
	}
	
	/**
	 * Function: createPassword(String password)
	 * @author ZackEvans
	 * @param password
	 * 
	 * Function creates initial row in settings table
	 */
	
	public static void createInitialRow()
	{
		Connection c = null; // create connection
	    
	    try 
	    {  
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // connect to db
	    	c.setAutoCommit(false);
	  
	    	String sql = "INSERT INTO SYSTEM_SETTINGS (PASSWORD,PASS_EXIST)" + // sql code to create a new row in db
	    				 "VALUES (?,?);"; 
	      			   	    
	    	PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement for database
	    	preparedStatement.setString(1,""); // set password blank
	    	preparedStatement.setBoolean(2,false); // set does pass exist false

	    	preparedStatement.executeUpdate(); // execute statement
	    	
	    	// close connections
	    	preparedStatement.close();
	    	c.commit();
	    	c.close();
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    }
	}
	
	/**
	 * Function: updatePassword(String password)
	 * @author ZackEvans
	 * @param password
	 * 
	 * Function updates the password in the database
	 */
	
	public void updatePassword(String password)
	{
		Connection dbConnection = null; // create connection
		
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection(dbLocation); // connect to database
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE SYSTEM_SETTINGS SET PASSWORD = ? WHERE ID = ?"; // sql code to update the password in db
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create a prepared statement
			preparedStatement.setString(1, password); // set first ? = to password
			preparedStatement.setInt(2, 1); // set second ? to 1
			
			//dbConnection.commit();
			preparedStatement.executeUpdate(); // update database
			dbConnection.setAutoCommit(true);
			
			// close connections
			preparedStatement.close();
			dbConnection.close();
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    }
	}
	
	/**
	 * Function: getPassword()
	 * @author ZackEvans
	 * @return password from db
	 * 
	 * Function get the password from the database
	 */
	
	public String getPassword()
	{
	    String password = "-1"; // create value to be returned
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	Connection c = DriverManager.getConnection(dbLocation); // connect to database
	    	c.setAutoCommit(false);

	    	Statement stmt = c.createStatement();
	    	
	    	ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM SYSTEM_SETTINGS WHERE ID = 1"); // sql code to get password from db
	    	
	    	password = rs.getString("PASSWORD"); // get value from the PASSWORD col and the 1st row
	    	
	    	// close all connections
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    }
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    
	    return password; // return the password
	}
	
	
	/**
	 * Function: checkIfRowExists()
	 * @return whether the initial row exists
	 * 
	 * This Function returns whether the initial row exists in the database
	 */
	
	public static boolean checkIfRowExists()
	{
	    boolean rVal = false; // create val to be returned
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	Connection c = DriverManager.getConnection(dbLocation); // create connection to db
	    	c.setAutoCommit(false);
	    	
	    	Statement stmt = c.createStatement(); // create a sql statement
	    	
	    	ResultSet rs = stmt.executeQuery("SELECT * FROM SYSTEM_SETTINGS WHERE ID = 1"); // get the result set for items in db
	    	
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
	    }
	    
	    return rVal; // return it
	}
	
	/**
	 * Function: getPassExist()
	 * @author ZackEvans
	 * @return if a system password exists or not
	 */
	
	public boolean getPassExist()
	{
		Connection c = null; // create connection
	    Statement stmt = null; // create statement var
	    boolean rVal = false; // create value to be returned
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // connect to database
	    	c.setAutoCommit(false);
	  
	    	stmt = c.createStatement();
	    	ResultSet rs = stmt.executeQuery("SELECT PASS_EXIST FROM SYSTEM_SETTINGS WHERE ID = 1"); // get the passexist var from the 1st col in the db
	    	
	    	rVal = rs.getBoolean("PASS_EXIST"); // get val in Passexist col
	    	
	    	// close connections
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage());
	    }
	    
	    return rVal; // return the return value
	}
	
	/**
	 * Function: updatePassExist(boolean tf)
	 * @author ZackEvans
	 * @param tf
	 * 
	 * This function updates the does pass exist var in the db
	 */
	
	public void updatePassExist(boolean tf)
	{
		Connection dbConnection = null; // create a connection var.
		
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection(dbLocation); // connect to database
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE SYSTEM_SETTINGS SET PASS_EXIST = ? WHERE ID = ?"; // sql code to update passexist col
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create a prepared statement
			preparedStatement.setBoolean(1,tf); // set the first ? = tf
			preparedStatement.setInt(2,1); // set the second ? to 1
			
			dbConnection.setAutoCommit(true); // turn on commit
			preparedStatement.executeUpdate(); // execute code
			
			// close connections
			preparedStatement.close();
			dbConnection.close();
	    } 
		
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
}
