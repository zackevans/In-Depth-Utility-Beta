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

public class LastNameField 
{
	public static JTextField lastNameField = new TextFieldShell();
	public static JLabel lastNameLabel = new JLabel("Last Name");
	
	public LastNameField()
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
		lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameLabel.setOpaque(false);
		lastNameLabel.setForeground(new Color(0,0,0,200));
	}
	
	public void addListeners()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		lastNameField.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateLastName(lastNameField.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateLastName(lastNameField.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		lastNameField.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if(lastNameField.getText().length() == 0)
				{
					lastNameLabel.setVisible(true);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				lastNameLabel.setVisible(false);
				
			}
		});
	}
	
	public static void updateField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		if(userInfoDatabase.getLastName().length() !=0)
		{
			lastNameField.setText(userInfoDatabase.getLastName());
			lastNameLabel.setVisible(false);
		}
	}
}
