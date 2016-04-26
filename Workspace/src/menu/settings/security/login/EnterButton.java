package menu.settings.security.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import menu.buffer.BufferPanel;
import sql.systemsettings.SystemDatabase;

public class EnterButton extends JButton
{
	BufferPanel bufferPanel;
	Login login = new Login(bufferPanel);
	SystemDatabase systemDB = new SystemDatabase();
	
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
				System.out.println("Enter Btn clicked");
				
				if (login.getPassword().equals(systemDB.getPassword()))
				{
					bufferPanel.showPanel(login.getNextPanel());
					login.showWarning(false);
				}
				
				else
				{
					login.showWarning(true);
					login.clearField();
					login.setFocusOnField();
				}
			}
		});
	}
}
