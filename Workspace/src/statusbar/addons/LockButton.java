package statusbar.addons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

/**
 * Class: LockButton 
 * @author ZackEvans
 *
 * This class holds a button that when clicked shows the user the login screen(locks the program).
 */

public class LockButton 
{
	BufferPanel bufferPanel;
	public static JButton lockButton = new JButton();

	/**
	 * Constructor: LockButton(BufferPanel bufferPanel)
	 * @param bufferPanel
	 * 
	 * This constructor inherits the bufferpanel object and calls a method to create the button.
	 */
	
	public LockButton(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
		initialize();
	}

	/**
	 * Function: initialize() 
	 * 
	 * This function calls functions to create the button.
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function create the lock image to put on the button. Then it sets other properties to clean up the look of the button.
	 */
	
	public void createButton()
	{
		URL lockButtonURL = LockButton.class.getResource("/Button_Images/TopBar/LockButton.png"); // Create URL on the image
		URL lockButtonPressedURL = LockButton.class.getResource("/Button_Images/TopBar/LockButtonPressed.png");
		ImageIcon lockButtonIcon = new ImageIcon(lockButtonURL); // create a Image icon from the URL
		ImageIcon lockbuttonPressedIcon = new ImageIcon(lockButtonPressedURL);
		lockButton.setIcon(lockButtonIcon); // set button iamge
		lockButton.setPressedIcon(lockbuttonPressedIcon);
		
		lockButton.setVisible(false);
		
		lockButton.setBorder(null); // get rid of the border
		lockButton.setFocusable(false); // make the button not have focus
		lockButton.setFocusPainted(false); // remove annoying blue border when clicked
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button. When clicked the button shows the login screen.
	 */
	
	public void addListeners()
	{
		lockButton.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				bufferPanel.showRawPanel("LOGIN_PANEL");
			}
		});
	}
}
