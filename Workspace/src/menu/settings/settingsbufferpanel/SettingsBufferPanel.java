package menu.settings.settingsbufferpanel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import menu.settings.SettingsList;
import menu.settings.settingspanels.passwordandsecuritypanel.PasswordAndSecuritySettingsPanel;
import menu.settings.settingspanels.userpanel.UserSettingsPanel;

public class SettingsBufferPanel extends JPanel
{
	private Map <String, JPanel> mapPanels = new HashMap <String, JPanel>();
	private PasswordAndSecuritySettingsPanel passwordAndSecuritySettingsPanel;
	private UserSettingsPanel userSettingsPanel;
	
	public SettingsBufferPanel ()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createComponents();
		initializePanels();
		addComponents();
		
		setOpaque(false);
	}
	
	public void createComponents()
	{
		passwordAndSecuritySettingsPanel = new PasswordAndSecuritySettingsPanel(this);
		userSettingsPanel = new UserSettingsPanel();
		
		mapPanels.put("PASSWORD_AND_SECURITY_SETTINGS", passwordAndSecuritySettingsPanel);
		mapPanels.put("USER_SETTINGS", userSettingsPanel);
	}
	
	public void initializePanels()
	{
		passwordAndSecuritySettingsPanel.initialize();
		userSettingsPanel.initialize();
	}
	
	public void addComponents()
	{
		add(passwordAndSecuritySettingsPanel);
		add(userSettingsPanel);
	}
	
	/** 
	 * Function: showPanel
	 * @author ZackEvans
	 * @param panelName
	 * @see new panel
	 * 
	 * Hide all panels in the hashmap using for loop
	 * Create holding panel and set to new panel (Passed pram)
	 * Set new panel visible
	 */
	
	public void showPanel(String panelName)
	{
		clearPanel();
		setPanelPresets(panelName);
		
		JPanel panelToShow = mapPanels.get(panelName); // Create holding panel (panelToShow) and set equal to panelName (Passed Pram)
		panelToShow.setVisible(true); // set new panel visible 
	}
	
	public void showPanelAndList(String panelName)
	{
		clearPanel();
		setPanelPresets(panelName);
		
		JPanel panelToShow = mapPanels.get(panelName); // Create holding panel (panelToShow) and set equal to panelName (Passed Pram)
		panelToShow.setVisible(true); // set new panel visible 
		
		SettingsList.settingsList.setSelectedIndex(Arrays.asList(SettingsList.listItems).indexOf(panelName) +1);
	}
	
	public void clearPanel()
	{
		for (JPanel panel : mapPanels.values()) // Run through all panels in hashmap
		{
			panel.setVisible(false); // hide all panels in bufferpanel
		}
	}
	
	public static void resetAllPanels()
	{
		PasswordAndSecuritySettingsPanel.resetPanel();
		UserSettingsPanel.resetPanel();
	}
	
	public void setPanelPresets(String panelName)
	{
		if(panelName == "USER_SETTINGS")
		{
			UserSettingsPanel.resetPanel();
		}
		
		if(panelName == "PASSWORD_AND_SECURITY_SETTINGS")
		{
			PasswordAndSecuritySettingsPanel.resetPanel();
		}
	}
}
