package menu.notes.mailnote.saveandsenddialog;

import javax.swing.JTextArea;

/**
 * Class: ErrorLabel
 * @author ZackEvans
 * 
 * This class is a text area that displays the error text.
 */

public class ErrorLabel extends JTextArea
{
	/**
	 * Constructor: ErrorLabel()
	 * 
	 * This constructor calls text area hierarchy and calls a method to setup the label
	 */
	
	public ErrorLabel()
	{
		super();
		createLabel();
	}
	
	/**
	 * Function: createLabel()
	 * 
	 * This method makes the text ara clear and wraps the words so words are not cut off.
	 */
	
	public void createLabel()
	{
		// make full words wrap around
		setWrapStyleWord(true);
		setLineWrap(true);
		
		setOpaque(false); // make clear
		setEditable(false); // make the user not be able to interact with the text
		setFocusable(false); // make the textarea not gain focus ever
		setText("You are not currently connected to the internet. Would you like to send this email when you come back online."); // set the error message
	}
}
