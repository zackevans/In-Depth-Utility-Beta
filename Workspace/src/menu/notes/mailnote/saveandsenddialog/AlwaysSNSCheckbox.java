package menu.notes.mailnote.saveandsenddialog;

import javax.swing.JCheckBox;

/**
 * Class: AlwaysSNSCheckbox
 * @author ZackEvans
 * 
 * This class is a check box that the user checks it, the SaveAndSend dialog will never show again and it will defult to saving the emails in the db
 */

public class AlwaysSNSCheckbox extends JCheckBox
{
	/**
	 * Constructor: AlwaysSNSCheckbox()
	 * 
	 * This constructor calls the check box hierarchy and calls a method to setup the checkbox
	 */
	
	public AlwaysSNSCheckbox()
	{
		super();
		createCheckBox();
	}
	
	/**
	 * Function: createCheckBox()
	 * 
	 * This function sets the text for the check box and removes the border & focus border.
	 */
	
	public void createCheckBox()
	{
		setText("Always Save & Send"); // set text to display next to checkbox
		setFocusPainted(false); // removed blue outline when clicked
		setBorderPainted(false); // remove the border.
	}
}
