package panel.screensaver;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.buffer.BufferPanel;

public class ScreenSaver extends JPanel
{	
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final int btnWidth = 150;
	public static final int btnHeight = 50;
	public static final int btnLn2 = 200;
	public static final int leftRow = -75;
	public static final int rightRow = 125;
	public static JLabel mainBanner;
	public static JLabel clickMessage;
	BufferPanel bufferPanel;
	
	//TODO add keylistener and mouse listener to wake up panel
	//TODO make setting to lock notifications settings when panel is displayed
	
	public ScreenSaver (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize ()
	{
		createComponents();
		layoutComponents();
		addListeners();
		setOpaque(false);
	}
	
	public void createComponents()
	{
		mainBanner = new JLabel("In Depth Utility");
		clickMessage = new JLabel("click or press any key");
		
		createMainBanner();
		createClickMessage();
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(mainBanner);
		add(clickMessage);
	}
	
	public void addListeners()
	{
		addMouseListener(new MouseAdapter() 
		{ 
	          public void mousePressed(MouseEvent e) 
	          { 
	        	  System.out.println("Panel Clicked");
	        	  bufferPanel.showPanel("LOGIN_PANEL");  
	          } 
		}); 
	}
	
	
	public void createMainBanner()
	{
		mainBanner.setFont(new Font("Helvetica Neue",Font.PLAIN,50));
	    mainBanner.setBounds(0, 90, Window_Width,100);
	    mainBanner.setVerticalAlignment(SwingConstants.CENTER);
	    mainBanner.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void createClickMessage()
	{
		clickMessage.setFont(new Font("Helvetica Neue",Font.PLAIN,16));
		clickMessage.setBounds(0, 170, Window_Width,100);
	    clickMessage.setVerticalAlignment(SwingConstants.CENTER);
	    clickMessage.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
