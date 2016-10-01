package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.lockaftertimeoutpanel;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class RequirePasswordSettingsPanel extends JPanel
{
	public static JCheckBox requirePasswordCheckbox = new LockCheckbox();
	
	public RequirePasswordSettingsPanel()
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
		requirePasswordCheckbox.setFocusPainted(false); // removed blue outline when clicked
		requirePasswordCheckbox.setBorderPainted(false); // remove the border.
	}
	
	
	public void addComponents()
	{
		add(requirePasswordCheckbox);
		add(TimeCombobox.timeCombobox);
	}
	
	public static void updateCheckbox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		requirePasswordCheckbox.setSelected(securitySettingsDatabase.getRequirePasswordValue());
	}
	
}
