package menu.notes.exportnotepanel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.searchbar.SearchBarShell;

public class FileNameField 
{
	public static JTextField fileNameTextField = new SearchBarShell();
	public JLabel fileNameLabel = new JLabel("File Name:");
	private ExportErrorNotePanel exportErrorNotePanel;
	
	public FileNameField ()
	{
		
	}
	
	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	public void createComponents()
	{
		exportErrorNotePanel = new ExportErrorNotePanel();
	}
	
	public void addListeners()
	{
		fileNameTextField.getDocument().addDocumentListener(new DocumentListener() 
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				exportErrorNotePanel.checkFileWarning(); // check to see if file exists
				
				if (fileNameTextField.getText().length() == 0)
				{
					exportErrorNotePanel.fileNameError.setVisible(true);
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				exportErrorNotePanel.checkFileWarning();
				exportErrorNotePanel.fileNameError.setVisible(false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
}
