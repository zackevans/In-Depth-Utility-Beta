package sql.notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class: NotesDataBase
 * @author ZackEvans
 * 
 * Class contains all methods related to the notes tabel in the database 
 */

public class NotesDataBase
{
    final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // file path to db
    
    /**
     * Function: createNotesTable()
     * @author ZackEvans
     * 
     * create the notes tabel in the database if it doesent already
     */
    
    public void createNotesTable()
    {
        Connection c = null; // create var for the connection to the database
        Statement stmt = null; // create a var for statement
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // connect to database
            
            stmt = c.createStatement(); //create statement for db 
            
            String sql = "CREATE TABLE if not exists USER_NOTES" + // create table sql code
            "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
            " NAME           varchar       NOT NULL, " +
            " BODY           varchar               , " +
            " DATE           varchar       NOT NULL, " +
            " TIME           varchar       NOT NULL, " +
            " LIST_POSITION  integer                )";
            
            stmt.executeUpdate(sql); // push statement to db
            stmt.close(); // close current statment
            c.close(); // close connection with database
        }
        catch (Exception e) // if connection and creating db fails
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /**
     * Function: createNotesTable()
     * @author ZackEvans
     * @param noteName
     * 
     * Function creates a new personal note in the database that is blank.
     * Function uses prepared statments so inserted string doesent bug with '"() characters.
     * Function sets note at the top of the list
     */
    
    public void createPersonalNote(String noteName)
    {
        Connection c = null; // create var for the connection to the database
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // create date format for months, days and years
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); // creates a date forment for Hours, min, seconds
        Calendar date = Calendar.getInstance(); // create a calender var to be later used to calculate date
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection to db
            c.setAutoCommit(false); // turn off autocommit
            
            String sql = "INSERT INTO USER_NOTES (NAME,BODY,DATE,TIME,LIST_POSITION) " + // create sql code string
                         "VALUES (?,?,?,?,?);";
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create a prepared statement object 
            preparedStatement.setString(1,noteName); // set note name
            preparedStatement.setString(2,""); // set body of note blank
            preparedStatement.setString(3,dateFormat.format(date.getTime())); // set months, days and years
            preparedStatement.setString(4,timeFormat.format(date.getTime())); // set Hours, min, seconds
            preparedStatement.setInt(5,1); // set new note to first postion in the list (puts item on top)
            
