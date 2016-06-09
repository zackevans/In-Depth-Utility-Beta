package menu.notes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sql.notes.NotesDataBase;

/**
 * Class: DisplayNotes
 * @author ZackEvans
 *
 * This class is a scroll pane that shows a text area where the user edits their note.
 */

public class DisplayNotes extends JScrollPane
{
	public static JTextArea textArea = new JTextArea(); // create text area
	
	/**
	 * Constructor: DisplayNotes()
	 * @author ZackEvans
	 * 
	 * call hierarchy
	 */
	
	public DisplayNotes()
	{
		super(); // call hierarchy
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function calls methods to create the notes display
	 */
	
	public void initialize()
	{
		createComponents();
		documentChange();
	}
	
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This function sets properties on the text area.
	 * This function adds a thin matte border on 2 sides of the text area and makes it not be able to scroll right to left
	 * This function sets the text area in the scroll pane
	 */
	
	public void createComponents()
	{
		// wrap full words around the end of the text area
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		setBorder(BorderFactory.createMatteBorder(1,0, 1, 0, Color.BLACK)); // set border around the text area on 2 sides of it
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // make the scroll pane note scroll right to left
		
		setViewportView(textArea); // set the text area in the scroll pane
	}
	
	/**
	 * Function: displayNote(int ID)
	 * @author ZackEvans
	 * @param ID
	 * 
	 * This function allows the note to be edited.
	 * This function displays a note body in the text area.
	 */
	
	public void displayNote(int ID)
	{
		NotesDataBase notesdb = new NotesDataBase();
		textArea.setEditable(true); // when text is to be show make text area be able to edit the note
		textArea.setText(notesdb.getNotesBody(ID)); // set note body in the text area.
	}
	
	/**
	 * Function: documentChange()
	 * @author ZackEvans
	 * 
	 * This function adds a document listener to the text area.
	 * Function is set up to continuously save the users progress as the work.
	 */
	
	public void documentChange()
	{
		textArea.getDocument().addDocumentListener(new DocumentListener() // add document listener to the text ara
		{
			@Override
			public void removeUpdate(DocumentEvent e) // when a character is removed
			{
				updateChange(); // update the current text in the text area in the database.
			}
			
			@Override
			public void insertUpdate(DocumentEvent e)  // when a character is added
			{
				updateChange(); // update the current text in the text area in the database.
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {} // not used
		});
	}
	
	/**
	 * Function: updateChange()
	 * @author ZackEvans
	 * 
	 * This function updates the body in the database
	 */
	
	public void updateChange()
	{
		NotesList notesList = new NotesList();
		SearchBar searchBar = new SearchBar();
		NotesDataBase notesDatabase = new NotesDataBase();
		int index = NotesList.list.getSelectedIndex(); // get current selected index
		
		if (index != -1) // if there is a item selected
		{
			if(searchBar.doesTextExist() == false) // if there is no text in the search bar
			{
				notesDatabase.updateNotesBody(notesDatabase.getID(index+1), textArea.getText()); // update the body with the current text
			}
			
			else // if there is text in the search bar
			{
				notesDatabase.updateNotesBody(notesList.notesCorrespondingID.get(index), textArea.getText()); // update the body with the current text
			}
		}
	}
	
	/**
	 * Function: clearDisplay()
	 * @author ZackEvans
	 * 
	 * This function restricts the text area if there is not a note selected
	 * This function clears all the text out of the text area.
	 */
	
	public void clearDisplay()
	{
		textArea.setEditable(false); // when there is no text restrict text area
		textArea.setText(""); // clear the text area
	}
}
