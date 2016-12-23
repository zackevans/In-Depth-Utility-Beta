package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import file.files.PasswordAttemptsFile;

/**
 * Class: ClearButton
 * @author ZackEvans
 *
 * This class is a button that when clicked deletes all of the failed attempts.
 */

public class ClearButton extends JButton
{
	/**
	 * Constructor: ClearButton()
	 * 
	 * This constructor calls the button hierarchy.
	 */
	
	public ClearButton()
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
	 * This function sets the button text and the border look.
	 */
	
	public void createButton()
	{
		setText("Clear");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function add a action listener to the button that when clicked it deletes all of the saved failed password attempts and updates the list.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PasswordAttemptsFile.clearAttempts();
				AttemptsList.updateList();
				PasswordAttemptsDialog.emptyListIconPanel.setVisible(true);
			}
		});
	}
}
