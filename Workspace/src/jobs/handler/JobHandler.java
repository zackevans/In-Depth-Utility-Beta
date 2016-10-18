package jobs.handler;

import jobs.handler.email.CheckAndSendEmailJobScheduler;
import jobs.handler.lockapp.LockAppJobScheduler;
import jobs.handler.statusbar.UpdateTimeScheduler;
import menu.buffer.BufferPanel;

public class JobHandler 
{
	BufferPanel bufferPanel;
	
	public JobHandler(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
	}
	
	/**
	 * Function: createJobs()
	 * 
	 * This function creates all the jobs to be run
	 */
	
	public void createJobs()
	{
		UpdateTimeScheduler.createUpdateTimeJob();
		CheckAndSendEmailJobScheduler.createCheckAndSendEmailJob();
		
		LockAppJobScheduler lockAppJobScheduler = new LockAppJobScheduler(bufferPanel);		
	}
}