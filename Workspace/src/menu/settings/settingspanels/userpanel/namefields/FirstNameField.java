package menu.settings.settingspanels.userpanel.namefields;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.textfield.TextFieldShell;
import sql.userinfo.UserInfoDatabase;

public class FirstNameField 
{
	public static JTextField firstNameField = new TextFieldShell();
	public static JLabel firstNameLabel = new JLabel("First Name");
	
	public FirstNameField()
	{
		initialize();
	}
	
	public void initialize()
	{
		createComponents();
    	addListeners();
	}
	
	public void createComponents()
	{
		firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameLabel.setOpaque(false);
		firstNameLabel.setForeground(new Color(0,0,0,200));
	}
	
	public void addListeners()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		firstNameField.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateFirstName(firstNameField.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateFirstName(firstNameField.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		firstNameField.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if(firstNameField.getText().length() == 0)
				{
					firstNameLabel.setVisible(true);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				firstNameLabel.setVisible(false);
			}
		});
	}
	
	public static void updateField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		if(userInfoDatabase.getFirstName().length() !=0)
		{
			firstNameField.setText(userInfoDatabase.getFirstName());
			firstNameLabel.setVisible(false);
		}
	}
}
