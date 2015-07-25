package statusbar.topbar;


import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class TopBar extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private TimeAndDate TimeandDate;
	private NotificationsButton notificationsBtn;
	BufferPanel bufferPanel;
	
	public TopBar (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawLine(0, 20, 700,20);
		g.drawLine(655, 1, 655, 20);
		TimeandDate.showTime(g);
	}
	
	public void initialize()
	{
		createComponents();
		layoutComponents();
	}
	
	public void createComponents()
	{
		TimeandDate = new TimeAndDate();
		notificationsBtn = new NotificationsButton(bufferPanel);
		
		notificationsBtn.setBounds(655, 0, 50, 20);
		notificationsBtn.initialize();
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(notificationsBtn);
	}		
}
