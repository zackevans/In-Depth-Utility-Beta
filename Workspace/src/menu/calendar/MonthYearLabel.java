package menu.calendar;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import menu.buffer.BufferPanel;

public class MonthYearLabel extends JLabel
{
	BufferPanel bufferPanel;
	String [] Months = {"January", "February", "March",
			"April", "May", "June", "July", "August",
			"September", "October", "November", "December"}; 
	
	public MonthYearLabel (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createYearMonthLbl();
	}
	
	
	public void createYearMonthLbl()
	{
		Date Date = new Date();
        DateFormat years = new SimpleDateFormat("yyyy");
        Calendar date = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date);
        
        int month = cal.get(Calendar.MONTH);
        String monthName = Months[month];
 
        String bond = monthName + "  " + years.format(date.getTime());
		setFont(new Font("Helvetica Neue",Font.PLAIN,32));
		
		setText(bond);

		//setBorder(BorderFactory.createLineBorder(Color.blue));
		setHorizontalAlignment(SwingConstants.LEFT);
	}
	
	
	
	
	
}
