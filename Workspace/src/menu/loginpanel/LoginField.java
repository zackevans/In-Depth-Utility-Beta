package menu.loginpanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import menu.buffer.BufferPanel;
import program.textfield.PasswordTextFieldShell;

public class LoginField 
{
	BufferPanel bufferPanel;
	public static JTextField loginField = new PasswordTextFieldShell();
	public JLabel loginLabel = new JLabel("Password: ");
	
	public LoginField(BufferPanel bufferPanel)
	{
		initialize();
		this.bufferPanel = bufferPanel;
	}
	
	
	public void initialize()
	{
		createTextField();
		addListeners();
	}
	
	public void createTextField()
	{
		
	}
	
	public void addListeners()
	{
		loginField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					LoginButton loginButton = new LoginButton(bufferPanel);
					loginButton.loginAction();
				}
				
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
	
	

}
