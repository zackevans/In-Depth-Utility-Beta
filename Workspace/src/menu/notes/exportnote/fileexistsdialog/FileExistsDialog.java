package menu.notes.exportnote.fileexistsdialog;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;

/**
 * Function: FileExistsDialog  
 * @author ZackEvans
 *
 * This Class is holds that main frame object for a dialog that warns the user that the file they are exporting already exists.
 */

public class FileExistsDialog 
{
	public static JFrame sameNameDialogFrame= new JFrame();  // create frame to hide the dialog
	BufferPanel bufferPanel;
	DialogMessage dialogMessage;
	JLabel warningImage;
	JButton replaceButton;
	JButton renameButton;
	JButton cancelButton;
	private static boolean clicked = false;
	
	/**
	 * Constructor: FileExistsDialog (BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This function inherits the bufferpanel object
	 */
	
	public FileExistsDialog (BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
	}
	
	/**
	 * Function: launchDialog()
	 * @author ZackEvans
	 * 
	 * This function creates the panel and shows it if the panel has not been shown yet. If the panel has been shown
	 * then it just displays the panel
	 */
	
	public void launchDialog()
	{	
		if (clicked == false) // if the panel has not been clicked
		{
			createAndShowGUI(); // creaet the panel and show it.
			clicked = true; // set the obejct to be marked as shown
		}
		
		else // if the window has been shown
		{
			showWindow(); // show the dialog window.
		}
	}
	
	/**
	 * Function: createAndShowGUI()
	 * @author ZackEvans
	 * 
	 * This function creates and shows the panel
	 */
	
	
	public void createAndShowGUI()
	{
		createComponents(); // call method to create the components on the panel
		
		sameNameDialogFrame.setSize(393, 167);  // set the size of the dialog frame.
		sameNameDialogFrame.setResizable(false); // make the frame note resizeable
		
		addComponents(); // add the components to the frame
		
		showWindow(); // display the window.
	}
	
	/**
	 * Function: showWindow()
	 * @author ZackEvans
	 * 
	 * This function sets the location where the dialog will display and then shows the dilaog frame.
	 */
	
	public void showWindow()
	{
		LaunchApp launchApp = new LaunchApp();
		
		// set location of the dialog
		int x = launchApp.frameXPosition() + 154; 
		int y = launchApp.frameYPosition() + 167; // centers window for y
		sameNameDialogFrame.setLocation(x, y); // set frame in center of main frame 
		
		//ShareList.list.clearSelection();
		sameNameDialogFrame.setVisible(true);
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This function creates the components that will be added to the panel
	 */
	
	public void createComponents()
	{
		// create components to be added to the dialog panel
		dialogMessage = new DialogMessage();
		warningImage = new WarningImage();
		replaceButton = new ReplaceButton(bufferPanel);
		renameButton = new RenameButton();
		cancelButton = new CancelButton();
		
		// set the size and location of the objects
		DialogMessage.errorTextArea.setBounds(100, 30, 293, 50);
		warningImage.setBounds(-10,16,120,75);
	}
	
	/**
	 * Function: addComponents()
	 * @author ZackEvans
	 * 
	 * This function adds all of the components to the panel.
	 */
	
	public void addComponents()
	{
		sameNameDialogFrame.getContentPane().setLayout(null); // remove the layout manager from the panel
		
		JPanel buttonPanel = new JPanel(); // create a new panel so that buttons will be evenly spaced along the bottom if the dialog frame.
		buttonPanel.setBounds(0, 100, 393, 30); // set the size and location of the dialog panel
		
		// add buttons to the panel
		buttonPanel.add(cancelButton);
		buttonPanel.add(renameButton);
		buttonPanel.add(replaceButton);
		
		// add all components to the dialog frame.
		sameNameDialogFrame.getContentPane().add(buttonPanel);
		sameNameDialogFrame.getContentPane().add(warningImage);
		sameNameDialogFrame.getContentPane().add(DialogMessage.errorTextArea);
	}
}
