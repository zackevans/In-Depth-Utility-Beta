package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ReceiveSafteyEmailPanel extends JPanel
{
	public static JCheckBox receiveSafteyEmailCheckbox = new JCheckBox("Receive Email After");
	SafteyEmailCountCombobox safteyEmailCountCombobox = new SafteyEmailCountCombobox();
	
	public ReceiveSafteyEmailPanel()
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
		receiveSafteyEmailCheckbox.setFocusPainted(false); // removed blue outline when clicked
		receiveSafteyEmailCheckbox.setBorderPainted(false); // remove the border.
	}
	
	public void addComponents()
	{
		add(receiveSafteyEmailCheckbox);
		add(SafteyEmailCountCombobox.safteyEmailCombobox);
	}
}
