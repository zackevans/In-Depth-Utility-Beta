package menu.settings.security.enterpassword;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.buffer.BufferPanel;

/**
 * @author ZackEvans
 * Class: EnterPassword (ResetPassword)
 * 
 * login window before the passconfirm panel
 *
 */

public class EnterPassword extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final int btnWidth = 150; // button height for return and enter button
	public static final int btnHeight = 50; // button width for return and enter button
	public static final int btnLn2 = 200; // button lane for return and enter button
	public static final int leftRow = -75; //  Vertical button position for left side button
	public static final int rightRow = 125; // Vertical button position for right side buttons
	private static JLabel mainTittleLbl; // create main tittle label
	private static JLabel companyNameLbl; // create bottom label
	private static JLabel enterPasswordLbl; // create enter pass label
	private static JLabel incorrectLbl; // create incorrent lbl shown when password is enterd 
	private static EnterPassField enterPassField; // jpassword field created
	private static EnterButton enterBtn; // created enter button
	private static ReturnButton returnBtn; // created return button
	BufferPanel bufferPanel; // create bufferPanel to inharet object
	
	/**
	 * Constructor: EnterPassword 
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Set Panel Seethrough
	 * Inharet bufferPanel object
	 * 
	 */
	
	
	public EnterPassword(BufferPanel bufferPanel)
	{
		super(); // call hierarchy
		setOpaque(false); // set panel seethrough 
		this.bufferPanel = bufferPanel; // inharet bufferPanel 
	}
	
	
	/**
	 * function: initialize
	 * 
	 * Call all main methods
	 * 
	 */
	
	public void initialize ()
	{
		createComponents();
		layoutComponents();
		initializeComponents();
	}
	
	/**
	 * Function: Create components
	 * 
	 * Initilize panels and set Button/Label names
	 * Call methods to set and position buttons
	 * 
	 */
	
	public void createComponents()
	{
		// initilize components
		mainTittleLbl = new JLabel("Reset Password");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014©");
		enterPasswordLbl = new JLabel("Enter Password: ");
		enterPassField = new EnterPassField(bufferPanel);
		enterBtn = new EnterButton(bufferPanel);
		returnBtn = new ReturnButton(bufferPanel);
		incorrectLbl = new JLabel("Incorrect Password"); // center label
		
		// call methods to set up labels
		createMainTittleLable();
		createBottomLabel();
		createEnterPassLabel();
		createIncorrectLabel();
		
		// set location of password field and buttons
		enterPassField.setBounds(235,112,240,25);
		enterBtn.setBounds((Window_Height)/2+rightRow, btnLn2, btnWidth, btnHeight);
		returnBtn.setBounds((Window_Height)/2+leftRow, btnLn2, btnWidth, btnHeight);
	}
	
	/**
	 * Function: layoutComponents
	 * 
	 * set layout
	 * set panel size
	 * add objects to panel
	 */
	
	public void layoutComponents()
	{
		setLayout(null); // no layout on panel
		setPreferredSize(new Dimension(Window_Width,Window_Height-20)); // set size of the panel
		
		// add componemts to panel
		add(mainTittleLbl);
		add(companyNameLbl);
		add(enterPasswordLbl);
		add(enterPassField);
		add(enterBtn);
		add(returnBtn);
		add(incorrectLbl);
	}
	
	/**
	 * function: initilizeComponents
	 * 
	 * call initilize methods for buttons
	 */
	
	public void initializeComponents()
	{
		enterBtn.initialize();
		returnBtn.initialize();
	}
	
	/**
	 * Function: getPassword
	 * @return text in password field
	 */
	
	public String getPassword()
	{
		return enterPassField.getText(); // return text from password field (Too lazy to convet bytes .getPassword())
	}
	
	/**
	 * Function: clearFields
	 * 
	 * set text in password field to nothing
	 */
	
	public void clearFields()
	{
		enterPassField.setText(""); // set text in field to nothing
	}
	
	/**
	 * Function: showWarrning
	 * @param tf
	 * 
	 * set label visiable depending on tf
	 * 
	 */
	
	public void showWarning (boolean tf) 
	{
		incorrectLbl.setVisible(tf); // set Visiable depending on passed var
	}
	
	/**
	 * Function: createMainTittleLable(), createBottomLabel(), createEnterPassLabel()
	 * 			 createIncorrectLabel()
	 * 
	 * set up caomponets
	 * 
	 */
	
	public static void  createMainTittleLable() 
	{
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30)); // set font
	    mainTittleLbl.setBounds(0, 15, Window_Width,40); // set size and location
	    mainTittleLbl.setHorizontalAlignment(SwingConstants.CENTER); // set text in the center of label
	}
	
	public static void  createBottomLabel() 
	{
		companyNameLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12)); // set font
	    int y = (int) (Window_Width)/2+90; // find y
	    companyNameLbl.setBounds(480, y,225,40); // set size and location
	}
	
	public static void createEnterPassLabel()
	{
		enterPasswordLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12)); // set font
	    enterPasswordLbl.setBounds(239,82,225,40); // set size and location
	}
	
	public static void createIncorrectLabel()
	{
		int x = (int) (Window_Height) / 2+30; // find x
		incorrectLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,14)); // set font
		incorrectLbl.setBounds(x,140,225,40); // set size and location
		incorrectLbl.setForeground(Color.red); // set text color red
		incorrectLbl.setVisible(false); // hide label by default
	}
}
