package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: EraseAppDataPanel
 * @author ZackEvans
 *
 * This class is a panel that holds the checkbox and the text for the erase app data settings.
 */

public class EraseAppDataPanel extends JPanel
{
	public static JCheckBox lockAfterTimeoutCheckbox = new EraseAppDataCheckbox();
	JLabel secondHalfofMessage = new JLabel(" Failed Attempts");
	public static AttemptsCombobox attemptsCombobox = new AttemptsCombobox();
	
	/**
	 * Constructor: EraseAppDataPanel()
	 * 
	 * This constructor calls the panel hierarchy and a method to setup the panel
	 */
	
	public EraseAppDataPanel()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls function to cretate the panel
	 */
	
	public void initialize()
	{
		createPanel();
		createCheckbox();
		addComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function sets the layout manager for the panel.
	 */
	
	public void createPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
	}
	
	/**
	 * Function: createCheckbox()
	 * 
	 * This function sets the borders for the checkbox.
	 */
	
	public void createCheckbox()
	{
		lockAfterTimeoutCheckbox.setFocusPainted(false); // removed blue outline when clicked
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This functions adds all the components to the panel.
	 */
	
	public void addComponents()
	{
		add(lockAfterTimeoutCheckbox);
		add(AttemptsCombobox.attemptsCombobox);
		add(secondHalfofMessage);
	}
	
	/**
	 * Function: updateCheckbox()
	 * 
	 * This function updates the selection of the checkbox.
	 */
	
	public static void updateCheckbox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		lockAfterTimeoutCheckbox.setSelected(securitySettingsDatabase.getEraseAppDataValue());
	}
}