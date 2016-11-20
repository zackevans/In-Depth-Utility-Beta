package statusbar.addons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;

/**
 * Class: NotificationsButton
 * @author ZackEvans
 *
 * This class is the notifications button that is in the status bar 
 */ 

public class NotificationsButton 
{
	public static JPanel buttonPanel = new JPanel();
	public static JButton notificationsButton = new JButton();
	BufferPanel bufferPanel; // bufferPanel object created so class has access to the BufferPanel 
	
	/**
	 * Constructor: NotificationsButton (BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * Call call button hierarchy then inherit bufferpanel
	 */
	
	public NotificationsButton (BufferPanel bufferPanel)
	{
		super(); // call button 
		this.bufferPanel = bufferPanel; // Inherit bufferpanel object
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function creates the button and adds listeners to the button 
	 */
	
	public void initialize()
	{
		createButton();
		createPanel();
		addListeners();
	}
	
	/**
	 * Function: createPanel()
	 * @author ZackEvans
	 * 
	 * Function creates the panel and its properties
	 */
	
	public void createButton()
	{
		// create URLs for button images
		URL btnURL = NotificationsButton.class.getResource("/Button_Images/TopBar/Notifications.png"); 
		URL pressedBtnURL = NotificationsButton.class.getResource("/Button_Images/TopBar/NotificationsClicked.png");
		
		// create imageicons for the button images 
		ImageIcon btnImg = new ImageIcon(btnURL);
		ImageIcon btnPressedImg = new ImageIcon(pressedBtnURL);
		
		// set button icons
		notificationsButton.setIcon(btnImg);
		notificationsButton.setPressedIcon(btnPressedImg);
		
		notificationsButton.setFocusable(false); // button cannot be focused 
		notificationsButton.setBorderPainted(false); // border doesn't show
		
		notificationsButton.setBounds(0, 0, 45, 21);
	}
		
	public void createPanel()
	{
		buttonPanel.setLayout(null);
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK)); // add border on one side for division line
		
		buttonPanel.add(notificationsButton);
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a action listener to the button so a action can be performed when clicked.
	 */
	
	public void addListeners()
	{
		notificationsButton.addActionListener(new ActionListener()  // create a new action listener for the 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // Override standard function to do what i want
			{	
				System.out.println("Notifications Btn Clicked");
			}
		});
	}
}