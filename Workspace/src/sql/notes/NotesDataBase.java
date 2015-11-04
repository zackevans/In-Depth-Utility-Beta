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

public class NotesDataBase
{
    final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db";
    
    public void createNotesTable()
    {
        Connection c = null;
        Statement stmt = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            System.out.println("Opened database successfully");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE if not exists USER_NOTES" +
            "(ID INTEGER PRIMARY KEY   AUTOINCREMENT, " +
            " NAME           varchar       NOT NULL, " +
            " BODY           varchar               , " +
            " DATE           varchar       NOT NULL, " +
            " TIME           varchar       NOT NULL, " +
            " LIST_POSITION  integer                )";
            
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        System.out.println("Notes Table created successfully");
    }
    
    
    public void createPersonalNote(String noteName)
    {
        Connection c = null;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar date = Calendar.getInstance();
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            String sql = "INSERT INTO USER_NOTES (NAME,BODY,DATE,TIME,LIST_POSITION) " +
            "VALUES (?,?,?,?,?);";
            
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,noteName);
            preparedStatement.setString(2,"");
            preparedStatement.setString(3,dateFormat.format(date.getTime()));
            preparedStatement.setString(4,timeFormat.format(date.getTime()));
            preparedStatement.setInt(5,1); // set new note to first postion in the list (puts item on top)
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.commit();
            c.close();
        } catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        System.out.println("Note Created Successfully");
    }
    
    public void deleteNote(int idNum)
    {
        Connection c = null;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            String sql = "DELETE from USER_NOTES where ID = ?;";
            
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,idNum);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.commit();
            c.close();
        } 

        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        System.out.println("Note Deleted Successfully");
    }
    
    public void pushWholeListDownOne()
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
            int updatedPosition = listPosition +1; // add 1 to the list position
            updateListPosition(IDNumber, updatedPosition); // set new value to the [i] row
        }
    }
    
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
    
    public void pushItemsAboveClickedDown (int numberClicked)
    {
    	for (int i = numberClicked-1; i >= 1; i --) // loop that counts down from number clicked -1
    	{
    		int updatedPosition = i + 1;
    		updateListPosition(getID(i), updatedPosition);
    	}	
    }
    
    public int countItems() // counts how may rows there are
    {
        Connection c = null;
        Statement stmt = null;
        int returnValue = -1;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM USER_NOTES");
            
            returnValue = rs.getInt("total"); // set return value to value total
            
            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        return returnValue;
    }
    
    public int getListPosition(int rowNum)
    {
        Connection c = null;
        Statement stmt = null;
        int rVal = -1;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT LIST_POSITION FROM USER_NOTES WHERE ID ="+ rowNum +";" );
            
            while (rs.next())
            {
                rVal = rs.getInt("LIST_POSITION");
            }
            
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
    
    
    public void updateListPosition(int id, int listPosition)
    {
        Connection dbConnection = null;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation);
            dbConnection.setAutoCommit(false);
            
            String updateTableSQL = "UPDATE USER_NOTES SET LIST_POSITION = ? WHERE ID = ?";
            
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
            preparedStatement.setInt(1, listPosition);
            preparedStatement.setInt(2, id);
            
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
    }
    
    
    public String getNoteNameFromPosition(int listPosition)
    {
        Connection c = null;
        Statement stmt = null;
        String rVal = "-1";
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery( "SELECT NAME FROM USER_NOTES WHERE LIST_POSITION = "+ listPosition +";" );
            
            while (rs.next()) 
            {
                rVal = rs.getString("NAME");
            }
            
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
    
    public int getID(int listPosition)
    {
        Connection c = null;
        Statement stmt = null;
        int rVal = -1;
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT ID FROM USER_NOTES WHERE LIST_POSITION = "+ listPosition +";" );
            while (rs.next()) 
            {
                rVal = rs.getInt("ID");
            }
            
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
}
