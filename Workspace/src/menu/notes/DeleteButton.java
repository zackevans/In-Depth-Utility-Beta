package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import launch.app.LaunchApp;
import sql.notes.NotesDataBase;

/**
 * Class: DeleteButton
 * @author ZackEvans
 *
 * This class is a button that when clicked, deletes a note
 */

public class DeleteButton extends JButton
{	
	/**
	 * Constructor: DeleteButton()
	 * @author ZackEvans
	 * 
	 * Constructor calls hierarchy of button
	 */
	
	public DeleteButton()
	{
		super(); // call hierarchy
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This Function calls methods to create the button
	 */
	public void initialize()
	{
		createBtn(); // call method the set up butoon
		addListeners(); // call method to add listeners to the button
	}
	
	/**
	 * Function: createBtn()
	 * @author ZackEvans
	 * 
	 * Function sets an icon on the button
	 */
	
	public void createBtn()
	{
		URL iconURL = DeleteButton.class.getResource("/Button_Images/Notes/NotesPanel/Delete.png"); // create URL to store the image
		ImageIcon img = new ImageIcon(iconURL); // create an icon image from the URL
		setIcon(img); // set image on the button 
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a action listener to the button. When clicked the selected note is deleted
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				NotesList notesList = new NotesList();
				SearchBar searchBar = new SearchBar();
				DisplayNotes displayNotes = new DisplayNotes();
				
				int listIndex = notesList.list.getSelectedIndex(); // get the list index
        		int listPosition = listIndex+1; // adapt to base 1 system
				
				if(listIndex != -1) // if a item in the list is selected
				{
					Notes.noSelectedNotePanel.setVisible(true);
					
					if (searchBar.doesTextExist() == false) // if there is no text in the search bar
					{
						NotesDataBase notesDatabase = new NotesDataBase();
	        			int id = notesDatabase.getID(listPosition); // get id of selected note
	        			
	        			notesDatabase.pushWholeListUpOne(); // push list items in databse up
	        			notesDatabase.deleteNote(id); // delete note from the database
	        			
	        			notesList.loadData(); // load data in the notes list
	        			displayNotes.clearDisplay();
					}
					
					else
					{
						// call method to remove the note in the search list and the database.
		        		notesList.removeSearchListItem(listIndex);
		        		displayNotes.clearDisplay();
					}
				}
				
				else
				{
					LaunchApp launchApp = new LaunchApp();
        			
        			// show warning message
        			JOptionPane.showMessageDialog(launchApp.getFrame(),
							  "No Item Was Seclected",
							  "Delete Warning",
							  JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}