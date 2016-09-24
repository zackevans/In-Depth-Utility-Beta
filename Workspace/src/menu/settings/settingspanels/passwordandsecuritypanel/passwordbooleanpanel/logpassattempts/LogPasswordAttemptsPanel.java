package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import sql.systemsettings.securitysettings.SecuritySettingsDatabase;

public class LogPasswordAttemptsPanel extends JPanel
{
	public static JCheckBox logPasswordCheckbox = new LogPasswordCheckbox();
	JButton viewFailedPasswordAttemptsButton = new ViewFailedPasswordButton();
	
	public LogPasswordAttemptsPanel()
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
		logPasswordCheckbox.setFocusPainted(false); // removed blue outline when clicked
		logPasswordCheckbox.setBorderPainted(false); // remove the border.
	}
	
	public void addComponents()
	{
		add(logPasswordCheckbox);
		add(viewFailedPasswordAttemptsButton);
	}
	
	public static void updateCheckbox()
	{
		SecuritySettingsDatabase securitySettingsDatabase = new SecuritySettingsDatabase();
		logPasswordCheckbox.setSelected(securitySettingsDatabase.getLogFailedAttemptsValue());
		
	}
}
