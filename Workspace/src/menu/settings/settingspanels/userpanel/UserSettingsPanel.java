package menu.settings.settingspanels.userpanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.settings.settingspanels.userpanel.extras.ExtrasPanel;
import menu.settings.settingspanels.userpanel.namefields.NameFieldsPanel;

/**
 * Class: UserSettingsPanel
 * @author ZackEvans
 * 
 * This class is a panel that holds all of the components for the user settings panel
 */

public class UserSettingsPanel extends JPanel
{
	private UserIcon userIcon;
	private NameFieldsPanel nameFieldsPanel;
	private ExtrasPanel extrasPanel;
	
	/**
	 * Constructor: UserSettingsPanel ()
	 * 
	 * This method calls the panel hierarchy.
	 */
	
	public UserSettingsPanel ()
	{
		super();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This method calls other methods to create the panel
	 */
	
	public void initialize()
	{
		createPanel();
		createComponenets();
		addComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This fucntion sets the size and the layout for the panel.
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
	 * This function initializes the component variables and then sets the size and locaiton of them.
	 */
	
	public void createComponenets()
	{
		userIcon = new UserIcon();
		nameFieldsPanel = new NameFieldsPanel();
		extrasPanel = new ExtrasPanel();
		
		userIcon.setBounds(0,0,200,200);	
		nameFieldsPanel.setBounds(200,0,300,200);
		extrasPanel.setBounds(10, 200, 454, 240);
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function sets the layout for the panel and adds all of the compoenents to the panel.
	 */
	
	public void addComponents()
	{
		setLayout(null);
		
		add(userIcon);
		add(nameFieldsPanel);
		add(extrasPanel);
	}
	
	/**
	 * Function: resetPanel()
	 * 
	 * This method calls functions to reset the user settings panel.
	 */
	
	public static void resetPanel()
	{
		NameFieldsPanel.updatePanel();
		ExtrasPanel.updatePanel();
	}
}
