package sql.saveandsend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SaveAndSendDataBase 
{
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db";
	
	public void createSaveAndSendTable()
	{
		Connection c = null;
        Statement stmt = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            System.out.println("Opened database successfully");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE if not exists SAVE_AND_SEND_EMAIL" +
                    "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
                    " TOADDRESS      varchar       NOT NULL, " +
                    " SUBJECT        varchar               , " +
                    " BODY           varchar                )";
            
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        System.out.println("Save And Send  Table created successfully");
	}
	
	public void createSavedEmail(String[] to, String subject, String body)
	{
		Connection c = null;
		
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            String sql = "INSERT INTO SAVE_AND_SEND_EMAIL (TOADDRESS,SUBJECT,BODY) " +
            "VALUES (?,?,?);";
            
            String sendTO = to[0];
            
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1,sendTO);
            preparedStatement.setString(2, subject);
            preparedStatement.setString(3,body);
            
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
        
        System.out.println("createSavedEmail(String[] to, String subject, String body) - Email Created Successfully");
	}
	
	public int getFirstIndex()
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
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM SAVE_AND_SEND_EMAIL");
            
            rVal = rs.getInt("ID");
            
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
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM SAVE_AND_SEND_EMAIL");
            
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
	
	public String getToAddress(int id)
	{
		Connection c = null;
        Statement stmt = null;
        String rVal = "-1";
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            
            String sql = "SELECT TOADDRESS FROM SAVE_AND_SEND_EMAIL WHERE ID = ?;";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            
           stmt = c.createStatement();
           ResultSet rs = preparedStatement.executeQuery();
            
            rVal = rs.getString("TOADDRESS");
            
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
	
	public String getSubject(int id)
	{
		Connection c = null;
        Statement stmt = null;
        String rVal = "-1";
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            
            String sql = "SELECT SUBJECT FROM SAVE_AND_SEND_EMAIL WHERE ID = ?;";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            
            stmt = c.createStatement();
            ResultSet rs = preparedStatement.executeQuery();
            
            rVal = rs.getString("SUBJECT");
            
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
	
	public String getBody(int id)
	{
		Connection c = null;
        Statement stmt = null;
        String rVal = "-1";
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            
            String sql = "SELECT BODY FROM SAVE_AND_SEND_EMAIL WHERE ID = ?;";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            
            stmt = c.createStatement();
            ResultSet rs = preparedStatement.executeQuery();
            
            rVal = rs.getString("BODY");
            
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
	
	public void deleteSavedEmail(int idNum)
    {
        Connection c = null;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            String sql = "DELETE from SAVE_AND_SEND_EMAIL where ID = ?;";
            
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1,idNum);
            
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
        
        System.out.println("Saved Note Deleted Successfully");
    }
    
	
}
