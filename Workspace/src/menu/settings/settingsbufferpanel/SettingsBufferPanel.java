package menu.settings.settingsbufferpanel;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import menu.settings.settingspanels.passwordandsecuritypanel.PasswordAndSecuritySettingsPanel;

public class SettingsBufferPanel extends JPanel
{
	private Map <String, JPanel> mapPanels = new HashMap <String, JPanel>();
	private PasswordAndSecuritySettingsPanel passwordAndSecuritySettingsPanel;
	
	public SettingsBufferPanel ()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createPanel();
		createComponents();
		initializePanels();
		addComponents();
		
		setOpaque(false);
	}
	
	public void createPanel()
	{
		setLayout(new BorderLayout());
	}
	
	public void createComponents()
	{
		passwordAndSecuritySettingsPanel = new PasswordAndSecuritySettingsPanel();
		
		mapPanels.put("PASSWORD_AND_SECURITY_SETTINGS", passwordAndSecuritySettingsPanel);
	}
	
	public void initializePanels()
	{
		passwordAndSecuritySettingsPanel.initialize();
	}
	
	public void addComponents()
	{
		add(passwordAndSecuritySettingsPanel);
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
		for (JPanel panel : mapPanels.values()) // Run through all panels in hashmap
		{
			panel.setVisible(false); // hide all panels in bufferpanel
		}
		
		JPanel panelToShow = mapPanels.get(panelName); // Create holding panel (panelToShow) and set equal to panelName (Passed Pram)
		panelToShow.setVisible(true); // set new panel visible 
	}
	
	
	public static void resetAllPanels()
	{
		PasswordAndSecuritySettingsPanel.resetPanel();
	}
	
	
}
