package menu.buffer;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import menu.main.MainMenu;

public class BufferPanel extends JPanel
{
	private Map <String, JPanel> mapPanels = new HashMap <String, JPanel>();
	private MainMenu mainMenu;
	
	public void initialize()
	{
		createComponents();
		addComponents();
		setDefaults();
		initializePanels();
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	public void createComponents() 
	{
		mainMenu = new MainMenu(this);
		mapPanels.put("MAIN_MENU", mainMenu);
	}
	
	public void addComponents()
	{
		add(mainMenu);
	}
	
	public void setDefaults()
	{
		showPanel("MAIN_MENU");
	}
	
	public void initializePanels()
	{
		mainMenu.initialize();
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
