package menu.settings.settingspanels.userpanel.extras;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Class: ExtrasPanel
 * @author ZackEvans
 * 
 * This class is a panel that holds all of the other fields that are not the name or company fields.
 */

public class ExtrasPanel extends JPanel
{
	private PhoneNumberPanel phoneNumberPanel;
	private EmailPanel emailPanel;
	private AddressPanel addressPanel;
	private BirthdayFields birthdayFields;
	private ExtrasPanelErrors extrasPanelErrors;
	
	/**
	 * Constructor: ExtrasPanel()
	 * 
	 * This constructor calls the panel hierarchy and a method to create the panel.
	 */
	
	public ExtrasPanel() 
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the components and the panel.
	 */
	
	public void initialize()
	{
		createPanel();
		createComponents();
		addComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function sets the border for the panel and the size.
	 */
	
	public void createPanel()
	{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.black,Color.gray), "Additional Information"));
		setPreferredSize(new Dimension(500, 300));
		setOpaque(false);
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function initializes all of the components on the panel.
	 * Then it sets the size and location of the components.
	 */
	
	public void createComponents()
	{
		phoneNumberPanel = new PhoneNumberPanel();
		emailPanel = new EmailPanel();
		addressPanel = new AddressPanel();
		birthdayFields = new BirthdayFields();
		extrasPanelErrors = new ExtrasPanelErrors();
		
		phoneNumberPanel.setBounds(30,30 , 400, 35);
		
		emailPanel.setBounds(30,80, 400,35);
		ExtrasPanelErrors.emailErrorLabel.setBounds(340, 81, 30,30);
		
		addressPanel.setBounds(30,130,400,35);
		
		birthdayFields.setBounds(30,180, 400,35);
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function sets the layout manager for the panel and adds the components to the panel.
	 */
	
	public void addComponents()
	{
		setLayout(null);
		
		add(phoneNumberPanel);
		add(ExtrasPanelErrors.emailErrorLabel);
		add(emailPanel);
		add(addressPanel);
		add(birthdayFields);
	}
	
	/**
	 * Function: updatePanel()
	 * 
	 * This function calls each panels fucntion to load their components with data from the database.
	 */
	
	public static void updatePanel()
	{
		PhoneNumberPanel.updateValue();
		EmailPanel.updateField();
		AddressPanel.updateAddressField();
		BirthdayFields.updateBoxes();
		BirthdayFields.yearBox.requestFocusInWindow();
	}
}
