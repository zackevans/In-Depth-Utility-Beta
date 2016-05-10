package menu.settings.security;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.buffer.BufferPanel;
import menu.settings.security.enterpassword.EnterPassword;
import menu.settings.security.passconfirm.PasswordConfirm;
import menu.settings.security.removepass.RemovePassword;
import sql.systemsettings.SystemDatabase;

/**
 * @author ZackEvans
 * 
 * Class: Security settings 
 * 
 * panel that holds settings menu panel
 * settings include create,set,remove password
 *
 */

public class SecuritySettings extends JPanel
{
	private SystemDatabase systemdb = new SystemDatabase(); // create a system database object
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final int btnLn1 = 75 +25; // first line of buttons
	public static final int btnLn2 = 175+25; // Second line of buttons
	public static final int btnLn3 = 275; // Third lane of buttons
	public static final int leftRow = -75; // left verticle row
	public static final int rightRow = 125; // right verticle row
	public static final int btnWidth = 150; // button width
	public static final int btnHeight = 50; // button height
	public static final int btnPadding = 25; // space between the buttons
	private static JLabel companyNameLbl; // create bottom right label
	private static JLabel mainTittleLbl; // create main tittle label 
	private JButton createPswBtn; // create a button
	private JButton resetPswBtn; // create a button
	private JButton removePswBtn; // create a button
	private JButton returnBtn; // create a button
	private PasswordConfirm passConfirm; // create password confirm panel object
	private EnterPassword enterPass; // create enter pass panel object
	private RemovePassword removePass; // create remove password panel object
	BufferPanel bufferPanel; // create bufferPanel obejct 
	
	/**
	 * Constructor: SecuritySettings
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Inharet bufferPanel object
	 * Set Panel Seethrough
	 */
	
	public SecuritySettings (BufferPanel bufferPanel)
	{
		super(); // call hierarchy
		setOpaque(false); // set the panel see through
		this.bufferPanel = bufferPanel; // inharet bufferpanel
	}
	
