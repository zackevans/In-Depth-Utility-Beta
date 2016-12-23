package menu.loginpanel;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class: LoginErrors 
 * @author ZackEvans
 * 
 * This class holds the error images to be displayed on the login panel
 */

public class LoginErrors 
{
	public static JLabel loginError = new JLabel();

	/**
	 * Constructor: LoginErrors()
	 * 
	 * This constructor calls a method to create the label
	 */
	
	public LoginErrors()
	{
		createLabelImage();
	}
	
	/**
	 * Function: createLabelImage()
	 * 
	 * This function creates the image to be displayed on the label
	 */
	
	public void createLabelImage()
	{
		URL loginErrorURL = LoginErrors.class.getResource("/Button_Images/Notes/NoteErrors/Error.png");
		ImageIcon loginErrorIcon = new ImageIcon(loginErrorURL);
		loginError.setIcon(loginErrorIcon);
		
		loginError.setVisible(false);
	}
}
