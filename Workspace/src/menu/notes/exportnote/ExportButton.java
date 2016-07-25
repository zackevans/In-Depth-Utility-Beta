package menu.notes.exportnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.exportnote.fileexistsdialog.DialogMessage;
import menu.notes.exportnote.fileexistsdialog.FileExistsDialog;
import program.util.FileUtil;

/**
 * Class: ExportButton
 * @author ZackEvans
 *
 * This class is a button that when clicked will export the note
 */

public class ExportButton extends JButton
{
	BufferPanel bufferPanel; // bufferPanel object used to access method to show a new panel
	
	/**
	 * Constructor: ExportButton (BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This constructor inherits the bufferPanel object and calls button hierarchy
	 */
	
	public ExportButton (BufferPanel bufferPanel)
	{
		super(); 
		this.bufferPanel = bufferPanel; // inherit bufferPanel object
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods that setup the button.
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton ()
	 * @author ZackEvans
	 * 
	 * This method sets the button text and removes the border from the button
	 */
	
	public void createButton ()
	{
		setText("Export");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This method adds an action listener to the button.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener()  // add action listener
		{
			@Override
			public void actionPerformed(ActionEvent e) // override what happens when button is clicked.
			{
				// create the filelocation d
				String fileLocation = FileDirectoryField.fileDirectoryTextField.getText() + "/" + FileNameField.fileNameTextField.getText() + ".txt";
			
				// if all the fields are checked
				if(SelectNote.comboBox.getSelectedIndex() !=0 && FileNameField.fileNameTextField.getText().length() !=0 && FileDirectoryField.fileDirectoryTextField.getText().length() !=0)
				{
					if (!FileUtil.doesFileExist(fileLocation)) // check if file doesen't exit.
					{				
						FileUtil.createNewFile(fileLocation, ExportPreviewNote.textArea.getText());	 // create a new file
						bufferPanel.showPanel("NOTES"); // show the notes panel
					}
					
					else // if the file exists
					{
						FileExistsDialog  fileExistsDialog = new FileExistsDialog(bufferPanel);
						
						fileExistsDialog.launchDialog(); // call method to show the file dialog
						DialogMessage.updateErrorText(); // update the error text on the dialog
					}
				}
				
				else // if not all the fields are filled
				{
					ExportErrorNotePanel.checkAllErrors(); // check all the errorss
				}
			}
		});
	}
}