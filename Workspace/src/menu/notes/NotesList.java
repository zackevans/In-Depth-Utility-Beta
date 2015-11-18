package menu.notes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

import menu.buffer.BufferPanel;
import sql.notes.NotesDataBase;

public class NotesList extends JScrollPane
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private static ArrayList <String> noteNames = new ArrayList <String>(); // names of data
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; 
	private JList list = new JList();
	BufferPanel bufferPanel;
	private NotesListData notesData = new NotesListData();
	private NotesDataBase notesdb = new NotesDataBase();
	private NotesListData notesListData = new NotesListData();
	private SearchBar searchBar = new SearchBar(bufferPanel);
	private DisplayNotes dispNotes; 
	
	public static int lastIndex = -1;
	
	public NotesList (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void initialize()
	{
		createComponents();
		layoutComponents();
		addListeners();
	}
	
	public void createComponents()
	{
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		list = new JList(); // add data to the list here
		list.setFont(new Font("Helvetica Neue",Font.PLAIN,17));
		dispNotes = new DisplayNotes(bufferPanel);
	}
	
	public void layoutComponents()
	{
		setViewportView(list);	
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
	        		int listIndex = list.getSelectedIndex();
	        		int listPosition = listIndex+1;
	        		
	        		// created var for the ID(in db) of item clicked on
	        		int ID = notesdb.getID(listPosition); 
	        		// updates the item clicked on set to the first list position 
	        		
	        		notesdb.pushItemsAboveClickedDown(listPosition); // push items above clicked down
	        		notesdb.updateListPosition(ID, 1); // set item clicked to first
	        		
	        		lastIndex = 0;// when item is clicked it is moved to the first place
	        		updateListData();
	        		keepSelection();
	        		
	        		dispNotes.displayNote();
	        		
	        		repaint();
	        	}
	        }
	    });
	}
	
	public void updateListData()
	{
		noteNames.clear();
		noteNames = notesData.getNoteListData();
		list.setListData(noteNames.toArray());
	}
	
//	public void updateSearchListData()
//	{
//		noteNames.clear();
//		noteNames = notesData.getSearcNoteListData(searchBar.getText());
//		list.setListData(noteNames.toArray());
//	}
	
	public void setListSelection(int listNumber)
	{
		list.setSelectedIndex(listNumber);
	}
	
	public void keepSelection()
	{
		if (lastIndex != -1)
		{
			list.setSelectedIndex(lastIndex);
		}
	}
	
	public void clearSelections()
	{
		list.clearSelection();
		lastIndex = -1;
	}
	
	public int getLastIndex ()
	{
		return lastIndex;
	}
	
	public int getDBLocation()
	{
		return notesdb.getID(lastIndex+1);
	}
}
