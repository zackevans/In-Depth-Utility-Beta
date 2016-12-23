package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog.PasswordAttemptsDialog;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: ViewFailedPasswordButton
 * @author ZackEvans
 * 
 * This class is a button that when clicked shows the dialog with all the failed attempts
 */

public class ViewFailedPasswordButton extends JButton
{
	/**
	 * Constructor: ViewFailedPasswordButton ()
	 * 
	 * This constructor calls the button hierarchy and a method to set up the button
	 */
	
	public ViewFailedPasswordButton ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the panel
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets the text of the button and the border.
	 */
	
	public void createButton()
	{
		setText("View");
		setFocusPainted(false);
	}
	
	/**
	 * Function: updateButton()
	 * 
	 * This function decides if the button should be shown or not
	 */
	
	public void updateButton()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		if(!securitySettingsDatabase.getLogFailedAttemptsValue())
		{
			setEnabled(false);
		}
		
		else
		{
			setEnabled(true);
		}	
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button that when clicked shows the password dialog
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PasswordAttemptsDialog passwordAttemptsDialog = new PasswordAttemptsDialog();
				
				passwordAttemptsDialog.launchDialog();
			}
		});
	}
}
