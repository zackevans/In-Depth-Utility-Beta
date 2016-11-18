package menu.settings;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.settings.settingsbufferpanel.SettingsBufferPanel;

public class SettingsPanel extends JPanel
{
	BufferPanel bufferPanel;
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	SettingsList settingsList;
	SettingsBufferPanel settingsBufferPanel;
	
	public SettingsPanel(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createComponents();
		layoutComponents();
	}
	
	public void createComponents()
	{
		settingsBufferPanel = new SettingsBufferPanel();
		settingsList = new SettingsList(settingsBufferPanel);
		
		SettingsList.scrollPane.setBounds(0, 15, 225, 500);
		settingsBufferPanel.setBounds(225,15, 575,500);
	}
	
	public void layoutComponents()
	{
		setOpaque(false);
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20)); // set size of panel
		
		add(SettingsList.scrollPane);
		add(settingsBufferPanel);
	}
}
