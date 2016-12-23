package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel.AttemptsCombobox;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel.EraseAppDataPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.panel.LogPasswordAttemptsPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.ReceiveSafetyEmailPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.SafetyEmailCountCombobox;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings.RequirePasswordSettingsPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings.TimeCombobox;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.shownotificationsbuttonpanel.ShowNotificationsButtonPanel;

/**
 * Class: PasswordBooleanPanel
 * @author ZackEvans
 *
 * This class is a panel that holds additional password and security settings.
 */

public class PasswordBooleanPanel extends JPanel
{
	SettingsBufferPanel settingsBufferpanel;
	
	/**
	 * Constructor: PasswordBooleanPanel (SettingsBufferPanel settingsBufferPanel)
	 * @param settingsBufferPanel
	 * 
	 * This function calls the panel hierarchy and inherits the settingsbufferpanel object.
	 * Then it calls a method which creates the panel
	 */
	
	public PasswordBooleanPanel (SettingsBufferPanel settingsBufferPanel)
	{
		super();
		this.settingsBufferpanel = settingsBufferPanel;
		initialize();
	}

	/**
	 * Function: initialize()
	 * 
	 * This function calls functions to create the panel.
	 */
	
	public void initialize()
	{
		createPanel();
		createComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function sets the layout manager for the panel and makes the panels clear
	 */
	
	public void createPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(false);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.black,Color.gray), "Security Settings"));
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function creates the settings panels and adds them to the panel.
	 */
	
	public void createComponents()
	{
		JPanel requirePasswordTimeoutPanel = new RequirePasswordSettingsPanel();
		JPanel logPasswordAttemptsPanel = new LogPasswordAttemptsPanel();
		JPanel reciveSafteyEmailPanel = new ReceiveSafetyEmailPanel(settingsBufferpanel);
		JPanel showNotificationsButtonPanel = new ShowNotificationsButtonPanel();
		JPanel eraseAppDataPanel = new EraseAppDataPanel();
		
		add(requirePasswordTimeoutPanel);
		add(logPasswordAttemptsPanel);
		add(reciveSafteyEmailPanel);
		add(showNotificationsButtonPanel);
		add(eraseAppDataPanel);
	}
	
	/**
	 * Function: resetPanel()
	 * 
	 * This function calls methods that reset the panels to their default settings
	 */
	
	public static void resetPanel()
	{
		RequirePasswordSettingsPanel.updateCheckbox(); 
		EraseAppDataPanel.updateCheckbox();
		LogPasswordAttemptsPanel.updatePanel();
		ReceiveSafetyEmailPanel.updateCheckboxSelection();
		ShowNotificationsButtonPanel.updateCheckBox();
		TimeCombobox.updateCombobox();
		SafetyEmailCountCombobox.updateCombobox();
		AttemptsCombobox.updateCombobox();
	}
}
