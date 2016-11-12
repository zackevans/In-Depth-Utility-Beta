package menu.settings.settingspanels.userpanel.extras;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.textfield.TextFieldShell;
import sql.userinfo.UserInfoDatabase;

public class EmailPanel extends JPanel
{
	static JTextField emailField = new TextFieldShell();
	
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
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		emailField.getDocument().addDocumentListener(new DocumentListener() 
		{
			
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateEmail(emailField.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateEmail(emailField.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	
	public static void updateField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		emailField.setText(userInfoDatabase.getEmail());
	}
}
