package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

/**
 * Class: SafetyEmailCountCombobox
 * 
 * This class is a combobox that lets the user set how many failed attempts to allow
 */

public class SafetyEmailCountCombobox 
{
	public static JComboBox <String> safteyEmailCombobox = new JComboBox <>();

	/**
	 * Constructor: SafetyEmailCountCombobox ()
	 *
	 * This constructor calls a function to create the combobox
	 */
	
	public SafetyEmailCountCombobox ()
	{
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the combobox
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
	 * This method make the comobox not get the blue focus border
	 */
	
	public void createCombobox()
	{
		safteyEmailCombobox.setFocusable(false); // make the combobox not be able to focous
		safteyEmailCombobox.setBorder(null); // remove the border
	}
	
	/**
	 * Function: setData()
	 * 
	 * This function adds the data to the combobox
	 */
	
	public void setData()
	{
		DefaultComboBoxModel <String> safteyEmailModel = new DefaultComboBoxModel <> ();
		safteyEmailModel.addElement("5");
		safteyEmailModel.addElement("10");
		safteyEmailModel.addElement("15");
		safteyEmailModel.addElement("20");
		safteyEmailModel.addElement("25");
		
		safteyEmailCombobox.setModel(safteyEmailModel);
	}
	
	/**
	 * Function: updateCombobox()
	 * 
	 * This function updates which item is selected in the combobox
	 */
	
	public static void updateCombobox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		safteyEmailCombobox.setSelectedIndex(securitySettingsDatabase.getReceiveEmailAttemptsCount());
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the combobox. 
	 * When fired it updates the db value with the current value of the combobox.
	 */
	
	public void addListeners()
	{
		safteyEmailCombobox.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
				securitySettingsDatabase.updateReceiveEmailAttemptsCount(safteyEmailCombobox.getSelectedIndex());
			}
		});
	}
}
