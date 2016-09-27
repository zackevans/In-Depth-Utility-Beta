package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class EraseAppDataCheckbox extends JCheckBox
{
	public EraseAppDataCheckbox()
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
		setText("Erase App Data After");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateEraseAppDataValue(EraseAppDataPanel.lockAfterTimeoutCheckbox.isSelected());
			}
		});
	}
}
