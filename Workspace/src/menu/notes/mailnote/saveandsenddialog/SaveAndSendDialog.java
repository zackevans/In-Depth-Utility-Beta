package menu.notes.mailnote.saveandsenddialog;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;

/**
 * Class: SaveAndSendDialog 
 * @author ZackEvans
 *
 * This class holds the dialog frame that asks the user is they want to save the notes that can't be sent.
 */

public class SaveAndSendDialog 
{
	public static JFrame saveAndSendDialogFrame = new JFrame(); // create frame to show
	public static final int Window_Width = 393;
	public static final int Window_Height = 167;
	private static boolean clicked = false; // has the window been show before 
	BufferPanel bufferPanel;
	JLabel errorImage = new ErrorImage();
	JTextArea errorTextArea = new ErrorLabel();
	public static JCheckBox checkBox = new AlwaysSNSCheckbox();
	JButton discardButton;
	JButton saveAndSendButton;
	
	/**
	 * Constructor: SaveAndSendDialog(BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This constructor inherits the bufferPanel object.
	 */
	
	public SaveAndSendDialog(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
	}
	
	/**
	 * Function: launchDialog ()
	 * @author ZackEvans
	 * 
	 * This function launches and shows the dialog
	 */
	
	public void launchDialog ()
	{
		if (clicked == false) // if the window has not been shown 
		{
			createAndShowGUI();// create and setup the window
			clicked = true; // mark that the winodw has been shown
		}
		
		else // if the window has been shown
		{
			showWindow(); // show the window
		}
	}
	
	/**
	 * Function: createAndShowGUI()
	 * @author ZackEvans
	 * 
	 * This function creates the components to be added to the window and shows the frame.
	 */
	
	public void createAndShowGUI()
	{
		// set size of window and make it not resizeable 
		saveAndSendDialogFrame.setSize(Window_Width, Window_Height); 
		saveAndSendDialogFrame.setResizable(false);
		
		createComponents(); // call method to create the components to be added to the panel
		
		saveAndSendDialogFrame.getContentPane().setLayout(null); // remove layout manager from the frames content panel
		
		addComponents(); // call method to add components to frame
		
		showWindow(); // call method to set the position of the window and show it.
	}
	
	/**
	 * Function: showWindow()
	 * @author ZackEvans
	 * 
	 * this method sets the location of the window in the center of the Main Frame and shows it.
	 */
	
	public void showWindow()
	{
		// calculate the position of the window
		LaunchApp launchApp = new LaunchApp();
		int x = launchApp.frameXPosition() + 154; 
		int y = launchApp.frameYPosition() + 167;
		
		saveAndSendDialogFrame.setLocation(x, y); // set the location of the window
		
		checkBox.setSelected(false); // un-check the check box
		
		saveAndSendDialogFrame.setVisible(true); // show the frame
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This method creates componets and sets the size and location of them.
	 */
	
	public void createComponents()
	{
		// initialize the components
		discardButton = new DiscardButton(bufferPanel);
		saveAndSendButton = new SaveAndSendButton(bufferPanel);
		
		// set the size and location of the components
		errorImage.setBounds(10,16,75,55);
		errorTextArea.setBounds(95, 16, 298,50 );
		checkBox.setBounds(89, 70, 200, 25);
		discardButton.setBounds(148,110, 100, 25);
		saveAndSendButton.setBounds(253, 110, 120, 25);
	}
	
	/**
	 * Function: addComponents()
	 * @author ZackEvans
	 * 
	 * This method adds all the components to the frame
	 */
	
	public void addComponents()
	{
		// add components to the contenet pane of the frame
		saveAndSendDialogFrame.getContentPane().add(errorImage);
		saveAndSendDialogFrame.getContentPane().add(errorTextArea);
		saveAndSendDialogFrame.getContentPane().add(checkBox);
		saveAndSendDialogFrame.getContentPane().add(discardButton);
		saveAndSendDialogFrame.getContentPane().add(saveAndSendButton);
	}
}