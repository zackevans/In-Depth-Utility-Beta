package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.shownotificationsbuttonpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class ShowNotificationsButtonCheckbox extends JCheckBox
{
	public ShowNotificationsButtonCheckbox ()
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
		setText("Show Notificatins Tab When Locked");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateShowNotificaitonsValue(ShowNotificationsButtonPanel.showNotificationsButtonCheckbox.isSelected());
			}
		});
	}
	
	

}
