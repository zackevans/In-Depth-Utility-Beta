package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RemovePasswordButton extends JButton
{
	public RemovePasswordButton ()
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
		setText("Remove Password");
		setFocusPainted(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PasswordSettingsPanel.showRemovePasswordField();
				PasswordFields.removePasswordField.requestFocusInWindow();
			}
		});
	}
}
