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

public class AddressPanel extends JPanel
{
	static JTextField addressField = new TextFieldShell();
	
	public AddressPanel ()
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
		addressField.setPreferredSize(new Dimension(250, 23));
	}
	
	public void addComponents()
	{
		JLabel addressLabel = new JLabel("Address: ");
		
		add(addressLabel);
		add(addressField);
	}
	
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
	
	public static void updateAddressField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		addressField.setText(userInfoDatabase.getAddress());
	}
}
