package jobs.handler.email;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import jobs.jobs.CheckAndSendEmailJob;

/**
 * Class: CheckAndSendEmailJobScheduler
 * @author ZackEvans
 *
 * This class contains methods that creates a job for sending emails saved in a db.
 */


public class CheckAndSendEmailJobScheduler 
{
	/**
     * Function: createCheckAndSendEmailJob()
     * @author ZackEvans
     * 
     * Function runs every 30 min to check if emails can be sent.
     */
    
    public static void createCheckAndSendEmailJob()
    {
    	JobDetail job = JobBuilder.newJob(CheckAndSendEmailJob.class) // create the job todo
    			.withIdentity("CheckAndSendEmailJob", "emailJobs").build();
    	
    	Trigger trigger = TriggerBuilder // create a trigger for when it should happen
    			.newTrigger()
    			.withIdentity("CheckAndSendEmail", "emailTriggers")
    			.withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * 1/1 * ? *"))
    			.build();
    
    	Scheduler scheduler; // schedule the process
    	
		try 
		{
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
		} 
		
		catch (SchedulerException e) 
		{
			e.printStackTrace();
		}
    }
}
