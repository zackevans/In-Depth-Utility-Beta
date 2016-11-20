package menu.notes.addnotedialog;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class: DialogWarning
 * @author ZackEvans
 *
 * This class is a JLabel that is shown when a warning occours
 */

public class DialogWarningLabel extends JLabel
{
	/**
	 * Constructor: DialogWarning()
	 * @author ZackEvans
	 * 
	 * Constructor calls hierarchy and calls method to create label properties
	 */
	
	public DialogWarningLabel()
	{
		super(); // call hierarchy
		initialize(); // call method to create label
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function calls mathods to create the label
	 */
	
	public void initialize()
	{
		createLabel();
	}
	
	/**
	 * Function: createLabel()
	 * @author ZackEvans
	 * 
	 * Function sets icon on label, adds tool tip, and by default hides label
	 */
	
	public void createLabel()
	{
		URL warningUrl = DialogWarningLabel.class.getResource("/Button_Images/Notes/NoteErrors/WarningSign.png"); // create URL of image
		ImageIcon warningIcon = new ImageIcon(warningUrl); // create a image icon to set on label
		
		setIcon(warningIcon); // add image icon to label
		
		setToolTipText("This is not a Valid Name"); // set text for tool tip
		
		setVisible(false); // hide label
	}

}
