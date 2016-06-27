package menu.notes.sharenotesdialog;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;

/**
 * Class: ShareNotesDialog
 * @author ZackEvans
 *
 * This class is a window that prompts users with the options to share with
 */
public class ShareNotesDialog 
{
	public static JFrame customFrame = new JFrame();  // Created JFrame 
	private static boolean clicked = false; // create var to see if the window has been shown before
	BufferPanel bufferPanel; // create a bufferPanel object
	
	/**
	 * Constructor: ShareNotesDialog(BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 *
	 * This constructor inherit the bufferPanel object and calls panel hierarchy.
	 */
	
	public ShareNotesDialog(BufferPanel bufferPanel)
	{
		super(); // calls panel hierarchy
		this.bufferPanel  = bufferPanel; // inherit bufferPanel object
	}
	
	/**
	 * Function: launchDialog()
	 * @author ZackEvans
	 * 
	 * This function is called to show the share window
	 */
	
	public void launchDialog()
	{
		if (clicked == false) // if the window has not been shown before
		{		
			createAndShowGUI(); // create the window and show it
			clicked = true; // register that window has been clicked
		}
		
		else // if window has been shown before
		{
			showWindow(); // display window
		}
	}
	
	/**
	 * Function: createAndShowGUI()
	 * @author ZackEvans
	 * 
	 * This function creates the share window and adds list to it.
	 */
	
	public void createAndShowGUI()
	{
		LaunchApp launchApp = new LaunchApp();
		final int Window_Width = 150; 
		final int Window_Height = 167; 
		// sets window off the the right of the panel
		int x = launchApp.frameXPosition()+ 700; 		
		int y = launchApp.frameYPosition()+ 42; 
		
		createComponents(); // create components first before launching the app
		
		customFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK)); // create a black border around
		customFrame.getRootPane().putClientProperty("Window.shadow", Boolean.FALSE ); // take the shadow off the window
		
		customFrame.setLocation(x, y); // set frame in top right of panel
		customFrame.setSize(Window_Width, Window_Height); // set size of the window
		customFrame.setUndecorated(true); // removes status bar from frame
		customFrame.setResizable(false); // make the window resizeable
		customFrame.setBackground(new Color(225,225,225,0x66)); // set color of window to clear white
		
		addComponents(); // add components to frame
		
		customFrame.setVisible(true); // show frame
	}
	
	/**
	 * Function: showWindow()
	 * @author ZackEvans
	 * 
	 * This function updates the location of the window and displays it.
	 */
	
	public void showWindow()
	{
		LaunchApp launchApp = new LaunchApp();
		
		int x = launchApp.frameXPosition()+700 ; //  centers window for x
		int y = launchApp.frameYPosition()+ 42 ; // centers window for y
		customFrame.setLocation(x, y); // set frame in center of main frame 
		
		ShareList.list.clearSelection(); // clear the selection of the list
		customFrame.setVisible(true); // show the list
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * Function creates the list object that is to be added to the panel
	 */
	
	public void createComponents()
	{
		ShareList shareList = new ShareList(bufferPanel);
		shareList.initialize(); // call method to set up the share list.
	}
	
	/**
	 * Function: addComponents()
	 * @author ZackEvans
	 * 
	 * Function adds the list to the frame
	 */
	
	public void addComponents()
	{
		customFrame.getContentPane().add(ShareList.list); // add share list to panel
	}
}
