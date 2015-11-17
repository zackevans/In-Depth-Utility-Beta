package menu.calendar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import launch.app.LaunchApp;
import menu.buffer.BufferPanel;
import javax.swing.JButton;

public class CurrentDayButton extends JButton
{
	private BufferPanel bufferPanel;
	private LaunchApp launchApp = new LaunchApp();
	
	public CurrentDayButton(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createButton();
		addListeners();
	}

	public void createButton()
	{
		setText("Today");
		setFont(new Font("Helvetica Neue",Font.PLAIN,12));
		validate();
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//Return to current day
				System.out.println("currentDayBtn");
			}
		});
	}
}