package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: LogPasswordCheckbox
 * @author ZackEvans
 *
 * This class is a checkbox that is used to set whether the user would like to save incorrect passwords.
 */

public class LogPasswordCheckbox extends JCheckBox
{
	/**
	 * Constructor: LogPasswordCheckbox()
	 * 
	 * This constructor calls the checkbox hierarchy and a method to set up the checkbox
	 */
	
	public LogPasswordCheckbox()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls functions to crate the checkbox
	 */
	
	public void initialize()
	{
		createCheckbox();
		addListeners();
	}
	
	/**
	 * Function: createCheckbox()
	 * 
	 * this function sets the text for the checkbox and hide the blue border.
	 */
	
	public void createCheckbox()
	{
		setText("Log Failed Password Attempts");
		setFocusPainted(false); // removed blue outline when clicked
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the panel. 
	 * When the checkbox is clicked it updates the value in the database and the panel
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateLogFailedAttemptsValue(LogPasswordAttemptsPanel.logPasswordCheckbox.isSelected());
				LogPasswordAttemptsPanel.updatePanel(); // should the button be showing
			}
		});
	}
}
