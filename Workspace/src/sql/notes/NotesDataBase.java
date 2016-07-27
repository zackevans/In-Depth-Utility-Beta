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

import program.util.security.Encryption;
import sql.util.DatabaseUtil;

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
     * create the notes table in the database if it doesen't already
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
            " LIST_POSITION  integer       NOT NULL )";
            
            stmt.executeUpdate(sql); // push statement to db
            stmt.close(); // close current statement
            c.close(); // close connection with database
        }
        catch (Exception e) // if connection and creating db fails
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
    /**
     * Function: addNewNoteToList(String noteName)
     * @author ZackEvans
     * @param noteName
     * 
     * Function adds a new note to the top of the list and the database
     */
    
    public void addNewNoteToList(String noteName)
    {
    	pushWholeListDownOne(); // push down note to insert one at the top of the list
    	createNote(noteName); // put a note in the first position
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
    
    public void createNote(String noteName)
    {
        Connection c = null; // create var for the connection to the database
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // create date format for months, days and years
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); // creates a date format for Hours, min, seconds
        Calendar date = Calendar.getInstance(); // create a calendar var to be later used to calculate date
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection to db
            c.setAutoCommit(false); // turn off autocommit
            
            String sql = "INSERT INTO USER_NOTES (NAME,BODY,DATE,TIME,LIST_POSITION) " + // create sql code string
                         "VALUES (?,?,?,?,?);";
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create a prepared statement object 
            preparedStatement.setBytes(1,Encryption.encryptString(noteName)); // set note name
            preparedStatement.setBytes(2,Encryption.encryptString("")); // set body of note blank
            preparedStatement.setString(3,dateFormat.format(date.getTime())); // set months, days and years
            preparedStatement.setString(4,timeFormat.format(date.getTime())); // set Hours, min, seconds
            preparedStatement.setInt(5,1); // set new note to first position in the list (puts item on top)
            
            preparedStatement.executeUpdate(); // push request to the db
            preparedStatement.close(); // close open prepared statement
            c.commit(); // close commit
            c.close(); // close connection with the db
        }
        
        catch ( Exception e ) // if adding new note to db fails
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
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
    	for (int i = 1; i <= DatabaseUtil.countItems("USER_NOTES"); i++) // run a loop for as many items in the database
    	{
    		updateListPosition(getID(i), i+1); // update list position +1
    	}	
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
            preparedStatement.setInt(1, listPosition); // set first ? = to list position
            
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
            System.exit(0);
        }
        
        return rVal; // return ID
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
            System.exit(0);
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
           dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
           
           String updateTableSQL = "UPDATE USER_NOTES SET LIST_POSITION = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
           
           PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
           preparedStatement.setInt(1, listPosition); // set first ? to list position
           preparedStatement.setInt(2, id); // set second ? to id
           
           preparedStatement.executeUpdate(); // execute update to db
           
           dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
           preparedStatement.close();
           dbConnection.close();
       }
       catch ( Exception e )
       {
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
           System.exit(0);
       }
   }
    
    /**
     * Function: getListNamesData()
     * @author ZackEvans
     * @return note names sorted by list position
     * 
     * This function returns an arraylist that contains note names sorted from least to greatest LIST_POSITION.
     */
    
    public ArrayList<String> getListNamesData()
    {
		ArrayList<String> returnList = new ArrayList<String>();
		Connection c = null; // create connection for db
        Statement stmt = null; 
		
    	try
    	{
    		Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create the connection to the db
            c.setAutoCommit(false); // turn off autocommit
            
            stmt = c.createStatement(); // create statemnt
            
            String sql = "SELECT NAME FROM USER_NOTES ORDER BY LIST_POSITION ASC;"; // sql code to get notes names sorted by least to greatest (ListPostion)
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement from sql code
            
            ResultSet rs = preparedStatement.executeQuery(); // create result set of data
            
            while(rs.next()) // run through all results
            {
            	returnList.add(Encryption.decryptString(rs.getBytes("NAME"))); // add the names to an arraylist (returnList)
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
    	
    	return returnList;
    }
    
    /**
     * Function: getSortedListNamesData(String searchText)
     * @author ZackEvans
     * @param searchText
     * @return an arraylist with note names sorted from the database
     * 
     * This function returns a arraylist of note names that contain the (searchText)
     */
    
//    public ArrayList<String> getSortedListNamesData(String searchText)
//    {
//    	ArrayList<String> returnList = new ArrayList<String>(); // create array to be returned
//    	String sqlSearchText = "%" + searchText + "%"; // add % to each side to comply with sql syntax
//		Connection c = null; // create connection for db
//        Statement stmt = null; 
//        
//    	try
//    	{
//    		Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection(dbLocation); // create the connection to the db
//            c.setAutoCommit(false); // turn off autocommit
//            
//            stmt = c.createStatement(); // create statement
//            
//            String sql = "SELECT NAME FROM USER_NOTES WHERE NAME LIKE ?;"; // get note names from database that contain the searched text
//            
//            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement from sql code
//            preparedStatement.setString(1, sqlSearchText);
//            
//            ResultSet rs = preparedStatement.executeQuery(); // create result set of data
//            
//            while(rs.next()) // run through all results
//            {
//            	returnList.add(rs.getString("NAME")); // add the names to an arraylist (returnList)
//            }
//            
//            // close all connections
//            rs.close();
//            stmt.close();
//            c.close();
//    	}
//        
//    	catch ( Exception e ) 
//        {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }
//    	
//    	return returnList;
//    }
    
    
    public ArrayList<String> getSortedListNamesData(String searchText)
    {
    	ArrayList<String> returnList = new ArrayList<String>(); // create array to be returned
		Connection c = null; // create connection for db
        Statement stmt = null; 
        
    	try
    	{
    		Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create the connection to the db
            c.setAutoCommit(false); // turn off autocommit
            
            stmt = c.createStatement(); // create statement
            
            String sql = "SELECT NAME FROM USER_NOTES;"; // get all note names in the NAME column
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement from sql code
            
            ResultSet rs = preparedStatement.executeQuery(); // create result set of data
            
            while(rs.next()) // run through all results
            {
            	String noteName = Encryption.decryptString(rs.getBytes("NAME")); // decrypt the note name from the database
            	
            	
            	if(noteName.toLowerCase().contains(searchText.toLowerCase())) // check to see if the note name matches the search text
            	{
            		returnList.add(noteName); // add the note name to the array being returned
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
    	
    	return returnList;
    }
    
    /**
     * Function: getSortedListID(String searchText)
     * @author ZackEvans
     * @param searchText
     * @return arraylist with IDs of notes that contain the (searchText)
     * 
     * This function returns an arraylist of IDs from notes that contain the (searchText)
     */
    
    public ArrayList<Integer> getSortedListID(String searchText)
    {
    	ArrayList<Integer> returnList = new ArrayList<Integer>(); // create array to be returned
		Connection c = null; // create connection for db
        Statement stmt = null; 
        
    	try
    	{
    		Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create the connection to the db
            c.setAutoCommit(false); // turn off autocommit
            stmt = c.createStatement(); // create statemnt
            
            String sql = "SELECT NAME,ID FROM USER_NOTES;"; // get the IDs of the notes names that contain the searched text
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement from sql code
            
            ResultSet rs = preparedStatement.executeQuery(); // create result set of data
            
            while(rs.next()) // run through all results provided from the result set 
            {
            	String noteName = Encryption.decryptString(rs.getBytes("NAME")); // decrypt the note name from the database
            	
            	if(noteName.toLowerCase().contains(searchText.toLowerCase())) // check to see if the note name matches the search text
            	{
            		returnList.add(rs.getInt("ID")); // add the note id to the return array list
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
    	
    	return returnList;
    }
    
    /**
     * Function: pushItemsAboveClickedDown (int numberClicked)
     * @author ZackEvans
     * @param numberClicked
     * 
     * This function moves all notes above the one selected down (+1) in the database
     */
    
    public void pushItemsAboveClickedDown (int numberClicked) // number count from 1
    {
    	for (int i = numberClicked-1; i >= 1; i --) // loop that counts down from number clicked -1 and stops at 1
    	{
    		updateListPosition(getID(i), i+1); // update items one position down
    	}	
    }
    
    /**
     * Function: pushItemsBelowClickedUp(int numberClicked)
     * @author ZackEvans
     * @param numberClicked
     * 
     * When a note is removed from the database this function pushes all the notes under the removed one up one position.
     */
    
    public void pushItemsBelowClickedUp(int idOfNoteClicked)
    {
    	DatabaseUtil databaseUtil = new DatabaseUtil(); // create object to acess class method to count items in db.
    	int listPosition = getListPosition(idOfNoteClicked); // get the list position of the note based on the id passed.
    	
    	// run through all items under the notes selected
    	for (int i = listPosition+1; i <= databaseUtil.countItems("USER_NOTES"); i++)
    	{
    		updateListPosition(getID(i), i-1); // move item 1 position up
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
        for (int i = 1; i <= DatabaseUtil.countItems("USER_NOTES"); i++) // run through all items in the database
        {
        	updateListPosition(getID(i), i-1); // update list position up 1. (its "-" because subtracting 1 makes it closer to the top of the list)
        }
    }
    
    /**
     * Function: deleteNote(int id)
     * @author ZackEvans
     * @param idNum
     * 
     * delete note from db 
     */
    
    public void deleteNote(int id)
    {
        Connection c = null; // create connection to the db
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection to db
            c.setAutoCommit(false); // turn off autocommit
            
            String sql = "DELETE from USER_NOTES where ID = ?;"; // sql code string
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement
            preparedStatement.setInt(1,id); // set ? to id
            
            preparedStatement.executeUpdate(); // push to db
            preparedStatement.close(); // close ps
            c.commit(); // close commit
            c.close(); // close connection to the db
        } 

        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    
    /**
     * Function: getNotesBody(int id)
     * @author ZackEvans
     * @param id
     * @return body of the note
     * 
     * This function takes a id and then return the Body for that ID;
     */
    
    public String getNotesBody(int id)
	{
		Connection c = null; // create connection
	    Statement stmt = null; // statement
	    String body= "-1"; // body
	    
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(dbLocation); // create connection to db
	      c.setAutoCommit(false); // turn off auto commit
	    
	      stmt = c.createStatement(); // create the statement
	      
	      String sql = "SELECT BODY FROM USER_NOTES WHERE ID = ?"; // sql code to select the body from the db
	      
	      PreparedStatement preparedStatement = c.prepareStatement(sql); // create a prepared statement from the database
	      preparedStatement.setInt(1, id); // set first ? = to id
	      
	      ResultSet rs = preparedStatement.executeQuery(); // execute sql query
	      
	      body = Encryption.decryptString(rs.getBytes("BODY")); // get the body name from the database and set it to body
	      
	      // close all connections
	      rs.close();
	      stmt.close();
	      c.close();
	    } 
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0); // kill
	    }
	    
	    return body; // return the body
	}
    
    /**
     * Function: updateNotesBody (int id, String body)
     * @author ZackEvans
     * @param id
     * @param body
     * 
     * This function updates the note body in the database
     */
    
    public void updateNotesBody (int id, String body)
    {
    	Connection dbConnection = null;
    	
		try 
        {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection(dbLocation); // create connection to database
		    dbConnection.setAutoCommit(false); // turn off auto commit
		    
			String updateTableSQL = "UPDATE USER_NOTES SET BODY = ? WHERE ID = ?"; // sql code to update the body of the note based of the id of the note
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create prepared statement
			preparedStatement.setBytes(1, Encryption.encryptString(body)); // set first ? to body 
			preparedStatement.setInt(2, id); // set second ? to id
			
			dbConnection.setAutoCommit(true); // turn on commit
			preparedStatement.executeUpdate(); // execute sql
			
			// close connections
			preparedStatement.close();
			dbConnection.close();
	    } 
		
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
    }
}
