package statusbar.topbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class LockButton extends JButton
{
	BufferPanel bufferPanel;
	
	public LockButton (BufferPanel bufferPanel)
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
		setFocusable(false);
		setBorderPainted(false);
		setIcon(new ImageIcon("Images/Button_Images/TopBar/Locked.png"));
		setPressedIcon(new ImageIcon("Images/Button_Images/TopBar/LockedClicked.png"));
		validate();	
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				System.out.println("Lock Btn Clicked");
				bufferPanel.showPanel("LOGIN_PANEL");
			}
		});
	}
	
	
	
	

}
