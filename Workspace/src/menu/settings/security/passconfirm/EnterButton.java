package menu.settings.security.passconfirm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import sql.system.settings.SystemDatabase;

public class EnterButton extends JButton 
{
	BufferPanel bufferPanel;
	private PasswordConfirm passConfirm = new PasswordConfirm(bufferPanel);
	private SystemDatabase systemdb = new SystemDatabase();
	
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
				System.out.println("EnterBtn");
				
				boolean matchPass = passConfirm.comparePass();
				
				if (matchPass == true)
				{
					boolean row = systemdb.checkRow();
					
					if(row == true)
					{
						
					}
					
					else
					{
						
					}
					
					// TODO tip move to first launch
					passConfirm.hideWarning();
					passConfirm.clearTxtFields();
					bufferPanel.showPanel("SECURITY_SETTINGS");
					
				}
				
				else
				{
					passConfirm.showWarning();
					passConfirm.clearTxtFields();
				}
				
			}
		});
	}

}
