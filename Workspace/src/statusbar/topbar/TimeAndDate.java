package statusbar.topbar;

import java.awt.Graphics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import menu.buffer.BufferPanel;

public class TimeAndDate extends JLabel
{	
	public void showTime(Graphics g)
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
		
		g.drawString(timeAndDate, 590, 15);
	}

}
