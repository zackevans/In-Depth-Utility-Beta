package menu.buffer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.main.MainMenu;
import menu.settings.SettingsMenu;
import toolbar.topbar.TopBar;

public class BufferPanel extends JPanel
{
	private Map <String, JPanel> mapPanels = new HashMap <String, JPanel>();
	private MainMenu mainMenu;
	private SettingsMenu settingsMenu;
	
	public void initialize()
	{
		createComponents();
		addComponents();
		setDefaults();
		initializePanels();
		setOpaque(false);
	}
	
	public void createComponents() 
	{
		// Main Menu Settings
		mainMenu = new MainMenu(this);
		mapPanels.put("MAIN_MENU", mainMenu);
		
		// Security Settings
		settingsMenu = new SettingsMenu(this);
		mapPanels.put("SETTINGS_MENU", settingsMenu);
	}
	
	public void addComponents()
	{
		add(mainMenu);
		
		add(settingsMenu);
		
	}
	
	public void setDefaults()
	{
		showPanel("MAIN_MENU");
	}
	
	public void initializePanels()
	{
		mainMenu.initialize();
		
		settingsMenu.initialize();
	}
	
	public void showPanel(String panelName)
	{
		for (JPanel panel : mapPanels.values())
		{
			panel.setVisible(false);
		}
		
		JPanel panelToShow = mapPanels.get(panelName);
		panelToShow.setVisible(true);
	}
}
