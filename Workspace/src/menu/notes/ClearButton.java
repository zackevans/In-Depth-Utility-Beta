package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class: ClearButton
 * @author ZackEvans
 * 
 * This class is a button that when clicked will clear the text field of searched text
 */

public class ClearButton extends JButton
{
	/**
	 * Constructor: ClearButton()
	 * 
	 * Constructor calls hierarchy
	 */
	
	public ClearButton()
	{
		super(); // call hierarchy
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to set up the button and add listeners to it
	 */
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * 
	 * This function set icons for the button
	 */
	
	public void createBtn()
	{
		URL clearBtnUrl = ClearButton.class.getResource("/Button_Images/Notes/NotesPanel/Clear.png"); // create a URL for the button image
		URL pressedClearBtnUrl = ClearButton.class.getResource("/Button_Images/Notes/NotesPanel/ClearPressed.png"); // create a URL for the image when the button is pressed
		
		// make the button clear except for the image on the button
		setOpaque(false); 
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		setIcon(new ImageIcon(clearBtnUrl)); // set icon for the button
		setPressedIcon(new ImageIcon(pressedClearBtnUrl)); // set the icon for when the button is pressed
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a action listener to the button that when clicked clears the search bar text.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SearchBar.textField.setText("");
				SearchBar.textField.requestFocusInWindow();
			}
		});
	}
}
