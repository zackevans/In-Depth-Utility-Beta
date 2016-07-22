package menu.notes.exportnote;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.textfield.TextFieldShell;

public class FileNameField 
{
	public static JTextField fileNameTextField = new TextFieldShell();
	public JLabel fileNameLabel = new JLabel("File Name:");
	
	public void initialize()
	{
		addListeners();
	}
	
	public void addListeners()
	{
		fileNameTextField.getDocument().addDocumentListener(new DocumentListener() 
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				ExportErrorNotePanel.checkFileWarning(); // check to see if file exists
				
				if (fileNameTextField.getText().length() == 0)
				{
					ExportErrorNotePanel.fileNameError.setVisible(true);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				ExportErrorNotePanel.checkFileWarning();
				ExportErrorNotePanel.fileNameError.setVisible(false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
}
