package menu.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.buffer.BufferPanel;

/**
 * Class: MainMenu
 * @author ZackEvans
 * 
 * This class extends JPanel.
 * This class holds the main menu panel
 */

public class MainMenu extends JPanel 
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
	private BufferPanel bufferPanel; // Bufferpanel object
	private static JLabel mainTittleLbl; // Create title label of the panel
	private static JLabel companyNameLbl; // Create bottom info Label 
	private JButton notesBtn; // Create notes button
	private JButton remindersBtn; // Create reminders button
	private JButton calendarBtn; // created button object
	private JButton settingsBtn; // Create setting button
	private JButton aboutBtn; // Create about button
	//Notes notes;
	
	/**
	 * Constructor: MainMenu(BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Inharet bufferPanel object
	 * Set Panel Seethrough
	 * 
	 */
	
	public MainMenu (BufferPanel bufferPanel)
	{
		super(); 
		this.bufferPanel = bufferPanel; // inharet object.
		setOpaque(false); // set panel see through
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Call methods to create menu
	 */
	
	public void initialize ()
	{
		createComponents();
		layoutComponents();
		addListeners();
	}
	
	/**
	 * 
	 * Function: Create components
	 * @author ZackEvans
	 * 
	 * Initilize panels and set Button/Label names
	 * Call methods to set and position buttons
	 */
	
	public void createComponents() 
	{
		// Initialize buttons and set names
		mainTittleLbl = new JLabel("In Depth Utility");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2016©");
		notesBtn = new JButton("Notes");
		remindersBtn = new JButton ("Reminders");
		calendarBtn = new JButton("Calendar");
		settingsBtn = new JButton ("Settings Menu");
		aboutBtn = new JButton ("About IDU");
		//notes = new Notes (bufferPanel);
		
		// call set up methods
		createMainTittleLable();
		createBottomLabel();
		createPersonalButton();
		createReminderButton();
		createCalendarButton();
		createSettingsButton();
		createAboutMenu ();
	}
	
	/**
	 * Function: layoutComponents
	 * @author ZackEvans
	 * 
	 * set panel layout
	 * set panel size
	 * add all componets to panel
	 */
	
	public void layoutComponents() 
	{	
		setLayout(null); // set panel layout to null
		setPreferredSize(new Dimension(Window_Width,Window_Height-20)); // set size of the panel to fit the screen
		
		// Add components to panel
		add(mainTittleLbl);
		add(companyNameLbl);
		add(notesBtn);
        add(remindersBtn);
		add(calendarBtn);
		add(settingsBtn);
		add(aboutBtn);
	}
	
	/**
	 * Function: addListeners
	 * @author ZackEvans
	 * @see when buttons are clicked bufferPanel shows differnt tools
	 * 
	 * add a action listener to each button on panel
	 */
	
	public void addListeners()
	{
		/**
		 * @author ZackEvans
		 * Action Listener Acts On: notes button 
		 * 
		 * check to see if the notes button has been clicked.
		 * if it has not been clicked it loads basic data from db.
		 * if the note panel has been displayed then just show the panel.
		 */
		
		notesBtn.addActionListener(new ActionListener() // add action listener to button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // function: action when button is clicked. Passed pram is not used
			{
				System.out.println("notesBtn");
				
				bufferPanel.showPanel("NOTES");
			}
		});
		
		remindersBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("remindersBtn");
			}
		});
		
		calendarBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("calendarBtn");
				//bufferPanel.showPanel("CALENDAR_MENU");
			}
		});
		
		settingsBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("SettignsBtn"); // print out settings button
				//bufferPanel.showPanel("SETTINGS_MENU"); // call show panel method and show the settings menu panel
			}
		});
		
		aboutBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("AboutBtn");
			}
		});	
	}
	
	/**
	 * Function: createMainTittleLable()
	 * @author ZackEvans
	 * 
	 * Set font and textsize
	 * position the label
	 */
	
	public static void  createMainTittleLable() 
	{
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30)); // set font and set text size = 30
	    mainTittleLbl.setBounds(0, 15, Window_Width,40); // set location and heigh an width of the label
	    mainTittleLbl.setHorizontalAlignment(SwingConstants.CENTER); // center the text on the inside of the label
	}
	
	/**
	 * Function: createBottomLabel
	 * @author ZackEvans
	 * 
	 * Set font and textsize
	 * Position the label
	 */

	public static void  createBottomLabel()
	{
		companyNameLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12)); // set font and set text size = 12
	    int y = (Window_Width)/2+90; // set the vertical position
	    companyNameLbl.setBounds(470, y,225,40); // set postion and width and height of label
	}
	
	/**
	 * Function: createPersonalButton(), createSchoolButton(), createComputerButton(),
	 * 			 createSettingsButton(), createAboutMenu ()
	 * @author ZackEvans
	 * 
	 * Set the position and font of all buttons
	 */
	
	public void createPersonalButton()
	{   
		int x = (Window_Height)/2+leftRow; // Calculate the horizantal point
		notesBtn.setBounds(x,btnLn1,btnWidth,btnHeight); // Set potition and size of button
		notesBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set font of button
		notesBtn.setFocusPainted(false);
	}
	
	public void createReminderButton()
	{
		int x = (Window_Height)/2+rightRow; // Calculate the horizantal point
		remindersBtn.setBounds(x,btnLn1,btnWidth,btnHeight); // Set potition and size of button
		remindersBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set font of button
		remindersBtn.setFocusPainted(false);
	}
	
	public void createCalendarButton()  
	{
		int x = (Window_Height)/2+leftRow; // Calculate the horizantal point
		calendarBtn.setBounds(x,btnLn2,btnWidth,btnHeight); // Set potition and size of button
		calendarBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set font of button
		calendarBtn.setFocusPainted(false);
	}
	
	public void createSettingsButton()
	{
		int x = (Window_Height)/2+rightRow; // Calculate the horizantal point
	    settingsBtn.setBounds(x,btnLn2,btnWidth,btnHeight); // Set potition and size of button
	    settingsBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set font of button
	    settingsBtn.setFocusPainted(false);
	}
	
	public void createAboutMenu ()
	{
		int x = (Window_Height)/2+btnPadding; // Calculate the horizantal point
	    aboutBtn.setBounds(x,btnLn3,btnWidth,btnHeight); // Set potition and size of button
	    aboutBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,16)); // Calculate the horizantal point
	    aboutBtn.setFocusPainted(false);
	}
}
