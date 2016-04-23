package menu.notes.samenamedialog;

import javax.swing.JTextArea;

import menu.buffer.BufferPanel;
import menu.notes.exportnotepanel.FileNameField;

public class ExportTextLabel
{
	BufferPanel bufferPanel;
	public static JTextArea errorTextArea = new JTextArea();
	
	public ExportTextLabel(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
		createField();
	}
	
	public void createField()
	{
		SameNameDialog sameNameDialog = new SameNameDialog(bufferPanel);
		
		errorTextArea.setBackground(sameNameDialog.sameNameDialogFrame.getBackground());
		errorTextArea.setEditable(false);
		errorTextArea.setLineWrap(true);
		errorTextArea.setWrapStyleWord(true);
	}
	
	public void createErrorText()
	{
		FileNameField fileNameField = new FileNameField();
		String areaText = "The file " + fileNameField.fileNameTextField.getText() + ".txt " + "already exists";
		errorTextArea.setText(areaText);
	}
}
