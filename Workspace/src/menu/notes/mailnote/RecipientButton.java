package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.notes.mailnote.recipientviewer.RecipientViewer;

/**
 * Class: RecipientButton
 * @author ZackEvans
 *
 * This class is a button that when clicked shows the recipient viewer.
 */

public class RecipientButton 
{
	public static JButton button = new JButton();	// create the button
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create button
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets an image on the button.
	 * Creates the border.
	 * set the Default text on the button.
	 * Sets the text position on the button.
	 */
	
	public void createButton()
	{
		URL recipentDecal = RecipientButton.class.getResource("/Button_Images/Notes/MailNote/RecipientButton.png"); // Create URL on the image
		ImageIcon decalImage = new ImageIcon(recipentDecal); // create a Image icon from the URL
		button.setIcon(decalImage); // set button iamge
		
		button.setText("0"); // set the default text to 0
		button.setBorder(null); // get rid of the border
		button.setFocusable(false); // make the button not have focus
		button.setFocusPainted(false); // remove annoying blue border when clicked
		// set the button text in the center
		button.setVerticalTextPosition(JButton.CENTER); 
		button.setHorizontalTextPosition(JButton.CENTER);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a action listener to the button
	 */
	
	public void addListeners()
	{
		button.addActionListener(new ActionListener() // add action listener
		{
			@Override
			public void actionPerformed(ActionEvent e) // override default method
			{
				RecipientViewer.recipientViewerPanel.setVisible(true); // show the recipient viewer.
			}
		});
	}
	
	/**
	 * Function: updateButtonNumber()
	 * 
	 * This function sets the button text the the number of emails in the que.
	 */
	
	public static void updateButtonNumber()
	{
		button.setText((new Integer(RecipientViewer.listOfEmails.size()).toString())); // count items and text text
		button.setBorder(null); // remove border bc it comes back every time :(
	}
}
	