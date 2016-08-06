package menu.settings.settingspanels.passwordandsecuritypanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel.PasswordSettingsPanel;

public class PasswordAndSecuritySettingsPanel extends JPanel
{
	private JPanel passwordSettingsPanel;
		
	public PasswordAndSecuritySettingsPanel()
	{
		super();
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
		
		passwordSettingsPanel.setBounds(10, 10, 455, 200);
	}
	
	public void layoutComponents()
	{
		add(passwordSettingsPanel);
	}
	
	public static void resetPanel()
	{
		PasswordSettingsPanel.showMenu();
	}
}