	/**
	 * Function: paintConponent
	 * @param g
	 * 
	 * Paint objects added to the panel
	 * Check to see what buttons should be shown
	 */
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // paint components on panel
		setButtons(); // check to show what buttons are shown
	}
	
	/**
	 * Function: initialize
	 * 
	 * call main menthods to create and set up panel
	 */
	
	public void initialize ()
	{
		createComponents();
		layoutComponents();
		addListeners();
	}
	
	/**
	 * Function: createComponents
	 * 
	 * initialize objects
	 * call methods that create button settings
	 * 
	 */
	
	public void createComponents()
	{
		// initialize objects
		mainTittleLbl = new JLabel("Security Settings");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014©");
		createPswBtn = new JButton ("Create Password");
		resetPswBtn = new JButton ("Reset Password");
		removePswBtn = new JButton ("Remove Password");
		returnBtn = new JButton ("Return");
		passConfirm =  new PasswordConfirm(bufferPanel);
		enterPass = new EnterPassword(bufferPanel);
		removePass = new RemovePassword(bufferPanel);
		
		// create object methods
		createMainTittleLable();
		createBottomLabel();
		createPswButton();
		createResetButton();
		createRemoveButton();
		createReturnButton();
	}
	
	/**
	 * Function: layoutComponents
	 * 
	 * set panel layout to null
	 * set size of the panel
	 * add componets to panel
	 */
	
	public void layoutComponents()
	{
		setLayout(null); // set layout to null to position objects with points
		setPreferredSize(new Dimension(Window_Width,Window_Height-20)); // set the size of the panel
		
		// add components to panel
		add(mainTittleLbl);
		add(companyNameLbl);
		add(createPswBtn);
		add(resetPswBtn);
		add(removePswBtn);
		add(returnBtn);
	}
	
	/**
	 * Function: addListeners
	 * 
	 * add action listener to buttons
	 * set action listeners function
	 * 
	 */
	
	public void addListeners()
	{
		createPswBtn.addActionListener(new ActionListener()  // add listener to button
		{
			@Override
			public void actionPerformed(ActionEvent arg0)  // function: action when button is clicked. Passed pram is not used
			{
				System.out.println("Create Passowrd Btn"); // print button name
				bufferPanel.showPanel("PASSWORD_CONFIRM"); // show password confirm panel
				passConfirm.hideWarning(); // hide passconfirm panel warnings
				passConfirm.clearTxtFields(); // clear passconfirm text fields
				passConfirm.setDefaultFocus(); // set text field as active
			}
		});
		
		resetPswBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Reset Pass Btn"); // print button name
				bufferPanel.showPanel("ENTER_PASSWORD"); // show enter password panel
				enterPass.clearFields(); // clear enter password text fields
				enterPass.showWarning(false); // hide warrnings on enterpass panel
				enterPass.setDefaultFocus();
			}
		});
		
		removePswBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Remove Pass Btn"); // print button name
				bufferPanel.showPanel("REMOVE_PASSWORD"); // show remove password panel
				removePass.clearFields(); // clear text fields
				removePass.showWarning(false); // set panel warring off
				removePass.setDefaultFocus(); // set text field active
			}
		});
		
		returnBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				bufferPanel.showPanel("SETTINGS_MENU"); // show last panel (settings menu)
			}
		});
	}
	
	
	/**
	 * Functions: createMainTittleLable(), createBottomLabel(), createPswButton(), 
	 * 			  createResetButton(), createRemoveButton(), createReturnButton()
	 * 
	 * set button settings
	 * 
	 */
	
	public static void  createMainTittleLable() {
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30)); // set size and location of label
	    int x = (Window_Height) / 2; // calculate x cordanate
	    mainTittleLbl.setBounds(0, 20, Window_Width,40); // set size and location of object
	    mainTittleLbl.setHorizontalAlignment(SwingConstants.CENTER); // set text in the center of the object
	}
	
	public static void  createBottomLabel() {
		companyNameLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12)); // set font and size
	    int y = (Window_Width)/2+90; // find y cordanate 
	    companyNameLbl.setBounds(480, y,225,40); // set size and location of label
	}
	
	public void createPswButton() {   
		int x = (Window_Height)/2+leftRow; // calculate x cordanate
		createPswBtn.setBounds(x,btnLn1,btnWidth,btnHeight); // set size and location of button
		createPswBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set size and location of button
	}
	
	public void createResetButton() {
		int x = (Window_Height)/2+rightRow; // calculate x cordanate
		resetPswBtn.setBounds(x,btnLn1,btnWidth,btnHeight);// set size and location of label
		resetPswBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set size and location of button
	}
	
	public void createRemoveButton() {
		int x = (Window_Height)/2+leftRow; // calculate x cordanate
	    removePswBtn.setBounds(x,btnLn2,btnWidth,btnHeight);// set size and location of label
	    removePswBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set size and location of button
	}
	
	public void createReturnButton() {
		int x = (Window_Height)/2+rightRow; // calculate x cordanate
	    returnBtn.setBounds(x,btnLn2,btnWidth,btnHeight); // set size and location of button
	    returnBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set font text and size
	}
	
	/**
	 * Function: setButtons
	 * 
	 * check for password in database
	 * switch buttons depending on passexist
	 */
	
	public void setButtons()
	{
		boolean passExist = systemdb.getPassExist(); // check if there is a password in the data base
		
		if (passExist == true) // if there is as password
		{
			createPswBtn.setEnabled(false); // hide
			resetPswBtn.setEnabled(true); // show
			removePswBtn.setEnabled(true); // show
		}
		
		else // if password doesent exist|error
		{
			createPswBtn.setEnabled(true); // show
			resetPswBtn.setEnabled(false); // hide
			removePswBtn.setEnabled(false); // hide
		}
	}
	
}
