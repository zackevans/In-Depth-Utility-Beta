package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts;

import javax.swing.JButton;

public class ViewFailedPasswordButton extends JButton
{
	public ViewFailedPasswordButton ()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		setText("View");
		setFocusPainted(false);
	}
	
	public void addListeners()
	{
		
	}
	

}
