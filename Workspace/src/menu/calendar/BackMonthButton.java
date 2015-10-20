package menu.calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;

public class BackMonthButton extends JButton
{
	private BufferPanel bufferPanel;
	private LaunchApp launchApp = new LaunchApp();

	
	public BackMonthButton (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize ()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		setIcon(new ImageIcon("Images/Button_Images/Calendar/Back.png"));
		validate();
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Back Month Button");
			}
		});
	}

}
