package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: EraseAppDataCheckbox
 * @author ZackEvans
 *
 * This class is a check box that which is used to set whether the user would like to delete their data after X attempts.
 */

public class EraseAppDataCheckbox extends JCheckBox
{
	/**
	 * Constructor: EraseAppDataCheckbox()
	 * 
	 * This constructor calls the checkebox hierarchy and a method to set up the panel
	 */
	
	public EraseAppDataCheckbox()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This method calls other methods to create the panel
	 */
	
	public void initialize()
	{
		createCheckbox();
		addListeners();
	}
	
	/**
	 * Function: setText("Erase App Data After");
	 * 
	 * This function sets the text after the check box
	 */
	
	public void createCheckbox()
	{
		setText("Erase App Data After");
	}
	
	/**
	 * addListeners()
	 * 
	 * This function adds an action listener to the checkbox.
	 * When the checkbox is clicked it updates its value in the database.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateEraseAppDataValue(EraseAppDataPanel.lockAfterTimeoutCheckbox.isSelected());
			}
		});
	}
}
