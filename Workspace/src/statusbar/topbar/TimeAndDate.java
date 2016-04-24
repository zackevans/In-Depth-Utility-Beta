package statusbar.topbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class TimeAndDate
{	
	public static JLabel timeLabel = new JLabel();
	
	
	
	
	
	public void showTime()
	{
		DateFormat hours = new SimpleDateFormat("hh");
		DateFormat min = new SimpleDateFormat("mm a");
		DateFormat DF24 = new SimpleDateFormat("HH:mm");
		String timeAndDate = "";
		
		Date date = new Date();
		
		String hrString = hours.format(date);
		int hrInt = Integer.parseInt(hrString);
		
		if (hrInt <= 9)
		{
			 timeAndDate = (hours.format(date).substring(1)) + ":" +  min.format(date);
		}
		
		else
		{
			timeAndDate = (hours.format(date)) + ":" +  min.format(date);
		}
		
		timeLabel.setText(timeAndDate);
	}
}
