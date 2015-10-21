package menu.calendar;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.JPanel;
import menu.buffer.BufferPanel;
import menu.notes.AddNoteButton;


public class Calendar extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private AddMonthButton addMonthButton;
	private BackMonthButton backMonthButton;
	private ReturnButton returnButton;
	private CurrentDayButton currentDayButton;
	private static JLabel monthLbl;
	
	BufferPanel bufferPanel; // create bufferPanel object
	
	/**
	 * Constructor: Notes
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Inherent bufferPanel object
	 * 
	 */
	
	public Calendar (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false);
	}
	
	/**
	 * Function: paintComponent*
	 * 
	 */
	
//	public void paintComponent(Graphics g)
//	{
//		super.paintComponent(g); 
//		
//	}
	
	/**
	 * Function: initialize
	 * 
	 * call function to create panel
	 * 
	 */
	
	public void initialize()
	{
		createComponents();
		initializeComponents();
		layoutComponents();
	}
	
	public void createComponents()
	{
		addMonthButton = new AddMonthButton(bufferPanel);
		addMonthButton.setBounds(140, 50, 30, 30);
		
		backMonthButton = new BackMonthButton(bufferPanel);
		backMonthButton.setBounds(40, 50, 30, 30);
		
		returnButton = new ReturnButton(bufferPanel);
		returnButton.setBounds(5,20, 30, 30);
		
		currentDayButton = new CurrentDayButton(bufferPanel);
		currentDayButton.setBounds(600, 25, 55, 20);
		
		monthLbl = new JLabel("Month");
		createMonthLbl();
		
		
	}
	
	public void createMonthLbl()
	{
		monthLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,18));
		monthLbl.setBounds(78, 50, 200, 30);
	}

	public void initializeComponents()
	{
		addMonthButton.initialize();
		backMonthButton.initialize();
		currentDayButton.initialize();
		returnButton.initialize();
		
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(addMonthButton);
		add(backMonthButton);
		add(returnButton);
		add(currentDayButton);
		add(monthLbl);
	}
	
}
