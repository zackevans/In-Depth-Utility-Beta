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

/**
 * Function: EmailPanel
 * @author ZackEvans
 *
 * This class is a panel that holds all of the components for the user to enter their email.
 */

public class EmailPanel extends JPanel
{
	public static JTextField emailField = new TextFieldShell();
	
	/**
	 * Constructor: EmailPanel()
	 * 
	 * This constructor calls the panels hierarchy and a method to create the panel.
	 */
	
	public EmailPanel()
	{
		super();
		initialize();
	}
	
	/**
	 * Function:  initialize()
	 * 
	 * This function calls methods to create the panel and its components.
	 */
	
	public void initialize()
	{
		createComponenets();
		addComponents();
		addListeners();
	}
	
	/**
	 * Function: createComponenets()
	 * 
	 * This function sets the layout manager for the panel and the size of the textfield.
	 */
	
	public void createComponenets()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
		emailField.setPreferredSize(new Dimension(250, 23));
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function adds components to the panel.
	 */
	
	public void addComponents()
	{
		JLabel emailLabel = new JLabel("Email: ");
		
		add(emailLabel);
		add(emailField);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function calls methods to add action listeners to the email field.
	 */
	
	public void addListeners()
	{
		addDocumentListener();
		addFocusListener();
	}
	
	/**
	 * Function: addDocumentListener()
	 * 
	 * This function adds a document listener to the field so when the user types we can make assessments on each character as they are entered.
	 */
	
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
	
	/**
	 * Function: addFocusListener()
	 * 
	 * This function adds an action listener to the panel so it can check if the email that entered is valid.
	 */
	
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
	
	/**
	 * Function: updateField()
	 * 
	 * This function updates the value of the textfield with the value in the database.
	 */
	
	public static void updateField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		emailField.setText(userInfoDatabase.getEmail());
	}
}
