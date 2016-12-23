package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: AttemptsCombobox
 * @author ZackEvans
 *
 * This class holds a combo box which sets how many attempts the user is allowed
 */

public class AttemptsCombobox 
{
	public static JComboBox <String> attemptsCombobox = new JComboBox <>();

	/**
	 * Constructor: AttemptsCombobox ()
	 * 
	 * This constructor calls a methods to create the combobox
	 */
	
	public AttemptsCombobox ()
	{
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the checkbox
	 */
	
	public void initialize()
	{
		createCombobox();
		setData();
		addListeners();
	}
	
	/**
	 * Function: createCombobox()
	 * 
	 * This method make the comobox not focus and removes the border.
	 */
	
	public void createCombobox()
	{
		attemptsCombobox.setFocusable(false); // make the combobox not be able to focous
		attemptsCombobox.setBorder(null); // remove the border
	}
	
	/**
	 * Function: setData()
	 * 
	 * This function loads the data into the combobox
	 */
	
	public void setData()
	{
		DefaultComboBoxModel <String> attemptModel = new DefaultComboBoxModel <> ();
		attemptModel.addElement("5");
		attemptModel.addElement("10");
		attemptModel.addElement("15");
		attemptModel.addElement("20");
		attemptModel.addElement("25");
		
		attemptsCombobox.setModel(attemptModel);
	}
	
	/**
	 * Function: updateCombobox()
	 * 
	 * This function updates the selected item in the combobox 
	 */
	
	public static void updateCombobox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		attemptsCombobox.setSelectedIndex(securitySettingsDatabase.getReceiveEmailAttemptsCount());
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function add an action listener to the combobox.
	 * When the item is changed it is updated in the database.
	 */
	
	public void addListeners()
	{
		attemptsCombobox.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateReceiveEmailAttemptsCount(attemptsCombobox.getSelectedIndex());
			}
		});
	}
}
