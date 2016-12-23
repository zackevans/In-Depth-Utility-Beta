package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class: RemovePasswordButton
 * @author ZackEvans
 * 
 * This class is a button that when clicked shows components to remove the users password.
 */

public class RemovePasswordButton extends JButton
{
	/**
	 * Constructor: RemovePasswordButton ()
	 * 
	 * This constructor calls the button hierarchy and a funciton to create the button.
	 */
	
	public RemovePasswordButton ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This fucntion calls methods to create the panel.
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets the text on the button and removes the blue selection border.
	 */
	
	public void createButton()
	{
		setText("Remove Password");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button that when clicked it shows components to remove the users password.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PasswordSettingsPanel.showRemovePasswordField();
			}
		});
	}
}