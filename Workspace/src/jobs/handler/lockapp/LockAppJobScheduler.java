package jobs.handler.lockapp;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import jobs.jobs.LockAppJob;
import menu.buffer.BufferPanel;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: LockAppJobScheduler
 * @author ZackEvans
 *
 * This class holds methods that create a job for locking the app after X ammout of time
 */

public class LockAppJobScheduler 
{
	BufferPanel bufferPanel;
	static Scheduler scheduler; // schedule the process
	private static final String[] cronExpressions = {"0 0/5 * 1/1 * ? *", "0 0/20 * 1/1 * ? *", "0 0/40 * 1/1 * ? *", "0 0 0/1 1/1 * ? *", "0 0 0/2 1/1 * ? *", "0 0 0/5 1/1 * ? *"};
	
	/**
	 * Constructor: LockAppJobScheduler(BufferPanel bufferPanel)
	 * @param bufferPanel
	 * 
	 * This constructor inherits the bufferPanel object and calls a method to create the Job.
	 */
	
	public LockAppJobScheduler(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
		createLockJob();
	}
	
	/**
	 * Function: createLockJob()
	 * 
	 * This function creates a job that locks the application after a certian interval.
	 */
	
	public void createLockJob()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		JobDetail job = JobBuilder.newJob(LockAppJob.class) // create the job todo
    			.withIdentity("LockAppJob", "lockAppJob").build();
    	
    	Trigger trigger = TriggerBuilder // create a trigger for when it should happen
    			.newTrigger()
    			.withIdentity("LockAppJob", "lockAppTriggers")
    			.withSchedule(CronScheduleBuilder.cronSchedule(cronExpressions[securitySettingsDatabase.getRequirePasswordTimeValue()]))
    			.build();
		try 
		{
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.getContext().put("BufferPanel", bufferPanel); // "Pass" value
			scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
	    } 
		
		catch (SchedulerException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Function: recheduleLockAppJob(int jobTime)
	 * @param jobTime
	 * 
	 * This function makes the job fire at a different interval
	 */
	
	public static void recheduleLockAppJob(int jobTime)
	{
		try 
		{
			Trigger currentTrigger = scheduler.getTrigger(new TriggerKey("LockAppJob", "lockAppTriggers"));
			
			TriggerBuilder triggerBuilder = currentTrigger.getTriggerBuilder();
			
			Trigger newTrigger = TriggerBuilder.newTrigger()
	    			.withIdentity("LockAppJob", "lockAppTriggers")
	    			.withSchedule(CronScheduleBuilder.cronSchedule(cronExpressions[jobTime]))
	    			.build();
			
			scheduler.rescheduleJob(currentTrigger.getKey(), newTrigger);
		} 
		
		catch (SchedulerException e) 
		{
			e.printStackTrace();
		}
	}
}