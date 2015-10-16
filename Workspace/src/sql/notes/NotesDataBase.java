package sql.notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	      		  " LIST_POSITION  integer       NOT NULL )";
	      
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
	      preparedStatement.setInt(5,1); // set new note to first postion in the list

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
	
	public void deleteNote(int dbNum)
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(dbLocation);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      
	      String sql = "DELETE from USER_NOTES where ID = " + dbNum +";";
	      
	      stmt.executeUpdate(sql);
	      c.commit();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
	    System.out.println("Deleted Item");
	}
	
	
	public int countItems()
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
	      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM USER_NOTES");
	      while (rs.next()) 
	      {
	    	  //returnValue = rs.getInt("ID");
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
	    
	    return returnValue;
	}

}
