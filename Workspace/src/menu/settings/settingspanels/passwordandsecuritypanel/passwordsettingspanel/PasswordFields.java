package menu.settings.settingspanels.passwordandsecuritypanel.passwordsettingspanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import program.textfield.PasswordTextFieldShell;

/**
 * Class: PasswordFields
 * @author ZackEvans
 * 
 * This class holds all of the textfields needed to set change and remove a password.
 */

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
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls functions to create the panel
	 */
	
	public void initialize()
	{
		hideAllFields();
		addListeners();
	}
	
	/**
	 * Function: hideAllFields()
	 * 
	 * This function hides all of the textfields on the panel
	 */
	
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
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds action listeners to all the fields on the panel
	 */
	
	public void addListeners()
	{
		createPasswordFieldListeners();
		createRetypePasswordFieldListener();
		
		createCurrentPasswordFieldListener();
		createNewPasswordFieldListener();
		createNewConfirmPasswordFieldListener();
		
		createRemovePasswordFieldListener();
	}

	
	/**
	 * Function: createPasswordFieldListeners()
	 * 
	 * This function adds an action listener to the passwordField.
	 * When the enter key is pressed it make the user move on to the next password field (retype field).
	 */
	
	public void createPasswordFieldListeners()
	{
		passwordField.addKeyListener(new KeyListener() 
		{
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					retypeField.requestFocusInWindow();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	/**
	 * Function: createRetypePasswordFieldListener()
	 * 
	 * This function adds an action listener to the retype field.
	 * When the enter button is clicked it updates the password change or shows errors.
	 */
	
	public void createRetypePasswordFieldListener()
	{
		retypeField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					SaveButton.saveButtonAction();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});	
	}
	
	/**
	 * Function: createCurrentPasswordFieldListener()
	 * 
	 * This function adds an action listener the field.
	 * When the enter button is clicked it moves the focus to the text textfield.
	 */
	
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
	
	/**
	 * Function: createNewPasswordFieldListener()
	 * 
	 * This function adds an action listener to the field.
	 * When the enter button is clicked it moves the focus to the text textfield.
	 */
	
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
	
	/**
	 * Function: createNewConfirmPasswordFieldListener()
	 * 
	 * This function adds an action listener to the field.
	 * When the enter button is clicked it updates the password change or shows errors.
	 */
	
	public void createNewConfirmPasswordFieldListener()
	{
		confirmPasswordField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					SaveButton.saveButtonAction();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	/**
	 * Function: createRemovePasswordFieldListener()
	 * 
	 * This function adds an action listener to the field.
	 * When the enter button is clicked it updates the password change or shows errors.
	 */
	
	public void createRemovePasswordFieldListener()
	{
		removePasswordField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					SaveButton.saveButtonAction();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
}
