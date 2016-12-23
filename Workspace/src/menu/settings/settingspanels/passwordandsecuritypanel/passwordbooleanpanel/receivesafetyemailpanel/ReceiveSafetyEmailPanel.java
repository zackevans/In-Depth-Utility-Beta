package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: ReceiveSafetyEmailPanel
 * @author ZackEvans
 *
 * This class is a panel that holds all of the components for the Receive Safety Email settings
 */

public class ReceiveSafetyEmailPanel extends JPanel
{
	SettingsBufferPanel settingsBufferPanel;
	public static JCheckBox receiveSafteyEmailCheckbox;
	SafetyEmailCountCombobox safetyEmailCountCombobox;
	JLabel secondHalfofMessage = new JLabel(" Failed Attempts");
	
	/**
	 * Constructor: ReceiveSafetyEmailPanel(SettingsBufferPanel settingsBufferPanel)
	 * @param settingsBufferPanel
	 * 
	 * This constructor calls the panel hierarchy and inherits the settings bufferpanel.
	 * Then it calls a method to create the panel
	 */
	
	public ReceiveSafetyEmailPanel(SettingsBufferPanel settingsBufferPanel)
	{
		super();
		this.settingsBufferPanel = settingsBufferPanel;
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the panel
	 */
	
	public void initialize()
	{
		createPanel();
		addComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function initializes the components and sets the layout and color or the panel.
	 */
	
	public void createPanel()
	{
		receiveSafteyEmailCheckbox = new ReceiveSafetyEmailCheckBox(settingsBufferPanel);
		safetyEmailCountCombobox = new SafetyEmailCountCombobox();	
		
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
		add(receiveSafteyEmailCheckbox);
		add(SafetyEmailCountCombobox.safteyEmailCombobox);
		add(secondHalfofMessage);
	}
	
	/**
	 * Function: updateCheckboxSelection()
	 * 
	 * This function updates the checkbox selection.
	 */
	
	public static void updateCheckboxSelection()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		receiveSafteyEmailCheckbox.setSelected(securitySettingsDatabase.getReceiveEmailAttemptsValue());
	}
}
