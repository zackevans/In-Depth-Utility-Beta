package statusbar;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import statusbar.addons.BufferPanelBackButton;
import statusbar.addons.LockButton;
import statusbar.addons.NotificationsButton;
import statusbar.addons.TimeAndDate;

/**
 * Class: TopBar
 * @author ZackEvans
 *
 * This class is the statusbar that runs along the top of the program
 */

public class StatusBar extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static NotificationsButton notificationsBtn;
	private TimeAndDate timeAndDate;
	public static  BufferPanelBackButton backButton;
	public static LockButton lockButton;
	BufferPanel bufferPanel;
	
	/**
	 * Constructor: TopBar (BufferPanel bufferPanel)
	 * @param bufferPanel
	 * 
	 * Constructor call hierarchy, inherits the bufferPanel object and sets the topbar clear
	 */
	
	public StatusBar (BufferPanel bufferPanel)
	{
		super(); // call hierarchy 
		this.bufferPanel = bufferPanel; // inherit bufferPanel
		setOpaque(false); // set true to show gray
	}
	
	/**
	 * Function: paintComponent(Graphics g)*
	 * 
	 * Function paints lines to create the top bar
	 */
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawLine(0, 20, 700,20); // draw line across window
	}
	
	/**
	 * Function: initialize()
	 * 
	 * this function calls functions to create the topbar
	 */
	
	public void initialize()
	{
		createComponents();
		layoutComponents();
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * this function creates and sets location of items to be added to the status bar
	 */
	
	public void createComponents()
	{	
		notificationsBtn = new NotificationsButton(bufferPanel); // create notofications object
		timeAndDate = new TimeAndDate(); // create time and date object
		backButton = new BufferPanelBackButton(bufferPanel);
		lockButton = new LockButton(bufferPanel);
		
		// set location and size of the time label
		timeAndDate.createLabel();
		TimeAndDate.timeLabel.setBounds(580, 0, 70, 20); 
		timeAndDate.showTime();
		
		// create and set the size/location of the notifications button
		notificationsBtn.initialize();
		NotificationsButton.buttonPanel.setBounds(655, 0, 50, 21);
		
		BufferPanelBackButton.backButton.setBounds(10, -1, 55,20);
		
		LockButton.lockButton.setBounds(570, 1, 20,20);
		//LockButton.lockButton.setBorder(BorderFactory.createLineBorder(Color.red));
		
	}
	
	/**
	 * Function: layoutComponents()
	 * 
	 * This function sets the layout manager and adds the components to the status bar.
	 */
	
	public void layoutComponents()
	{
		setLayout(null); // dont set a layout manager
		setPreferredSize(new Dimension(Window_Width,Window_Height-20)); // set size of the panel
		
		// add items to the top bar
		add(NotificationsButton.buttonPanel);
		add(TimeAndDate.timeLabel);
		add(BufferPanelBackButton.backButton);
		add(LockButton.lockButton);
	}
}
