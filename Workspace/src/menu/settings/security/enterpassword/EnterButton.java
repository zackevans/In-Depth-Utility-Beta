package menu.settings.security.enterpassword;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import sql.system.settings.SystemDatabase;

public class EnterButton extends JButton
{
	BufferPanel bufferPanel;
	private EnterPassword ep = new EnterPassword(bufferPanel);
	private SystemDatabase sd = new SystemDatabase();
	private EnterPassword enterPass = new EnterPassword(bufferPanel);
	
	public EnterButton(BufferPanel bufferPanel)
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
		setText("Enter");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String pass = ep.getPassword();
				String dbPass = sd.getPassword();
				
				if (pass.equals(dbPass))
				{
					bufferPanel.showPanel("PASSWORD_CONFIRM");
				}
				
				else
				{
					enterPass.showWarning(true);
				}
			}
		});
	}
}
