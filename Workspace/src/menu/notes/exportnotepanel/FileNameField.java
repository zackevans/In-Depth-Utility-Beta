package menu.notes.exportnotepanel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import program.searchbar.SearchBarShell;

public class FileNameField 
{
	public static JTextField fileNameField = new SearchBarShell();
	public JLabel fileNameLabel = new JLabel("File Name:");
	
	public FileNameField()
	{
		super();
	}
	
	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	public void createComponents()
	{
		//fileNameLabel.setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	public void addListeners()
	{
		
	}
}
