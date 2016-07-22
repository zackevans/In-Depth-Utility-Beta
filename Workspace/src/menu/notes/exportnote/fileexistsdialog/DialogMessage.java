package menu.notes.exportnote.fileexistsdialog;

import javax.swing.JTextArea;

import menu.notes.exportnote.FileNameField;

public class DialogMessage 
{
	public static JTextArea errorTextArea = new JTextArea();
	
	public DialogMessage ()
	{
		createField();
	}
	
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
