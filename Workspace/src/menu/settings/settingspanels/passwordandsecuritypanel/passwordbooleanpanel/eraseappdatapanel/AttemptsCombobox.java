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
		attemptModel.addElement("5");
		attemptModel.addElement("10");
		attemptModel.addElement("15");
		attemptModel.addElement("20");
		attemptModel.addElement("25");
		
		attemptsCombobox.setModel(attemptModel);
	}
}
