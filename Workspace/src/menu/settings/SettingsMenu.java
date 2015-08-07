package menu.settings;

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
 * @author ZackEvans
 * Class: SettingsMenu
 * 
 * Main setting menu panel
 *
 */

public class SettingsMenu extends JPanel
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
	private static JLabel mainTittleLbl; // Create main tittle text
	private static JLabel companyNameLbl; // Create Brand label
	private BufferPanel bufferPanel; // Created bufferPanel object
	private JButton generalBtn; // created general button
	private JButton securityBtn; // created security button
	private JButton returnBtn; // created return button
	
	//SETTINGS
	//general (Sounds,Mail,
	//security
	//Wallpaper
	//Notifacations
	//RETURN
	
	
	/**
	 * Constructor: SettingsMenu 
	 * @param bufferPanel
	 * 
	 * calls hierarchy
	 * inharets bufferPanel object
	 *  
	 */
	
	public SettingsMenu (BufferPanel bufferPanel)
	{
		super(); // calls hierarchy
		setOpaque(false);
		this.bufferPanel = bufferPanel; // inharets bufferpanel
	}
	
	/**
	 * Function: initialize
	 * 
	 * call methods to create panel
	 * 
	 */
	
	public void initialize ()
	{
		// call methods
		createComponents();
		layoutComponents();
		addListeners();
	}
	
	
	/**
	 * Function: createComponents
	 * 
	 * initilize components
	 * call methods to set up and place components
	 * 
	 */
	
	public void createComponents() 
	{
		// initilize components
		mainTittleLbl = new JLabel("Settings Menu");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014©");
		generalBtn = new JButton("General");
		securityBtn = new JButton("Security");
		returnBtn = new JButton("Return");
		
		// set up components
		createMainTittleLable();
		createBottomLabel();
		createGeneralButton();
		createSecurityButton();
		createReturnButton();
	}
	
	
	/**
	 * Function: layoutComponents
	 * 
	 * set panel layout to null
	 * set the size of the panel
	 * add components to panel
	 * 
	 */
	
	public void layoutComponents() 
	{
		setLayout(null); // set panel layout to null
		setPreferredSize(new Dimension(Window_Width,Window_Height-20)); // set the panel size
		
		// add components to panel
		add(mainTittleLbl);
		add(companyNameLbl);
		add(generalBtn);
		add(securityBtn);
		add(returnBtn);
	}
	
	/**
	 * Function: addListeners
	 * 
	 * add action listener to all buttons
	 * 
	 */
	
	
	public void addListeners() 
	{
		generalBtn.addActionListener(new ActionListener() // add action listeners to button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // when action is proformed  
			{
				System.out.println("GeneralBtn"); // print button name
			}
		});

		securityBtn.addActionListener(new ActionListener() // add action listeners to button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // when action is proformed
			{
				System.out.println("SecurityBtn"); // print button name
				bufferPanel.showPanel("SECURITY_SETTINGS"); // show Security Settings on the bufferpanel
			}
		});

		returnBtn.addActionListener(new ActionListener() // add action listeners to button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // when action is proformed
			{
				System.out.println("ReturnBtn"); // print button name
				bufferPanel.showPanel("MAIN_MENU"); // show Main Menu on the bufferPanel
			}
		});

	}
	
	/**
	 * Functions: createMainTittleLable(), createBottomLabel(), createGeneralButton(), 
	 * 			  createSecurityButton(), createReturnButton()
	 * 
	 * set font, position and location
	 * 
	 */
	
	
	
	public static void  createMainTittleLable() {
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30));
	    int x = (int) (Window_Height) / 2;
	    mainTittleLbl.setBounds(0, 15, Window_Width,40);
	    mainTittleLbl.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public static void  createBottomLabel() {
		companyNameLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12));
	    int y = (int) (Window_Width)/2+90;
	    companyNameLbl.setBounds(480, y,225,40);
	}
	
	public void createGeneralButton() {   
		int x = (Window_Height)/2+leftRow;;
		generalBtn.setBounds(x,btnLn1,btnWidth,btnHeight);
		generalBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	}

	public void createSecurityButton() {
		int x = (Window_Height)/2+rightRow;
		securityBtn.setBounds(x,btnLn1,btnWidth,btnHeight);
		securityBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	}

	public void createReturnButton() {
		int x = (Window_Height)/2+btnPadding;
	    returnBtn.setBounds(x,btnLn3,btnWidth,btnHeight);
	    returnBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,16));
	}

}
