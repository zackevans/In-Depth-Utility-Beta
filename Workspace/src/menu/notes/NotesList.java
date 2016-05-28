package menu.notes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

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
	private Notes notes;
	
	/**
	 * Constructor: NotesList (Notes notes) 
	 * @author ZackEvans
	 * @param notes
	 * 
	 * Call super Run panel hierarchy 
	 * Inherit notes object
	 */
	
	public NotesList (Notes notes)
	{
		super();
		this.notes = notes;
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
		//list.setListData(listData); // load straight from db
	}
	
}
