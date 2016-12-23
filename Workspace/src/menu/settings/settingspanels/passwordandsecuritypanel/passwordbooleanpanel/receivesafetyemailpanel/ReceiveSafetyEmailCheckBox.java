package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.noemaildialog.NoEmailDialog;
import program.util.email.EmailUtil;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;
import sql.userinfo.UserInfoDatabase;

/**
 * Class: ReceiveSafteyEmailCheckBox
 * @author ZackEvans
 *
 * This class is a checkbox will allow the user to receive an email when there are too many failed attempts.
 */

public class ReceiveSafetyEmailCheckBox extends JCheckBox
{
	SettingsBufferPanel settingsBufferPanel;
	
	/**
	 * Constructor: ReceiveSafteyEmailCheckBox (SettingsBufferPanel settingsBufferPanel)
	 * @param settingsBufferPanel
	 * 
	 * This panel calls the checkbox hierarchy and inherits the settingsbufferpanel object.
	 * Then it calls a method to create the checkbox
	 */
	
	public ReceiveSafetyEmailCheckBox (SettingsBufferPanel settingsBufferPanel)
	{
		super();
		this.settingsBufferPanel = settingsBufferPanel;
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls functions to create the panels
	 */
	
	public void initialize()
	{
		createCheckbox();
		addListeners();
	}
	
	/**
	 * Function: createCheckbox()
	 * 
	 * This function sets the text for the checkbox
	 */
	
	public void createCheckbox()
	{
		setText("Receive Email After");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the checkbox that when clicked it updates the value in the database 
	 * or lets the user know that there is no email available for use.
	 */
	
	public void addListeners()
	{	
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
				
				if(isSelected()) // if the check box is being selected
				{
					if(EmailUtil.validateEmailAddress(userInfoDatabase.getEmail()))
					{
						updateCheckboxValue();
					}
					
					else
					{
						NoEmailDialog noEmailDialog = new NoEmailDialog(settingsBufferPanel);
						setSelected(false);
						
						noEmailDialog.launchDialog();
					}
				}
				
				else
				{
					updateCheckboxValue();
				}				
			}
		});
	}
	
	/**
	 * Function: updateCombobox()
	 * 
	 * this function updates the database with the current value of the checkbox.
	 */
	
	public void updateCheckboxValue()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		securitySettingsDatabase.updateReceiveEmailAttemptsValue(ReceiveSafetyEmailPanel.receiveSafteyEmailCheckbox.isSelected());
	}
}
