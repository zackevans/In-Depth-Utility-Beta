package jobs.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: LockAppJob
 * @author ZackEvans
 * @see login panel
 *
 * This class is a job that locks the app after a user specified amount of time
 */

public class LockAppJob implements Job
{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		if(securitySettingsDatabase.getRequirePasswordValue())
		{
			SchedulerContext schedulerContext; // create object to get "Passed" value
			
			try 
			{
				schedulerContext = arg0.getScheduler().getContext(); // get "Passed value"
				BufferPanel bufferPanel = (BufferPanel) schedulerContext.get("BufferPanel"); // get the object passed
				LaunchApp.hideAllOtherWindows(); // hide all windows that could possibly showing
				bufferPanel.showRawPanel("LOGIN_PANEL"); // show the login panel
			} 
			catch (SchedulerException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
