package menu.notes.mailnote;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;


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
		
	}
}
