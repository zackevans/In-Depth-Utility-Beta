package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class TimeCombobox 
{
	public static JComboBox <String> timeCombobox = new JComboBox <String>();

	public TimeCombobox()
	{
		initialize();
	}
	
	public void initialize()
	{
		createCombobox();
		setData();
		addListeners();
	}
	
	/**
	 * This method make the comobox not focus and removes the border.
	 */
	
	public void createCombobox()
	{
		timeCombobox.setFocusable(false); // make the combobox not be able to focous
		timeCombobox.setBorder(null); // remove the border
	}
	
	public void setData()
	{
		DefaultComboBoxModel <String> timeModel = new DefaultComboBoxModel <String> ();
		timeModel.addElement("5 minutes");
		timeModel.addElement("20 minutes");
		timeModel.addElement("40 minutes");
		timeModel.addElement("1 hour");
		timeModel.addElement("2 hours");
		timeModel.addElement("5 hours");
		
		timeCombobox.setModel(timeModel);
	}
	
	public static void updateCombobox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		timeCombobox.setSelectedIndex(securitySettingsDatabase.getRequirePasswordTimeValue());
	}
	
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
