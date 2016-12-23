package menu.loginpanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import menu.buffer.BufferPanel;
import program.textfield.PasswordTextFieldShell;

/**
 * Class: LoginField
 * @author ZackEvans
 *
 * This class holds the textfield the user enters their password in
 */

public class LoginField 
{
	BufferPanel bufferPanel;
	public static JTextField loginField = new PasswordTextFieldShell();
	public JLabel loginLabel = new JLabel("Password: ");
	
	/**
	 * Constructor: LoginField(BufferPanel bufferPanel)
	 * @param bufferPanel
	 * 
	 * This constructor inherits the bufferpanel object and calls a method to 
	 */
	
	public LoginField(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
		addListeners();
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function add a key listener to the text field. Every time the user hits the enter key it executes an action.
	 */
	
	public void addListeners()
	{
		loginField.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) // if the key is the enter button
				{
					LoginPanel.loginButton.loginAction();
				}	
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
}
