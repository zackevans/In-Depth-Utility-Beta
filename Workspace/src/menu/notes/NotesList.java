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

public class NotesList extends JScrollPane
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private static ArrayList <String> noteNames = new ArrayList <String>(); // names of data
	final String dbLocation = "jdbc:sqlite:" + System.getProperty("user.home") + "/Library/IDU Data/User.db"; 
	private JList list = new JList();
	NotesListData notesData = new NotesListData();
	BufferPanel bufferPanel;
	
	public NotesList (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void paintComponent(Graphics g)
	{		
		super.paintComponent(g); 
		
		updateListData();
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
	        	if (e.getClickCount() == 2)
	        	{
	        		int listIndex = list.getSelectedIndex();
	        		
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
	
	
	public void loadData()
	{	
		int index = 0;
		Connection c = null;
	    Statement stmt = null;
	    try 
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(dbLocation);
	      c.setAutoCommit(false);

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM USER_NOTES;" );
	      while (rs.next()) 
	      {
	    	  int id = rs.getInt("ID");
	    	  String  name = rs.getString("NAME");
	    	  //DBlocations.put(index, id);
	    	  noteNames.add(name);
	    	  index++;
	      }
	      
	      rs.close();
	      stmt.close();
	      c.close();
	    } 
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
	    //System.out.println("personal Data Loaded");
	}
	
	
}
