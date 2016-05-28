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
		returnButton = new ReturnButton(bufferPanel);
		addNoteButton = new AddNoteButton();
		
		// sets location for GUI components 
		NotesList.scrollPane.setBounds(0,50,250,445);
		returnButton.setBounds(250,20,30,30);
		addNoteButton.setBounds(280,20,30,30);
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
		returnButton.initialize();
		addNoteButton.initialize();
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
		
		add(notesList.scrollPane);
		add(returnButton);
		add(addNoteButton);
	}
}
