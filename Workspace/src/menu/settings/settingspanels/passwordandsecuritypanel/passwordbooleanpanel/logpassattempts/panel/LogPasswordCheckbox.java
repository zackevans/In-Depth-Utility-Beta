package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class LogPasswordCheckbox extends JCheckBox
{
	public LogPasswordCheckbox()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createCheckbox();
		addListeners();
	}
	
	public void createCheckbox()
	{
		setText("Log Failed Password Attempts");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateLogFailedAttemptsValue(LogPasswordAttemptsPanel.logPasswordCheckbox.isSelected());
				LogPasswordAttemptsPanel.updatePanel();
			}
		});
	}
}
