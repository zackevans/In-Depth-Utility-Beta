package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.shownotificationsbuttonpanel;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings.TimeCombobox;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: ShowNotificationsButtonPanel
 * @author ZackEvans
 *
 * This class is a panel that holds the hide notification settings components.
 */

public class ShowNotificationsButtonPanel extends JPanel
{
	public static JCheckBox showNotificationsButtonCheckbox = new ShowNotificationsButtonCheckbox();
	TimeCombobox timeCombobox = new TimeCombobox();
	
	/**
	 * Constructor: ShowNotificationsButtonPanel()
	 * 
	 * This constructor calls the panel hierarchy and a method to create the components.
	 */
	
	public ShowNotificationsButtonPanel()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the panel.
	 */
	
	public void initialize()
	{
		createPanel();
		createCheckbox();
		addComponents();
	}
	
	/**
	 * Function:createPanel()
	 * 
	 * This fucntion sets the panels layout manager and makes the panel clear.
	 */
	
	public void createPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
	}
	
	/**
	 * Function: createCheckbox()
	 * 
	 * This function removes the section border from the component.
	 */
	
	public void createCheckbox()
	{
		showNotificationsButtonCheckbox.setFocusPainted(false); // removed blue outline when clicked
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function adds the components to the panel.
	 */
	
	public void addComponents()
	{
		add(showNotificationsButtonCheckbox);
	}
	
	/**
	 * Function: updateCheckBox()
	 * 
	 * This function updates the combox value.
	 */
	
	public static void updateCheckBox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		showNotificationsButtonCheckbox.setSelected(securitySettingsDatabase.getShowNotificationsValue());
	}
}
