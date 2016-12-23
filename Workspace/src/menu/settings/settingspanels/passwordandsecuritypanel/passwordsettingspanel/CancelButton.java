package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class: CancelButton
 * @author ZackEvans
 *
 * This class is a button that when clicked shows the password menu.
 */

public class CancelButton extends JButton
{
	/**
	 * Constructor: CancelButton()
	 * 
	 * This constructor calls the button hierarchy and a method to create the button
	 */
	
	public CancelButton()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the panel.
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets the button text and removes the focus border.
	 */
	
	public void createButton()
	{
		setText("Cancel");
		setFocusPainted(false);
		setVisible(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button. When the listener fires it shows the password menu.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PasswordSettingsPanel.showMenu();
			}
		});
	}
}
