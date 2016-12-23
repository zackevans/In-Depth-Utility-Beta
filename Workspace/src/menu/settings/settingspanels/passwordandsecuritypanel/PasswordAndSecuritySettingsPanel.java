package menu.settings.settingspanels.passwordandsecuritypanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.PasswordBooleanPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel.PasswordSettingsPanel;

/**
 * Class: PasswordAndSecuritySettingsPanel
 * @author ZackEvans
 *
 * This class is a panel that holds the password settings and the extra checkbox settings.
 */

public class PasswordAndSecuritySettingsPanel extends JPanel
{
	private JPanel passwordSettingsPanel;
	private JPanel passwordBooleanPanel;
	SettingsBufferPanel settingsBufferPanel;
	
	/**
	 * Constructor: PasswordAndSecuritySettingsPanel(SettingsBufferPanel settingsBufferpanel)
	 * 
	 * This constructor calls the panel heirarchy and inherits the settignsbufferpanel object
	 */
	
	public PasswordAndSecuritySettingsPanel(SettingsBufferPanel settingsBufferpanel)
	{
		super();
		this.settingsBufferPanel = settingsBufferpanel;
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the panel
	 */
	
	public void initialize()
	{
		createPanel();
		createComponenets();
		layoutComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function calls methods set up the panel
	 */
	
	public void createPanel()
	{
		setLayout(null); // set panel layout to null
		setPreferredSize(new Dimension(575,500));
		setOpaque(false);
		setVisible(false);
	}
	
	/**
	 * Function: createComponenets()
	 * 
	 * This function creates the objects to be added to the panel and sets the size and location of them.
	 */
	
	public void createComponenets()
	{
		passwordSettingsPanel = new PasswordSettingsPanel();
		passwordBooleanPanel = new PasswordBooleanPanel(settingsBufferPanel);
		
		passwordSettingsPanel.setBounds(10, 10, 455, 200);
		passwordBooleanPanel.setBounds(10, 215, 455, 225);
	}
	
	/**
	 * Function: layoutComponents()
	 * 
	 * This function adds the panels to the panel
	 */
	
	public void layoutComponents()
	{
		add(passwordSettingsPanel);
		add(passwordBooleanPanel);
	}
	
	/**
	 * Function: resetPanel()
	 * 
	 * This function sets the defaults for the panels.
	 */
	
	public static void resetPanel()
	{
		PasswordSettingsPanel.showMenu();
		PasswordBooleanPanel.resetPanel();
	}
}
