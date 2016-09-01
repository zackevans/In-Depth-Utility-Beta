package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import sql.systemsettings.passwordsettings.PasswordSettingsDatabase;
import statusbar.addons.LockButton;

public class SaveButton extends JButton
{
	public SaveButton ()
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
		setText("Save");
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
				saveButtonAction();
			}
		});
	}
	
	public void saveButtonAction()
	{
		PasswordSettingsDatabase passwordSettingsDatabase = new PasswordSettingsDatabase();
		
		if(passwordSettingsDatabase.doesPasswordExist()) // if the password exists
		{
			if(PasswordFields.currentPasswordField.isVisible()) // check if the change password panel is showing
			{
				if(PasswordFields.currentPasswordField.getText().equals(passwordSettingsDatabase.getPassword())) // if the password matches the one in the databse
				{
					
					if( PasswordFields.newPasswordField.getText().length() > 0 && PasswordFields.newPasswordField.getText().equals(PasswordFields.confirmPasswordField.getText())) // if the two password fields match eachother and they are not empty
					{
						passwordSettingsDatabase.updatePassword(PasswordFields.newPasswordField.getText());
						PasswordSettingsPanel.showMenu();
						LockButton.lockButton.setVisible(true);
					}
					
					else // if the text fields dont equal each other
					{
						PasswordErrorPanel.checkAllResetPasswordPanelErrors();
					}
					
				}
				
				else // if the password doesent match the one in the databse
				{
					PasswordErrorPanel.checkAllResetPasswordPanelErrors();
				}
			}
			
			else // the remove panel is showing
			{
				if(PasswordFields.removePasswordField.getText().equals(passwordSettingsDatabase.getPassword()))
				{
					passwordSettingsDatabase.updatePassword("");
					PasswordSettingsPanel.showMenu();
					LockButton.lockButton.setVisible(false);
				}
				
				else
				{
					PasswordErrorPanel.checkRemovePasswordPanelErrors();
				}
			}
		}
		
		else
		{
			if(PasswordFields.passwordField.getText().length() >0)
			{
				if(PasswordFields.passwordField.getText().equals(PasswordFields.retypeField.getText())) // if the passwords equal eachother
				{
					passwordSettingsDatabase.updatePassword(PasswordFields.passwordField.getText());
					PasswordSettingsPanel.showMenu();
					LockButton.lockButton.setVisible(true);
				}
				
				else
				{
					PasswordErrorPanel.newPasswordDoesentMatchErrorLabel.setVisible(true);
				}
			}
			
			else
			{
				PasswordErrorPanel.newPasswordDoesentMatchErrorLabel.setVisible(true);
			}

		}
	}
}
