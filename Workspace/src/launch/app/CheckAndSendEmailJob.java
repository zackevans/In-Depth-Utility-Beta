package launch.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import program.mail.SendMail;
import sql.saveandsend.SaveAndSendDataBase;

public class CheckAndSendEmailJob implements Job
{
	private SaveAndSendDataBase saveAndSendDb = new SaveAndSendDataBase();
	private SendMail sendEmail = new SendMail();
	
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{
		if (isNetworkAvailable())
		{
			if(saveAndSendDb.countItems() > 0)
			{
				int firstdbIndex = saveAndSendDb.getFirstIndex();
				int numberOfItemsIndb = saveAndSendDb.countItems();
			
				System.out.println();
				
				for(int i = firstdbIndex; i < firstdbIndex + numberOfItemsIndb; i++)
				{
					String[] to = {saveAndSendDb.getToAddress(i)};
					String subject = saveAndSendDb.getSubject(i);
					String body  = saveAndSendDb.getBody(i);
					
					sendEmail.sendNoteEMail(to, subject, body);
					
					saveAndSendDb.deleteSavedEmail(i);
				}
			}	
		}
	}
	
	private static boolean isNetworkAvailable() 
	{                                                                                                                                                                                                 
	    try 
	    {                                                                                                                                                                                                                                 
	        final URL url = new URL("http://www.google.com");                                                                                                                                                                                 
	        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
	        conn.connect();                                                                                                                                                                                                                   
	        return true;                                                                                                                                                                                                                      
	    } 
	    catch (MalformedURLException e) 
	    {                                                                                                                                                                                                   
	        throw new RuntimeException(e);                                                                                                                                                                                                    
	    } 
	    catch (IOException e) 
	    {                                                                                                                                                                                                             
	        return false;                                                                                                                                                                                                                     
	    }                                                                                                                                                                                                                                     
	}
}
