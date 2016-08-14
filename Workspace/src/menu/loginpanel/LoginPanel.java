package menu.loginpanel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class LoginPanel extends JPanel
{
	BufferPanel bufferPanel;
	private LoginLabels loginLabels;
	private LoginField loginField;
	private JButton loginButton;
	
	public LoginPanel(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createPanel();
		createComponents();
		layoutComponents();
	}
	
	public void createPanel()
	{
		setOpaque(false);
	}
	
	public void createComponents()
	{
		loginLabels = new LoginLabels();
		loginField = new LoginField(bufferPanel);
		loginButton = new LoginButton(bufferPanel);
		
		loginLabels.panelLabel.setBounds(0, 25, 700,50);
		LoginField.loginField.setBounds(230,200, 240,24);
		loginField.loginLabel.setBounds(155,200, 75,25);
		loginButton.setBounds(475, 196,30,30);
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(700, 500));
		
		add(loginLabels.panelLabel);
		add(LoginField.loginField);
		add(loginButton);
		add(loginField.loginLabel);
	}
}
