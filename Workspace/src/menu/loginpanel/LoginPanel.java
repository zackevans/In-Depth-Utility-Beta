package menu.loginpanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;

/**
 * Class: 
 * @author ZackEvans
 *
 * This class is a panel that propts the user to login to the application.
 */

public class LoginPanel extends JPanel
{
	BufferPanel bufferPanel;
	private LoginLabels loginLabels;
	private LoginField loginField;
	public static LoginButton loginButton;
	private LoginErrors loginErrors;
	
	/**
	 * Constructor: LoginPanel(BufferPanel bufferPanel)
	 * @param bufferPanel
	 * 
	 * This constructor inherits the buffer panel object and calls panel hierarchy.
	 */
	
	public LoginPanel(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls method to create the panel
	 */
	
	public void initialize()
	{
		createPanel();
		createComponents();
		layoutComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function sets up the login panel
	 */
	
	public void createPanel()
	{
		setOpaque(false);
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function creates the objects and sets the location and size of them.
	 */
	
	public void createComponents()
	{
		loginLabels = new LoginLabels();
		loginField = new LoginField(bufferPanel);
		loginButton = new LoginButton(bufferPanel);
		loginErrors = new LoginErrors();
		
		loginLabels.panelLabel.setBounds(0, 25, 700,50);
		LoginField.loginField.setBounds(230,200, 240,24);
		loginField.loginLabel.setBounds(155,200, 75,25);
		loginButton.setBounds(475, 196,30,30);
		LoginErrors.loginError.setBounds(510, 200, 22,22);
	}
	
	/**
	 * Function: layoutComponents()
	 * 
	 * This function sets the panel layout and size. Then it adds all of the panels components.
	 */
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(700, 500));
		
		add(loginLabels.panelLabel);
		add(LoginField.loginField);
		add(loginButton);
		add(loginField.loginLabel);
		add(LoginErrors.loginError);
	}
}
