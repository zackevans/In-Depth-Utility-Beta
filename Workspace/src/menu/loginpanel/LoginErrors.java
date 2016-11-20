package menu.loginpanel;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoginErrors 
{
	public static JLabel loginError = new JLabel();

	public LoginErrors()
	{
		initialize();
	}
	
	
	public void initialize()
	{
		createLabelImage();
	}
	
	public void createLabelImage()
	{
		URL loginErrorURL = LoginErrors.class.getResource("/Button_Images/Notes/NoteErrors/Error.png");
		ImageIcon loginErrorIcon = new ImageIcon(loginErrorURL);
		loginError.setIcon(loginErrorIcon);
		
		loginError.setVisible(false);
	}
}
