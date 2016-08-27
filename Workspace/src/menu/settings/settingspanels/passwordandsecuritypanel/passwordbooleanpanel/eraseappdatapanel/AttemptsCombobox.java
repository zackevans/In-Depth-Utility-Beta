package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class AttemptsCombobox 
{
	public static JComboBox <String> attemptsCombobox = new JComboBox <String>();

	public AttemptsCombobox ()
	{
		initialize();
	}
	
	public void initialize()
	{
		createCombobox();
		setData();
	}
	
	/**
	 * Function: createCombobox()
	 * @author ZackEvans
	 * 
	 * This method make the comobox not focus and removes the border.
	 */
	
	public void createCombobox()
	{
		attemptsCombobox.setFocusable(false); // make the combobox not be able to focous
		attemptsCombobox.setBorder(null); // remove the border
	}
	
	public void setData()
	{
		DefaultComboBoxModel <String> attemptModel = new DefaultComboBoxModel <String> ();
		attemptModel.addElement("5 Attempts");
		attemptModel.addElement("10 Attempts");
		attemptModel.addElement("15 Attempts");
		attemptModel.addElement("20 Attempts");
		attemptModel.addElement("25 Attempts");
		
		attemptsCombobox.setModel(attemptModel);
	}
}
