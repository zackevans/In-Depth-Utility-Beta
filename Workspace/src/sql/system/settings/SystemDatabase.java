package sql.system.settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SystemDatabase 
{
	public void createSystemTable()
	{
		Connection c = null;
	    Statement stmt = null;
	    
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE if not exists SYSTEM_SETTINGS" + 
                  "(ID INTEGER PRIMARY KEY   AUTOINCREMENT, " +
                  "PASSWORD           varchar              ," +
                  "PASS_EXIST         boolean              )";
	      
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
	    System.out.println("System Settings Table created successfully");
	}
	
	public static void createPassword(String password)
	{
		Connection c = null;
	    
	    try 
	    {  
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
	      c.setAutoCommit(false);
	  
	      String sql = "INSERT INTO SYSTEM_SETTINGS (PASSWORD,PASS_EXIST)" +
	      			   "VALUES (?,?);";
	      			   	    
	      PreparedStatement preparedStatement = c.prepareStatement(sql);
	      preparedStatement.setString(1,password);
	      preparedStatement.setBoolean(2,true);

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
	    
	    System.out.println("Password created successfully");	
	}
	
	public void updatePassword(String password)
	{
		Connection dbConnection = null;
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE SYSTEM_SETTINGS SET PASSWORD = ? WHERE ID = ?";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			
			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, 1);
			
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
		
		System.out.println("Updated Password successfully");
	}
	
	public String getPassword()
	{
		Connection c = null;
	    Statement stmt = null;
	    String name = "";
	    
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
	      c.setAutoCommit(false);

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT PASSWORD FROM SYSTEM_SETTINGS WHERE ID = 1" );
	      while ( rs.next() ) 
	      {
	         name = rs.getString("PASSWORD");
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
	    return name;
	}
	
	
	public static boolean checkRow()
	{
		Connection c = null;
	    Statement stmt = null;
	    boolean rVal = false;
	    
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
	      c.setAutoCommit(false);
	     

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery("SELECT * FROM SYSTEM_SETTINGS WHERE ID = 1");
	      while (rs.next()) 
	      {
	    	  int i = rs.getInt("ID");
	   
	    	  if (i == 1)
	    	  {
	    		  rVal = true;
	    	  }
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
	
	public boolean getPassExist()
	{
		Connection c = null;
	    Statement stmt = null;
	    boolean rVal = false;
	    
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
	      c.setAutoCommit(false);
	  
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery("SELECT PASS_EXIST FROM SYSTEM_SETTINGS WHERE ID = 1");
	      while (rs.next()) 
	      {
	    	  rVal = rs.getBoolean("PASS_EXIST");
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
	
	public void updatePassExist(boolean tf)
	{
		Connection dbConnection = null;
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection("jdbc:sqlite:IDU_Files/IDU_User.db");
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE SYSTEM_SETTINGS SET PASS_EXIST = ? WHERE ID = ?";
			
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			preparedStatement.setBoolean(1,tf);
			preparedStatement.setInt(2,1);
			
			dbConnection.commit();
			preparedStatement.executeUpdate();
			preparedStatement.close();
			dbConnection.setAutoCommit(true);
			dbConnection.close();
			System.out.println("Passexist updated");
	    } 
		
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	

}
