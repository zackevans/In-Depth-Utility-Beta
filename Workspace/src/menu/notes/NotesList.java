package menu.notes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

import sql.notes.NotesDataBase;

public class NotesList
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private NotesListData notesData = new NotesListData();
	private NotesDataBase notesdb = new NotesDataBase();
	private Notes notes;
	private SearchBar searchBar;
	private DisplayNotes dispNotes;
	public static JScrollPane scrollPane = new JScrollPane();
	public static JList list = new JList();
	public static int lastID = -1;
	
	
	public NotesList (Notes notes)
	{
		super();
		this.notes = notes;
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void initialize()
	{
		createComponents();
		layoutComponents();
		addListeners();
	}
	
	public void createComponents()
	{
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		list = new JList(); // add data to the list here
		list.setFont(new Font("Helvetica Neue",Font.PLAIN,17));
		dispNotes = new DisplayNotes(notes);
		searchBar = new SearchBar(notes);
	}
	
	public void layoutComponents()
	{
		scrollPane.setViewportView(list);	
	}
	
	@SuppressWarnings("unchecked")
	public void loadRawData() // loads the raw data structure with what ever in it
	{
		list.setListData(notesData.getRawNoteListData().toArray());
	}
	
	@SuppressWarnings("unchecked")
	public void loadData() // loads standard data from db 
	{
		list.setListData(notesData.getNoteListData().toArray());
	}
	
	@SuppressWarnings("unchecked")
	public void loadSearchData(String searchText) //Sets list data = to the search data
	{
		list.setListData(notesData.getSearcNoteListData(searchText).toArray());
	}
	
	public void addListeners()
	{
		list.addMouseListener(new MouseListener() 
		{
	        @Override
	        public void mouseReleased(MouseEvent e) {}
	        @Override
	        public void mousePressed(MouseEvent e) {}
	        @Override
	        public void mouseExited(MouseEvent e) {}
	        @Override
	        public void mouseEntered(MouseEvent e) {}
	        
	        @Override
	        public void mouseClicked(MouseEvent e) 
	        {  
	        	if (e.getClickCount() == 1)
	        	{	
	        		if (list.getSelectedIndex() != -1) // checks if a list item was clicked
	        		{
	        			if (searchBar.doesTextExist() == true) // if text exists 
		        		{
		        			int listIndex = list.getSelectedIndex();
		        			int listPosition = notesData.getOldEquivalentPosition(listIndex);
		        			
		        			lastID = notesdb.getID(listPosition);
		        			
		        			notesData.moveListItemUp(listIndex);
		        			loadRawData();
		        			setListSeclection(0);
		        			dispNotes.displayNote(lastID);
		        		}
		        		
		        		else // if text doesent exist
		        		{
		        			int listIndex = list.getSelectedIndex();
			        		int listPosition = listIndex+1; 
		        			
			        		lastID = notesdb.getID(listPosition);
			        		
			        		notesdb.pushItemsAboveClickedDown(listPosition); // push items above clicked down
			        		notesdb.updateListPosition(lastID, 1);
			        		
			        		loadData();
			        		setListSeclection(0); // set it to the first item
			        		dispNotes.displayNote(lastID);
		        		}
	        		}
	        	}
	        }
	    });
	}
	
	public void setListSeclection(int listNumber)
	{
		list.setSelectedIndex(listNumber);
	}
	
	public int getLastID()
	{
		return lastID; // returns ID of last note clicked
	}
	
	public void setLastID(int lastID)
	{
		this.lastID = lastID;
	}
}