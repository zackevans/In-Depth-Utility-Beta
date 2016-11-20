package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class SafteyEmailCountCombobox 
{
	public static JComboBox <String> safteyEmailCombobox = new JComboBox <String>();

	public SafteyEmailCountCombobox ()
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
		safteyEmailCombobox.setFocusable(false); // make the combobox not be able to focous
		safteyEmailCombobox.setBorder(null); // remove the border
	}
	
	public void setData()
	{
		DefaultComboBoxModel <String> safteyEmailModel = new DefaultComboBoxModel <String> ();
		safteyEmailModel.addElement("5");
		safteyEmailModel.addElement("10");
		safteyEmailModel.addElement("15");
		safteyEmailModel.addElement("20");
		safteyEmailModel.addElement("25");
		
		safteyEmailCombobox.setModel(safteyEmailModel);
	}
	
	public static void updateCombobox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		safteyEmailCombobox.setSelectedIndex(securitySettingsDatabase.getReceiveEmailAttemptsCount());
	}
	
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
