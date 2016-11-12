package menu.settings.settingspanels.userpanel.extras;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class ExtrasPanel extends JPanel
{
	private PhoneNumberPanel phoneNumberPanel;
	private EmailPanel emailPanel;
	private AddressPanel addressPanel;
	private BirthdayFields birthdayFields;
	
	public ExtrasPanel() 
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createPanel();
		createComponents();
		addComponents();
	}
	
	public void createPanel()
	{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.black,Color.gray), "Additional Information"));
		setPreferredSize(new Dimension(500, 300));
		setOpaque(false);
	}
	
	public void createComponents()
	{
		phoneNumberPanel = new PhoneNumberPanel();
		emailPanel = new EmailPanel();
		addressPanel = new AddressPanel();
		birthdayFields = new BirthdayFields();
		
		phoneNumberPanel.setBounds(30,30 , 400, 35);
		
		emailPanel.setBounds(30,80, 400,35);
		
		addressPanel.setBounds(30,130,400,35);
		
		birthdayFields.setBounds(30,180, 400,35);
	}
	
	
	public void addComponents()
	{
		setLayout(null);
		
		add(phoneNumberPanel);
		add(emailPanel);
		add(addressPanel);
		add(birthdayFields);
	}
	
	public static void updatePanel()
	{
		PhoneNumberPanel.updateNumberFields();
		EmailPanel.updateField();
		AddressPanel.updateAddressField();
		BirthdayFields.updateBoxes();
		BirthdayFields.yearBox.requestFocusInWindow();
	}
}
