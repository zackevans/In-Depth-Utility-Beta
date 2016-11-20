package menu.settings.settingspanels.passwordandsecuritypanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.PasswordBooleanPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel.PasswordSettingsPanel;

public class PasswordAndSecuritySettingsPanel extends JPanel
{
	private JPanel passwordSettingsPanel;
	private JPanel passwordBooleanPanel;
	SettingsBufferPanel settingsBufferPanel;
	
	public PasswordAndSecuritySettingsPanel(SettingsBufferPanel settingsBufferpanel)
	{
		super();
		this.settingsBufferPanel = settingsBufferpanel;
	}
	
	public void initialize()
	{
		createPanel();
		createComponenets();
		layoutComponents();
	}
	
	public void createPanel()
	{
		setLayout(null); // set panel layout to null
		setPreferredSize(new Dimension(575,500));
		setOpaque(false);
		setVisible(false);
	}
	
	public void createComponenets()
	{
		passwordSettingsPanel = new PasswordSettingsPanel();
		passwordBooleanPanel = new PasswordBooleanPanel(settingsBufferPanel);
		
		passwordSettingsPanel.setBounds(10, 10, 455, 200);
		passwordBooleanPanel.setBounds(10, 215, 455, 225);
	}
	
	public void layoutComponents()
	{
		add(passwordSettingsPanel);
		add(passwordBooleanPanel);
	}
	
	public static void resetPanel()
	{
		PasswordSettingsPanel.showMenu();
		PasswordBooleanPanel.resetPanel();
	}
}
