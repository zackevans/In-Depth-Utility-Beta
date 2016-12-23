package menu.settings.settingspanels.userpanel.extras;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import program.util.email.EmailUtil;
import sql.userinfo.UserInfoDatabase;

/**
 * Class: ExtrasPanelErrors  
 * @author ZackEvans
 *
 * This class holds components that display the errors on the panel.
 */

public class ExtrasPanelErrors 
{
	public static JLabel emailErrorLabel = new JLabel();

	/**
	 * Constructor: ExtrasPanelErrors()
	 * 
	 * This constructor calls a method to crete the panel
	 */
	
	public ExtrasPanelErrors()
	{
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the error labels. Then it calls a method to update the status of the error.
	 */
	
	public void initialize()
	{
		createEmailErrorLabel();
		updateLabel();
	}
	
	/**
	 * Function: createEmailErrorLabel()
	 * 
	 * This function creates the error images.
	 */
	
	public void createEmailErrorLabel()
	{
		URL emailErrorURL = ExtrasPanelErrors.class.getResource("/Button_Images/Notes/NoteErrors/Error.png");
		ImageIcon emailIcon = new ImageIcon(emailErrorURL);
		ExtrasPanelErrors.emailErrorLabel.setIcon(emailIcon);
	}
	
	/**
	 * Function: updateLabel()
	 * 
	 * This function decides of the error label should be showing.
	 */
	
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