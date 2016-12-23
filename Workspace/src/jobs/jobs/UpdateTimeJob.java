package jobs.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import statusbar.addons.TimeAndDate;

/**
 * Class: UpdateTimeJob
 * @author ZackEvans
 * @see updated time in menu
 *
 * This class is a job that updates the time in the statusbar
 */

public class UpdateTimeJob implements Job
{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{
		TimeAndDate timeAndDate = new  TimeAndDate();
		
		timeAndDate.showTime(); // updates the time in the status bar
		TimeAndDate.timeLabel.repaint(); // repaint the label in the status bar
	}   
}