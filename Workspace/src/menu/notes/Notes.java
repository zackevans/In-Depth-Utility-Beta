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

@SuppressWarnings("serial")
public class Notes extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final int btnLn1 = 75; // first line of buttons
	public static final int btnLn2 = 175; // Second line of buttons
	public static final int btnLn3 = 275; // Third lane of buttons
	public static final int leftRow = -75; // left verticle row
	public static final int rightRow = 125; // right verticle row
	public static final int btnWidth = 150; // button width
	public static final int btnHeight = 50; // button height
	public static final int btnPadding = 25; // space between the buttons
	private static NotesList notesList;
	private AddNoteButton addNoteButton;
	private ReturnButton returnButton;
	private DeleteButton deleteBtn;
	private DisplayNotes displayNotes;
	private InfoButton infoButton;
	private ShareButton shareButton;
	private SearchBar searchBar;
	private ClearButton clearButton;
	BufferPanel bufferPanel; // create bufferPanel object
	String searchText = "-1";
	
	/**
	 * @author ZackEvans
	 * Constructor: Notes
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Inharet bufferPanel object
	 * 
	 */
	
	public Notes (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false);
	}

	/**
	 * @author ZackEvans
	 * Function: initialize
	 * 
	 * call functions that set up the basic panel
	 * 
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
		notesList = new NotesList(this);
		addNoteButton = new AddNoteButton(this);
		returnButton = new ReturnButton(bufferPanel);
		deleteBtn = new DeleteButton(this);
		displayNotes = new DisplayNotes(this);
		infoButton = new InfoButton(bufferPanel);
		shareButton = new ShareButton(this);
		searchBar = new SearchBar(this);
		clearButton = new ClearButton(bufferPanel,searchBar);
		
		// sets location for GUI components 
		NotesList.scrollPane.setBounds(0,50,250,445);
		addNoteButton.setBounds(280,20,30,30);
		returnButton.setBounds(250,20,30,30);
		deleteBtn.setBounds(310,20,30,30);
		displayNotes.setBounds(250,50,450,445);
		infoButton.setBounds(630,20,30,30);
		shareButton.setBounds(660,20,30,30);
		SearchBar.textField.setBounds(5, 25, 240, 22);
		searchBar.searchLabel.setBounds(8, 25, 240, 22);
		clearButton.setBounds(224,25,20,20);
	}
	
	/**
	 * @author ZackEvans
	 * Function: initializeComponents
	 * 
	 * call initialize methods
	 * 
	 */
	
	public void initializeComponents()
	{
		notesList.initialize();
		addNoteButton.initialize();
		returnButton.initialize();
		deleteBtn.initialize();
		displayNotes.initialize();
		infoButton.initialize();
		shareButton.initialize();
		searchBar.initialize();
		clearButton.initialize();
	}
	
	/**
	 * @author ZackEvans
	 * Function: layoutComponents
	 * 
	 * set panel layout not nothing
	 * set size of panel
	 * add components to panel
	 * 
	 */
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(searchBar.searchLabel); // must be added first to stay on top
		add(clearButton); // must be added first to stay on top
		add(NotesList.scrollPane);
		add(addNoteButton);
		add(returnButton);
		add(deleteBtn);
		add(displayNotes);
		add(infoButton);
		add(shareButton);
		add(SearchBar.textField);
	}
	
	
	/**
	 * @author ZackEvans
	 * Function: loadData
	 * 
	 * reloads notes list to defult data
	 * Called when notes is clicked the first time
	 * 
	 */
	
	public static void loadData()
	{
		notesList.loadData();
	}
}