package menu.notes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import sql.notes.NotesDataBase;

/**
 * Class: NotesList 
 * @author ZackEvans
 *
 * This class holds the notes list that is shown on the notes panel
 */

public class NotesList 
{
	public static JScrollPane scrollPane = new JScrollPane();
	public static JList list = new JList();
	public static ArrayList<Integer> notesCorrespondingID = new ArrayList<Integer>(); // INDEX is the position of the note in the displayed list, VALUE is the id of the note in the database
	
	/**
	 * Constructor: NotesList () 
	 * @author ZackEvans
	 * @param notes
	 * 
	 * Call super Run panel hierarchy 
	 */
	
	public NotesList ()
	{
		super();
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function calls function to set up the list
	 */
	
	public void initialize()
	{
		createComponents();
		loadDefultData();
		layoutComponents();
		addListeners();
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * Function creates the list customize the scroll pane
	 */
	
	public void createComponents()
	{
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // never scroll left to right
		list.setFont(new Font("Helvetica Neue",Font.PLAIN,17)); // set the text size and font
	}
	
	/**
	 * Function: layoutComponents()
	 * @author ZackEvans
	 * 
	 * Function puts list in scroll pane to make it be able to expand and scroll
	 */
	
	public void layoutComponents()
	{
		scrollPane.setViewportView(list); // add the list to the scroll pane
	}
	
	/**
	 * Function: loadData()
	 * @author ZackEvans
	 * 
	 * Function loads data from lowest to highest LIST_POSITION
	 */
	
	public void loadData() // loads standard data from db 
	{
		NotesDataBase notesDataBase = new NotesDataBase();
		
		list.setListData(notesDataBase.getListNamesData().toArray());
		notesCorrespondingID = notesDataBase.getSortedListID(""); // update corresponding IDs. set param to "" because no notes are being searched for
	}
	
	/**
	 * Function: loadSearchData(String searchText)
	 * @author ZackEvans
	 * @param searchText
	 * 
	 * This function loads sorted list names from the database into the Jlist that displays all the notes
	 */
	
	public void loadSearchData(String searchText)
	{
		NotesDataBase notesDataBase = new NotesDataBase(); // create obejct to access the notes database
		
		list.setListData(notesDataBase.getSortedListNamesData(searchText).toArray()); // set the data in the list
		notesCorrespondingID = notesDataBase.getSortedListID(searchText); // update corresponding IDs
	}
	
	/**
	 * Function: removeSearchListItem(int index)
	 * @author ZackEvans
	 * @param index
	 * 
	 * Function removes item from sorted notes list. 
	 * Then removes it from the database. 
	 * Items below the selected note are moved up to fill gap of deleted note.
	 */
	
	public void removeSearchListItem(int index)
	{
		NotesDataBase notesdb = new NotesDataBase();
		ArrayList<String> noteNamesInList = new ArrayList<String>();
		ListModel<?> model = list.getModel();
		int id = notesCorrespondingID.get(index); // get the id of the note selected
		
		// get names out of the note list
		for (int i = 0; i < model.getSize(); i++) // loop runs through everything in the note name list and sets the strings into the array
		{
			noteNamesInList.add(model.getElementAt(i).toString()); // adds name element to the array
		}
		
		noteNamesInList.remove(index); // remove the name from the notes list
		
		list.setListData(noteNamesInList.toArray()); // set the updated data to the list
		 
		notesdb.pushItemsBelowClickedUp(id); // push notes in databaes up that were under the selected note
		notesdb.deleteNote(id); // delete note in the database
		
		notesCorrespondingID.remove(index); // remove selected id from the arraylist
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * Function adds a mouse listener to the note list.
	 */
	
	public void addListeners()
	{
		list.addMouseListener(new MouseListener() // add mouse listener 
		{
			@Override
			public void mouseClicked(MouseEvent e) // when clicked
			{
				SearchBar searchBar = new SearchBar();
				
				if (list.getSelectedIndex() != -1) // checks if a list items was selected when the list was clicked
				{
					Notes.noSelectedNotePanel.setVisible(false);
					
					if (searchBar.doesTextExist() == false) // if no text is here
					{
						NotesDataBase notesDatabase = new NotesDataBase();
						DisplayNotes dispNotes = new DisplayNotes();
						int listIndex = list.getSelectedIndex(); // get the current selcected index
		        		int listPosition = listIndex+1; 
		        		int id = notesDatabase.getID(listPosition); // get the selected notes id
		        		
		        		notesDatabase.pushItemsAboveClickedDown(listPosition); // push items above clicked down
		        		notesDatabase.updateListPosition(id, 1); // update the note clicked to the top of the list
		        		
		        		loadData(); // load data into the list
		        		list.setSelectedIndex(0); // set it to the first item
		        		
		        		dispNotes.displayNote(id); // display the selected note
		        		DisplayNotes.textArea.requestFocusInWindow(); // go to where user ended typing
					}
					
					else // if there is text in the search bar textfield 
					{
						DisplayNotes dispNotes = new DisplayNotes();
						int id = notesCorrespondingID.get(list.getSelectedIndex()); // get id based on the list index
						
						dispNotes.displayNote(id); // display the note that was selected
						DisplayNotes.textArea.requestFocusInWindow(); // set focus display notes
					}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
		});
	}
	
	/**
	 * Function: loadDefultData()
	 * @author ZackEvans
	 * 
	 * This function loads the initial data from the database
	 */
	
	public void loadDefultData()
	{
		loadData(); // load sorted data from first to last position
	}
}
