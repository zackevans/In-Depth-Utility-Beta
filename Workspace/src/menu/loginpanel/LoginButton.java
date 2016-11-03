package menu.loginpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import file.files.PasswordAttemptsFile;
import menu.buffer.BufferPanel;
import sql.systemsettings.passwordsettings.PasswordSettingsDatabase;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;
import statusbar.addons.LockButton;

public class LoginButton extends JButton
{
	BufferPanel bufferPanel;
	
	public LoginButton(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		initialize();
	}
	
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		URL url = LoginButton.class.getResource("/Button_Images/Login/loginButton.png"); // create a URL for the image
		ImageIcon icon = new ImageIcon(url); // create image icon
		setIcon(icon); // set image icon as button image
		
		setOpaque(false);
		setBorder(null);
		setFocusPainted(false);	
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				loginAction();
			}
		});
	}
	
	public void loginAction()
	{
		PasswordSettingsDatabase passwordSettingsDatabase = new PasswordSettingsDatabase();
		
		if(LoginField.loginField.getText().equals(passwordSettingsDatabase.getPassword()))
		{
			bufferPanel.showRawPanel(BufferPanel.currentPanel);
			LockButton.lockButton.setVisible(true);
			bufferPanel.checkBackButton(BufferPanel.currentPanel); // check back button bc method .showRawPanel doesent include it :(
		}
		
		else
		{
			SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
			
			if(securitySettingsDatabase.getLogFailedAttemptsValue())
			{
				if(LoginField.loginField.getText().length() > 0)
				{
					PasswordAttemptsFile.addAttempt(LoginField.loginField.getText());
				}
			}
			
			LoginErrors.loginError.setVisible(true);
			LoginField.loginField.selectAll();
		}
	}
}
