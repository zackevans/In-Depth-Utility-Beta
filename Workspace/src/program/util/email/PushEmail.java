package program.util.email;

import menu.buffer.BufferPanel;
import menu.notes.mailnote.saveandsenddialog.SaveAndSendDialog;
import program.util.NetworkUtil;
import sql.saveandsend.SaveAndSendDataBase;
import sql.saveandsend.SaveAndSendSettingsDataBase;

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
	
	/**
	 * Function: sendEmailAndShowDialog(String[] to, String subject, String body,BufferPanel bufferPanel)
	 * @param to
	 * @param subject
	 * @param body
	 * @param bufferPanel
	 * 
	 * This function checks if there is internet and starts a thread to send an email.
	 * If there is no email then it shows a dialog to ask if you want to save it.
	 * This is used when sharing a note.
	 */
	
	
	public static void sendEmailAndShowDialog(String[] to, String subject, String body,BufferPanel bufferPanel)
	{
		if(NetworkUtil.isNetworkAvailable()) // if network is available
		{
			Thread sendMail = new Thread(new PushEmailThread(to, subject, body)); // create new thread to send email	in the background
			sendMail.start(); // start the thread
		}
		
		else
		{
			SaveAndSendSettingsDataBase saveAndSendSettingsDataBase = new SaveAndSendSettingsDataBase();
			
			if (saveAndSendSettingsDataBase.getNeverShow() == true) // if the user never wants to see the dialog again
			{
				SaveAndSendDataBase saveAndSendDataBase = new SaveAndSendDataBase();
				saveAndSendDataBase.createSavedEmail(to, subject, body); // save emails in db
				bufferPanel.showPanel(BufferPanel.lastPanel);
			}
			
			else // if the user has not checked never show again
			{
				SaveAndSendDialog saveAndSendDialog = new SaveAndSendDialog(bufferPanel);
				saveAndSendDialog.launchDialog(); // show dialog
			}
		}	
	}
}
