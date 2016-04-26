package sql.saveandsend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SaveAndSendSettingsDataBase 
{
	
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db";
	
	public void createSaveAndSendSettingsTable()
	{
		Connection c = null;
        Statement stmt = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            System.out.println("Opened database successfully");
            
            stmt = c.createStatement();
            
            String sql = "CREATE TABLE if not exists SAVE_AND_SEND_SETTINGS" +
                    "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
                    " NEVERSHOW      boolean       NOT NULL )";
       
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        System.out.println("Save And Send Settings Table created successfully");
        
        if(checkIfRowExists() == false)
        {
        	try
            {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(dbLocation);
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");
                
                String sql = "INSERT INTO SAVE_AND_SEND_SETTINGS (NEVERSHOW) " +
                "VALUES (?);";
                
                PreparedStatement preparedStatement = c.prepareStatement(sql);
                preparedStatement.setBoolean(1,false);
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
                c.commit();
                c.close();
            }
            
            catch (Exception e)
            {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            
            System.out.println("Row Created Successfully");
        }
	}
	
	public boolean checkIfRowExists()
	{
		Connection c = null;
	    Statement stmt = null;
	    boolean rVal = false;
	    
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(dbLocation);
	      c.setAutoCommit(false);
	  
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery("SELECT * FROM SAVE_AND_SEND_SETTINGS WHERE ID = 1");
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
	
	public void updateNeverShow(boolean tf)
	{
		Connection dbConnection = null;
		
		try 
	    {
			Class.forName("org.sqlite.JDBC");
		    dbConnection = DriverManager.getConnection(dbLocation);
		    dbConnection.setAutoCommit(false);
		    
			String updateTableSQL = "UPDATE SAVE_AND_SEND_SETTINGS SET NEVERSHOW = ? WHERE ID = ?";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			
			preparedStatement.setBoolean(1, tf);
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
		
		System.out.println("Updated nevershow value successfully");
	}
	
	public boolean getNeverShow()
	{
		Connection c = null;
        Statement stmt = null;
        boolean rVal = false;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            
            String sql = "SELECT NEVERSHOW FROM SAVE_AND_SEND_SETTINGS WHERE ID = ?;";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            
           stmt = c.createStatement();
           ResultSet rs = preparedStatement.executeQuery();
            
            rVal = rs.getBoolean("NEVERSHOW");
            
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
