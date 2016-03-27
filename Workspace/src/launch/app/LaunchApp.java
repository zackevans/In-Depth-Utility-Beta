package launch.app;
 // package identifier 

// Imports 
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import menu.buffer.BufferPanel;
import panel.wallpaper.Wallpaper;
import sql.DataBase;
import sql.notes.NotesDataBase;
import sql.saveandsend.SaveAndSendDataBase;
import sql.system.settings.SystemDatabase;
import statusbar.topbar.TopBar;

/**
 * @author ZackEvans
 * Class: Launch App
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
	private JFrame frame = new JFrame(); // Created JFrame VAR.
	private static Image wallpaperImage = null;
    private JLayeredPane layerPane = new JLayeredPane(); // Created JLayerPane to layer statusbar/wallpaper/bufferpanel.
    private static Wallpaper wallpaper;  // Created a Wallpaper Class Object.
    static BufferPanel bufferPanel = new BufferPanel(); // Created a BufferPanel Class Object.
    public static TopBar topBar; // Created a TopBar Class Object.
    
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
			@Override
			public void run() 
			{
				LaunchApp app = new LaunchApp();
				
				app.createAndShowGUI(); // Method Call to create program.
			}
		});
    	
    	dataBaseCalls(); // Method call to Initialize the Database.
    }
    
    /**
     * Create and show GUI method 
     * 
     * sets size restraints
     * TODO Create a Wrapper so the main panel will be able to auto resize (See IDU Beta 1 Resize) 
     */
    
    private void createAndShowGUI()
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
        
        createCheckAndSendEmailJob();
    }
    
    /**
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
    	// Create objects
    	final DataBase dataBase = new DataBase();
    	final SystemDatabase systemdb = new SystemDatabase();
    	final NotesDataBase notesdb = new NotesDataBase();
    	final SaveAndSendDataBase snsdb = new SaveAndSendDataBase();
    	
    	// create db location and create the database
    	dataBase.createDBLocation();
		dataBase.createDatabase();
		dataBase.checkConnection();
		
		// create the systems table in the database
		systemdb.createSystemTable();
		
		// create the notes table in the database
		notesdb.createNotesTable();
		
		//create the notes
		snsdb.createSaveAndSendTable();
    }
    
    
    public void createCheckAndSendEmailJob()
    {
    	JobDetail job = JobBuilder.newJob(CheckAndSendEmail.class)
    			.withIdentity("CheckAndSendEmailJob", "emailJobs").build();
    	
    	Trigger trigger = TriggerBuilder
    			.newTrigger()
    			.withIdentity("CheckAndSendEmail", "emailTriggers")
    			.withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * 1/1 * ? *")) 
    			.build();
    
    	Scheduler scheduler;
    	
		try 
		{
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
		} 
		
		catch (SchedulerException e) 
		{
			System.out.println("createCheckAndSendEmailJob() - Unable to Create Email Job");
			e.printStackTrace();
		}
    }
    
    /**
     * Function: frameXPosition
     * @return frame x coordinate
     */
    
    public int frameXPosition()
    {
    	return frame.getX(); // get x cord from frame
    }
    
    /**
     * Function: frameYPosition
     * @return frame y coordinate
     */
    
    public int frameYPosition()
    {
    	return frame.getY(); // get Y cord from frame
    }
    
    public JFrame getFrame()
    {
    	return frame;
    }
    
    public static void createWallpaperImage(URL url)
    {
    	try 
    	{
    		wallpaperImage = ImageIO.read(url);
 		} 
    	catch (IOException e) 
    	{
    		// TODO Auto-generated catch block
 			e.printStackTrace();
 			System.err.println("WallPaper Failed to Load");
 		} 
    }
}
