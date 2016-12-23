package menu.notes.exportnote;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.textfield.TextFieldShell;

/**
 * Class: FileDirectoryField 
 * @author ZackEvans
 * 
 * This class holds a textfield that shows the selected directory path.
 */

public class FileDirectoryField 
{
	public static JTextField fileDirectoryTextField = new TextFieldShell(); // create textfield to hold the file path
	public JLabel fileDirectoryLabel = new JLabel("Location: "); // create a label to sit next to the textifield

	/**
	 * Function: initialize()
	 * 
	 * This function calls methods that setup the text field and label
	 */
	
	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function makes the text field not editable by the user.
	 */
	
	public void createComponents()
	{
		fileDirectoryTextField.setEditable(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a document listener to the textfield
	 */
	
	public void addListeners()
	{
		fileDirectoryTextField.getDocument().addDocumentListener(new DocumentListener() // add listener to the panel
		{
			@Override
			public void removeUpdate(DocumentEvent e)  // override what happens when a char is removed from the text field
			{
				if(fileDirectoryTextField.getText().length() == 0) // if there is no text in the textfield.
				{
					ExportErrorNotePanel.drictoryError.setVisible(true); // show error
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) // override what happens when a char is added to the textfield.
			{
				ExportErrorNotePanel.drictoryError.setVisible(false); // hide the error
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {} // not used
		});
	}	
}
