package menu.notes;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;


/**
 * @author ZackEvans
 * Class: Notes
 * 
 * panel that holds all notes components
 */

public class Notes extends JPanel 
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private static NotesList notesList;
	private ReturnButton returnButton;
	private AddNoteButton addNoteButton;
	private SearchBar searchbar;
	private DeleteButton deleteButton;
	private DisplayNotes displayNotes;
	private ClearButton clearButton;
	private ShareButton shareButton;
	BufferPanel bufferPanel;
	
	/**
	 * @author ZackEvans
	 * Constructor: Notes
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Inherit bufferPanel object
	 * 
	 */
	
	public Notes (BufferPanel bufferPanel)
	{
		super(); // Call super Run panel hierarchy 
		this.bufferPanel = bufferPanel; // Inherit buffer panel 
		setOpaque(false); // make panel clear
	}
	
	/**
	 * @author ZackEvans
	 * Function: initialize
	 * 
	 * call functions that set up the basic panel
	 */
	
	public void initialize()
	{
		createComponents();
		initializeComponents();
		layoutComponents();
	}
	
	/**
	 * @author ZackEvans
	 * Function: create Components
	 * 
	 * create objects
	 * set location and size of components
	 */
	
	public void createComponents()
	{
		// creates objects for GUI elements needed
		notesList = new NotesList();
		returnButton = new ReturnButton(bufferPanel);
		addNoteButton = new AddNoteButton();
		searchbar = new SearchBar();
		deleteButton = new DeleteButton();
		displayNotes = new DisplayNotes();
		clearButton = new ClearButton();
		shareButton = new ShareButton(bufferPanel);
		
		// sets location for GUI components 
		NotesList.scrollPane.setBounds(0,50,250,423);
		returnButton.setBounds(250,18,30,30);
		addNoteButton.setBounds(280,18,30,30);
		SearchBar.textField.setBounds(5, 23, 240, 22);
		searchbar.searchLabel.setBounds(8, 23, 240, 22);
		deleteButton.setBounds(310,18,30,30);
		displayNotes.setBounds(250,50,450,423);
		clearButton.setBounds(224,23,20,20);
		shareButton.setBounds(660,18,30,30);
	}
	
	/**
	 * @author ZackEvans
	 * Function: initializeComponents
	 * 
	 * call initialize methods
	 */
	
	public void initializeComponents()
	{
		notesList.initialize();
		returnButton.initialize();
		addNoteButton.initialize();
		searchbar.initialize();
		deleteButton.initialize();
		displayNotes.initialize();
		clearButton.initialize();
		shareButton.initialize();
	}
	
	/**
	 * @author ZackEvans
	 * Function: layoutComponents
	 * 
	 * set panel layout not nothing
	 * set size of panel
	 * add components to panel
	 */
	
	public void layoutComponents()
	{
		setLayout(null); // get rid of layout manager
		setPreferredSize(new Dimension(Window_Width,Window_Height-20)); // set size of panel
		
		// add items to panel
		add(clearButton); // add clear button first so it stays on top of the search bar. (Im to lazy to make a layeredpane rn)
		add(searchbar.searchLabel);
		add(notesList.scrollPane);
		add(returnButton);
		add(SearchBar.textField);
		add(addNoteButton);
		add(deleteButton);
		add(displayNotes);
		add(shareButton);
	}
}
