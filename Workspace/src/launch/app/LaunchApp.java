package launch.app; // package identifier 

// Imports 
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import menu.buffer.BufferPanel;
import program.wallpaper.Wallpaper;
import sql.DataBase;
import sql.system.settings.SystemDatabase;
import statusbar.topbar.TopBar;



// Main Class: 
public class LaunchApp 
{
	// Created locked VAR. 
	public static final int Window_Width = 700; 
	public static final int Window_Height = 500; 
	
	// Created JFrame VAR. 
	private static JFrame frame = new JFrame();
	
	// Created JLayerPane to layer statusbar/wallpaper/bufferpanel.
    private static JLayeredPane layerPane = new JLayeredPane();
    
    // Created a Wallpaper Class Object.
    private static Wallpaper wallpaper; 
    
    // Created a BufferPanel Class Object.
    static BufferPanel bufferPanel = new BufferPanel();
    
    // Created a TopBar Class Object.
    public static TopBar topBar;
    
    
    
    /**
     * Main initial method
     * 
     * Creates thread to run GUI components
     * Calls Main Database methods 
     * 
     */
    public static void main(String[] args) 
    {
    	SwingUtilities.invokeLater(new Runnable() // Created Runnable thread to run GUI.
		{
			public void run() 
			{
				createAndShowGUI(); // Method Call to created program.
			}
		});
    	
    	dataBaseCalls(); // Method call to Initialize the Database.
    }
    
    /**
     * Create and show GUI method 
     * 
     * sets size restraints
     * 		//TODO Create a Wrapper so the main panel will be able to auto resize (See IDU Beta 1 Resize) 
     */
    
    private static void createAndShowGUI()
    {
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
        
        // Initialize wallpaper object.
        // TODO put image on JLabel and resize to fit the Frame size.
        wallpaper = new Wallpaper("/Library/Desktop Pictures/Underwater.jpg");
        
        // Initialize top bar object.
        // Set location and size of top bar object.
        // Call Method to create top bar.
        topBar = new TopBar(bufferPanel); 
        topBar.setBounds(0, 0, 700, 21);
        topBar.initialize();
        
        // Add objects to layer panel.
        	// wallpaper on bottom and not opaque.
        	// bufferpanel and top bar are all opaque. 
        layerPane.add(wallpaper, new Integer(0), 0);
        layerPane.add(bufferPanel, new Integer(1), 0);
        layerPane.add(topBar, new Integer(2), 0);
        
        // Add the layerPane to the Content pane of the frame
        // Pack Frame to compress the panel
        // Set the frame visible 
        frame.getContentPane().add(layerPane);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Method that creates dbLocation and the Database
     * 
     * Declare database objects
     * Create Database Location and checkConnection 
     * 		// TODO if this fails put alert icon in status bar
     * 		put the icons on own panel with manager to put in line 
     * 		put the notifications button on own section to it doesn't resize with list it is Locked
     * Initialize system db table
     * 
     */
    
    private static void dataBaseCalls()
    {
    	// Create objects
    	final DataBase dataBase = new DataBase();
    	final SystemDatabase systemdb = new SystemDatabase();
    	
    	// create db location and create the database
    	dataBase.createDBLocation();
		dataBase.createDatabase();
		dataBase.checkConnection();
		
		// create the systems table in the database
		systemdb.createSystemTable();
    }
}
