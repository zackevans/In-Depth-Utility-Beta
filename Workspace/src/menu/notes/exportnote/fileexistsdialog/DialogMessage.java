package menu.notes.exportnote.fileexistsdialog;

import javax.swing.JTextArea;

import menu.notes.exportnote.FileNameField;

/**
 * Class: DialogMessage
 * @author ZackEvans
 *
 * This class holds a text area that displays the warning message that that file being exported already exists.
 */

public class DialogMessage 
{
	public static JTextArea errorTextArea = new JTextArea(); // create the a text area to hold the error message
	
	/**
	 * Constructor: DialogMessage ()
	 * 
	 * This constructor calls a method to create the text area.
	 */
	
	public DialogMessage ()
	{
		createField();
	}
	
	/**
	 * Function: createField()
	 * 
	 * This function makes the text area note not editable by the user and makes the words wrap around in the warning box.
	 */
	
	public void createField()
	{
		errorTextArea.setEditable(false);
		errorTextArea.setLineWrap(true);
		errorTextArea.setWrapStyleWord(true);
		errorTextArea.setBackground(FileExistsDialog.sameNameDialogFrame.getBackground());
	}
	
	/**
	 * Function: updateErrorText()
	 * 
	 * This function assembles the string and then sets it in the text area
	 */
	
	public static void updateErrorText()
	{
		String areaText = "The file " + FileNameField.fileNameTextField.getText() + ".txt " + "already exists";
		errorTextArea.setText(areaText);
	}
}
