package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import launch.app.LaunchApp;
import sql.notes.NotesDataBase;

public class DeleteButton extends JButton
{
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	public void createBtn()
	{
		URL iconURL = DeleteButton.class.getResource("/Button_Images/Notes/NotesPanel/Delete.png");
		ImageIcon img = new ImageIcon(iconURL);
		setIcon(img);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				SearchBar searchBar = new SearchBar();
				
				if (searchBar.doesTextExist() == false) // if there is no text in the search bar
				{
					NotesList notesList = new NotesList();
					
					int listIndex = notesList.list.getSelectedIndex(); // get the list index
	        		int listPosition = listIndex+1; // adapt to base 1 system
	        		
	        		if(listIndex != -1) // if a item in the list is selected
	        		{
	        			NotesDataBase notesDatabase = new NotesDataBase();
	        			int id = notesDatabase.getID(listPosition); // get id of selected note
	        			
	        			notesDatabase.pushWholeListUpOne(); // push list items in databse up
	        			notesDatabase.deleteNote(id); // delete note from the database
	        			notesList.loadData(); // load data in the notes list
	        			//TODO clear display
	        		}
	        		
	        		else // if there is nothing selected in the list
	        		{
	        			LaunchApp launchApp = new LaunchApp();
	        			
	        			// show warning message
	        			JOptionPane.showMessageDialog(launchApp.getFrame(),
								  "No Item Was Seclected",
								  "Delete Warning",
								  JOptionPane.WARNING_MESSAGE);
	        		}
				}
				
				else // if there is text in the searchbar
				{	
					NotesList notesList = new NotesList();
					int listIndex = NotesList.list.getSelectedIndex(); // get the list index of the selected note.
					
	        		if(listIndex != -1) // check if a note is selected
	        		{		
	        			// call method to remove the note in the search list and the database.
		        		notesList.removeSearchListItem(listIndex);
	        		}
	        		
	        		else
	        		{
	        			LaunchApp launchApp = new LaunchApp();
	        			// display note error
	        			JOptionPane.showMessageDialog(launchApp.getFrame(),
								  "No Item Was Seclected",
								  "Delete Warning",
								  JOptionPane.WARNING_MESSAGE);
	        		}
				}
			}
		});
	}
}
