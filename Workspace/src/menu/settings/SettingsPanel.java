package menu.settings;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.settings.settingsbufferpanel.SettingsBufferPanel;

/**
 * Class: SettingsPanel
 * @author ZackEvans
 * 
 * This class is a panel that holds all the components that make up the settings menu
 */

public class SettingsPanel extends JPanel
{
	BufferPanel bufferPanel;
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	SettingsList settingsList;
	SettingsBufferPanel settingsBufferPanel;
	
	/**
	 * Constructor: SettingsPanel(BufferPanel bufferPanel)
	 * 
	 * This constructor inherits the bufferPanel object and calls the panels hierarchy.
	 */
	
	public SettingsPanel(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls other methods to create the panel
	 */
	
	public void initialize()
	{
		createComponents();
		layoutComponents();
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function initializes the components and sets the size and location of them.
	 */
	
	public void createComponents()
	{
		settingsBufferPanel = new SettingsBufferPanel();
		settingsList = new SettingsList(settingsBufferPanel);
		
		SettingsList.scrollPane.setBounds(0, 15, 225, 500);
		settingsBufferPanel.setBounds(225,15, 575,500);
	}
	
	/**
	 * Function: layoutComponents()
	 * 
	 * This function sets up the panel and adds the components to it.
	 */
	
	public void layoutComponents()
	{
		setOpaque(false);
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20)); // set size of panel
		
		add(SettingsList.scrollPane);
		add(settingsBufferPanel);
	}
}
