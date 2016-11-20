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

public class CompanyField 
{
	public static JTextField companyField = new TextFieldShell();
	public static JLabel companyLabel = new JLabel("Company");
	
	public CompanyField()
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
		companyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyLabel.setOpaque(false);
		companyLabel.setForeground(new Color(0,0,0,200));
	}
	
	public void addListeners()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		companyField.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateCompanyName(companyField.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateCompanyName(companyField.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		companyField.addFocusListener(new FocusListener() 
		{	
			@Override
			public void focusLost(FocusEvent e)
			{
				if(companyField.getText().length() == 0)
				{
					companyLabel.setVisible(true);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				companyLabel.setVisible(false);
				
			}
		});
	}
	
	public static void updateField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		if(userInfoDatabase.getCompanyName().length() !=0)
		{
			companyField.setText(userInfoDatabase.getCompanyName());
			companyLabel.setVisible(false);
		}
	}
}
