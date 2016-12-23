package menu.loginpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import file.files.PasswordAttemptsFile;
import menu.buffer.BufferPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel.AttemptsCombobox;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.SafetyEmailCountCombobox;
import program.util.email.PushEmail;
import sql.DataBase;
import sql.systemsettings.passwordsettings.PasswordSettingsDatabase;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;
import sql.userinfo.UserInfoDatabase;
import statusbar.addons.LockButton;
import statusbar.addons.NotificationsButton;

/**
 * Class: LoginButton
 * @author ZackEvans
 *
 * This class is a button that when clicked will prompt the user with a warning or let them into the application
 */

public class LoginButton extends JButton
{
	BufferPanel bufferPanel;
	static int attempts = 0;
	
	/**
	 * Constructor: LoginButton(BufferPanel bufferPanel)
	 * @param bufferPanel
	 * 
	 * This constructor inherits the buffer panel object and calls button hierarchy.
	 * Then the constructor call a method to set up the button.
	 */
	
	public LoginButton(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function call methods to create the button
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function creates the image for the button and its properties.
	 */
	
	public void createButton()
	{
		URL url = LoginButton.class.getResource("/Button_Images/Login/loginButton.png"); // create a URL for the image
		ImageIcon icon = new ImageIcon(url); // create image icon
		setIcon(icon); // set image icon as button image
		
		setOpaque(false);
		setBorder(null);
		setFocusPainted(false);	
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function add an action listener to the button
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				loginAction();
			}
		});
	}
	
	/**
	 * Function: loginAction()
	 * @see main menu panel
	 * 
	 * This method is called when the login button is clicked or when the enter key is pressed.
	 * This method evaluates if the password entered is correct and if show it lets the user into the program.
	 */
	
	public void loginAction()
	{
		PasswordSettingsDatabase passwordSettingsDatabase = new PasswordSettingsDatabase();
		
		if(LoginField.loginField.getText().equals(passwordSettingsDatabase.getPassword())) // check to see if the password is correct
		{
			bufferPanel.showRawPanel(BufferPanel.currentPanel);
			LockButton.lockButton.setVisible(true);
			bufferPanel.checkBackButton(BufferPanel.currentPanel); // check back button bc method .showRawPanel doesent include it :(
			NotificationsButton.notificationsButton.setVisible(true);
		}
		
		else
		{
			attempts = attempts +=1;
			
			checkAttemptsForDataDelete();
			checkAttemptsForEmail();
			
			addAttempt();
			
			LoginErrors.loginError.setVisible(true);
			LoginField.loginField.selectAll();
		}
	}
	
	/**
	 * Function: checkAttemptsForEmail()
	 * 
	 * This method checks to see if the user should receive an email. This method is called when the password is incorrect.
	 */
	
	public static void checkAttemptsForEmail()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		if(securitySettingsDatabase.getReceiveEmailAttemptsValue()) // if the user wants to revive an email (Check box is selected)
		{			
			int permittedAttempts = (SafetyEmailCountCombobox.safteyEmailCombobox.getSelectedIndex()+1) *5; // calculate the ammount of attempts
			
			if(attempts >= permittedAttempts) // if the user used up all their attempts
			{
				String[] to = {userInfoDatabase.getEmail()};
				
				PushEmail.sendEmail(to, "Unusual Account Activity", "There have been " + permittedAttempts + " consecutive failed attempts on your IDU account");
				attempts =0;
			}		
		}
	}
	
	/**
	 * Function: checkAttemptsForDataDelete()
	 * 
	 * This function checks to see if all the data should be deleted. This method is called when the password is incorrect.
	 */
	
	public static void checkAttemptsForDataDelete()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		if(securitySettingsDatabase.getEraseAppDataValue())
		{
			int permittedAttempts = (AttemptsCombobox.attemptsCombobox.getSelectedIndex()+1) *5;
			
			if(attempts >= permittedAttempts) // if the user used up all their attempts
			{
				DataBase dataBase = new DataBase();
				dataBase.deleteDatabase();
			}	
		}
	}
	
	/**
	 * Function: addAttempt()
	 * 
	 * This method saves the failed password attempt if the user has selected the option to do so.
	 */
	
	public static void addAttempt()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		if(securitySettingsDatabase.getLogFailedAttemptsValue()) // if the user set to record attempts
		{
			if(LoginField.loginField.getText().length() > 0) // if a attempt was actually made
			{
				PasswordAttemptsFile.addAttempt(LoginField.loginField.getText());
			}
		}
	}
}
