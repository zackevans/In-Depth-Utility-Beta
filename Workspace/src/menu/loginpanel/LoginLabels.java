package menu.loginpanel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoginLabels 
{
	public JLabel panelLabel  = new JLabel("Login",SwingConstants.CENTER);
	
	public LoginLabels()
	{
		initialize();
	}
	
	public void initialize()
	{
		createPanelLabel();
	}
	
	public void createPanelLabel()
	{
		panelLabel.setFont(new Font("Helvetica Neue",Font.PLAIN,40)); // set font of button
	}
}
