package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CancelButton extends JButton
{
	public CancelButton()
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
		setText("Cancel");
		setFocusPainted(false);
		setVisible(false);
	}
	
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
