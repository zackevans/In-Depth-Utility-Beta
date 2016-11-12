package menu.settings.settingspanels.userpanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.settings.settingspanels.userpanel.extras.ExtrasPanel;
import menu.settings.settingspanels.userpanel.namefields.NameFieldsPanel;

public class UserSettingsPanel extends JPanel
{
	private UserIcon userIcon;
	private NameFieldsPanel nameFieldsPanel;
	private ExtrasPanel extrasPanel;
	
	
	public UserSettingsPanel ()
	{
		super();
	}
	
	public void initialize()
	{
		createPanel();
		createComponenets();
		addComponents();
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
		userIcon = new UserIcon();
		nameFieldsPanel = new NameFieldsPanel();
		extrasPanel = new ExtrasPanel();
		
		userIcon.setBounds(0,0,200,200);	
		nameFieldsPanel.setBounds(200,0,300,200);
		extrasPanel.setBounds(10, 200, 454, 240);
	}
	
	public void addComponents()
	{
		setLayout(null);
		
		add(userIcon);
		add(nameFieldsPanel);
		add(extrasPanel);
	}
	
	public static void resetPanel()
	{
		NameFieldsPanel.updatePanel();
		ExtrasPanel.updatePanel();
	}
	
	
}
