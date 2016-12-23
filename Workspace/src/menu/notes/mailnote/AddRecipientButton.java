package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.notes.mailnote.recipientviewer.RecipientHolder;
import menu.notes.mailnote.recipientviewer.RecipientViewer;
import program.util.email.EmailUtil;

/**
 * Class: AddRecipientButton
 * @author ZackEvans
 *
 * This class is a button that when clicked it will add the email to a list of emails.
 */

public class AddRecipientButton extends JButton
{
	/**
	 * Constructor: AddRecipientButton ()
	 * 
	 * This constructor calls button hierarchy
	 */
	
	public AddRecipientButton ()
	{
		super(); // call button hierarchy
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the button 
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function creates a image and sets it on the button.
	 * This function removes the border and makes the button not be able to focous.
	 */
	
	public void createButton()
	{
		URL recipentDecal = RecipientButton.class.getResource("/Button_Images/Notes/MailNote/AddRecipient.png"); // create URL for the image
		ImageIcon decalImage = new ImageIcon(recipentDecal); // create image icon
		setIcon(decalImage); // set image on a button
		
		setBorder(null); // remove border
		setFocusable(false); // make button not focous
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add listener
		{
			@Override
			public void actionPerformed(ActionEvent e) // override method to call add email method
			{
				addEmail(); // call addEmail() method
			}	
		});
	}
	
	/**
	 * Function: addEmail()
	 * 
	 * This function adds the email in the To field (ToField) into the list of emails
	 */
	
	public void addEmail()
	{
		if (EmailUtil.validateEmailAddress(ToField.textField.getText())) // if there is a valid email in the textfield 
		{		
			if(! (RecipientViewer.listOfEmails.contains(ToField.textField.getText()))) // if the email has not already been added
			{
				RecipientViewer.listOfEmails.add(ToField.textField.getText()); // add email to list of email's to be sent to
				RecipientButton.updateButtonNumber(); // update the recipient button number 
				RecipientViewer.noRecipientsWarrning.setVisible(false); // remove warning that no email exist
				ErrorPanel.toFieldError.setVisible(false); // remove error that toField doesn't have valid email
				RecipientHolder.createNewPanel(ToField.textField.getText()); //create a new email item
				ToField.textField.setText(""); // clear the toField
			}
			
			else // if the email still exists
			{
				ToField.textField.setText(""); // clear the text
				ErrorPanel.toFieldError.setVisible(false); // reset error
			}
		}
		
		else // if email is not valid
		{
			ErrorPanel.toFieldError.setVisible(true); // show error
		}
	}
}
