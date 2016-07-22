package menu.buffer;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import menu.main.MainMenu;
import menu.notes.Notes;
import menu.notes.exportnote.ExportNote;
import menu.notes.mailnote.MailNote;
import sql.systemsettings.SystemSettingsDatabase;

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
		// Creates SystemDataBase to access startup functions
		SystemSettingsDatabase systemDB = new SystemSettingsDatabase();
		
		if (systemDB.getPassExist() == true) // Check database is a password exists
		{
			//showPanel("LOGIN_PANEL"); // show the login panel
		}
		
		else
		{
			showPanel("MAIN_MENU"); // show the main menu as the first panel shown 
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
		for (JPanel panel : mapPanels.values()) // Run through all panels in hashmap
		{
			panel.setVisible(false); // hide all panels in bufferpanel
		}
		
		JPanel panelToShow = mapPanels.get(panelName); // Create holding panel (panelToShow) and set equal to panelName (Passed Pram)
		panelToShow.setVisible(true); // set new panel visible 
	}
}