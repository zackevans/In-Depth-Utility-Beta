package menu.notes.mailnote;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import program.util.email.EmailUtil;

/**
 * Class: ToField
 * @author ZackEvans
 *
 * This class holds the text field that collects the TO: subject field for the email.
 */

public class ToField 
{
	public static JTextField textField = new JTextField(); // text field is created to receive email's that user want to share with
	public JLabel toLabel = new JLabel(" To:");
	public JLabel addEmailButtonBackground = new JLabel(); // create label to fill the space after the text field ends.
	
	/**
	 * Function: initialize();
	 * @author ZackEvans
	 * 
	 * This function calls methods to create the text field and the label.
	 */
	
	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This function sets the color and border for the components
	 */
	
	public void createComponents()
	{
		toLabel.setBackground(Color.white); // set label white
		toLabel.setOpaque(true); // make label not clear
		
		addEmailButtonBackground.setBackground(Color.white); // set label white
		addEmailButtonBackground.setOpaque(true); // make label not clear
		
		textField.setBorder(null); // get rid of text field border
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a key listener to the text field to add functionality when the enter key is pressed
	 * and update the errors on the error panel.
	 */
	
	public void addListeners()
	{
		textField.addKeyListener(new KeyListener()  // add key listener to the textfield
		{
			@Override
			public void keyPressed(KeyEvent e)  // override what happens when a key is pressed
			{	
				if(e.getKeyCode() == KeyEvent.VK_ENTER) // if the key pressed is enter
	            {
	                AddRecipientButton addRecipientButton = new AddRecipientButton();
	                addRecipientButton.addEmail(); // add email that is in the textfield
	            }
				
				else // if the key pressed is not enter
				{
					if(EmailUtil.validateEmailAddress(textField.getText())) // if the email is valid
					{
						ErrorPanel.toFieldError.setVisible(false); // hide error
					}
					
					else // if the email is not valid
					{
						ErrorPanel.toFieldError.setVisible(true); // show the error
					}
				}
			}
			
			// not needed
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
}
