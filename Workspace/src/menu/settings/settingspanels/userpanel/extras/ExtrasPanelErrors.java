package menu.settings.settingspanels.userpanel.extras;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import program.util.email.EmailUtil;
import sql.userinfo.UserInfoDatabase;

public class ExtrasPanelErrors 
{
	public static JLabel emailErrorLabel = new JLabel();

	public ExtrasPanelErrors()
	{
		initialize();
	}
	
	public void initialize()
	{
		createEmailErrorLabel();
		updateLabel();
	}
	
	public void createEmailErrorLabel()
	{
		URL emailErrorURL = ExtrasPanelErrors.class.getResource("/Button_Images/Notes/NoteErrors/Error.png");
		ImageIcon emailIcon = new ImageIcon(emailErrorURL);
		ExtrasPanelErrors.emailErrorLabel.setIcon(emailIcon);
	}
	
	public static void updateLabel()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		if(EmailUtil.validateEmailAddress(userInfoDatabase.getEmail()) || userInfoDatabase.getEmail().length() ==0)
		{		
			emailErrorLabel.setVisible(false);
		}
		
		else
		{
			emailErrorLabel.setVisible(true);
		}
	}
}

