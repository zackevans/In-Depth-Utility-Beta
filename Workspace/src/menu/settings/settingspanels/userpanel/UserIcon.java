package menu.settings.settingspanels.userpanel;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Class: UserIcon
 * @author ZackEvans
 * 
 * This class is a label that displays the users picture
 */

public class UserIcon extends JLabel
{
	/**
	 * Constructor: UserIcon()
	 * 
	 * This constructor calls the labels hierarchy and a method to create the image
	 */
	
	public UserIcon()
	{
		super();
		createIcon();
	}
	
	/**
	 * Function:createIcon() 
	 * 
	 * This function creates the icon for the users image
	 */
	
	public void createIcon()
	{
		URL iconURL = UserIcon.class.getResource("/Button_Images/Settings/UserImages/UserIcon.png");
		ImageIcon iconImage = new ImageIcon(iconURL);
		setIcon(iconImage);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}