            preparedStatement.executeUpdate(); // push request to the db
            preparedStatement.close(); // close open prepared statement
            c.commit(); // close commit
            c.close(); // close connection with the db
        }
        
        catch ( Exception e ) // if adding new note to db fails
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /**
     * Function: deleteNote(int idNum)
     * @author ZackEvans
     * @param idNum
     * 
     * delete note from db 
     */
    
    public void deleteNote(int idNum)
    {
        Connection c = null; // create connection to the db
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection to db
            c.setAutoCommit(false); // turn off autocommit
            
            String sql = "DELETE from USER_NOTES where ID = ?;"; // sql code string
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement
            preparedStatement.setInt(1,idNum); // set ? to idNum
            
            preparedStatement.executeUpdate(); // push to db
            preparedStatement.close(); // close ps
            c.commit(); // close commmit
            c.close(); // close connection to the db
        } 

        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /**
     * Function: pushWholeListDownOne()
     * @author ZackEvans
     * 
     * Push all list positions down 1 value
     */
    
    public void pushWholeListDownOne()
    {
        ArrayList<Integer> IDList = new ArrayList<Integer>(); // stores the ID number in the database
        
        IDList.clear(); // clear row number list
        
        for (int i = 1; i <= countItems(); i++) // sets the row number array
        {
            IDList.add(getID(i)); // add the id from the database to the arraylist
        }
        
        for (int i = 0; i < countItems(); i++) 
        {
            int IDNumber = IDList.get(i);
            int listPosition = getListPosition(IDNumber); // get list position from [i] row
            int updatedPosition = listPosition +1; // add 1 to the list position
            updateListPosition(IDNumber, updatedPosition); // update list position with the new number
        }
    }
    
    /**
     * Function: pushWholeListUpOne()
     * @author ZackEvans
     * 
     * push all list positions up 1 value
     */
    
    public void pushWholeListUpOne()
    {
    	ArrayList<Integer> IDList = new ArrayList<Integer>(); // stores the ID number in the database
        
        IDList.clear(); // clear row number list
        
        for (int i = 1; i <= countItems(); i++) // sets the row number array
        {
            IDList.add(getID(i)); // add the id from the database to the row list
        }
        
        for (int i = 0; i < countItems(); i++) 
        {
            int IDNumber = IDList.get(i);
            int listPosition = getListPosition(IDNumber); // get list position from [i] row
            int updatedPosition = listPosition - 1; // add 1 to the list position
            updateListPosition(IDNumber, updatedPosition); // set new value to the [i] row
        }
    }
    
    /**
     * Function: pushItemsAboveClickedDown (int numberClicked)
     * @author ZackEvans
     * @param numberClicked
     * 
     * push list posisitions above the item clicked down in table.
     */
    
    public void pushItemsAboveClickedDown (int numberClicked)
    {
    	for (int i = numberClicked-1; i >= 1; i --) // loop that counts down from number clicked -1
    	{
    		int updatedPosition = i + 1; // add 1 current position
    		updateListPosition(getID(i), updatedPosition); // update position in the db
    	}	
    }
    
    /**
     * Function: countItems()
     * @author ZackEvans
     * @return number of items in the database
     * 
     * return the number of items currently in the table
     */
    
    public int countItems() // counts how may rows there are
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
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM USER_NOTES"); // sql code added to result set to count all items in the db
            
            returnValue = rs.getInt("total"); // set return value to value total
            
            rs.close(); // close open result set
            stmt.close(); // close open statement
            c.close(); // close the connection to the db
        }
        catch ( Exception e )
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        return returnValue; // return the total number of items in the note tabel
    }

    /**
     * Function: getListPosition(int rowNum)
     * @author ZackEvans
     * @param id # of note in the db table
     * @return number of items in the database
     * 
     * return the list position by inputting ID
     */

    public int getListPosition(int id)
    {
        Connection c = null; // create connection to db
        Statement stmt = null;
        int rVal = -1; // create var to be returned.
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create db connection
            c.setAutoCommit(false); // turn off autocommit

            String sql = "SELECT LIST_POSITION FROM USER_NOTES WHERE ID = ?;"; // sql query to find the list postion of a note from the id
            PreparedStatement preparedStatement = c.prepareStatement(sql);	// create prepared statemnt to give to db
            preparedStatement.setInt(1,id); // set ? to id passed to funciton

            stmt = c.createStatement(); // create the statement
            ResultSet rs = preparedStatement.executeQuery(); // create request from the db
            
            rVal = rs.getInt("LIST_POSITION"); // value is set equal to value from db
            
            // close all open connections
            rs.close();
            stmt.close();
            c.close();
        }
        
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        
        return rVal;
    }
    
    /**
     * Function: updateListPosition(int id, int listPosition)
     * @author ZackEvans
     * @param id
     * @param listPosition
     * 
     * Updates the list position in the database based on the id of the note in the tabel
     */
    
    public void updateListPosition(int id, int listPosition)
    {
        Connection dbConnection = null; // create var to hold connection to the database
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
            dbConnection.setAutoCommit(false); // turn off autocommit so I can run my query
            
            String updateTableSQL = "UPDATE USER_NOTES SET LIST_POSITION = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
            preparedStatement.setInt(1, listPosition); // set first ? to listposition
            preparedStatement.setInt(2, id); // set second ? to id
            
            preparedStatement.executeUpdate(); // execute update to db
            
            dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
            preparedStatement.close();
            dbConnection.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    
    /**
     * Function: getNoteName(int ID)
     * @author ZackEvans
     * @param ID
     * @return note name
     * 
     * return the note name from an ID item
     */
    
    public String getNoteName(int ID)
    {
    	Connection c = null; // create connection for db
        Statement stmt = null; // create statement to give to the db
        String rVal = "-1"; // create a value to return that will be set later in try statment
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create the connection to the db
            c.setAutoCommit(false); // turn off autocommit
            
            stmt = c.createStatement(); // create statemnt
            
            String sql = "SELECT NAME FROM USER_NOTES WHERE ID = ?"; // sql code string to get name from ID item
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement from sql code
            preparedStatement.setInt(1, ID); // set ? to ID
            
            ResultSet rs = preparedStatement.executeQuery(); // create prepared statment
            
            rVal = rs.getString("NAME"); // get the value in the NAME col
            
            // close all open connections
            rs.close();
            stmt.close();
            c.close();
        }
        
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        
        return rVal; // reutrn the name of the note 
    }
    
    
    /**
     * Function: getNoteNameFromPosition(int listPosition)
     * @author ZackEvans
     * @param listPosition
     * @return note name
     * 
     * Function returns the note name from the db where the list position is equal to a value
     */
    
    public String getNoteNameFromPosition(int listPosition)
    {
    	// create conncetions
        Connection c = null;
        Statement stmt = null;
        // create value to be returned
        String rVal = "-1";
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection to the db
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT NAME FROM USER_NOTES WHERE LIST_POSITION = ?"; // sql Query code
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create a prepared statement for sql
            preparedStatement.setInt(1, listPosition);
            
            ResultSet rs = preparedStatement.executeQuery(); // create the result set
             
            rVal = rs.getString("NAME"); // get value from NAME col
            
            // close all connections
            rs.close();
            stmt.close();
            c.close();
        } 
        
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        
        return rVal; // reutrn the note name
    }
    
    /**
     * Function: getID(int listPosition)
     * @author ZackEvans
     * @param listPosition
     * @return ID
     * 
     * return the ID of a note based on the list position
     */
    
    public int getID(int listPosition)
    {
        // create connections
    	Connection c = null;
        Statement stmt = null;
        // create value to be returned
        int rVal = -1;
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT ID FROM USER_NOTES WHERE LIST_POSITION = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, listPosition);
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getInt("ID"); // get val from Col ID
      
            // close everything
            rs.close();
            stmt.close();
            c.close();
        } 
        
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        
        return rVal; // return ID
    }
    
    /**
     * Function: getNotesBody(int id)
     * @author ZackEvans
     * @param id
     * @return notes body
     * 
     * reutrn the body of a note
     */
    
    public String getNotesBody(int id)
	{
    	// create connection
		Connection c = null;
	    Statement stmt = null;
	    // create return value
	    String rVal = "-1";
	    
	    try 
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // create connection
	    	c.setAutoCommit(false);
	      
	    	stmt = c.createStatement();
	      
	    	String sql = "SELECT BODY FROM USER_NOTES WHERE ID = ?"; // sql code to get body
	      
	    	PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement
	    	preparedStatement.setInt(1, id); // set ? to id
          
	    	ResultSet rs = preparedStatement.executeQuery(); // create the result set 
	      
	    	rVal = rs.getString("BODY"); // get val from db from the BODY col	
	      
	    	// close all open connections
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    } 
	    catch ( Exception e ) 
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    
	    return rVal; // return the note body
	}
    
    /**
     * Function: updateNotesBody (int id, String body)
     * @author ZackEvans
     * @param id
     * @param body
     * 
     * update note body
     */
    
    public void updateNotesBody (int id, String body)
    {
    	Connection dbConnection = null; // create connection
    	
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection(dbLocation); // create connection
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE USER_NOTES SET BODY = ? WHERE ID = ?"; // sql code to update the note body based on the id
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create prepared statement
			preparedStatement.setString(1, body); // set first ? to body
			preparedStatement.setInt(2, id); // set second ? to id
			
			// update body in the db
			preparedStatement.executeUpdate();
			dbConnection.setAutoCommit(true);
			
			// close all connections
			preparedStatement.close();
			dbConnection.close();
	    } 
		
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
    }
}
