package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import program.util.StringUtil;
import sql.systemsettings.passwordsettings.PasswordSettingsDatabase;

public class PasswordSettingsPanel extends JPanel
{
	JPanel contentPanel = new JPanel();
	private static JButton createPasswordButton;
	private static JButton resetPasswordButton;
	private static JButton removePasswordButton;
	private PasswordFields passwordFields;
	private static JButton cancelButton;
	private static JButton saveButton;
	PasswordErrorPanel passwordErrorPanel;
	
	public PasswordSettingsPanel()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createPanel();
		createComponents();
		initializeComponents();
		layoutComponents();
		showMenu();
	}
	
	public void createPanel()
	{
		setOpaque(false);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.black,Color.gray), "Password Settings"));
	}
	
	public void createComponents()
	{
		createPasswordButton = new CreatePasswordButton();
		resetPasswordButton = new ResetPasswordButton();
		removePasswordButton = new RemovePasswordButton();
		passwordFields = new PasswordFields();
		cancelButton = new CancelButton();
		saveButton = new SaveButton();
		passwordErrorPanel = new PasswordErrorPanel();
		
		createPasswordButton.setBounds(15, 30, 150, 25);
		
		resetPasswordButton.setBounds(15, 30, 150, 25);
		removePasswordButton.setBounds(15, 65, 150, 25);
		
		PasswordFields.passwordField.setBounds(150, 50, 220, 22);
		PasswordFields.passwordFieldLabel.setBounds(80, 50, StringUtil.getStringWidth(PasswordFields.passwordFieldLabel.getText(), PasswordFields.passwordFieldLabel.getFont()), 22);
		
		PasswordFields.retypeField.setBounds(150, 82, 220,22);
		PasswordFields.retypeFieldLabel.setBounds(25, 82, StringUtil.getStringWidth(PasswordFields.retypeFieldLabel.getText(), PasswordFields.retypeFieldLabel.getFont()), 22);
		
		PasswordFields.currentPasswordField.setBounds(150, 50, 220, 22);
		PasswordFields.currentPasswordLabel.setBounds(30, 50, StringUtil.getStringWidth(PasswordFields.currentPasswordLabel.getText(), PasswordFields.currentPasswordLabel.getFont()), 22);
		
		PasswordFields.newPasswordField.setBounds(150, 82, 220,22);
		PasswordFields.newPasswordLabel.setBounds(49,82,StringUtil.getStringWidth(PasswordFields.newPasswordLabel.getText(), PasswordFields.newPasswordLabel.getFont()),22);
		
		PasswordFields.confirmPasswordField.setBounds(150, 112, 220,22);
		PasswordFields.confirmPasswordLabel.setBounds(25,112,StringUtil.getStringWidth(PasswordFields.confirmPasswordLabel.getText(), PasswordFields.confirmPasswordLabel.getFont()),22);
		
		PasswordFields.removePasswordField.setBounds(150, 82, 220,22);
		PasswordFields.removePasswordLabel.setBounds(80,82,StringUtil.getStringWidth(PasswordFields.removePasswordLabel.getText(), PasswordFields.removePasswordLabel.getFont()),22);
		
		cancelButton.setBounds(240, 165, 100, 25);
		saveButton.setBounds(345, 165, 100, 25);
	}
	
	public void initializeComponents()
	{
		passwordFields.initialize();
	}
	
	public void layoutComponents()
	{		
		setLayout(null); // remove layout manager from the panel 
		setPreferredSize(new Dimension(575,500)); // set the size of the panel
		setOpaque(false); // make the panel clear
		
		createContentPanel(); // create the panel to hold all of the main panel components.
		
		JLayeredPane layerPane = new JLayeredPane();
		
		// add components to the panel
		layerPane.add(contentPanel, new Integer(0),0); 
		layerPane.add(passwordErrorPanel, new Integer(1),0);
			
		
		// set the location of the panel and layers
		contentPanel.setBounds(0, 0, 575,500);
		layerPane.setBounds(0, 0,575,500);
		passwordErrorPanel.setBounds(0, 0, 575,500);
		
		add(layerPane);
	}
	
	
	public void createContentPanel()
	{
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(575,500)); // set the size of the panel
		contentPanel.setOpaque(false); // make the panel clear
		
		contentPanel.add(createPasswordButton);
		contentPanel.add(resetPasswordButton);
		contentPanel.add(removePasswordButton);
		
		contentPanel.add(PasswordFields.passwordField);
		contentPanel.add(PasswordFields.passwordFieldLabel);
		
		contentPanel.add(PasswordFields.retypeField);
		contentPanel.add(PasswordFields.retypeFieldLabel);
		
		contentPanel.add(PasswordFields.currentPasswordField);
		contentPanel.add(PasswordFields.currentPasswordLabel);
		
		contentPanel.add(PasswordFields.newPasswordField);
		contentPanel.add(PasswordFields.newPasswordLabel);
		
		contentPanel.add(PasswordFields.confirmPasswordField);
		contentPanel.add(PasswordFields.confirmPasswordLabel);
		
		contentPanel.add(PasswordFields.removePasswordField);
		contentPanel.add(PasswordFields.removePasswordLabel);
		
		contentPanel.add(cancelButton);
		contentPanel.add(saveButton);	
	}
	
	public static void clearPanel()
	{
		createPasswordButton.setVisible(false);
		resetPasswordButton.setVisible(false);
		removePasswordButton.setVisible(false);
			
		PasswordFields.passwordField.setText("");
		PasswordFields.retypeField.setText("");
		PasswordFields.currentPasswordField.setText("");
		PasswordFields.newPasswordField.setText("");
		PasswordFields.confirmPasswordField.setText("");
		
		
		PasswordFields.passwordField.setVisible(false);
		PasswordFields.passwordFieldLabel.setVisible(false);
		
		PasswordFields.retypeField.setVisible(false);
		PasswordFields.retypeFieldLabel.setVisible(false);
		
		PasswordFields.currentPasswordField.setVisible(false);
		PasswordFields.currentPasswordLabel.setVisible(false);
		
		PasswordFields.newPasswordField.setVisible(false);
		PasswordFields.newPasswordLabel.setVisible(false);
		
		PasswordFields.confirmPasswordField.setVisible(false);
		PasswordFields.confirmPasswordLabel.setVisible(false);
		
		PasswordFields.removePasswordField.setVisible(false);
		PasswordFields.removePasswordLabel.setVisible(false);
		
		cancelButton.setVisible(false);
		saveButton.setVisible(false);
		
		PasswordErrorPanel.hideAllWarnings();
	}
	
	
	public static void showMenu()
	{
		PasswordSettingsDatabase passwordSettingsDatabase = new PasswordSettingsDatabase();
		
		clearPanel();
		
		if(passwordSettingsDatabase.doesPasswordExist()) // if password exists
		{
			showUpdatePasswordMenu();
		}
		
		else // if a password is not present in the database
		{
			showCreatePasswordMenu();
		}
	}
	
	
	public static void showCreatePasswordMenu()
	{
		clearPanel();
		
		createPasswordButton.setVisible(true);
	}
	
	public static void showUpdatePasswordMenu()
	{
		clearPanel();
		
		resetPasswordButton.setVisible(true);
		removePasswordButton.setVisible(true);
	}
	
	public static void showCreatePasswordFields()
	{
		clearPanel();
		
		PasswordFields.passwordField.setText("");
		PasswordFields.retypeField.setText("");
		
		PasswordFields.passwordField.setVisible(true);
		PasswordFields.passwordFieldLabel.setVisible(true);
		
		PasswordFields.retypeField.setVisible(true);
		PasswordFields.retypeFieldLabel.setVisible(true);
		
		cancelButton.setVisible(true);
		saveButton.setVisible(true);
	}
	
	public static void showResetPasswordFields()
	{
		clearPanel();
		
		PasswordFields.currentPasswordField.setText("");
		PasswordFields.newPasswordField.setText("");
		PasswordFields.confirmPasswordField.setText("");
		
		PasswordFields.currentPasswordField.setVisible(true);
		PasswordFields.currentPasswordLabel.setVisible(true);
		
		PasswordFields.newPasswordField.setVisible(true);
		PasswordFields.newPasswordLabel.setVisible(true);
		
		PasswordFields.confirmPasswordField.setVisible(true);
		PasswordFields.confirmPasswordLabel.setVisible(true);
		
		cancelButton.setVisible(true);
		saveButton.setVisible(true);
	}
	
	public static void showRemovePasswordField()
	{
		clearPanel();
		
		PasswordFields.removePasswordField.setText("");
		
		PasswordFields.removePasswordField.setVisible(true);
		PasswordFields.removePasswordLabel.setVisible(true);
		
		cancelButton.setVisible(true);
		saveButton.setVisible(true);
	}	
}
