package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.panel;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: LogPasswordAttemptsPanel
 * @author ZackEvans
 *
 * This class is a panel that holds the settings to log failed password attemtps
 */

public class LogPasswordAttemptsPanel extends JPanel
{
	public static JCheckBox logPasswordCheckbox = new LogPasswordCheckbox();
	private static ViewFailedPasswordButton viewFailedPasswordAttemptsButton = new ViewFailedPasswordButton();
	
	/**
	 * Constructor: LogPasswordAttemptsPanel()
	 * 
	 * This constructor calls the panel hierarchy and a method to create the panel
	 */
	
	public LogPasswordAttemptsPanel()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls functions to create the panel
	 */
	
	public void initialize()
	{
		createPanel();
		addComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function sets the layout manager for the panel and makes it clear.
	 */
	
	public void createPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function adds the components to the panel
	 */
	
	public void addComponents()
	{
		add(logPasswordCheckbox);
		add(viewFailedPasswordAttemptsButton);
	}
	
	/**
	 * Function: updatePanel()
	 * 
	 * This function updates the panels components.
	 */
	
	public static void updatePanel()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		logPasswordCheckbox.setSelected(securitySettingsDatabase.getLogFailedAttemptsValue());
		viewFailedPasswordAttemptsButton.updateButton();
	}
}
