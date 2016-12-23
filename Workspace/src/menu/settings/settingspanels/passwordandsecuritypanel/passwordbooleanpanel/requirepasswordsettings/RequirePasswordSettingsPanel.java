package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: RequirePasswordSettingsPanel
 * @author ZackEvans
 * 
 * This class is a panel that holds all of the password settings components.
 */

public class RequirePasswordSettingsPanel extends JPanel
{
	public static JCheckBox requirePasswordCheckbox = new LockCheckbox();
	
	/**
	 * Constructor: RequirePasswordSettingsPanel()
	 * 
	 * This constructor calls the panel hierarchy and a method to create the panel.
	 */
	
	public RequirePasswordSettingsPanel()
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
	 * Function: createPanel()
	 * 
	 * This fucntion sets the panel layout and makes the panel clear.
	 */
	
	public void createPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
	}
	
	/**
	 * Function: createCheckbox()
	 * 
	 * This fucntion removes the blue selection border from the component.
	 */
	
	public void createCheckbox()
	{
		requirePasswordCheckbox.setFocusPainted(false); // removed blue outline when clicked
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function adds the compopnents to the panel.
	 */
	
	public void addComponents()
	{
		add(requirePasswordCheckbox);
		add(TimeCombobox.timeCombobox);
	}
	
	/**
	 * Function: updateCheckbox()
	 * 
	 * This function updates the selection of the checkbox.
	 */
	
	public static void updateCheckbox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		requirePasswordCheckbox.setSelected(securitySettingsDatabase.getRequirePasswordValue());
	}
	
}
