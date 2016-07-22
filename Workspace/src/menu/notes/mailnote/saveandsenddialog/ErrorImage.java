package menu.notes.mailnote.saveandsenddialog;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class: ErrorImage
 * @author ZackEvans
 * 
 * This class is a label with an Internet error image on it.
 */

public class ErrorImage extends JLabel
{
	/**
	 * Constructor: ErrorImage()
	 * @author ZackEvans
	 * 
	 * This constructor calls label heiarchy and calls method to create the label image.
	 */
	
	public ErrorImage()
	{
		super();
		createImage();
	}
	
	/**
	 * Function: createImage()
	 * @author ZackEvans
	 * 
	 * This method loads and sets the error image on the label
	 */
	
	public void createImage()
	{
		URL iconURL = ErrorImage.class.getResource("/Button_Images/Notes/NoteErrors/NoNetwork.png"); // create URL for iamge
		ImageIcon img = new ImageIcon(iconURL); // create image icon for image
		setIcon(img); // set image icon on the label
	}
}
