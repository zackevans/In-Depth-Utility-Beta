package launch.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import statusbar.addons.TimeAndDate;

public class UpdateTimeJob implements Job
{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{
		TimeAndDate timeAndDate = new  TimeAndDate();
		
		timeAndDate.showTime();
		timeAndDate.timeLabel.repaint();
	}            
}
