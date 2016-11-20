package menu.settings.settingspanels.userpanel.extras;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import menu.settings.settingspanels.passwordandsecuritypanel.PasswordAndSecuritySettingsPanel;
import program.textfield.TextFieldShell;
import program.util.email.EmailUtil;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;
import sql.userinfo.UserInfoDatabase;

public class EmailPanel extends JPanel
{
	public static JTextField emailField = new TextFieldShell();
	
	public EmailPanel()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		createTextfields();
		addComponents();
		addListeners();
	}
	
	public void createTextfields()
	{
		setOpaque(false);
		emailField.setPreferredSize(new Dimension(250, 23));
	}
	
	public void addComponents()
	{
		JLabel emailLabel = new JLabel("Email: ");
		
		add(emailLabel);
		add(emailField);
	}
	
	public void addListeners()
	{
		addDocumentListener();
		addFocusListener();
	}
	
	public void addDocumentListener()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		emailField.getDocument().addDocumentListener(new DocumentListener() 
		{
			
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateEmail(emailField.getText());
				
				ExtrasPanelErrors.updateLabel();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateEmail(emailField.getText());
				
				ExtrasPanelErrors.updateLabel();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	
	public void addFocusListener()
	{
		emailField.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) 
			{
				if(!EmailUtil.validateEmailAddress(emailField.getText())) // not valid email
				{
					SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
					
					securitySettingsDatabase.updateReceiveEmailAttemptsValue(false);
					PasswordAndSecuritySettingsPanel.resetPanel();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
	}
	
	public static void updateField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		emailField.setText(userInfoDatabase.getEmail());
	}
}
