package menu.notes.exportnote.fileexistsdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;
import menu.notes.exportnote.ExportPreviewNote;
import menu.notes.exportnote.FileDirectoryField;
import menu.notes.exportnote.FileNameField;
import program.util.FileUtil;

/**
 * Class: ReplaceButton
 * @author ZackEvans
 *
 * This class is a button that when pressed replaces the file that already exists whith the new selected note
 */

public class ReplaceButton extends JButton
{
	BufferPanel bufferPanel;
	
	/**
	 * Constructor: ReplaceButton (BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This constructor inherits the bufferPanel obejct and calls the button hierarchy.
	 * Then it calls a method that creates the button
	 */
	
	public ReplaceButton (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to create and setup the button.
	 */
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * @author ZackEvans
	 * 
	 * This function sets the text of the button and doesent let it gain focus ever
	 */
	
	public void createBtn()
	{
		setText("Replace");
		setFocusable(false);
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a action listener that overwrites the same file.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// create location to the file
				String fileLocation = FileDirectoryField.fileDirectoryTextField.getText() + "/" + FileNameField.fileNameTextField.getText() + ".txt"; // put together fileLocation
				
				FileUtil.overWriteFile(fileLocation, ExportPreviewNote.textArea.getText()); // overwrite text in the file
				
				FileExistsDialog.sameNameDialogFrame.setVisible(false); // hide the dialog
				
				Notes.shareButton.requestFocusInWindow(); // request focus to make the default focus not go to the search bar
				bufferPanel.showPanel("NOTES"); // show the notes panel
			}
		});
	}
}
