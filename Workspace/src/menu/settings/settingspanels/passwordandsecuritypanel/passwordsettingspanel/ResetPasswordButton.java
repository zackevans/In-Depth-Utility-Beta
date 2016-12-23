package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class: ResetPasswordButton
 * @author ZackEvans
 *
 * This class is a button that when clicked it shows components to reset the users password.
 */

public class ResetPasswordButton extends JButton
{
	/**
	 * Constructor: ResetPasswordButton ()
	 * 
	 * This constructor calls the button hierarchy and a function to create the button.
	 */
	
	public ResetPasswordButton ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods that create the button.
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets the buttons text and removes the blue selection border.
	 */
	
	public void createButton()
	{
		setText("Reset Password");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button. When clicked it shows components to reset the users password.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PasswordSettingsPanel.showResetPasswordFields();
			}
		});
	}
}
