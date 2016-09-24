package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class EraseAppDataPanel extends JPanel
{
	public static JCheckBox lockAfterTimeoutCheckbox = new JCheckBox("Erase App Data After");
	JLabel secondHalfofMessage = new JLabel(" Failed Attempts");
	AttemptsCombobox attemptsCombobox = new AttemptsCombobox();
	
	public EraseAppDataPanel()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createPanel();
		createCheckbox();
		addComponents();
	}
	
	public void createPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
	}
	
	public void createCheckbox()
	{
		lockAfterTimeoutCheckbox.setFocusPainted(false); // removed blue outline when clicked
		lockAfterTimeoutCheckbox.setBorderPainted(false); // remove the border.
	}
	
	public void addComponents()
	{
		add(lockAfterTimeoutCheckbox);
		add(AttemptsCombobox.attemptsCombobox);
		add(secondHalfofMessage);
	}
	
	public static void updateCheckbox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		lockAfterTimeoutCheckbox.setSelected(securitySettingsDatabase.getEraseAppDataValue());
	}
	
	public void addActionListener()
	{
		lockAfterTimeoutCheckbox.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
