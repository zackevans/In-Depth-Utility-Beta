package statusbar.topbar;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import sql.system.settings.SystemDatabase;

public class TopBar extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private SystemDatabase systemDB = new SystemDatabase();
	private NotificationsButton notificationsBtn;
	private TimeAndDate TimeandDate;
	private LockButton lockBtn;
	BufferPanel bufferPanel;
	
	public TopBar (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false); // set true to show gray
		setBackground(Color.LIGHT_GRAY);
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
		
		notificationsBtn = new NotificationsButton(bufferPanel);
		lockBtn = new LockButton(bufferPanel);
		TimeandDate = new TimeAndDate();
		
		notificationsBtn.initialize();
		notificationsBtn.setBounds(655, 0, 50, 21);
		
		
		lockBtn.initialize();
		lockBtn.setBounds(0, 0, 30, 21);
		setDefultLockBtn();
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(notificationsBtn);
		add(lockBtn);
	}
	
	public void setDefultLockBtn()
	{
		if(systemDB.getPassExist() == true)
		{
			showLockBtn(false);
		}
	}
	
	public void showLockBtn(Boolean tf)
	{
		lockBtn.setVisible(tf);
	}
	
}
