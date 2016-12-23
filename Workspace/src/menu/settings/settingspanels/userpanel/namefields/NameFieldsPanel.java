package menu.settings.settingspanels.userpanel.namefields;

import javax.swing.JPanel;

/**
 * Class: NameFieldsPanel
 * @author ZackEvans
 * 
 * This class is a panel that holds components to get the first,last name and the company they work at.
 */

public class NameFieldsPanel extends JPanel
{
	private FirstNameField firstNameField;
	private LastNameField lastNameField;
	private CompanyField companyField;
	
	/**
	 * Constructor:  NameFieldsPanel()
	 * 
	 * This constructor calls the panel hierarchy and a function to create the panel.
	 */
	
	public NameFieldsPanel()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the panel and its components.
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
	 * This function make the panel clear. 
	 */
	
	public void createPanel()
	{
		setOpaque(false);
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This fucntion initializes the the components and sets the size and location of them.
	 */
	
	public void createComponents()
	{
		firstNameField = new FirstNameField();
		lastNameField = new LastNameField();
		companyField = new CompanyField();
		
		FirstNameField.firstNameField.setBounds(5,37 ,250,25);
		FirstNameField.firstNameLabel.setBounds(5,37,250,25);
		
		
		LastNameField.lastNameField.setBounds(5, 87,250,25);
		LastNameField.lastNameLabel.setBounds(5, 87,250,25);
		
		CompanyField.companyField.setBounds(5,137,250,25);
		CompanyField.companyLabel.setBounds(5,137,250,25);
		
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function sets the layout manager of the panel and adds the compoennets to the panel.
	 */
	
	public void addComponents()
	{
		setLayout(null);
		
		add(FirstNameField.firstNameLabel);
		add(FirstNameField.firstNameField);
		
		add(LastNameField.lastNameLabel);
		add(LastNameField.lastNameField);
		
		add(CompanyField.companyLabel);
		add(CompanyField.companyField);
	}
	
	/**
	 * Function: updatePanel()
	 * 
	 * This function calls methods to update the data in all of the components. 
	 */
	
	public static void updatePanel()
	{
		FirstNameField.updateField();
		LastNameField.updateField();
		CompanyField.updateField();
	}
}
