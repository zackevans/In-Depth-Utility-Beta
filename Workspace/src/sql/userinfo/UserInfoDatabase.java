package sql.userinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import program.util.security.Encryption;
import sql.util.DatabaseUtil;

/**
 * Class: UserInfoDatabase
 * @author ZackEvans
 *
 * This class holds methods that interact with the user info table.
 */

public class UserInfoDatabase 
{
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; // file path to db
	
	/**
	 * Function: createUserInfoTable()
	 * 
	 * This function creates the USER_INFO table in the database. If there is not an initial row then it creates one.
	 */
	
    public void createUserInfoTable()
    {
        Connection c = null; // create var for the connection to the database
        Statement stmt = null; // create a var for statement
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // connect to database
            
            stmt = c.createStatement(); //create statement for db 
            
            String sql = "CREATE TABLE if not exists USER_INFO" + // create table sql code
            "(ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
            " FIRST_NAME     varchar       		   , " +
            " LAST_NAME      varchar               , " +
            " COMPANY        varchar     		   , " +
            " PHONE          varchar       		   , " +
            " EMAIL          varchar       		   , " +
            " ADDRESS        varchar       		   , " +
            " BIRTHDAY_MONTH integer       		   , " +
            " BIRTHDAY_DAY   integer       		   , " +
            " BIRTHDAY_YEAR  integer                )";
            
            stmt.executeUpdate(sql); // push statement to db
            stmt.close(); // close current statement
            c.close(); // close connection with database
        }
        catch (Exception e) // if connection and creating db fails
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        if(DatabaseUtil.countItems("USER_INFO") == 0) // check if the table is empty
	    {
	    	createInitialRow(); // create the defult row
	    }    
    }
    
    /**
     * Function: createInitialRow()
     * 
     * This function creates the first row in the table where the settings values will be stored.
     */
    
    public void createInitialRow()
    {
    	Connection c = null; // create connection
    	
	    try 
	    {  
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection(dbLocation); // connect to db
	    	c.setAutoCommit(false);
	  
	    	String sql = "INSERT INTO USER_INFO (FIRST_NAME,LAST_NAME,COMPANY,PHONE,EMAIL,ADDRESS,BIRTHDAY_MONTH,BIRTHDAY_DAY,BIRTHDAY_YEAR)" +  // sql code to create a new row in db
	    				 "VALUES (?,?,?,?,?,?,?,?,?);"; 
	      			   	    
	    	PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement for database
	    	preparedStatement.setBytes(1,Encryption.encryptString(""));
	    	preparedStatement.setBytes(2,Encryption.encryptString(""));
	    	preparedStatement.setBytes(3,Encryption.encryptString(""));
	    	preparedStatement.setBytes(4,Encryption.encryptString(""));
	    	preparedStatement.setBytes(5,Encryption.encryptString(""));
	    	preparedStatement.setBytes(6,Encryption.encryptString(""));
	    	preparedStatement.setInt(7,0);
	    	preparedStatement.setInt(8,0);
	    	preparedStatement.setInt(9,0);
	    	
	    	preparedStatement.executeUpdate(); // execute statement
	    	
	    	// close connections
	    	preparedStatement.close();
	    	c.commit();
	    	c.close();
	    } 
	    
	    catch ( Exception e ) 
	    {
	    	System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    	System.exit(0);
	    }
    }
    
    /**
     * Function: updateFirstName(String updatedString)
     * @param updatedString
     * 
     * This function updates the FIRST_NAME value in the first row of the table
     */
    
    public void updateFirstName(String updatedString)
    {
    	Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE USER_INFO SET FIRST_NAME = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
	           
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
			preparedStatement.setBytes(1, Encryption.encryptString(updatedString)); // set first ? to list position
			preparedStatement.setInt(2, 1); // set second ? to id
	           
			preparedStatement.executeUpdate(); // execute update to db
	           
			dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
			preparedStatement.close();
			dbConnection.close();
		} 
		
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
    }
    
    /**
     * Function: getFirstName();
     * @return FIRST_NAME value
     * 
     * This function returns the FIRST_NAME value in the first row of the database.
     */
    
    public String getFirstName()
    {
    	Connection c = null;
        Statement stmt = null;
        String rVal  = "";  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT FIRST_NAME FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = Encryption.decryptString(rs.getBytes("FIRST_NAME")); // get val from Col ID
      
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
     * Function: updateLastName(String updatedString)
     * @param updatedString
     * 
     * This function updates the LAST_NAME value in the first row in the table.
     */
    
    public void updateLastName(String updatedString)
    {
    	Connection dbConnection = null; // create var to hold connection to the database
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
			dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
			
			String updateTableSQL = "UPDATE USER_INFO SET LAST_NAME = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
	           
			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
			preparedStatement.setBytes(1, Encryption.encryptString(updatedString)); // set first ? to list position
			preparedStatement.setInt(2, 1); // set second ? to id
	           
			preparedStatement.executeUpdate(); // execute update to db
	           
			dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
			preparedStatement.close();
			dbConnection.close();
		} 
		
		catch (Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
    }
    
    /**
     * Function: getLastName()
     * @return LAST_NAME value from database.
     * 
     * This function returns the LAST_NAME value from the first column in the table
     */
    
    public String getLastName()
    {
    	Connection c = null;
        Statement stmt = null;
        String rVal  = "";  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT LAST_NAME FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = Encryption.decryptString(rs.getBytes("LAST_NAME")); // get val from Col ID
      
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
     * Function: updateCompanyName(String updatedString)
     * @param updatedString
     * 
     * This function updates the COMPANY value in the first row of the table.
     */
    
    public void updateCompanyName(String updatedString)
    {
        Connection dbConnection = null; // create var to hold connection to the database
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
            dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
            
            String updateTableSQL = "UPDATE USER_INFO SET COMPANY = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
               
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
            preparedStatement.setBytes(1, Encryption.encryptString(updatedString)); // set first ? to list position
            preparedStatement.setInt(2, 1); // set second ? to id
               
            preparedStatement.executeUpdate(); // execute update to db
               
            dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
            preparedStatement.close();
            dbConnection.close();
        } 
        
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    /**
     * Function: getCompanyName()
     * @return COMPANY value in database.
     * 
     * This function returns the COMPANY value in the database.
     */
    
    public String getCompanyName()
    {
        Connection c = null;
        Statement stmt = null;
        String rVal  = "";  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT COMPANY FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = Encryption.decryptString(rs.getBytes("COMPANY")); // get val from Col ID
      
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
     * Function: 
     * @param updatedString
     * 
     * This function updates the PHONE value in the table.
     */
    
    public void updatePhoneNumber(String updatedString)
    {
        Connection dbConnection = null; // create var to hold connection to the database
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
            dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
            
            String updateTableSQL = "UPDATE USER_INFO SET PHONE = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
               
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
            preparedStatement.setBytes(1, Encryption.encryptString(updatedString)); // set first ? to list position
            preparedStatement.setInt(2, 1); // set second ? to id
               
            preparedStatement.executeUpdate(); // execute update to db
               
            dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
            preparedStatement.close();
            dbConnection.close();
        } 
        
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    /**
     * Function: getPhoneNumber()
     * @return PHONE value in the database.
     * 
     * This function returns the PHONE value from the first row in the table.
     */
    
    public String getPhoneNumber()
    {
        Connection c = null;
        Statement stmt = null;
        String rVal  = "";  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT PHONE FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = Encryption.decryptString(rs.getBytes("PHONE")); // get val from Col ID
      
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
     * Function: updateEmail(String updatedString)
     * @param updatedString
     * 
     * This function updates the EMAIL value in the database.
     */
    
    public void updateEmail(String updatedString)
    {
        Connection dbConnection = null; // create var to hold connection to the database
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
            dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
            
            String updateTableSQL = "UPDATE USER_INFO SET EMAIL = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
               
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
            preparedStatement.setBytes(1, Encryption.encryptString(updatedString)); // set first ? to list position
            preparedStatement.setInt(2, 1); // set second ? to id
               
            preparedStatement.executeUpdate(); // execute update to db
               
            dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
            preparedStatement.close();
            dbConnection.close();
        } 
        
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    /**
     * Function: 
     * @return EMAIL value from database.
     * 
     * This function returns the EMAIL value from the first row of the table.
     */
    
    public String getEmail()
    {
        Connection c = null;
        Statement stmt = null;
        String rVal  = "";  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT EMAIL FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = Encryption.decryptString(rs.getBytes("EMAIL")); // get val from Col ID
      
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
     * Function: updateAddress(String updatedString)
     * @param updatedString
     * 
     * This function updates the ADDRESS value in the first row of the table.
     */
    
    public void updateAddress(String updatedString)
    {
        Connection dbConnection = null; // create var to hold connection to the database
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
            dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
            
            String updateTableSQL = "UPDATE USER_INFO SET ADDRESS = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
               
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
            preparedStatement.setBytes(1, Encryption.encryptString(updatedString)); // set first ? to list position
            preparedStatement.setInt(2, 1); // set second ? to id
               
            preparedStatement.executeUpdate(); // execute update to db
               
            dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
            preparedStatement.close();
            dbConnection.close();
        } 
        
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    /**
     * Function: 
     * @return ADDRESS value in the database.
     * 
     * This function returns the ADDRESS value in the first row of the table.
     */
    
    public String getAddress()
    {
        Connection c = null;
        Statement stmt = null;
        String rVal  = "";  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT ADDRESS FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = Encryption.decryptString(rs.getBytes("ADDRESS")); // get val from Col ID
      
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
    * Function: updateBirthdayMonth (int updatedNumber)
    * @param updatedNumber
    * 
    * This function updates the BIRTHDAY_MONTH value in the database.
    */
    
    public void updateBirthdayMonth (int updatedNumber)
    {
        Connection dbConnection = null; // create var to hold connection to the database
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
            dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
            
            String updateTableSQL = "UPDATE USER_INFO SET BIRTHDAY_MONTH = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
               
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
            preparedStatement.setInt(1, updatedNumber); // set first ? to list position
            preparedStatement.setInt(2, 1); // set second ? to id
               
            preparedStatement.executeUpdate(); // execute update to db
               
            dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
            preparedStatement.close();
            dbConnection.close();
        } 
        
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
        
    /**
     * Function: getBirthdayMonth()
     * @return BIRTHDAY_MONTH value from database.
     * 
     * This function returns the BIRTHDAY_MONTH value from the database.
     */
    
    public int getBirthdayMonth()
    {
        Connection c = null;
        Statement stmt = null;
        int rVal  = 0;  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT BIRTHDAY_MONTH FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getInt("BIRTHDAY_MONTH"); // get val from Col ID
      
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
     * Function: updateBirthdayDay (int updatedNumber)
     * @param updatedNumber
     * 
     * This function updates the BIRTHDAY_DAY value in the first row of the table
     */
    
    public void updateBirthdayDay (int updatedNumber)
    {
        Connection dbConnection = null; // create var to hold connection to the database
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
            dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
            
            String updateTableSQL = "UPDATE USER_INFO SET BIRTHDAY_DAY = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
               
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
            preparedStatement.setInt(1, updatedNumber); // set first ? to list position
            preparedStatement.setInt(2, 1); // set second ? to id
               
            preparedStatement.executeUpdate(); // execute update to db
               
            dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
            preparedStatement.close();
            dbConnection.close();
        } 
        
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
        
    /**
     * Function: getBirthdayDay()
     * @return BIRTHDAY_DAY value from database.
     * 
     * This function returns the BIRTHDAY_DAY value from the first row of the table.
     */
    
    public int getBirthdayDay()
    {
        Connection c = null;
        Statement stmt = null;
        int rVal  = 0;  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT BIRTHDAY_DAY FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getInt("BIRTHDAY_DAY"); // get val from Col ID
      
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
     * Function: updateBirthdayYear (int updatedNumber)
     * @param updatedNumber
     * 
     * This function updates the BIRTHDAY_YEAR value in the first row of the table.
     */
    
    public void updateBirthdayYear (int updatedNumber)
    {
        Connection dbConnection = null; // create var to hold connection to the database
        
        try 
        {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(dbLocation); // create connection to the database
            dbConnection.setAutoCommit(false); // turn off auto commit so I can run my query
            
            String updateTableSQL = "UPDATE USER_INFO SET BIRTHDAY_YEAR = ? WHERE ID = ?"; // sql code string that updates the the list postion based on a ID
               
            PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL); // create sql prepared statement
            preparedStatement.setInt(1, updatedNumber); // set first ? to list position
            preparedStatement.setInt(2, 1); // set second ? to id
               
            preparedStatement.executeUpdate(); // execute update to db
               
            dbConnection.setAutoCommit(true); // turn on auto commit to allow commit to db
            preparedStatement.close();
            dbConnection.close();
        } 
        
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
        
    /**
     * Function: getBirthdayYear()
     * @return BIRTHDAY_YEAR value from database.
     * 
     * This function updates the BIRTHDAY_YEAR value in the first row of the table.
     */
    
    public int getBirthdayYear()
    {
        Connection c = null;
        Statement stmt = null;
        int rVal  = 0;  // create value to be returned
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(dbLocation); // create connection
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            
            String sql = "SELECT BIRTHDAY_YEAR FROM USER_INFO WHERE ID = ?"; // SQL code to get ID
            
            PreparedStatement preparedStatement = c.prepareStatement(sql); // create prepared statement 
            preparedStatement.setInt(1, 1); // set first ? = to list position
            
            ResultSet rs = preparedStatement.executeQuery(); // run query
            
            rVal = rs.getInt("BIRTHDAY_YEAR"); // get val from Col ID
      
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
}