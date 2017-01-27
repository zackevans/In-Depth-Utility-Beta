package program.util.email;

import program.util.NetworkUtil;
import sql.saveandsend.SaveAndSendDataBase;

/**
 * Class: PushEmail
 * @author ZackEvans
 *
 * This class holds functions that send emails.
 */

public class PushEmail 
{
	/**
	 * Function: sendEmail(String[] to, String subject, String body)
	 * @param to
	 * @param subject
	 * @param body
	 * 
	 * This function checks if there is internet and starts a thread to send an email.
	 * If there is no internet then it saves the email in the database to be sent later.
	 */
	
	
	public static void sendEmail(String[] to, String subject, String body)
	{
		if(NetworkUtil.isNetworkAvailable()) // if network is available
		{
			Thread sendMail = new Thread(new PushEmailThread(to, subject, body)); // create new thread to send email	in the background
			sendMail.start(); // start the thread
		}
		
		else
		{
			SaveAndSendDataBase saveAndSendDataBase = new SaveAndSendDataBase();
			saveAndSendDataBase.createSavedEmail(to, subject, body); // save emails in db
		}
	}
}
