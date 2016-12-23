package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.noemaildialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;
import menu.settings.settingspanels.userpanel.extras.EmailPanel;

/**
 * Function: AddEmailButton
 * @author ZackEvans
 *
 * This class is a button that when clicked shows the user settings panel and hilights the email field
 */

public class AddEmailButton extends JButton
{
	SettingsBufferPanel settingsBufferPanel;
	
	/**
	 * Constructor: AddEmailButton(SettingsBufferPanel settingsBufferPanel)
	 * 
	 * This constructor calls the button hierarchy and inherits the settings bufferpanel object.
	 * Then it calls a method to setup the button.
	 */
	
	public AddEmailButton(SettingsBufferPanel settingsBufferPanel)
	{
		super();
		this.settingsBufferPanel = settingsBufferPanel;
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * this function calls methods to create the button
	 */
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * 
	 * This function sets the text of the button and removes the blue selection border.
	 */
	
	public void createBtn()
	{
		setText("Add Email");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This fucntion adds an action listener to the button.
	 * When the action listener fires it shows the user settings panel, hide the dialog and highlights the email field.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				settingsBufferPanel.showPanelAndList("USER_SETTINGS");
				NoEmailDialog.noEmailDialogFrame.setVisible(false);
				EmailPanel.emailField.requestFocusInWindow();
			}
		});
	}
}
