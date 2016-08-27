package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class SafteyEmailCountCombobox 
{
	public static JComboBox <String> safteyEmailCombobox = new JComboBox <String>();

	public SafteyEmailCountCombobox ()
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
		safteyEmailCombobox.setFocusable(false); // make the combobox not be able to focous
		safteyEmailCombobox.setBorder(null); // remove the border
	}
	
	public void setData()
	{
		DefaultComboBoxModel <String> timeModel = new DefaultComboBoxModel <String> ();
		timeModel.addElement("5 Attempts");
		timeModel.addElement("10 Attempts");
		timeModel.addElement("15 Attempts");
		timeModel.addElement("20 Attempts");
		timeModel.addElement("50 Attempts");
		
		safteyEmailCombobox.setModel(timeModel);
	}

}
