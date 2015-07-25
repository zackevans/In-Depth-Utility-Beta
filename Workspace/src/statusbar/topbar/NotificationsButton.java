package statusbar.topbar;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import menu.buffer.BufferPanel;

public class NotificationsButton extends JButton
{
	BufferPanel bufferPanel;
	
	public NotificationsButton (BufferPanel bufferPanel)
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
		setText("IMAGE");
		setOpaque(false);
		//setContentAreaFilled(true);
		//setBorderPainted(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				System.out.println("NotificationsButton");
				
			}
		});
	}

}
