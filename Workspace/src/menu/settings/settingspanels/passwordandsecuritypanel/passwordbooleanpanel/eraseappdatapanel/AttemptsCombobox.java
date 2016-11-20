package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class AttemptsCombobox 
{
	public static JComboBox <String> attemptsCombobox = new JComboBox <String>();

	public AttemptsCombobox ()
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
	 * Function: createCombobox()
	 * @author ZackEvans
	 * 
	 * This method make the comobox not focus and removes the border.
	 */
	
	public void createCombobox()
	{
		attemptsCombobox.setFocusable(false); // make the combobox not be able to focous
		attemptsCombobox.setBorder(null); // remove the border
	}
	
	public void setData()
	{
		DefaultComboBoxModel <String> attemptModel = new DefaultComboBoxModel <String> ();
		attemptModel.addElement("5");
		attemptModel.addElement("10");
		attemptModel.addElement("15");
		attemptModel.addElement("20");
		attemptModel.addElement("25");
		
		attemptsCombobox.setModel(attemptModel);
	}
	
	public static void updateCombobox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		attemptsCombobox.setSelectedIndex(securitySettingsDatabase.getReceiveEmailAttemptsCount());
	}
	
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
