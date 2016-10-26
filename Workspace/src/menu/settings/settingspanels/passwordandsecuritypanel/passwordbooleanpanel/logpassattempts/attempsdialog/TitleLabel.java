package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TitleLabel extends JLabel
{
	public TitleLabel()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createLabel();
	}
	
	public void createLabel()
	{
		setText("Password Attempts");
		setFont(new Font("Helvetica Neue",Font.PLAIN,30));
		setHorizontalAlignment(SwingConstants.CENTER);
		
	}
}
