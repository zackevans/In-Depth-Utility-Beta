package menu.settings.settingspanels.userpanel.extras;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sql.userinfo.UserInfoDatabase;

/**
 * Class: BirthdayFields
 * @author ZackEvans
 * 
 * This class holds all the components needed to get the uses birthday.
 */

public class BirthdayFields extends JPanel
{
	public static JComboBox<Integer> monthBox = new JComboBox<>();
	public static JComboBox<Integer> dayBox = new JComboBox<>();
	public static JComboBox<Integer> yearBox = new JComboBox<>();
	
	/**
	 * Constructor: BirthdayFields() 
	 * 
	 * This constructor calls the panel hierarchy and a function to create the panel 
	 */
	
	public BirthdayFields() 
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This fucntion calls methods to create the fields.
	 */
	
	public void initialize()
	{
		createPanel();
		createComboboxes();
		addComponents();
		addListeners();
		setOpaque(false);
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function sets the layout mananger and makes the panel clear.
	 */
	
	public void createPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
	}
	
	/**
	 * Function: createComboboxes()
	 * 
	 * This function loads the comoboboxes with their data.
	 */
	
	public void createComboboxes()
	{
		DefaultComboBoxModel monthBoxModel = new DefaultComboBoxModel ();
		DefaultComboBoxModel dayBoxModel = new DefaultComboBoxModel ();
		DefaultComboBoxModel yearBoxModel = new DefaultComboBoxModel ();
		
		for (int i = 1; i <= 12; i++)
		{
			monthBoxModel.addElement(i);
		}
		
		for (int i = 1; i <=30; i++)
		{
			dayBoxModel.addElement(i);
		}
		
		for(int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1900 ; i-- )
		{
			yearBoxModel.addElement(i);
		}
		
		monthBox.setModel(monthBoxModel);
		dayBox.setModel(dayBoxModel);
		yearBox.setModel(yearBoxModel);
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function adds all of the componenets to the panel.
	 */
	
	public void addComponents()
	{
		JLabel birthdayLabel = new JLabel("Birthday: ");
		
		add(birthdayLabel);
		add(monthBox);
		add(dayBox);
		add(yearBox);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to each of the comboboxes. When they fire they update their value in the database.
	 */
	
	public void addListeners()
	{
		monthBox.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
				userInfoDatabase.updateBirthdayMonth(monthBox.getSelectedIndex());
			}
		});
		
		dayBox.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
				userInfoDatabase.updateBirthdayDay(dayBox.getSelectedIndex());
			}
		});
		
		yearBox.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
				userInfoDatabase.updateBirthdayYear(yearBox.getSelectedIndex());
			}
		});
	}
	
	/**
	 * Function: updateBoxes()
	 * 
	 * This fucntion updates the comboboxes with the values stored in the database.
	 */
	
	public static void updateBoxes()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		monthBox.setSelectedIndex(userInfoDatabase.getBirthdayMonth());
		dayBox.setSelectedIndex(userInfoDatabase.getBirthdayDay());
		yearBox.setSelectedIndex(userInfoDatabase.getBirthdayYear());
	}
}
