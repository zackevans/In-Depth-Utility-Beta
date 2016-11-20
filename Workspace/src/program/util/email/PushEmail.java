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
 * This class is a runnable thread that will send multiple emails in the background of the program.
 */

public class PushEmail 
{
	public static void sendEmail(String[] to, String subject, String body,BufferPanel bufferPanel)
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
				bufferPanel.showPanel(bufferPanel.lastPanel);
			}
			
			else // if the user has not checked never show again
			{
				SaveAndSendDialog saveAndSendDialog = new SaveAndSendDialog(bufferPanel);
				saveAndSendDialog.launchDialog(); // show dialog
			}
		}	
	}
	
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
