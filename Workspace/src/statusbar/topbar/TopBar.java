package statusbar.topbar;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class TopBar extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private NotificationsButton notificationsBtn;
	private TimeAndDate timeAndDate;
	BufferPanel bufferPanel;
	
	public TopBar (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false); // set true to show gray
		setBackground(Color.LIGHT_GRAY);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawLine(0, 20, 700,20);
		g.drawLine(655, -12, 655, 20);
	}
	
	public void initialize()
	{
		createComponents();
		layoutComponents();
	}
	
	public void createComponents()
	{
		
		notificationsBtn = new NotificationsButton(bufferPanel);
		timeAndDate = new TimeAndDate();
		
		timeAndDate.timeLabel.setBounds(595, 0, 60, 20);
		timeAndDate.showTime();
		
		notificationsBtn.initialize();
		notificationsBtn.setBounds(655, 0, 50, 21);
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(notificationsBtn);
		add(timeAndDate.timeLabel);
	}
}
