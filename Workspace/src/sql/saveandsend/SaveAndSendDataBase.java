package sql.saveandsend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class: SaveAndSendDataBase 
 * @author ZackEvans
 *
 * Class contains all functions that act on the save and send table
 */

public class SaveAndSendDataBase 
{
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db";
	
	/**
	 * Function: createSaveAndSendTable()
	 * @author ZackEvans
	 * 
	 * create table in db if it doesent already exist
	 */
	
	public void createSaveAndSendTable()
	{
		// create connections
		Connection c = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // connect to db
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE if not exists SAVE_AND_SEND_EMAIL" +  // string of sql code that creates table with constraints 
                    "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
                    " TOADDRESS      varchar       NOT NULL, " +
                    " SUBJECT        varchar               , " +
                    " BODY           varchar                )";
            
            stmt.executeUpdate(sql); // push to db
            
            // close all connections
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        System.out.println("Save And Send  Table created successfully");
	}
	
	/**
	 * Function: createSavedEmail(String[] to, String subject, String body)
	 * @author ZackEvans
	 * @param to
	 * @param subject
	 * @param body
	 * 
	 * Function creates a new saved email item in the database
	 */
	
	public void createSavedEmail(String[] to, String subject, String body)
	{
		Connection c = null; // create var to hold connection
		
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection to db
            c.setAutoCommit(false);
            
            String sql = "INSERT INTO SAVE_AND_SEND_EMAIL (TOADDRESS,SUBJECT,BODY) " + // sql code that creates a new item in db
            "VALUES (?,?,?);";
            
            String sendTO = to[0]; // get first item in the to array and set it to send TO
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement
            preparedStatement.setString(1,sendTO); // set first ? to sendTO
            preparedStatement.setString(2, subject); // set second ? to the subject
            preparedStatement.setString(3,body); // set the third ? to the body
            
            preparedStatement.executeUpdate(); // push update to db
            
            // close all open connections
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
	
	/**
	 * Function: getFirstIndex()
	 * @author ZackEvans
	 * @return return first ID in the db
	 * 
	 * Function returns the first id in the database
	 */
	
	public int getFirstIndex()
	{
		// create connection to db
		Connection c = null;
        Statement stmt = null;
        
        int rVal = -1; // create a return value
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create a connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM SAVE_AND_SEND_EMAIL"); // tell db to get all items 
            
            rVal = rs.getInt("ID"); // pick the first id
            
            // close all connections
            rs.close();
            stmt.close();
            c.close();
        } 
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        return rVal; // return it
	}
	
	/**
	 * Function: getToAddress(int id) 
	 * @author ZackEvans
	 * @param id
	 * @return email address of the receiver
	 * 
	 * This function returns the email address of the receiver
	 */
	
	public String getToAddress(int id)
	{
		// create connections and statement 
		Connection c = null;
        Statement stmt = null;
        String rVal = "-1"; // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // Create connection to the database
            c.setAutoCommit(false);
            
            String sql = "SELECT TOADDRESS FROM SAVE_AND_SEND_EMAIL WHERE ID = ?;"; // tell db to get the receiver email
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,id); // set ? equal to id
            
           stmt = c.createStatement();
           ResultSet rs = preparedStatement.executeQuery(); 
            
            rVal = rs.getString("TOADDRESS"); // set val from TOADDRESS equal to the return value 
            
            // close all connections
            rs.close();
            stmt.close();
            c.close();
        }
        
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        return rVal; // return it
	}
	
	/**
	 * Function: getSubject(int id)
	 * @author ZackEvans
	 * @param id
	 * @return note subject
	 * 
	 * get the email subject
	 */
	
	public String getSubject(int id)
	{
		//create connections
		Connection c = null;
        Statement stmt = null;
        
        String rVal = "-1"; // create return value
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            String sql = "SELECT SUBJECT FROM SAVE_AND_SEND_EMAIL WHERE ID = ?;"; // tell db to get the subject
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement
            preparedStatement.setInt(1,id); // set ? to id
            
            stmt = c.createStatement();
            ResultSet rs = preparedStatement.executeQuery();
            
            rVal = rs.getString("SUBJECT"); // get value from the SUBJECT column 
            
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
        
        return rVal; // return it
	}
	
	/**
	 * Function: getBody(int id)
	 * @author ZackEvans
	 * @param id
	 * @return
	 * 
	 * Function return the body of the email
	 */
	
	public String getBody(int id)
	{
		// create connection
		Connection c = null;
        Statement stmt = null;
        // create return value
        String rVal = "-1";
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection to the db
            c.setAutoCommit(false);
            
            String sql = "SELECT BODY FROM SAVE_AND_SEND_EMAIL WHERE ID = ?;"; // sql code to get the body of the email based on the id
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement
            preparedStatement.setInt(1,id); // set ? to id
            
            stmt = c.createStatement();
            ResultSet rs = preparedStatement.executeQuery(); // create a result set based on what the prepared statement returned
            
            rVal = rs.getString("BODY"); // get item from the BODY col
            
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
        
        return rVal; // return it
	}
	
	/**
	 * Function: deleteSavedEmail(int idNum)
	 * @author ZackEvans
	 * @param idNum
	 * 
	 * Function deletes a item from the tabel
	 */
	
	public void deleteSavedEmail(int idNum)
    {
        Connection c = null;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // get connection to db
            c.setAutoCommit(false);
            
            String sql = "DELETE from SAVE_AND_SEND_EMAIL where ID = ?;"; // sql code to delete an item from the table 
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1,idNum); // set ? to idnum
            
            preparedStatement.executeUpdate(); // execute query
            
            // close connections
            preparedStatement.close();
            c.commit();
            c.close();
        } 

        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}