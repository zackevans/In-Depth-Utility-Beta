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

public class BirthdayFields extends JPanel
{
	public static JComboBox<Integer> monthBox = new JComboBox<Integer>();
	public static JComboBox<Integer> dayBox = new JComboBox<Integer>();
	public static JComboBox<Integer> yearBox = new JComboBox<Integer>();
	
	public BirthdayFields() 
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		createComboboxes();
		addComponents();
		addListeners();
		setOpaque(false);
	}
	
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
	
	public void addComponents()
	{
		JLabel birthdayLabel = new JLabel("Birthday: ");
		
		add(birthdayLabel);
		add(monthBox);
		add(dayBox);
		add(yearBox);
	}
	
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
	
	public static void updateBoxes()
	{
		UserInfoDatabase userInfoDatabase = new UserInfoDatabase();
		
		monthBox.setSelectedIndex(userInfoDatabase.getBirthdayMonth());
		dayBox.setSelectedIndex(userInfoDatabase.getBirthdayDay());
		yearBox.setSelectedIndex(userInfoDatabase.getBirthdayYear());
	}
}
