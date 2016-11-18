package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.noemaildialog.NoEmailDialog;
import program.util.email.EmailUtil;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;
import sql.userinfo.UserInfoDatabase;

public class ReceiveSafteyEmailCheckBox extends JCheckBox
{
	SettingsBufferPanel settingsBufferPanel;
	
	public ReceiveSafteyEmailCheckBox (SettingsBufferPanel settingsBufferPanel)
	{
		super();
		this.settingsBufferPanel = settingsBufferPanel;
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
				UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
				
				if(isSelected()) // if the check box is being selected
				{
					if(EmailUtil.validateEmailAddress(userInfoDatabase.getEmail()))
					{
						updateCombobox();
					}
					
					else
					{
						NoEmailDialog noEmailDialog = new NoEmailDialog(settingsBufferPanel);
						setSelected(false);
						
						noEmailDialog.launchDialog();
					}
				}
				
				else
				{
					updateCombobox();
				}				
			}
		});
	}
	
	public void updateCombobox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		
		securitySettingsDatabase.updateReceiveEmailAttemptsValue(ReceiveSafteyEmailPanel.receiveSafteyEmailCheckbox.isSelected());
	}
}
