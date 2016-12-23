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
 * Class: LastNameField 
 * @author ZackEvans
 *
 * This class holds components to get the users last name.
 */

public class LastNameField 
{
	public static JTextField lastNameField = new TextFieldShell();
	public static JLabel lastNameLabel = new JLabel("Last Name");
	
	/**
	 * Constructor: LastNameField()
	 * 
	 * This constructor calls a method to create the panel.
	 */
	
	public LastNameField()
	{
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls two metod that create the components.
	 */
	
	public void initialize()
	{
		createComponents();
    	addListeners();
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function sets the text in the center of the label and the color of the text.
	 */
	
	public void createComponents()
	{
		lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameLabel.setOpaque(false);
		lastNameLabel.setForeground(new Color(0,0,0,200));
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a document and focus listener to the text field.
	 * The document listener updates the users typing in the database. 
	 * The focus listener handles the visibility of the label.
	 */
	
	public void addListeners()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		lastNameField.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateLastName(lastNameField.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				userInfoDatabase.updateLastName(lastNameField.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		lastNameField.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if(lastNameField.getText().length() == 0)
				{
					lastNameLabel.setVisible(true);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				lastNameLabel.setVisible(false);
				
			}
		});
	}
	
	/**
	 * Function: updateField()
	 * 
	 * This function updates the text in the text field with the data in the database.
	 */
	
	public static void updateField()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		if(userInfoDatabase.getLastName().length() !=0)
		{
			lastNameField.setText(userInfoDatabase.getLastName());
			lastNameLabel.setVisible(false);
		}
	}
}
