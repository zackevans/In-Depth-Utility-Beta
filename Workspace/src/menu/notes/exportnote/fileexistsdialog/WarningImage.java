package menu.notes.exportnote.fileexistsdialog;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class: WarningImage
 * @author ZackEvans
 *
 * This class is a label with an image that is added to the dialog window.
 */

public class WarningImage extends JLabel
{
	/**
	 * Constructor: WarningImage()
	 * @author ZackEvans
	 * 
	 * This constructor calls the label hierarchy and calls a method to setup the label
	 */
	
	public WarningImage()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls method to create the label
	 */
	
	public void initialize()
	{
		createImage();
	}
	
	/**
	 * Function: createImage()
	 * @author ZackEvans
	 * 
	 * This function sets the image on the label
	 */
	
	public void createImage()
	{
		URL iconURL = WarningImage.class.getResource("/Button_Images/Notes/NoteErrors/DialogWarningSign.png"); // create URL to image
		ImageIcon img = new ImageIcon(iconURL); // create an image icon for the image
		setIcon(img); // set the image icon on the button
	}
}
