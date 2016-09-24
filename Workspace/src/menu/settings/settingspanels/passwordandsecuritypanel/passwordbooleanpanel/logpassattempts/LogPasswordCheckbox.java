package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class LogPasswordCheckbox extends JCheckBox
{
	public LogPasswordCheckbox()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createCheckbox();
		addListeners();
	}
	
	public void createCheckbox()
	{
		setText("Log Failed Password Attempts");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("FUCK");
				
			}
		});
	}
}
