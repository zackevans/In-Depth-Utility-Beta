package sql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Class: DatabaseUtil 
 * @author ZackEvans
 *
 *This class contains function that can act and aid all tables of the database
 */

public class DatabaseUtil 
{
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // file path to db
	
	/**
     * Function: countItems()
     * @author ZackEvans
     * @param name of database table
     * @return number of items in the database
     * 
     * return the number of items currently in the table
     */
	
	public int countItems(String table) // counts how may rows there are
    {
        Connection c = null; // create object to store connection
        Statement stmt = null; // create a statement to hold db request
        int returnValue = -1; // create a value holder to return
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection to db
            c.setAutoCommit(false); // turn autocommit off
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM " + table); // sql code added to result set to count all items in the db
            
            returnValue = rs.getInt("total"); // set return value to value total
            
            rs.close(); // close open result set
            stmt.close(); // close open statement
            c.close(); // close the connection to the db
        }
        catch ( Exception e )
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        return returnValue; // return the total number of items in the note tabel
    }
}
