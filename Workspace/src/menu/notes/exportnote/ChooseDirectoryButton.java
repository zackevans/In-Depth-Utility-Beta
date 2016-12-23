package menu.notes.exportnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import launch.app.LaunchApp;

/**
 * Class: ChooseDirectoryButton
 * @author ZackEvans
 *
 * This class is a button that prompts the user with a file chooser so they can choose where the note will be exported to.
 */

public class ChooseDirectoryButton extends JButton
{
	/**
	 * Constructor: ChooseDirectoryButton ()
	 * 
	 * This constructor calls button hierarchy and calls a method to setup the button.
	 */
	
	public ChooseDirectoryButton ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls a method to create and setup the button
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets the text and removes the selected border from the button.
	 */
	
	public void createButton()
	{
		setText("Choose ...");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This method adds an action listener to the button.
	 * When the action is performed a JFileChooser is shown.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener()  // add action listener to button
		{
			@Override
			public void actionPerformed(ActionEvent e) // override what happens when the button is clicked
			{
				// create JFile chooser
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select File Location");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // only make the file chooser show directory paths
				fileChooser.setCurrentDirectory(new java.io.File("/Desktop")); // set the defult directory
				
				int status = fileChooser.showOpenDialog(LaunchApp.frame); // create a variable for the status of the dialog
			
				if (status == JFileChooser.APPROVE_OPTION) // if the filechoosers approve button is pressed
				{
					File selectedFile = fileChooser.getSelectedFile(); // create a file
					String directory = selectedFile.getParent() + "/" + selectedFile.getName(); // create a directroy path for the file
					
					FileDirectoryField.fileDirectoryTextField.setText(directory); // show the text in the textfield.
					
					ExportErrorNotePanel.checkFileWarning(); // check to see if the file already exists.
				} 
			}
		});
	}
}
