package menu.notes.exportnote;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.textfield.TextFieldShell;

/**
 * Class: FileNameField 
 * @author ZackEvans
 *
 * This class contains a text field where the user inputs the name of the text file it will export.
 */

public class FileNameField 
{
	// create the textfield and label
	public static JTextField fileNameTextField = new TextFieldShell(); 
	public JLabel fileNameLabel = new JLabel("File Name:");
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls a methods to create the button
	 */
	
	public void initialize()
	{
		addListeners(); 
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a document listener to the textfield.
	 */
	
	public void addListeners()
	{
		fileNameTextField.getDocument().addDocumentListener(new DocumentListener()  // add document listener to the text field.
		{
			@Override
			public void removeUpdate(DocumentEvent e) // override what happens when a char is removed from the text field
			{
				ExportErrorNotePanel.checkFileWarning(); // check to see if file exists
				
				if (fileNameTextField.getText().length() == 0) // if the text field is empty.
				{
					ExportErrorNotePanel.fileNameError.setVisible(true); // show the warning.
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) // override what happens when a char is added to the text field.
			{
				ExportErrorNotePanel.checkFileWarning(); // check if the file already exists
				ExportErrorNotePanel.fileNameError.setVisible(false);  // hide error
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
}
