package jobs.handler;

import jobs.handler.email.CheckAndSendEmailJobScheduler;
import jobs.handler.statusbar.UpdateTimeScheduler;

public class JobHandler 
{
	/**
	 * Function: createJobs()
	 * 
	 * This function creates all the jobs to be run
	 */
	
	public void createJobs()
	{
		UpdateTimeScheduler.createUpdateTimeJob();
		CheckAndSendEmailJobScheduler.createCheckAndSendEmailJob();
	}
	
	

	
}
