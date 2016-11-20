package launch.app;
 // package identifier 

// Imports 
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import com.apple.eawt.Application;

import file.filemanager.FileManager;
import jobs.handler.JobHandler;
import menu.buffer.BufferPanel;
import menu.notes.AddNoteButton;
import menu.notes.addnotedialog.AddNoteDialog;
import menu.notes.sharenotesdialog.ShareNotesDialog;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog.PasswordAttemptsDialog;
import panel.wallpaper.Wallpaper;
import sql.DataBase;
import sql.notes.NotesDataBase;
import sql.saveandsend.SaveAndSendDataBase;
import sql.saveandsend.SaveAndSendSettingsDataBase;
import sql.systemsettings.passwordsettings.PasswordSettingsDatabase;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;
import sql.userinfo.UserInfoDatabase;
import statusbar.StatusBar;

/**
 * Class: Launch App
 * @author ZackEvans
 *  
 * class that contains the main method.
 * main jframe and GUI is set up.
 * db is setup
 */

public class LaunchApp 
{
	// Created locked VAR. 
	public static final int Window_Width = 700; 
	public static final int Window_Height = 500;
	public static JFrame frame = new JFrame(); // Created JFrame VAR. MUST be static for popups to position correctly
	private static Image wallpaperImage = null;
    private static JLayeredPane layerPane = new JLayeredPane(); // Created JLayerPane to layer statusbar/wallpaper/bufferpanel.
    static BufferPanel bufferPanel = new BufferPanel(); // Created a BufferPanel Class Object.
    
    /**
     * Function: main(String[] args) 
     * @author ZackEvans
     * 
     * Main initial method
     * Creates thread to run GUI components
     * Calls Main Database methods 
     */
    
    public static void main(String[] args) 
    {	
    	dataBaseCalls(); // Method call to Initialize the Database.
    	FileManager.createFiles();
    	  	
    	SwingUtilities.invokeLater(new Runnable() // Created Runnable thread to run GUI.
		{
			@Override
			public void run() 
			{
				createAndShowGUI(); // Method Call to create program.
			}
		});
    }
    
    /**
     * Function: Create and show GUI()
     * @author ZackEvans
     * 
     * sets size restraints
     * TODO Create a Wrapper so the main panel will be able to auto resize (See IDU Beta 1 Resize) 
     */
    
    private static void createAndShowGUI()
    {
    	// create variables to be initialized and used later
    	Wallpaper wallpaper;
    	StatusBar statusBar;
    	
    	createAppIcon();
    	
    	//sets size restraints
    	frame.setSize(Window_Width, Window_Height); 
    	frame.setMinimumSize(new Dimension(Window_Width,Window_Height));
    	
    	// Set Frame nonResizable because it doesn't resize correct. 
    	frame.setResizable(false);
    	
    	// End program when 'x' is Clicked.
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	
    	// Initialize bufferpanel and set size.
        bufferPanel.initialize();    
        bufferPanel.setSize(frame.getWidth(),frame.getHeight());
        
        // Create url for the image location so it can be added to the program
        // Image Path: /Library/Desktop Pictures/Wave.jpg
        URL url = LaunchApp.class.getResource("/Wallpaper/mavericks_2560.jpg");
        
        //Create wallpaper image var and ini it 
        createWallpaperImage(url);
        // create wallpaper object
        wallpaper = new Wallpaper(wallpaperImage);
        
        // Initialize top bar object.
        // Set location and size of top bar object.
        // Call Method to create top bar.
        statusBar = new StatusBar(bufferPanel); 
        statusBar.setBounds(0, 0, 700, 21);
        statusBar.initialize();
        
        // Add objects to layer panel.
        // wallpaper on bottom and not opaque.
        // bufferpanel and top bar are all opaque. 
        layerPane.add(wallpaper, new Integer(0), 0);
        layerPane.add(bufferPanel, new Integer(1), 0);
        layerPane.add(statusBar, new Integer(2), 0);
        
        // Add the layerPane to the Content pane of the frame
        // Pack Frame to compress the panel
        // Set the frame visible 
        frame.getContentPane().add(layerPane);
        frame.pack();
        frame.setVisible(true);
        
       JobHandler jobHandler = new JobHandler(bufferPanel);
       jobHandler.createJobs();
    }
    
    /**
     * Function: dataBaseCalls()
     * @author ZackEvans
     * 
     * Method that creates dbLocation and the Database
     * 
     * Declare database objects
     * Create Database Location and checkConnection 
     * 		TODO if this fails put alert icon in status bar
     * 		put the icons on own panel with manager to put in line 
     * 		put the notifications button on own section to it doesn't resize with list it is Locked
     * Initialize system db table
     */
    
    private static void dataBaseCalls() 
    {
    	// Create  db objects
    	final DataBase dataBase = new DataBase();
    	final NotesDataBase notesdb = new NotesDataBase();
    	final SaveAndSendDataBase snsdb = new SaveAndSendDataBase();
    	final SaveAndSendSettingsDataBase saveAndSendSettingsdb = new SaveAndSendSettingsDataBase();
    	final PasswordSettingsDatabase passwordSettingsDatabase = new PasswordSettingsDatabase();
    	final SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
    	final UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
    	
    	// create db location and create the database
    	dataBase.createDBLocation();
		dataBase.createDatabase();
		
		// create settings tables
		passwordSettingsDatabase.createPasswordTable();
		
		// create security settings database
		securitySettingsDatabase.createSecuritySettingsTable();
		
		// create the notes table in the database
		notesdb.createNotesTable();
		
		//create the save and send email services tables
		snsdb.createSaveAndSendTable();
		saveAndSendSettingsdb.createSaveAndSendSettingsTable();
		
		userInfoDatabase.createUserInfoTable();
    }
     
    /**
     * Function: frameXPosition()
     * @author ZackEvans
     * @return frame x coordinate
     * 
     * Function returns the X cordinate of the MainJFrame.
     */
    
    public int frameXPosition()
    {
    	return frame.getX(); // get x cord from frame
    }
    
    /**
     * Function: frameYPosition()
     * @author ZackEvans
     * @return frame y coordinate
     * 
     * Function returns the Y cordinate of the MainJFrame.
     */
    
    public int frameYPosition()
    {
    	return frame.getY(); // get Y cord from frame
    }
    
    /**
     * Function: getFrame()
     * @author ZackEvans
     * @return frame
     * 
     * Function returns the main frame JFrame object
     */
    
    public JFrame getFrame()
    {
    	return frame; // return main frame object.
    }
    
    /**
     * Function: createWallpaperImage(URL url)
     * @author ZackEvans
     * @param url
     * 
     * Function creates wallpaper image from a URL
     */
    
    public static void createWallpaperImage(URL url)
    {
    	try 
    	{
    		wallpaperImage = ImageIO.read(url); // try to set the Image (wallpaperImage) to the URL(url)
 		} 
    	catch (IOException e) 
    	{
    		// if process fails then print failures
 			e.printStackTrace();
 		} 
    }
    
    public static void hideAllOtherWindows()
    {
    	ShareNotesDialog.customFrame.setVisible(false);
		AddNoteDialog.customFrame.setVisible(false);
		PasswordAttemptsDialog.customFrame.setVisible(false);
    }
    
    public static void createAppIcon()
    {
    	Application application = Application.getApplication();
    	URL url = AddNoteButton.class.getResource("/Button_Images/Program/Dock.png"); // add resource to the project
    	Image image = Toolkit.getDefaultToolkit().getImage(url);
    	
    	application.setDockIconImage(image);
    }
}
