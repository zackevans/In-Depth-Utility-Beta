package menu.settings.security.enterpassword;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import sql.systemsettings.SystemDatabase;


/**
 * @author ZackEvans
 * Class: EnterButton
 * 
 * Enter button for panel
 */

public class EnterButton extends JButton
{
	BufferPanel bufferPanel; // create bufferPanel object
	private EnterPassword ep = new EnterPassword(bufferPanel); // Enter password class object
	private SystemDatabase sd = new SystemDatabase(); // system database object
	
	/**
	 * Constructor: EnterButton
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Inharet bufferPanel object
	 */
	
	public EnterButton(BufferPanel bufferPanel)
	{
		super(); // call hierarchy
		this.bufferPanel = bufferPanel; // inharet bufferPanel object
	}
	
	/**
	 * Function: initialize
	 * 
	 * call functions to create button and listeners
	 */
	
	public void initialize()
	{
		createBtn(); // call create button method
		addListeners(); // call method to add listeners
	}
	
	/**
	 * Function: createBtn
	 * 
	 * set Text of button to Enter
	 */
	
	public void createBtn()
	{
		setText("Enter"); // set button text to Enter
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * add action listener to button
	 * when button is pressed check password in db and check against text in textfield
	 * 
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add listener to button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // when listener is triggerd
			{
				String pass = ep.getPassword(); // get pass word from textfield 
				String dbPass = sd.getPassword(); // get pass from database 
				
				if (pass.equals(dbPass)) // check if passwords match
				{
					bufferPanel.showPanel("PASSWORD_CONFIRM"); // show password confirm panel
				}
				
				else
				{
					ep.showWarning(true); // show warrning
					ep.setDefaultFocus();
				}
			}
		});
	}
}
