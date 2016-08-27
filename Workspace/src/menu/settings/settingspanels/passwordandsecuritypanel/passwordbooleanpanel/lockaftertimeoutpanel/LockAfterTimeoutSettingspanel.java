package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.lockaftertimeoutpanel;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class LockAfterTimeoutSettingspanel extends JPanel
{
	public static JCheckBox lockAfterTimeoutCheckbox = new JCheckBox("Require Password After");
	TimeCombobox timeCombobox = new TimeCombobox();
	
	public LockAfterTimeoutSettingspanel()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createPanel();
		createCheckbox();
		addComponents();
	}
	
	public void createPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
	}
	
	public void createCheckbox()
	{
		lockAfterTimeoutCheckbox.setFocusPainted(false); // removed blue outline when clicked
		lockAfterTimeoutCheckbox.setBorderPainted(false); // remove the border.
	}
	
	
	public void addComponents()
	{
		add(lockAfterTimeoutCheckbox);
		add(TimeCombobox.timeCombobox);
	}
}
	
	

