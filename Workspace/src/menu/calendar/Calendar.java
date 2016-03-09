package menu.calendar;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class Calendar extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private AddMonthButton addMonthButton;
	private BackMonthButton backMonthButton;
	private ReturnButton returnButton;
	private CurrentDayButton currentDayButton;
	private MonthYearLabel ymLabel;
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
		backMonthButton = new BackMonthButton(bufferPanel);
		backMonthButton.setBounds(575, 25, 20, 20);
		
		addMonthButton = new AddMonthButton(bufferPanel);
		addMonthButton.setBounds(660, 25, 20, 20);
		
	    returnButton = new ReturnButton(bufferPanel);
    	returnButton.setBounds(0, 20, 30, 20);
		
		currentDayButton = new CurrentDayButton(bufferPanel);
		currentDayButton.setBounds(600, 25, 55, 20);
		
		ymLabel = new MonthYearLabel(bufferPanel);
		ymLabel.setBounds(3, 40, 300, 40);
		
		grid = new Grid(bufferPanel);
		grid.setBounds(0, 80, 700, 450);
	}
	
	

	public void initializeComponents()
	{
		addMonthButton.initialize();
		backMonthButton.initialize();
		currentDayButton.initialize();
		returnButton.initialize();
		grid.initialize();
		ymLabel.initialize();
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(addMonthButton);
		add(backMonthButton);
		add(returnButton);
		add(currentDayButton);
		add(ymLabel);
		add(grid);
	}

}
