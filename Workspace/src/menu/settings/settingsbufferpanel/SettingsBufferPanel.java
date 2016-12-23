package menu.settings.settingsbufferpanel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import menu.settings.SettingsList;
import menu.settings.settingspanels.passwordandsecuritypanel.PasswordAndSecuritySettingsPanel;
import menu.settings.settingspanels.userpanel.UserSettingsPanel;

/**
 * Class: SettingsBufferPanel
 * @author ZackEvans
 *
 * This class is a panel that shows the content for the settings menus.
 */

public class SettingsBufferPanel extends JPanel
{
	private Map <String, JPanel> mapPanels = new HashMap <>();
	private PasswordAndSecuritySettingsPanel passwordAndSecuritySettingsPanel;
	private UserSettingsPanel userSettingsPanel;
	
	/**
	 * Constructor: SettingsBufferPanel ()
	 * 
	 * This constructor calls the panel hierarchy and calls a method to create the panel.
	 */
	
	public SettingsBufferPanel ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods that create the panel
	 */
	
	public void initialize()
	{
		createComponents();
		initializePanels();
		addComponents();
		
		setOpaque(false);
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function creates the objects to be added to the panel and adds the panels to a map for fast lookup.
	 */
	
	public void createComponents()
	{
		passwordAndSecuritySettingsPanel = new PasswordAndSecuritySettingsPanel(this);
		userSettingsPanel = new UserSettingsPanel();
		
		mapPanels.put("PASSWORD_AND_SECURITY_SETTINGS", passwordAndSecuritySettingsPanel);
		mapPanels.put("USER_SETTINGS", userSettingsPanel);
	}
	
	/**
	 * Function: initializePanels()
	 * 
	 * This function calls methods to set up the individual components.
	 */
	
	public void initializePanels()
	{
		passwordAndSecuritySettingsPanel.initialize();
		userSettingsPanel.initialize();
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This methods adds the components to the panel
	 */
	
	public void addComponents()
	{
		add(passwordAndSecuritySettingsPanel);
		add(userSettingsPanel);
	}
	
	/** 
	 * Function: showPanel
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
	
	/**
	 * Function: showPanelAndList(String panelName)
	 * @param panelName
	 * 
	 * This function shows a panel and highlights the corresponding item in the list.
	 */
	
	public void showPanelAndList(String panelName)
	{
		showPanel(panelName); 
		
		SettingsList.settingsList.setSelectedIndex(Arrays.asList(SettingsList.listItems).indexOf(panelName) +1);
	}
	
	/**
	 * Function: clearPanel()
	 * 
	 * This function hides all the panels on the frame.
	 */
	
	public void clearPanel()
	{
		for (JPanel panel : mapPanels.values()) // Run through all panels in hashmap
		{
			panel.setVisible(false); // hide all panels in bufferpanel
		}
	}
	
	/**
	 * Function: resetAllPanels()
	 * 
	 * This function sets the presets for all of the panels.
	 */
	
	public static void resetAllPanels()
	{
		PasswordAndSecuritySettingsPanel.resetPanel();
		UserSettingsPanel.resetPanel();
	}
	
	/**
	 * Function: setPanelPresets(String panelName)
	 * @param panelName
	 * 
	 * This function sets up default state for all of the panels.
	 */
	
	public void setPanelPresets(String panelName)
	{
		switch (panelName) 
		{
			case "USER_SETTINGS":
				UserSettingsPanel.resetPanel();
				break;
			case "PASSWORD_AND_SECURITY_SETTINGS":
				PasswordAndSecuritySettingsPanel.resetPanel();
				break;
			default:
				break;
		}
	}
}
