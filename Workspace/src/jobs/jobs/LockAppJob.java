package jobs.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;

import menu.buffer.BufferPanel;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class LockAppJob implements Job
{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		if(securitySettingsDatabase.getRequirePasswordValue())
		{
			SchedulerContext schedulerContext;
			try 
			{
				schedulerContext = arg0.getScheduler().getContext();
				BufferPanel bufferPanel = (BufferPanel) schedulerContext.get("BufferPanel");
				bufferPanel.showRawPanel("LOGIN_PANEL");
			} 
			catch (SchedulerException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
