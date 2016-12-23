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

/**
 * Class: PhoneNumberPanel
 * @author ZackEvans
 *
 * This class is a panel holds components that get the users phone number.
 */

public class PhoneNumberPanel extends JPanel
{
	static JTextField phoneNumberField = new TextFieldShell();
	
	/**
	 * Constructor: PhoneNumberPanel()
	 * 
	 * This constructor calls the panel hierarchy and a method to create the panel.
	 */
	
	public PhoneNumberPanel()
	{
		super();
		initialize();	
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function sets the layout manager of the panel and calls methods to create the components.
	 */
	
	public void initialize()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		createTextfields();
		addComponents();
		addListeners();
	}
	
	/**
	 * Function: createTextfields()
	 * 
	 * This function sets the size of the field.
	 */
	
	public void createTextfields()
	{
		phoneNumberField.setPreferredSize(new Dimension(200, 23));
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function adds the components to the panel
	 */
	
	public void addComponents()
	{	
		JLabel phoneLabel = new JLabel("Phone: ");
		setOpaque(false);
		
		add(phoneLabel);
		add(phoneNumberField);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a document listener to the panel so when info is changed in the field it updates the value in the database
	 */
	
	public void addListeners()
	{
		phoneNumberField.getDocument().addDocumentListener(new DocumentListener() 
		{
			
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				saveValue(phoneNumberField.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				saveValue(phoneNumberField.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	
	/**
	 * Function: saveValue(String phoneNumber)
	 * @param phoneNumber
	 * 
	 * This function updates the value in the database with the value passed into the function
	 */
	
	public void saveValue(String phoneNumber)
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		userInfoDatabase.updatePhoneNumber(phoneNumber);
	}
	
	/**
	 * Function: updateValue()
	 * 
	 * This function updates the field with the value saved in the database.
	 */
	
	public static void updateValue()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		phoneNumberField.setText(userInfoDatabase.getPhoneNumber());
	}
}
