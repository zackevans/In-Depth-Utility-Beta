package statusbar.addons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

/**
 * Class: BufferPanelBackButton
 * @author ZackEvans
 * 
 * This class holds a button that is added to the status bar and lets the user return to previous panels.
 */

public class BufferPanelBackButton
{
	public static JButton backButton = new JButton();
	BufferPanel bufferPanel;
	
	/**
	 * Constructor: BufferPanelBackButton(BufferPanel bufferPanel) 
	 * @param bufferPanel
	 * 
	 * This function inherits the bufferpanel object and calls a method to create the button.
	 */
	
	public BufferPanelBackButton(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to initialize the button.
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function creates the buttons images and how the button should look.
	 */
	
	public void createButton()
	{
		URL backArrowURL = BufferPanelBackButton.class.getResource("/Button_Images/TopBar/BackButton.png"); // Create URL on the image
		URL backArrowPressedURL = BufferPanelBackButton.class.getResource("/Button_Images/TopBar/BackbuttonPressed.png"); // Create URL on the image
		ImageIcon backArrowIcon = new ImageIcon(backArrowURL); // create a Image icon from the URL
		ImageIcon backArrowPressedIcon = new ImageIcon(backArrowPressedURL);
		backButton.setIcon(backArrowIcon); // set button iamge
		backButton.setPressedIcon(backArrowPressedIcon);
		
		backButton.setVisible(false);
		
		backButton.setBorder(null); // get rid of the border
		backButton.setFocusable(false); // make the button not have focus
		backButton.setFocusPainted(false); // remove annoying blue border when clicked
		// set the button text in the center
	}
	
	/**
	 * Function: addListeners()
	 * @see bufferpanels last panel
	 * 
	 * This function adds a action listener to the button. When clicked it shows that last panel that was shown.
	 */
	
	public void addListeners()
	{
		backButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				bufferPanel.showPanel(BufferPanel.lastPanel);
			}
		});
	}	
}
