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
import menu.settings.security.login.Login;
import menu.settings.security.passconfirm.PasswordConfirm;
import menu.settings.security.removepass.RemovePassword;
import panel.screensaver.ScreenSaver;
import sql.system.settings.SystemDatabase;
import statusbar.topbar.TopBar;

public class BufferPanel extends JPanel
{
	private Map <String, JPanel> mapPanels = new HashMap <String, JPanel>();
	private SystemDatabase systemDB = new SystemDatabase();
	private MainMenu mainMenu;
	private SettingsMenu settingsMenu;
	private SecuritySettings securitySettings;
	private PasswordConfirm passConfirm;
	private EnterPassword enterPassword;
	private RemovePassword removePassword;
	private Login login;
	private ScreenSaver screenSaver;
	
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
		removePassword = new RemovePassword(this);
		mapPanels.put("REMOVE_PASSWORD", removePassword);
		login = new Login(this);
		mapPanels.put("LOGIN_PANEL", login);
		
		// Other Panels
		screenSaver = new ScreenSaver(this);
		mapPanels.put("SCREEN_SAVER", screenSaver);
		
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
		add(removePassword);
		add(login);
		
		// Other Panels
		add(screenSaver);
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
		removePassword.initialize();
		login.initialize();
		
		//Other Panel
		screenSaver.initialize();
	}
	
	public void setDefaults()
	{
		if (systemDB.getPassExist() == true)
		{
			login.setNextPanel("MAIN_MENU");
			showPanel("LOGIN_PANEL");
		}
		
		else
		{
			showPanel("MAIN_MENU");
		}
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
