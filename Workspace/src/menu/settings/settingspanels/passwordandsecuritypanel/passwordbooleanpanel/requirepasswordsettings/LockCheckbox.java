package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: LockCheckbox
 * @author ZackEvans
 *
 * This class is a checkbox which lets the user select if they want to lock their app after a certain amount of time.
 */

public class LockCheckbox extends JCheckBox
{
	/**
	 * Constructor: LockCheckbox ()
	 * 
	 * This function calls the panel hierarchy and a method to create the checkbox.
	 */
	
	public LockCheckbox ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls fucntions to set up the checkbox.
	 */
	
	public void initialize()
	{
		createCheckbox();
		addListeners();
	}
	
	/**
	 * Function: createCheckbox()
	 * 
	 * This function sets the text of the the checkbox
	 */
	
	public void createCheckbox()
	{
		setText("Require Password After");
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a action listener to the checkbox. When fired it updates the db value with the current status of the checkbox.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateRequirePasswordValue(RequirePasswordSettingsPanel.requirePasswordCheckbox.isSelected());	
			}
		});
	}
}
