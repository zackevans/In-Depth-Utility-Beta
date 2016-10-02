package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class LockCheckbox extends JCheckBox
{
	public LockCheckbox ()
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
		setText("Require Password After");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateRequirePasswordValue(RequirePasswordSettingsPanel.requirePasswordCheckbox.isSelected());	
			}
		});
	}
}
