package menu.settings.settingspanels.userpanel.namefields;

import javax.swing.JPanel;

public class NameFieldsPanel extends JPanel
{
	private FirstNameField firstNameField;
	private LastNameField lastNameField;
	private CompanyField companyField;
	
	public NameFieldsPanel()
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
		setOpaque(false);
	}
	
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
	
	public static void updatePanel()
	{
		FirstNameField.updateField();
		LastNameField.updateField();
		CompanyField.updateField();
	}
}
