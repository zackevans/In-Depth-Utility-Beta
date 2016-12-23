package statusbar.addons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Class: TimeAndDate
 * @author ZackEvans
 * 
 * This class contains the time label that is shown in the status bar
 */

public class TimeAndDate
{	
	public static JLabel timeLabel = new JLabel(); // JLabel that is set to the current time
	
	/**
	 * Function: createLabel()
	 * 
	 * this function sets up label settings
	 */
	
	public void createLabel()
	{
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT); // set text to format to the right
	}
	
	/**
	 * Function: showTime()
	 * 
	 * This function sets label (timeLabel) to the current time
	 */
	
	public void showTime()
	{
		DateFormat hours = new SimpleDateFormat("hh"); // create format for hours
		DateFormat min = new SimpleDateFormat("mm a"); // create format for minutes including AM/PM
		String timeAndDate = ""; // create var to hold the final time to update the label.
		
		Date date = new Date(); // create date object
		
		String hrString = hours.format(date); // get the current hours
		int hrInt = Integer.parseInt(hrString); // turn the string time into a int
		
		// convert time to 12 hr time
		if (hrInt <= 9)
		{
			 timeAndDate = (hours.format(date).substring(1)) + ":" +  min.format(date);
		}
		
		else
		{
			timeAndDate = (hours.format(date)) + ":" +  min.format(date);
		}
		
		timeLabel.setText(timeAndDate); // set label to string
	}
}
