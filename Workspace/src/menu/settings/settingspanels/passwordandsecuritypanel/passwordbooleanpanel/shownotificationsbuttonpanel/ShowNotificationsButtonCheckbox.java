package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.shownotificationsbuttonpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: ShowNotificationsButtonCheckbox
 * @author ZackEvans
 *
 * This function is a checkbox that allows the user to hide the notifications center when the program is locked.
 */

public class ShowNotificationsButtonCheckbox extends JCheckBox
{
	/**
	 * Constructor: ShowNotificationsButtonCheckbox ()
	 * 
	 * This constructor calls the panel hierarchy and a mehtod to create the panel
	 */
	
	public ShowNotificationsButtonCheckbox ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the checkbox.
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
		setText("Hide Notificatins Tab When Locked");
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function add an action listener to the checkbox. When fired it updates the value in the db.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateShowNotificaitonsValue(ShowNotificationsButtonPanel.showNotificationsButtonCheckbox.isSelected());
			}
		});
	}
}
