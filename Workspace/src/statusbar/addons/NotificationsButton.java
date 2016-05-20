package statusbar.addons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

/**
 * Class: NotificationsButton
 * @author ZackEvans
 *
 * This class is the notifications button that is in the status bar 
 */ 

public class NotificationsButton extends JButton
{
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
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * @author ZackEvans
	 * 
	 * Function creates button properties and set the look
	 */
	
	public void createBtn()
	{
		// create URLs for button images
		URL btnURL = NotificationsButton.class.getResource("/Button_Images/TopBar/Notifications.png"); 
		URL pressedBtnURL = NotificationsButton.class.getResource("/Button_Images/TopBar/NotificationsClicked.png");
		
		// create imageicons for the button images 
		ImageIcon btnImg = new ImageIcon(btnURL);
		ImageIcon btnPressedImg = new ImageIcon(pressedBtnURL);
		
		// set button icons
		setIcon(btnImg);
		setPressedIcon(btnPressedImg);
		
		setFocusable(false); // button cannot be focused 
		setBorderPainted(false); // border doesn't show
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a action listener to the button so a action can be performed when clicked.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener()  // create a new action listener for the 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // Override standard function to do what i want
			{	
				System.out.println("Notifications Btn Clicked");
			}
		});
	}
}