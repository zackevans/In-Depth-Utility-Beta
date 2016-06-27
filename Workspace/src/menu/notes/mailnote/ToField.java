package menu.notes.mailnote;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import program.util.EmailUtil;


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
	
	public ToField ()
	{
		
	}
	
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
		toLabel.setOpaque(true); // make label clear
		
		textField.setBorder(null); // get rid of text field border
	}
	
	public void addListeners()
	{
		textField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{	
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
	            {
	                AddRecipientButton addRecipientButton = new AddRecipientButton();
	                addRecipientButton.addEmail();
	            }
				
				else
				{
					ErrorPanel errorPanel = new ErrorPanel();
					
					if(EmailUtil.validateEmailAddress(textField.getText()))
					{
						errorPanel.toFieldError.setVisible(false);
					}
					
					else
					{
						errorPanel.toFieldError.setVisible(true);
					}
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
}
