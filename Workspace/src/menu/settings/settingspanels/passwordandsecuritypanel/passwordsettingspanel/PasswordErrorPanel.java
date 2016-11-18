package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.notes.exportnote.ExportErrorNotePanel;
import sql.systemsettings.passwordsettings.PasswordSettingsDatabase;

public class PasswordErrorPanel extends JPanel
{
	public static JLabel newPasswordDoesentMatchErrorLabel = new JLabel();
	
	public static JLabel incorrectPasswordLabel= new JLabel();
	public static JLabel resetPasswordsDoesentMatchErrorLabel = new JLabel();

	public static JLabel removePasswordLabel = new JLabel();
	
	/**
	 * Constructor: PasswordErrorPanel ()
	 * @author ZackEvans
	 * 
	 * This constructor calls panel hierarchy and calls a method to create the button.
	 */
	
	public PasswordErrorPanel ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to set up the panel and error labels
	 */
	
	public void initialize()
	{
		createPanel();
		createComponents();
		addComponents();
	}
	
	/**
	 * Function: createPanel()
	 * @author ZackEvans
	 * 
	 * This function makes the panel clear
	 */
	
	public void createPanel()
	{
		setOpaque(false);
		setLayout(null);
	}
	
	public void createComponents()
	{
		// create URLs for images and create image icon for the warrnings.
		URL errorUrl = ExportErrorNotePanel.class.getResource("/Button_Images/Notes/NoteErrors/Error.png");
		URL warningUrl = ExportErrorNotePanel.class.getResource("/Button_Images/Notes/NoteErrors/WarningSign.png");
		ImageIcon errorIcon = new ImageIcon(errorUrl);
		ImageIcon warningIcon = new ImageIcon(warningUrl);
		
		newPasswordDoesentMatchErrorLabel.setIcon(errorIcon);
		incorrectPasswordLabel.setIcon(errorIcon);
		resetPasswordsDoesentMatchErrorLabel.setIcon(errorIcon);
		removePasswordLabel.setIcon(errorIcon);
		
		newPasswordDoesentMatchErrorLabel.setToolTipText("Passwords Don't Match");
		incorrectPasswordLabel.setToolTipText("Incorrect Password");
		resetPasswordsDoesentMatchErrorLabel.setToolTipText("Passwords Don't Match");
		removePasswordLabel.setToolTipText("Incorrect Password");
		
		newPasswordDoesentMatchErrorLabel.setBounds(375,82, 30,22);
		incorrectPasswordLabel.setBounds(375,50,30,22);
		resetPasswordsDoesentMatchErrorLabel.setBounds(375, 112, 30,22);
		removePasswordLabel.setBounds(375, 82, 30,22);
		
		hideAllWarnings();
	}
	
	public void addComponents()
	{
		add(newPasswordDoesentMatchErrorLabel);
		add(incorrectPasswordLabel);
		add(resetPasswordsDoesentMatchErrorLabel);
		add(removePasswordLabel);
	}
	
	public static void hideAllWarnings()
	{
		newPasswordDoesentMatchErrorLabel.setVisible(false);
		incorrectPasswordLabel.setVisible(false);
		resetPasswordsDoesentMatchErrorLabel.setVisible(false);
		removePasswordLabel.setVisible(false);
	}
	
	public static void checkAllResetPasswordPanelErrors()
	{
		PasswordSettingsDatabase passwordSettingsDatabase = new PasswordSettingsDatabase();
		
		if(!PasswordFields.currentPasswordField.getText().equals(passwordSettingsDatabase.getPassword())) // if the passwords dont match
		{
			incorrectPasswordLabel.setVisible(true);
		}
		
		
		if(!(PasswordFields.newPasswordField.getText().length() > 0 &&  PasswordFields.newPasswordField.getText().equals(PasswordFields.confirmPasswordField.getText())))
		{
			resetPasswordsDoesentMatchErrorLabel.setVisible(true);
		}
	}	
	
	public static void checkRemovePasswordPanelErrors()
	{
		PasswordSettingsDatabase passwordSettingsDatabase = new PasswordSettingsDatabase();
		
		if(!PasswordFields.removePasswordField.getText().equals(passwordSettingsDatabase.getPassword()))
		{
			removePasswordLabel.setVisible(true);
		}
	}
}
