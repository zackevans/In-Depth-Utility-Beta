package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class ReceiveSafteyEmailCheckBox extends JCheckBox
{
	public ReceiveSafteyEmailCheckBox ()
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
		setText("Receive Email After");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateReceiveEmailAttemptsValue(ReceiveSafteyEmailPanel.receiveSafteyEmailCheckbox.isSelected());
			}
		});
	}
}
