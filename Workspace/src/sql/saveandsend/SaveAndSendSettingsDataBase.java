package sql.saveandsend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class: SaveAndSendSettingsDataBase
 * @author ZackEvans
 *
 * Class contains all functions that act on the SaveAndSendSettingsDataBase 
 */

public class SaveAndSendSettingsDataBase 
{
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // database location
	
	/**
	 * Function: createSaveAndSendSettingsTable()
	 * 
	 * Creates the SaveAndSendSettingsTable if it doesn't already
	 */
	
	public void createSaveAndSendSettingsTable()
	{
		Connection c = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            
            stmt = c.createStatement();
            
            String sql = "CREATE TABLE if not exists SAVE_AND_SEND_SETTINGS" + // sql code to create new table in db
                    "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
                    " NEVERSHOW      boolean       NOT NULL )";
       
            stmt.executeUpdate(sql); // execute statement
            
            // close connections
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        System.out.println("Save And Send Settings Table created successfully");
        
        if(checkIfRowExists() == false) // if the setting row doesn't exist create it
        {
        	try
            {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(dbLocation); // create connection
                c.setAutoCommit(false);
                
                String sql = "INSERT INTO SAVE_AND_SEND_SETTINGS (NEVERSHOW) " + // sql code to create new settings row
                "VALUES (?);";
                
                PreparedStatement preparedStatement = c.prepareStatement(sql); // create a prepared statement for the sql code
                preparedStatement.setBoolean(1,false);
                
                preparedStatement.executeUpdate(); // push statement to db
                
                // close connections
                preparedStatement.close();
                c.commit();
                c.close();
            }
            
            catch ( Exception e )
            {
                System.err.println( e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
	}
	
	/**
	 * Function: checkIfRowExists()
	 * @return if a single row exists in the 
	 * 
	 * Function return a boolean value depending on if a initial row exists
	 */
	
	public boolean checkIfRowExists()
	{
		Connection c = null; // create connection
	    Statement stmt = null; // create statement var
	    boolean rVal = false; // create return value
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // get connection from db
	    	c.setAutoCommit(false);
	  
	    	stmt = c.createStatement();
	    	ResultSet rs = stmt.executeQuery("SELECT * FROM SAVE_AND_SEND_SETTINGS WHERE ID = 1"); // select all data in first row of db
	    	
	    	while (rs.next()) // run through all col in db
	    	{
	    		int i = rs.getInt("ID"); // get the ID in the first row of table
	   
	    		if (i == 1) // if there is a item
	    		{
	    			rVal = true; // return true
	    		}
	    	}
	      
	    	// close connections
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage());
	    	System.exit(0);
	    }
	    
	    return rVal;
	}
	
	/**
	 * Function: updateNeverShow(boolean tf)
	 * @param tf
	 * 
	 * Updates the Nevershow value in col 1
	 */
	
	public void updateNeverShow(boolean tf)
	{
		Connection dbConnection = null; // create var to hold connection from db
		
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection(dbLocation); // create connection
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE SAVE_AND_SEND_SETTINGS SET NEVERSHOW = ? WHERE ID = ?"; // sql code to update the NEVERSHOW col in the first Row
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create prepared statement 
			preparedStatement.setBoolean(1, tf); // set first ? = to tf
			preparedStatement.setInt(2, 1); // set second ? = to 1
			
			preparedStatement.executeUpdate(); // execute query
			dbConnection.setAutoCommit(true); // commit
			
			// close connections
			preparedStatement.close();
			dbConnection.close();
	    } 
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage());
	      System.exit(0);
	    }
	}
	
	/**
	 * Function: getNeverShow()
	 * 
	 * Get NEVERSHOW setting from the database.
	 */
	
	public boolean getNeverShow()
	{
		
		Connection c = null; // create connection
        Statement stmt = null; // statement
        boolean rVal = false; // return value
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // connect to db
            c.setAutoCommit(false);
            
            String sql = "SELECT NEVERSHOW FROM SAVE_AND_SEND_SETTINGS WHERE ID = ?;"; // sql code that get the NEVERHOW value from the first row
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement
            preparedStatement.setInt(1,1); // set first ? to 1
            
           stmt = c.createStatement();
           ResultSet rs = preparedStatement.executeQuery(); // execute query
            
            rVal = rs.getBoolean("NEVERSHOW"); // set return value to value in NEVERSHOW col and 1st row
            
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
}
