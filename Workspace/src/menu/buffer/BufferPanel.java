package menu.buffer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.calendar.Calendar;
import menu.main.MainMenu;
import menu.notes.Notes;
import menu.settings.SettingsMenu;
import menu.settings.security.SecuritySettings;
import menu.settings.security.enterpassword.EnterPassword;
import menu.settings.security.login.Login;
import menu.settings.security.passconfirm.PasswordConfirm;
import menu.settings.security.removepass.RemovePassword;
import panel.screensaver.ScreenSaver;
import sql.system.settings.SystemDatabase;
import statusbar.topbar.TopBar;

/**
 * @author ZackEvans
 *
 * Bufferpanel class used to show and change main content panels
 *
 */

public class BufferPanel extends JPanel
{
	// Creates hash map to store all main panels <Name of Panel,Panel Object>
	private Map <String, JPanel> mapPanels = new HashMap <String, JPanel>();
	// Creates SystemDataBase to access startup functions
	private SystemDatabase systemDB = new SystemDatabase();
	// Create VAR for all main panels 
	private MainMenu mainMenu;
	private SettingsMenu settingsMenu;
	private SecuritySettings securitySettings;
	private PasswordConfirm passConfirm;
	private EnterPassword enterPassword;
	private RemovePassword removePassword;
	private Login login;
	private ScreenSaver screenSaver;
	private Notes notes;
	private Calendar calendar;
	
	/**
	 * function: initialize
	 * 
	 * Calls methods to create panel and components
	 * 
	 */
	
	public void initialize()
	{
		createComponents();
		addComponents();
		setDefaults();
		initializePanels();
		setOpaque(false);
	}
	
	/**
	 * Function: createComponents
	 * 
	 * initialize object
	 * Add panel object in to hashmap (mapPanels) 
	 * 
	 */
	
	public void createComponents() 
	{
		// Main Menu 
		mainMenu = new MainMenu(this);
		mapPanels.put("MAIN_MENU", mainMenu);
		
		// Notes
		notes = new Notes(this);
		mapPanels.put("NOTES", notes);
		
		//Calendar
		calendar = new Calendar(this);
		mapPanels.put("CALENDAR_MENU", calendar);
		
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
	
	/**
	 * Function: addComponents
	 * 
	 * add all main panel components to panel (Separated in categories)
	 * 
	 * 
	 */
	
	public void addComponents()
	{
		// Main Menu 
		add(mainMenu);
		
		// Notes
		add(notes);
		
		//Calendar
		add(calendar);
		
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
	
	/**
	 * Function: initializePanels
	 * 
	 * Initialize (create) all Panels 
	 * 
	 */
	
	public void initializePanels()
	{
		// Main Menu
		mainMenu.initialize();
		
		// Notes
		notes.initialize();
		
		//Calendar
		calendar.initialize();
		
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
	
	/**
	 * Function: setDefaults
	 * 
	 * Check to see if there is a password in the database (getPassExist)
	 * Show login panel or main menu at start of the program
	 */
	
	public void setDefaults()
	{
		if (systemDB.getPassExist() == true) // Check database is a password exists
		{
			login.setNextPanel("MAIN_MENU"); // setNext panel the loginpanel will show
			showPanel("LOGIN_PANEL"); // show the login panel
		}
		
		else
		{
			showPanel("MAIN_MENU"); // show the main menu as the first panel shown 
		}
	}
	
	/** 
	 * Function: showPanel
	 * 
	 * Hide all panels in the hashmap using for loop
	 * Create holding panel and set to new panel (Passed pram)
	 * Set new panel visiable
	 * 
	 * @param panelName
	 */
	
	public void showPanel(String panelName)
	{
		for (JPanel panel : mapPanels.values()) // Run through all panels in hashmap
		{
			panel.setVisible(false); // hide all panels 
		}
		
		JPanel panelToShow = mapPanels.get(panelName); // Create holding panel (panelToShow) and set equal to panelName (Passed Pram)
		panelToShow.setVisible(true); // set new panel visiable 
	}
}