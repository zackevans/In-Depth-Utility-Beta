package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import program.textfield.PasswordTextFieldShell;

public class PasswordFields 
{
	// text fields for create password settings
	public static JTextField passwordField = new PasswordTextFieldShell();
	public static JLabel passwordFieldLabel = new JLabel("Password: ");
	
	public static JTextField retypeField = new PasswordTextFieldShell();
	public static JLabel retypeFieldLabel = new JLabel("Re-Type Password: ");
	
	// text fields for update password settings
	public static JTextField currentPasswordField = new PasswordTextFieldShell();
	public static JLabel currentPasswordLabel = new JLabel("Current Password: ");
	
	public static JTextField newPasswordField = new PasswordTextFieldShell();
	public static JLabel newPasswordLabel = new JLabel("New Password: ");
	
	public static JTextField confirmPasswordField = new PasswordTextFieldShell();
	public static JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
	
	//textfields for remove password settings
	public static JTextField removePasswordField = new PasswordTextFieldShell();
	public static JLabel removePasswordLabel = new JLabel("Password:" );
	
	public PasswordFields()
	{
		
	}
	
	public void initialize()
	{
		createTextFields();
		addListeners();
	}
	
	public void createTextFields()
	{
		hideAllFields();
	}
	
	public void hideAllFields()
	{
		passwordField.setVisible(false);
		passwordFieldLabel.setVisible(false);
		
		retypeField.setVisible(false);
		retypeFieldLabel.setVisible(false);
		
		currentPasswordField.setVisible(false);
		currentPasswordLabel.setVisible(false);
		
		newPasswordField.setVisible(false);
		newPasswordLabel.setVisible(false);
		
		confirmPasswordField.setVisible(false);
		confirmPasswordLabel.setVisible(false);
		
		removePasswordField.setVisible(false);
		removePasswordLabel.setVisible(false);
	}
	
	public void addListeners()
	{
		createPasswordFieldListeners();
		createRetypePasswordFieldListener();
		
		createCurrentPasswordFieldListener();
		createNewPasswordFieldListener();
		createNewConfirmPasswordFieldListener();
		
		createRemovePasswordFieldListener();
	}

	
	public void createPasswordFieldListeners()
	{
		passwordField.addKeyListener(new KeyListener() 
		{
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					//PasswordFields.retypeField.selectAll();
					PasswordFields.retypeField.requestFocusInWindow();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	public void createRetypePasswordFieldListener()
	{
		retypeField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					SaveButton saveButton = new SaveButton();
					saveButton.saveButtonAction();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});	
	}
	
	public void createCurrentPasswordFieldListener()
	{
		currentPasswordField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					newPasswordField.requestFocusInWindow();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	
	public void createNewPasswordFieldListener()
	{
		newPasswordField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					confirmPasswordField.requestFocusInWindow();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	public void createNewConfirmPasswordFieldListener()
	{
		confirmPasswordField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					SaveButton saveButton = new SaveButton();
					saveButton.saveButtonAction();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
				
		});
	}
	
	public void createRemovePasswordFieldListener()
	{
		removePasswordField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					SaveButton saveButton = new SaveButton();
					saveButton.saveButtonAction();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
				
		});
	}
}
