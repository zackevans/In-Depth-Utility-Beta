package jobs.handler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import jobs.jobs.UpdateTimeJob;

public class JobHandler 
{
	/**
	 * Function: createJobs()
	 * 
	 * This function creates all the jobs to be run
	 */
	
	public void createJobs()
	{
		createUpdateTimeJob();
	}
	
	/**
     * @author ZackEvans
     * Function: createUpdateTimeJob()
     * 
     * every min run a job that updates the time
     */
    
    public static void createUpdateTimeJob()
    {
    	JobDetail job = JobBuilder.newJob(UpdateTimeJob.class) // create a job detail from updatetimejob.java
    			.withIdentity("UpdateTimeJob", "updateJobs").build();
    	
    	Trigger trigger = TriggerBuilder // create trigger
    			.newTrigger()
    			.withIdentity("UpdateTimeJob", "updateJobs")
    			.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")) // set trigger to run every min
    			.build();
    
    	Scheduler scheduler; // create a schedule object
    	
		try 
		{
			// try to schedule the job
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
		} 
		
		catch (SchedulerException e) 
		{
			System.err.println("createUpdateTimeJob() - Unable to Create Email Job");
			e.printStackTrace();
		}
    }
	
	/**
     * Function: createCheckAndSendEmailJob()
     * @author ZackEvans
     * 
     * Function runs every 30 min to check if emails can be sent.
     */
    
//    public static void createCheckAndSendEmailJob()
//    {
//    	JobDetail job = JobBuilder.newJob(CheckAndSendEmailJob.class)
//    			.withIdentity("CheckAndSendEmailJob", "emailJobs").build();
//    	
//    	Trigger trigger = TriggerBuilder
//    			.newTrigger()
//    			.withIdentity("CheckAndSendEmail", "emailTriggers")
//    			.withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * 1/1 * ? *")) 
//    			.build();
//    
//    	Scheduler scheduler;
//    	
//		try 
//		{
//			scheduler = new StdSchedulerFactory().getScheduler();
//			scheduler.start();
//	    	scheduler.scheduleJob(job, trigger);
//		} 
//		
//		catch (SchedulerException e) 
//		{
//			System.out.println("createCheckAndSendEmailJob() - Unable to Create Email Job");
//			e.printStackTrace();
//		}
//    }
}
