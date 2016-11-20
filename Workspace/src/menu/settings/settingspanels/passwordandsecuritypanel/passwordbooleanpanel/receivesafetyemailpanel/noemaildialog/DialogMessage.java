package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.noemaildialog;

import javax.swing.JTextArea;

import menu.notes.exportnote.FileNameField;
import menu.notes.exportnote.fileexistsdialog.FileExistsDialog;

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
	 * @author ZackEvans
	 * 
	 * This constructor calls a method to create the text area.
	 */
	
	public DialogMessage ()
	{
		createField();
	}
	
	/**
	 * Function: createField()
	 * @author ZackEvans
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
	
	public static void updateErrorText()
	{
		String areaText = "The file " + FileNameField.fileNameTextField.getText() + ".txt " + "already exists";
		errorTextArea.setText(areaText);
	}
}
