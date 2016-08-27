package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.lockaftertimeoutpanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class TimeCombobox 
{
	public static JComboBox <String> timeCombobox = new JComboBox <String>();

	public TimeCombobox()
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
		timeCombobox.setFocusable(false); // make the combobox not be able to focous
		timeCombobox.setBorder(null); // remove the border
	}
	
	public void setData()
	{
		DefaultComboBoxModel <String> timeModel = new DefaultComboBoxModel <String> ();
		timeModel.addElement("5 minutes");
		timeModel.addElement("20 minutes");
		timeModel.addElement("40 minutes");
		timeModel.addElement("1 hour");
		timeModel.addElement("2 hours");
		timeModel.addElement("5 hours");
		
		timeCombobox.setModel(timeModel);
	}
}
