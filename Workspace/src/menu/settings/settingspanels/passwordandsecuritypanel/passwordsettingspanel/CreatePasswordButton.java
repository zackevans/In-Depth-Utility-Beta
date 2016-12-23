package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class: CreatePasswordButton
 * @author ZackEvans
 *
 * This class is a button that when clicked shows password fields to create a password.
 */

public class CreatePasswordButton extends JButton
{
	/**
	 * Function: CreatePasswordButton ()
	 * 
	 * This function calls the button hierarchy and a method to create the button.
	 */
	
	public CreatePasswordButton ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: this function calls fucntions to create the panel
	 * 
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets the button text and removes the slection border.
	 */
	
	public void createButton()
	{
		setText("Create Password");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button. When fired the button shows textfields to create a password.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				PasswordSettingsPanel.showCreatePasswordFields();
				PasswordFields.passwordField.requestFocusInWindow();
			}
		});
	}
}
