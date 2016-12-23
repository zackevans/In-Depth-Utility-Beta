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
 * Class: AddressPanel
 * @author ZackEvans
 *
 * This class holds the field the user enters their address in.
 */

public class AddressPanel extends JPanel
{
	static JTextField addressField = new TextFieldShell();
	
	/**
	 * Constructor: AddressPanel()
	 * 
	 * This constructor calls the panel hierarchy and a method to create the components on the panel.
	 */
	
	public AddressPanel()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function sets the layout of the panel and calls methods to create the individual components on the panel
	 */
	
	public void initialize()
	{
		createPanel();
		createTextfields();
		addComponents();
		addListeners();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function sets the layout for the panel and makes the panel clear.
	 */
	
	public void createPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
	}
	
	/**
	 * Function: createTextfields()
	 * 
	 */
	
	public void createTextfields()
	{
		
		addressField.setPreferredSize(new Dimension(250, 23));
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function adds the components to the panel
	 */
	
	public void addComponents()
	{
		JLabel addressLabel = new JLabel("Address: ");
		
		add(addressLabel);
		add(addressField);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a document listener to the address field. Ever time it fires it saves the current number in the database
	 */
	
	public void addListeners()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		addressField.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateAddress(addressField.getText());	
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateAddress(addressField.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	
	
	/**
	 * Function: updateAddressField()
	 * 
	 * This function updates the database value with the current number in the field.
	 */
	
	public static void updateAddressField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		addressField.setText(userInfoDatabase.getAddress());
	}
}
