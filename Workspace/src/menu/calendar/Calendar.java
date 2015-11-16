package menu.calendar;

import javax.swing.*;
import java.awt.*;

import menu.buffer.BufferPanel;

public class Calendar extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private AddMonthButton addMonthButton;
	private BackMonthButton backMonthButton;
	private ReturnButton returnButton;
	private CurrentDayButton currentDayButton;
	private static JLabel monthLbl;
	private static JLabel yrLbl;
	private Grid grid;
	BufferPanel bufferPanel; // create bufferPanel object
	String [] Months = {"January", "February", "March",
			"April", "May", "June", "July", "August",
			"September", "October", "November", "December"}; 
	
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
		
		// set month
	}
	
	public void createComponents()
	{
		backMonthButton = new BackMonthButton(bufferPanel);
		backMonthButton.setBounds(575, 25, 20, 20);
		
		addMonthButton = new AddMonthButton(bufferPanel);
		addMonthButton.setBounds(660, 25, 20, 20);
		
	    returnButton = new ReturnButton(bufferPanel);
    	returnButton.setBounds(0, 65, 30, 20);
		
		currentDayButton = new CurrentDayButton(bufferPanel);
		currentDayButton.setBounds(600, 25, 55, 20);
		
		monthLbl = new JLabel("September");
		createMonthLbl();
		
		yrLbl = new JLabel("2015");
		createYrLbl();
		
		//grid = new Grid(bufferPanel);
		grid = new Grid(6,7);
		grid.setBounds(0, 180, 500, 520);
	}
	
	public void createMonthLbl()
	{
		monthLbl.setFont(new Font("Helvetica Neue",Font.BOLD,32));
		monthLbl.setBounds(0, 15, 170, 50);
		//monthLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		monthLbl.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void createYrLbl()
	{
		yrLbl.setFont(new Font("Helvectica Neue", Font.PLAIN, 24));
		yrLbl.setBounds(170,23,80,40);
		//yrLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		yrLbl.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void initializeComponents()
	{
		addMonthButton.initialize();
		backMonthButton.initialize();
		currentDayButton.initialize();
		returnButton.initialize();
		grid.initialize();
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
		add(yrLbl);
		add(grid);
	}
	
}
