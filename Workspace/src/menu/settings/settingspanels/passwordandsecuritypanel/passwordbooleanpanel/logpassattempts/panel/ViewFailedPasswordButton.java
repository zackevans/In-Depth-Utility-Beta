package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog.AttemptsList;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog.PasswordAttemptsDialog;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

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
	
	public void updateButton()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		if(!securitySettingsDatabase.getLogFailedAttemptsValue())
		{
			setEnabled(false);
		}
		
		else
		{
			setEnabled(true);
		}	
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PasswordAttemptsDialog passwordAttemptsDialog = new PasswordAttemptsDialog();
				
				AttemptsList.updateList();
				passwordAttemptsDialog.launchDialog();
				
				
			}
		});
	}
	

}
