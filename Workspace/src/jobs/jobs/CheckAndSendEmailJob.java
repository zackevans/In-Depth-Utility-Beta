package jobs.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import program.util.NetworkUtil;
import program.util.email.EmailUtil;
import sql.saveandsend.SaveAndSendDataBase;
import sql.util.DatabaseUtil;

/**
 * Class: CheckAndSendEmailJob
 * @author ZackEvans
 *
 * This class is a job that checks to see if there is Internet and if so sends all the saved offline emails
 */

public class CheckAndSendEmailJob implements Job
{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{	
		SaveAndSendDataBase saveAndSenddb = new SaveAndSendDataBase();
		
		if (NetworkUtil.isNetworkAvailable()) // is there a network connection
		{
			if(DatabaseUtil.countItems("SAVE_AND_SEND_EMAIL") > 0) // are there items in the database`
			{
				int firstdbID = saveAndSenddb.getFirstIndex(); // get the first 
				int numberOfItemsIndb = DatabaseUtil.countItems("SAVE_AND_SEND_EMAIL"); // get number of items in the databse
				
				for(int i = firstdbID; i < firstdbID + numberOfItemsIndb; i++) // run through all the IDs in the db
				{
					// get all parts of the email
					String to = saveAndSenddb.getToAddress(i); 
					String subject = saveAndSenddb.getSubject(i);
					String body  = saveAndSenddb.getBody(i);
				
					EmailUtil.sendNoteEMail(to, subject, body); // send the email
					
					saveAndSenddb.deleteSavedEmail(i); // delete saved email in db
				}
			}	
		}
	}                                                                                                                                                                                                        
}
