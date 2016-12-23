package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Class: TitleLabel
 * @author ZackEvans
 *
 * This class is a label that is used as the title of the dialog.
 */

public class TitleLabel extends JLabel
{
	/**
	 * Constructor: TitleLabel()
	 * 
	 * This constructor calls the label hierarchy and calls a method to create the label.
	 */
	
	public TitleLabel()
	{
		super();
		createLabel();
	}
	
	/**
	 * Function: createLabel()
	 * 
	 * This function sets the text and font for the label.
	 * Then it sets the text in the center of the label.
	 */
	
	public void createLabel()
	{
		setText("Password Attempts");
		setFont(new Font("Helvetica Neue",Font.PLAIN,30));
		setHorizontalAlignment(SwingConstants.CENTER);	
	}
}
