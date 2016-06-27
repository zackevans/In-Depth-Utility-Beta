package program.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


/**
 * Class: EmailUtil
 * @author ZackEvans
 *
 * This class holds useful tools for Emails
 */

public class EmailUtil 
{
	/**
	 * Function: validateEmailAddress(String email) 
	 * @author ZackEvans
	 * @param email
	 * @return If the email passed into the method it a real email
	 * 
	 * This function returns if a email exists or not
	 */
	
	public static boolean validateEmailAddress(String email) 
	{
		boolean result = true;
		
		try 
		{
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate(); // try to validate email
		} 
		 
		catch (AddressException ex) 
		{
			result = false; // if email fails then set valid to false
		}
		 
		return result;
	}
}
