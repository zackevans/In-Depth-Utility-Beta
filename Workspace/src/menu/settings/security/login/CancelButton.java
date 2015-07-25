package menu.settings.security.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.settings.security.passconfirm.PasswordConfirm;
import sql.system.settings.SystemDatabase;

public class CancelButton extends JButton
{
	BufferPanel bufferPanel;
	private PasswordConfirm passConfirm = new PasswordConfirm(bufferPanel);
	private SystemDatabase systemdb = new SystemDatabase();
	
	public CancelButton (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	public void createBtn()
	{
		setText("Cancel");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				bufferPanel.showPanel("SCREEN_SAVER");
			}
		});
	}
}
