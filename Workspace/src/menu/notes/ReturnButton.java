package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

/**
 * Class: ReturnButton
 * @author ZackEvans
 *
 * When the button is clicked then go back to the main menu
 */

public class ReturnButton extends JButton 
{
	private BufferPanel bufferPanel; // create bufferPanel object
	
	/**
	 * Constructor: ReturnButton (BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * Constructor calls hierarchy and then inherits the bufferPanel
	 */
	
	public ReturnButton (BufferPanel bufferPanel)
	{
		super(); // call hierarchy
		this.bufferPanel = bufferPanel; // Inherit the bufferPanel object
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function calls methods create the button and add listeners to it
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
	 * Function adds an image to the button.
	 */
	
	public void createBtn()
	{
		URL url = ReturnButton.class.getResource("/Button_Images/Notes/NotesPanel/Return.png"); // add resource to the class
		ImageIcon icon = new ImageIcon(url); // create a image icon using url
		setIcon(icon); // set icon on the image
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * Function adds a action listener to the button
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add listener to the button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // override the default action of the button
			{
				System.out.println("returnBtn");
				bufferPanel.showPanel("MAIN_MENU");
			}
		});
	}
}
