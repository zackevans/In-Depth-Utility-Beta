package menu.notes.mailnote.recipientviewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.notes.mailnote.RecipientButton;

/**
 * Class: RemoveButton
 * @author ZackEvans
 *
 * This class is a button that when pressed it deletes the panel that it is on
 */

public class RemoveButton extends JButton
{
	RecipientPanel recipientPanel;
	
	/**
	 * Constructor: RemoveButton(RecipientPanel recipientPanel)
	 * @author ZackEvans
	 * @param recipientPanel
	 * 
	 * Constructor calls button hierarchy and saves the RecipientPanel object that is passed to it.
	 * Constructor calls method to create the button.
	 */
	
	public RemoveButton(RecipientPanel recipientPanel)
	{
		super(); // calls button hierarchy
		this.recipientPanel = recipientPanel; // saves the RecipientPanel object
		initialize(); // calls method to create the button.
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to create the button
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
	 * This function set icons for the button
	 */
	
	public void createBtn()
	{
		URL removeBtnUrl = RemoveButton.class.getResource("/Button_Images/Notes/NotesPanel/Clear.png"); // create a URL for the button image
		URL pressedRemoveBtnUrl = RemoveButton.class.getResource("/Button_Images/Notes/NotesPanel/ClearPressed.png"); // create a URL for the image when the button is pressed
		
		// make the button clear except for the image on the button
		setOpaque(false); 
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		
		setIcon(new ImageIcon(removeBtnUrl)); // set icon for the button
		setPressedIcon(new ImageIcon(pressedRemoveBtnUrl)); // set the icon for when the button is pressed
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a action listener to the button that when clicked clears the search bar text.
	 * This function adds a mouse listener to the button so RecipientViewer doesn't disappear when mouse is over it.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add a action listener
		{
			@Override
			public void actionPerformed(ActionEvent e) // when a action is detected (click)
			{	
				RecipientHolder.removePanel(getParent(),recipientPanel.emailText); // remove the panel the button is on
				RecipientButton.updateButtonNumber(); // update the button number after a deletion
			}
		});
		
		
		addMouseListener(new MouseAdapter()  // add a mouse listener to the button so RecipientViewer doesn't disappear when mouse is over it.
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				RecipientViewer.recipientViewerPanel.setVisible(true); // show the RecipientViewer
			}
		});
	}
}
