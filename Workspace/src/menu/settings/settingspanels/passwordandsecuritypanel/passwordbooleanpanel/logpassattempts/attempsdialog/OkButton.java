package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class: OkButton
 * @author ZackEvans
 *
 * This class is a button that when clicked hides the dialog
 */

public class OkButton extends JButton
{
	/**
	 * Constructor:  OkButton() 
	 * 
	 * This constructor calls the panel hierarchy
	 */
	
	public OkButton()
	{
		super();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the button
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets the buttons text and the border.
	 */
	
	public void createButton()
	{
		setText("Ok");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function add a action listener to the button. When the button is clicked it hide the dialog.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				PasswordAttemptsDialog.customFrame.setVisible(false);
			}
		});
	}
}
