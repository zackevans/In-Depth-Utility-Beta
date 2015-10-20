package menu.calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class ReturnButton extends JButton
{
	private BufferPanel bufferPanel;
	
	public ReturnButton (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	public void createBtn()
	{
		setIcon(new ImageIcon("Images/Button_Images/Notes/Return.png"));
		validate();
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("returnBtn");
				bufferPanel.showPanel("MAIN_MENU");
			}
		});
	}
}
