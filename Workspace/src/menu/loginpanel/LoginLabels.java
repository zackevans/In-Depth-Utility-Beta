package menu.loginpanel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Class: LoginLabels 
 * @author ZackEvans
 *
 * This class holds labels that are added to the login panel
 */

public class LoginLabels 
{
	public JLabel panelLabel  = new JLabel("In Depth Utility",SwingConstants.CENTER);
	
	/**
	 * Constructor: LoginLabels()
	 * 
	 * This constructor calls a method to create the labels
	 */
	
	public LoginLabels()
	{
		createPanelLabel();
	}
	
	/**
	 * Function: createPanelLabel()
	 * 
	 * This function sets up the label
	 */
	
	public void createPanelLabel()
	{
		panelLabel.setFont(new Font("Helvetica Neue",Font.PLAIN,40)); // set font of button
	}
}
