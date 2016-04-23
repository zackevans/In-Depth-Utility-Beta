package menu.notes.exportnotepanel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.searchbar.SearchBarShell;

public class FileDirectoryField 
{
	public static JTextField fileDirectoryTextField = new SearchBarShell();
	public JLabel fileDirectoryLabel = new JLabel("Location: ");
	
	private ExportErrorNotePanel exportErrorNotePanel;
	
	public FileDirectoryField ()
	{
		
	}
	
	public void initialize()
	{
		createObjects();
		createComponents();
		addListeners();
	}
	
	public void createObjects()
	{
		exportErrorNotePanel = new ExportErrorNotePanel();
	}
	
	public void createComponents()
	{
		//locationLabel.setBorder(BorderFactory.createLineBorder(Color.red));
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
					exportErrorNotePanel.drictoryError.setVisible(true);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				exportErrorNotePanel.drictoryError.setVisible(false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
}
