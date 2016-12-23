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

/**
 * Class: CompanyField 
 * @author ZackEvans
 *
 * This class holds a text field that the user enters their company field.
 */

public class CompanyField 
{
	public static JTextField companyField = new TextFieldShell();
	public static JLabel companyLabel = new JLabel("Company");
	
	/**
	 * Constructor: CompanyField()
	 * 
	 * This constructor calls a method to create the components. 
	 */
	
	public CompanyField()
	{
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls funcitons to create the components on the panel
	 */
	
	public void initialize()
	{
		createComponents();
    	addListeners();
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function sets up the company label text alignment and its color.
	 */
	
	public void createComponents()
	{
		companyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyLabel.setOpaque(false);
		companyLabel.setForeground(new Color(0,0,0,200));
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a document listener to the field. When the user types it updates the database.
	 * This function also adds a focus listener to the panel. When it looses focus it shows the label in the field.
	 */
	
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
	
	/**
	 * Function: updateField()
	 * 
	 * This function updates the value in the database with the value in the text field.
	 */
	
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
