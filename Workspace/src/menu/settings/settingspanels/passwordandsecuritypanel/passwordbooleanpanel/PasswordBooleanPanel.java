package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel.AttemptsCombobox;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.eraseappdatapanel.EraseAppDataPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.LogPasswordAttemptsPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.ReceiveSafteyEmailPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.SafteyEmailCountCombobox;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings.RequirePasswordSettingsPanel;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.requirepasswordsettings.TimeCombobox;
import menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.shownotificationsbuttonpanel.ShowNotificationsButtonPanel;

public class PasswordBooleanPanel extends JPanel
{
	public PasswordBooleanPanel ()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createPanel();
		interstingPanel();
	}
	
	public void createPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(false);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.black,Color.gray), "Security Settings"));
	}
	
	public void interstingPanel()
	{
		JPanel requirePasswordTimeoutPanel = new RequirePasswordSettingsPanel();
		JPanel logPasswordAttemptsPanel = new LogPasswordAttemptsPanel();
		JPanel reciveSafteyEmailPanel = new ReceiveSafteyEmailPanel();
		JPanel showNotificationsButtonPanel = new ShowNotificationsButtonPanel();
		JPanel eraseAppDataPanel = new EraseAppDataPanel();
		
		add(requirePasswordTimeoutPanel);
		add(logPasswordAttemptsPanel);
		add(reciveSafteyEmailPanel);
		add(showNotificationsButtonPanel);
		add(eraseAppDataPanel);
	}
	
	public static void resetPanel()
	{
		RequirePasswordSettingsPanel.updateCheckbox(); 
		EraseAppDataPanel.updateCheckbox();
		LogPasswordAttemptsPanel.updateCheckbox();
		ReceiveSafteyEmailPanel.updateCheckbox();
		ShowNotificationsButtonPanel.updateCheckBox();
		TimeCombobox.updateCombobox();
		SafteyEmailCountCombobox.updateCombobox();
		AttemptsCombobox.updateCombobox();
	}
	
}
