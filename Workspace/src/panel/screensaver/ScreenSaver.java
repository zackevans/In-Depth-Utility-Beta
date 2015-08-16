package panel.screensaver;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.buffer.BufferPanel;
import menu.settings.security.login.Login;

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
	Login login;
	
	//TODO add keylistener to wake up panel
	//TODO make setting to lock notifications settings when panel is displayed
	
	public ScreenSaver (BufferPanel bufferPanel)
	{
		super();
		setOpaque(false);
		this.bufferPanel = bufferPanel;
		setFocusable(true);
	}
	
	public void initialize ()
	{
		createComponents();
		layoutComponents();
		addListeners();
	}
	
	public void createComponents()
	{
		mainBanner = new JLabel("In Depth Utility");
		clickMessage = new JLabel("click or press any key");
		login = new Login(bufferPanel);
		
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
	        	  System.out.println("Panel Clicked"); // print text
	        	  bufferPanel.showPanel("LOGIN_PANEL"); // show login panel
	        	  login.clearField(); // clear text field
	        	  login.setFocusOnField(); // when panel is shown make textfield active
	          } 
		}); 
		
		addKeyListener(new KeyAdapter() 
		{
			public void keyReleased(KeyEvent e) {
		        
		    }

		    public void keyTyped(KeyEvent e) {
		    	
		    }

		    public void keyPressed(KeyEvent e) 
		    {
		    	System.out.println("key typed");
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
