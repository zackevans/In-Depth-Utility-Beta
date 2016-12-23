package menu.settings;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;

/**
 * Class: SettingsList
 * @author ZackEvans
 * 
 * This class holds a list that holds the main settings categories.
 */

public class SettingsList
{
	private SettingsBufferPanel settingsBufferPanel;
	public static JScrollPane scrollPane = new JScrollPane();
	public static JList settingsList = new JList();
	private Map<String, ImageIcon> imageMap = new HashMap<>(); // create a hashmap with the name and image
	public static String[] listItems = {"User","General", "Password/Security", "Notifications", "Status Bar" , "Sharing"};
	
	/**
	 * Constructor: SettingsList (SettingsBufferPanel settingsBufferPanel)
	 * @param settingsBufferPanel
	 * 
	 * This constructor inherits the settings settingsbufferpanel object and calls a method to crate the list.
	 */
	
	public SettingsList (SettingsBufferPanel settingsBufferPanel)
	{
		this.settingsBufferPanel = settingsBufferPanel;
		initialize();	
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the list.
	 */
	
	public void initialize()
	{
		createList();
		createIcons();
		addListeners();
	}
	
	/**
	 * Function: createList()
	 * 
	 * This function creates the scroll pane and the settings list
	 */
	
	public void createList()
	{
		settingsList.setListData(listItems); // put the list data in the list
		settingsList.setBorder(null);
		settingsList.setBackground(new Color(225,225,225,0x99)); // set color to clear white
		settingsList.setFixedCellHeight(50);
		settingsList.setCellRenderer(new SettingsListRender()); // add custom cell render to list
		
		scrollPane.setViewportView(settingsList);
		scrollPane.getViewport().setBackground(new Color(225,225,225,0x99));
		scrollPane.setBackground(new Color(225,225,225,0x99));
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		scrollPane.setFocusable(false);
	}
	
	/**
	 * Function: createIcons ()
	 * 
	 * This function loads and creates all the images. All images are stored in a map so they can be loaded later
	 */
	
	public void createIcons ()
	{
		URL userURL = SettingsList.class.getResource("/Button_Images/Settings/SettingsListImages/User.png");
		URL generalURL = SettingsList.class.getResource("/Button_Images/Settings/SettingsListImages/GeneralSettings.png"); // create URL to store mail image
		URL securityURL = SettingsList.class.getResource("/Button_Images/Settings/SettingsListImages/SecuritySettings.png"); // create URL to store mail image
		URL notificationsURL = SettingsList.class.getResource("/Button_Images/Settings/SettingsListImages/NotificationsSettings.png"); // create URL to store mail image
		URL statusBarURL = SettingsList.class.getResource("/Button_Images/Settings/SettingsListImages/StatusbarSettings.png"); // create URL to store mail image
		URL sharingURL = SettingsList.class.getResource("/Button_Images/Settings/SettingsListImages/SharingSettings.png"); // create URL to store mail image
		
		ImageIcon userImg = new ImageIcon(userURL);
		ImageIcon generalImg = new ImageIcon(generalURL);
		ImageIcon securityImg = new ImageIcon(securityURL);
		ImageIcon notificationsImg = new ImageIcon(notificationsURL);
		ImageIcon statusBarImg = new ImageIcon(statusBarURL);
		ImageIcon sharingImg = new ImageIcon(sharingURL);
	
		imageMap.put("User", userImg);
		imageMap.put("General", generalImg);
		imageMap.put("Password/Security", securityImg);
		imageMap.put("Notifications", notificationsImg);
		imageMap.put("Status Bar", statusBarImg);
		imageMap.put("Sharing", sharingImg);
	}
	
	/**
	 * Function: addListeners() 
	 * 
	 * This function adds an action listener to the list so the user can make selections.
	 */
	
	public void addListeners()
	{
		settingsList.addMouseListener(new MouseListener() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int listIndex = settingsList.getSelectedIndex();
				
				switch(listIndex)
				{
					case 0:
						settingsBufferPanel.showPanel("USER_SETTINGS");
				        break;
					case 2:
						settingsBufferPanel.showPanel("PASSWORD_AND_SECURITY_SETTINGS");
						 break;
				    default:
				    	settingsBufferPanel.clearPanel();  
				        break;
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
	
	/**
	 * Class: ShareListRender
	 * @author ZackEvans
	 *
	 * This class creates custom list cells.
	 * This class Overrides the cell render method to add an image and text to the list.
	 */
	
	class SettingsListRender extends DefaultListCellRenderer 
	{
	    Font font = new Font("Helvetica Neue",Font.PLAIN,21); // set font for list.

	    @Override
	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	    {
	        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // create new label
	        
	        label.setIcon(imageMap.get(value)); // add image to label
	        label.setHorizontalTextPosition(JLabel.RIGHT); // add text to the right of the Label
	        label.setFont(font); // set the label font
	        label.setBorder(null); // get rid of the border that surrounds the individual items in the list.
	        list.setFocusable(false);
	        list.setBackground(Color.white);
	        return label; // return the custom jlabel
	    }
	}
}
