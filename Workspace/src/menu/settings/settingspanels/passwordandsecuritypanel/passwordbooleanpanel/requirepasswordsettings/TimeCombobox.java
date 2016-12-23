package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class:  TimeCombobox
 * @author ZackEvans
 *
 * This class holds a combobox that lets the user select how often the program will lock.
 */

public class TimeCombobox 
{
	public static JComboBox <String> timeCombobox = new JComboBox <>();

	/**
	 * Constructor: TimeCombobox()
	 * 
	 * This constructor calls a method to create the combobox.
	 */
	
	public TimeCombobox()
	{
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * this method calls methods to create the panel
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
	 * This method makes the time combobox not be able to gain focus.
	 */
	
	public void createCombobox()
	{
		timeCombobox.setFocusable(false); 
	}
	
	/**
	 * Function: setData()
	 * 
	 * This functions adds the options to the combobox
	 */
	
	public void setData()
	{
		DefaultComboBoxModel <String> timeModel = new DefaultComboBoxModel <> ();
		timeModel.addElement("5 minutes");
		timeModel.addElement("20 minutes");
		timeModel.addElement("40 minutes");
		timeModel.addElement("1 hour");
		timeModel.addElement("2 hours");
		timeModel.addElement("5 hours");
		
		timeCombobox.setModel(timeModel);
	}
	
	/**
	 * Function: updateCombobox()
	 * 
	 * This function updates the combobox index based on the value from the db
	 */
	
	public static void updateCombobox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		timeCombobox.setSelectedIndex(securitySettingsDatabase.getRequirePasswordTimeValue());
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the combobox. When fired it updates the value in the db.
	 */
	
	public void addListeners()
	{
		timeCombobox.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateRequirePasswordTimeValue(timeCombobox.getSelectedIndex());
			}
		});
	}
}
