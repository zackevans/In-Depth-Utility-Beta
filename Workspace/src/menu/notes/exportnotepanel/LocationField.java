package menu.notes.exportnotepanel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import program.searchbar.SearchBarShell;

public class LocationField 
{
	public static JTextField locationField = new SearchBarShell();
	public JLabel locationLabel = new JLabel("Location: ");
	
	public LocationField ()
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
		//locationLabel.setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	public void addListeners()
	{
		
	}
}
