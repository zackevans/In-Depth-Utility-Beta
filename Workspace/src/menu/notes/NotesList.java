package menu.notes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

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
	public static JList list = new JList ();
	public ArrayList<Integer> notesCorrespondingID = new ArrayList<Integer>(); // index is the position of the note in the displayed list, value is the id of the note in the database
	
	/**
	 * Constructor: NotesList (Notes notes) 
	 * @author ZackEvans
	 * @param notes
	 * 
	 * Call super Run panel hierarchy 
	 */
	
	public NotesList ()
	{
		super();
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
		list = new JList(); // create jlist
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
	 * Function: addListeners()
	 * 
	 * Function adds a mouselistener to the list
	 */
	
	public void addListeners()
	{
		list.addMouseListener(new MouseListener() // add mouse listener 
		{
			@Override
			public void mouseClicked(MouseEvent e) // when clicked
			{
				System.out.println("List clicked");
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
		loadData();
	}
}
