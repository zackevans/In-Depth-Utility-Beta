package menu.settings.settingspanels.userpanel.extras;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.util.textfield.JTextFieldLimit;
import sql.userinfo.UserInfoDatabase;

public class PhoneNumberPanel extends JPanel
{
	static JTextField firstDigitsField = new JTextField();
	static JTextField middleDigitsField = new JTextField();
	static JTextField lastsDigitsField = new JTextField();
	
	public PhoneNumberPanel()
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
		firstDigitsField.setDocument(new JTextFieldLimit(3));
		firstDigitsField.setPreferredSize(new Dimension(40, 23));
		
		middleDigitsField.setDocument(new JTextFieldLimit(3));
		middleDigitsField.setPreferredSize(new Dimension(40, 23));
		
		lastsDigitsField.setDocument(new JTextFieldLimit(4));
		lastsDigitsField.setPreferredSize(new Dimension(50, 23));
	}
	
	public void addComponents()
	{	
		JLabel phoneLabel = new JLabel("Phone: ");
		JLabel spacerLabel1 = new JLabel("-");
		JLabel spacerLabel = new JLabel("-");
		setOpaque(false);
		
		add(phoneLabel);
		add(firstDigitsField);
		add(spacerLabel1);
		add(middleDigitsField);
		add(spacerLabel);
		add(lastsDigitsField);
	}
	
	public void addListeners()
	{
		addFirstDigitsListener();
		addMiddleDigitsListeners();
		addLastDigitsListeners();
	}
	
	public void addFirstDigitsListener()
	{
		firstDigitsField.getDocument().addDocumentListener(new DocumentListener() 
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				updateNumber();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				updateNumber();
				
				if(firstDigitsField.getText().length() == 3)
				{
					middleDigitsField.requestFocusInWindow();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	
	public void addMiddleDigitsListeners()
	{
		middleDigitsField.getDocument().addDocumentListener(new DocumentListener() 
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				updateNumber();
				if(middleDigitsField.getText().length() == 0)
				{
					firstDigitsField.requestFocusInWindow();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				updateNumber();
				
				if(middleDigitsField.getText().length() == 3)
				{
					lastsDigitsField.requestFocusInWindow();
				}	
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	
	public void addLastDigitsListeners()
	{
		lastsDigitsField.getDocument().addDocumentListener(new DocumentListener() 
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				updateNumber();
				
				if(lastsDigitsField.getText().length() == 0)
				{
					middleDigitsField.requestFocusInWindow();	
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				updateNumber();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	
	public void updateNumber()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		String phoneNumber = firstDigitsField.getText()+ middleDigitsField.getText() + lastsDigitsField.getText();
		
		userInfoDatabase.updatePhoneNumber(phoneNumber);
	}
	
	public static void updateNumberFields()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		String phoneNumber = userInfoDatabase.getPhoneNumber();
		
		parsePhoneNumber(phoneNumber);	
	}

	public static void parsePhoneNumber(String phoneNumber)
	{
		if(phoneNumber.length() >= 7)
		{
			firstDigitsField.setText(phoneNumber.substring(0, 3));
			phoneNumber = removeFirst3Char(phoneNumber);
			
			middleDigitsField.setText(phoneNumber.substring(0,3));
			phoneNumber = removeFirst3Char(phoneNumber);
			
			lastsDigitsField.setText(phoneNumber);
		}
		
		else if(phoneNumber.length() >= 4)
		{
			firstDigitsField.setText(phoneNumber.substring(0, 3));
			phoneNumber = removeFirst3Char(phoneNumber);
			
			middleDigitsField.setText(phoneNumber);
		}
		
		else
		{
			firstDigitsField.setText(phoneNumber);
		}
	}
	
	public static String removeFirst3Char(String str)
	{
		StringBuilder sb = new StringBuilder(str);
		
		for (int i=0; i <= 2; i++)
		{
			sb.deleteCharAt(0);
		}
		
		return sb.toString();
	}
}
