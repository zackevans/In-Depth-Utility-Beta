package menu.settings.settingspanels.userpanel;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UserIcon extends JLabel
{
	public UserIcon()
	{
		super();
		createIcon();
	}
	
	public void createIcon()
	{
		URL iconURL = UserIcon.class.getResource("/Button_Images/Settings/UserImages/UserIcon.png");
		ImageIcon iconImage = new ImageIcon(iconURL);
		setIcon(iconImage);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}
