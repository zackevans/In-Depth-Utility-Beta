package jobs.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class LockAppJob implements Job
{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		//if(securitySettingsDatabase.get)
		
	}
}
