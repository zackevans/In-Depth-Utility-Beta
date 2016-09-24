package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.shownotificationsbuttonpanel;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.lockaftertimeoutpanel.TimeCombobox;
import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class ShowNotificationsButtonPanel extends JPanel
{
	public static JCheckBox showNotificationsButtonCheckbox = new JCheckBox("Show Notificatins Tab When Locked");
	TimeCombobox timeCombobox = new TimeCombobox();
	
	public ShowNotificationsButtonPanel()
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
		showNotificationsButtonCheckbox.setFocusPainted(false); // removed blue outline when clicked
		showNotificationsButtonCheckbox.setBorderPainted(false); // remove the border.
	}
	
	
	public void addComponents()
	{
		add(showNotificationsButtonCheckbox);
	}
	
	public static void updateCheckBox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		showNotificationsButtonCheckbox.setSelected(securitySettingsDatabase.getShowNotificationsValue());
	}

}
