package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.sharenotesdialog.ShareNotesDialog;

/**
 * Function: ShareButton
 * @author ZackEvans
 *
 * This class is a button when clicked displays a list with sharing options
 */

public class ShareButton extends JButton
{
	BufferPanel bufferPanel;
	
	/**
	 * Constructor:  ShareButton ()
	 * 
	 * Call super Run button hierarchy
	 * Inherit bufferpanel object
	 */
	
	public ShareButton (BufferPanel bufferPanel)
	{
		super(); // call hierarchy
		this.bufferPanel = bufferPanel; // Inherit buffer panel object
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the button
	 */
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn();
	 * 
	 * This function adds an image to the button
	 */
	
	public void createBtn()
	{
		URL url = ShareButton.class.getResource("/Button_Images/Notes/NotesPanel/Share.png"); // create a URL for the image
		ImageIcon icon = new ImageIcon(url); // create image icon
		setIcon(icon); // set image icon as button image
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button.
	 * When the button is clicked it shows a window with sharing options
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add action listener to the button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // when button is clicked
			{
				ShareNotesDialog shareDialog = new ShareNotesDialog(bufferPanel);
				shareDialog.launchDialog(); // show window next to frame
			}
		});
	}
}
