package menu.buffer;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import menu.loginpanel.LoginErrors;
import menu.loginpanel.LoginField;
import menu.loginpanel.LoginPanel;
import menu.main.MainMenu;
import menu.notes.Notes;
import menu.notes.exportnote.ExportNote;
import menu.notes.mailnote.MailNote;
import menu.settings.SettingsMenu;
import sql.systemsettings.passwordsettings.PasswordSettingsDatabase;
import statusbar.addons.BufferPanelBackButton;
import statusbar.addons.LockButton;

/**
 * Class: BufferPanel
 * @author ZackEvans
 * 
 * Bufferpanel class used to show and change main content panels
 */

public class BufferPanel extends JPanel
{
	// Creates hash map to store all main panels <Name of Panel,Panel Object>
	private Map <String, JPanel> mapPanels = new HashMap <String, JPanel>();
	// Create VAR for all main panels 
	private MainMenu mainMenu;
	private Notes notes;
	private MailNote mailNote;
	private ExportNote exportNote;
	private SettingsMenu settingsMenu;
	private LoginPanel loginPanel;
	public static String currentPanel = "MAIN_MENU";
	public static String lastPanel = "MAIN_MENU";
	
	/**
	 * Constructor: BufferPanel()
	 * @author ZackEvans
	 * 
	 * this constructor calls panel hierarchy.
	 */
	
	public BufferPanel()
	{
		super();
	}
	
	/**
	 * Function: initialize
	 * @author ZackEvans
	 * 
	 * Calls methods to create panel and components
	 */
	
	public void initialize()
	{
		createComponents();
		initializePanels();
		addComponents();
		setDefaults();
		setOpaque(false);
	}
	
	/**
	 * Function: createComponents
	 * @author ZackEvans
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
		
		mailNote = new MailNote(this);
		mapPanels.put("MAIL_NOTES", mailNote);
		
		exportNote = new ExportNote(this);
		mapPanels.put("EXPORT_NOTE", exportNote);
		
		//Settings
		settingsMenu = new SettingsMenu(this);
		mapPanels.put("SETTINGS_MENU", settingsMenu);
		
		// login panel
		loginPanel = new LoginPanel(this);
		mapPanels.put("LOGIN_PANEL", loginPanel);
	}
	
	/**
	 * Function: initializePanels
	 * @author ZackEvans
	 * 
	 * Initialize (create) all Panels 
	 */
	
	public void initializePanels()
	{
		// Main Menu
		mainMenu.initialize();
		
		// Notes
		notes.initialize();
		mailNote.initialize();
		exportNote.initialize();
		
		//Settings
		settingsMenu.initialize();
		
		loginPanel.initialize();
	}
	
	/**
	 * Function: addComponents
	 * @author ZackEvans
	 * 
	 * add all main panel components to panel (Separated in categories)
	 */
	
	public void addComponents()
	{
		// Main Menu 
		add(mainMenu);
		
		// Notes
		add(notes);
		add(mailNote);
		add(exportNote);
		
		// Settings
		add(settingsMenu);
		
		// login panel
		add(loginPanel);
	}
	
	/**
	 * Function: setDefaults
	 * @author ZackEvans
	 * 
	 * Check to see if there is a password in the database (getPassExist)
	 * Show login panel or main menu at start of the program
	 */
	
	public void setDefaults()
	{
		PasswordSettingsDatabase passwordSettingsDatabase = new PasswordSettingsDatabase();
		
		if(passwordSettingsDatabase.doesPasswordExist())
		{
			showRawPanel("LOGIN_PANEL");
		}
		
		else
		{
			showPanel("MAIN_MENU");
		}
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
		lastPanel = currentPanel;
		currentPanel = panelName;
		
		checkBackButton(panelName);
		
		for (JPanel panel : mapPanels.values()) // Run through all panels in hashmap
		{
			panel.setVisible(false); // hide all panels in bufferpanel
		}
		
		JPanel panelToShow = mapPanels.get(panelName); // Create holding panel (panelToShow) and set equal to panelName (Passed Pram)
		panelToShow.setVisible(true); // set new panel visible 
		showPanelPresets(panelName);
	}
	
	/**
	 * Function: showRawPanel(String panelName)
	 * @author ZackEvans
	 * @param panelName
	 * 
	 * This function shows a panel on the bufferPanel no questions asked.
	 * This method would be used by something like the login panel, something that you want to show but you don't want to register on the lastpanel var.
	 */
	
	public void showRawPanel(String panelName)
	{
		for (JPanel panel : mapPanels.values()) // Run through all panels in hashmap
		{
			panel.setVisible(false); // hide all panels in bufferpanel
		}
		
		JPanel panelToShow = mapPanels.get(panelName); // Create holding panel (panelToShow) and set equal to panelName (Passed Pram)
		panelToShow.setVisible(true); // set new panel visible 
		showPanelPresets(panelName);
	}
	
	/**
	 * Function: checkBackButton(String panelName)
	 * @author ZackEvans
	 * @param panelname
	 * 
	 * This function shows the back button when needed.
	 * This function also corrects the last panel setting in the bufferpanel in order to always return back to the correct panel.
	 */
	
	public void checkBackButton(String panelName)
	{
		if(panelName == "MAIN_MENU")
		{
			BufferPanelBackButton.backButton.setVisible(false);
		}
			
		else if(panelName == "NOTES")
		{
			BufferPanelBackButton.backButton.setVisible(true);
			lastPanel = "MAIN_MENU";
		}
			
		else
		{
			BufferPanelBackButton.backButton.setVisible(true);
		}
	}
	
	public void showPanelPresets(String panelName)
	{
		if(panelName == "LOGIN_PANEL")
		{
			BufferPanelBackButton.backButton.setVisible(false);
			LockButton.lockButton.setVisible(false);
			LoginErrors.loginError.setVisible(false);
			LoginField.loginField.setText("");
			LoginField.loginField.requestFocusInWindow();
		}
	}
}