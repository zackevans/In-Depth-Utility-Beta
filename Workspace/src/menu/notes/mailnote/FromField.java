package menu.notes.mailnote;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Class: FromField
 * @author ZackEvans
 *
 * This class is a textfield that records who the email will be sent to.
 */

public class FromField 
{
	public static JTextField textField = new JTextField(); // create textfield to record sender name
	public JLabel fromLabel = new JLabel(" From:"); // create label to mark textfield
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods that create the textfield
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
	 * This function creates the label and the textfield.
	 */
	
	public void createComponents()
	{
		fromLabel.setBackground(Color.WHITE); // set background color to white
		fromLabel.setOpaque(true); // make lable not clear
		fromLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK)); // set only the top and bottom of border black
		
		textField.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK)); // set only the top and bottom of border black
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a document listener to the text field.
	 */
	
	public void addListeners()
	{
		textField.getDocument().addDocumentListener(new DocumentListener() // add document listener to the textfield
		{
			@Override
			public void removeUpdate(DocumentEvent e) // override remove method
			{
				if(FromField.textField.getText().length() == 0) // if the from field is not empty
				{
					ErrorPanel.fromFieldError.setVisible(true); // show error
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) // override insert method
			{
				ErrorPanel.fromFieldError.setVisible(false); // hide error
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
}
