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
     * Function: pushWholeListDownOne()
     * @author ZackEvans
     * 
     * Push all list positions down 1 value
     */
    
    public void pushWholeListDownOne()
    {
    	DatabaseUtil databaseUtil = new DatabaseUtil();
        ArrayList<Integer> IDList = new ArrayList<Integer>(); // stores the ID number in the database
        
        IDList.clear(); // clear row number list
        
        for (int i = 1; i <= databaseUtil.countItems("USER_NOTES"); i++) // sets the row number array
        {
            IDList.add(getID(i)); // add the id from the database to the arraylist
        }
        
        for (int i = 0; i < databaseUtil.countItems("USER_NOTES"); i++) 
        {
            int IDNumber = IDList.get(i);
            int listPosition = getListPosition(IDNumber); // get list position from [i] row
            int updatedPosition = listPosition +1; // add 1 to the list position
            updateListPosition(IDNumber, updatedPosition); // update list position with the new number
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
    
    public void createNote(String noteName)
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
            preparedStatement.setInt(1, listPosition); // set first ? to list position
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
}
