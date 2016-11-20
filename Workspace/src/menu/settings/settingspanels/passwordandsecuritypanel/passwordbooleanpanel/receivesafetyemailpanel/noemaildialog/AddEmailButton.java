package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.noemaildialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.settings.settingsbufferpanel.SettingsBufferPanel;
import menu.settings.settingspanels.userpanel.extras.EmailPanel;

public class AddEmailButton extends JButton
{
	SettingsBufferPanel settingsBufferPanel;
	
	public AddEmailButton(SettingsBufferPanel settingsBufferPanel)
	{
		super();
		this.settingsBufferPanel = settingsBufferPanel;
		initialize();
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	public void createBtn()
	{
		setText("Add Email");
		setFocusPainted(false);
	}
	
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
