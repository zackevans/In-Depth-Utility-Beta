package menu.settings.security.removepass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import sql.system.settings.SystemDatabase;


public class EnterButton extends JButton
{
	BufferPanel bufferPanel;
	private RemovePassword removePass = new RemovePassword(bufferPanel);
	private SystemDatabase sd = new SystemDatabase();
	
	public EnterButton (BufferPanel bufferPanel)
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
				String pass = removePass.getPassword();
				String dbPass = sd.getPassword();
				
				if (pass.equals(dbPass))
				{
					sd.updatePassExist(false);
					sd.updatePassword("");
					bufferPanel.showPanel("SECURITY_SETTINGS");
				}
				
				else
				{
					removePass.showWarning(true);
				}
			}
		});
	}

}
