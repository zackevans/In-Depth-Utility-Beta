package launch.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import program.mail.SendMail;
import program.util.NetworkUtil;
import sql.saveandsend.SaveAndSendDataBase;

public class CheckAndSendEmailJob implements Job
{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{
		SaveAndSendDataBase saveAndSendDb = new SaveAndSendDataBase();
		SendMail sendEmail = new SendMail();
		NetworkUtil networkUtil = new NetworkUtil();
		
		if (networkUtil.isNetworkAvailable())
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
					
					System.out.println("Send Email Job complete");
				}
			}	
		}
	}                                                                                                                                                                                                        
}
