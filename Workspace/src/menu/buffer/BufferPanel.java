package menu.buffer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.main.MainMenu;
import menu.settings.SettingsMenu;
import menu.settings.security.SecuritySettings;
import menu.settings.security.enterpassword.EnterPassword;
import menu.settings.security.passconfirm.PasswordConfirm;
import toolbar.topbar.TopBar;

public class BufferPanel extends JPanel
{
	private Map <String, JPanel> mapPanels = new HashMap <String, JPanel>();
	private MainMenu mainMenu;
	private SettingsMenu settingsMenu;
	private SecuritySettings securitySettings;
	private PasswordConfirm passConfirm;
	private EnterPassword enterPassword;
	
	
	public void initialize()
	{
		createComponents();
		addComponents();
		setDefaults();
		initializePanels();
		setOpaque(false);
	}
	
	public void createComponents() 
	{
		// Main Menu 
		mainMenu = new MainMenu(this);
		mapPanels.put("MAIN_MENU", mainMenu);
		
		// Security Settings
		settingsMenu = new SettingsMenu(this);
		mapPanels.put("SETTINGS_MENU", settingsMenu);
		securitySettings = new SecuritySettings(this);
		mapPanels.put("SECURITY_SETTINGS", securitySettings);
		passConfirm = new PasswordConfirm(this);
		mapPanels.put("PASSWORD_CONFIRM", passConfirm);
		enterPassword = new EnterPassword(this);
		mapPanels.put("ENTER_PASSWORD", enterPassword);
	}
	
	public void addComponents()
	{
		// Main Menu 
		add(mainMenu);
		
		// Security Settings
		add(settingsMenu);
		add(securitySettings);	
		add(passConfirm);
		add(enterPassword);
	}
	
	public void setDefaults()
	{
		showPanel("MAIN_MENU");
	}
	
	public void initializePanels()
	{
		// Main Menu
		mainMenu.initialize();
		
		// Security Settings
		settingsMenu.initialize();
		securitySettings.initialize();
		passConfirm.initialize();
		enterPassword.initialize();
	}
	
	public void showPanel(String panelName)
	{
		for (JPanel panel : mapPanels.values())
		{
			panel.setVisible(false);
		}
		
		JPanel panelToShow = mapPanels.get(panelName);
		panelToShow.setVisible(true);
	}
}
