package menu.notes.exportnote;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.textfield.TextFieldShell;

public class FileDirectoryField 
{
	public static JTextField fileDirectoryTextField = new TextFieldShell();
	public JLabel fileDirectoryLabel = new JLabel("Location: ");

	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	public void createComponents()
	{
		fileDirectoryTextField.setEditable(false);
	}
	
	public void addListeners()
	{
		fileDirectoryTextField.getDocument().addDocumentListener(new DocumentListener() 
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				if(fileDirectoryTextField.getText().length() == 0)
				{
					ExportErrorNotePanel.drictoryError.setVisible(true);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				ExportErrorNotePanel.drictoryError.setVisible(false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	
}
