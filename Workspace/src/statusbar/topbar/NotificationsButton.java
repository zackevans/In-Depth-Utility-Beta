package statusbar.topbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import menu.buffer.BufferPanel;


public class NotificationsButton extends JButton
{
	BufferPanel bufferPanel;
	
	public NotificationsButton (BufferPanel bufferPanel)
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
		URL btnURL = NotificationsButton.class.getResource("/Button_Images/TopBar/Notifications.png");
		URL pressedBtnURL = NotificationsButton.class.getResource("/Button_Images/TopBar/NotificationsClicked.png");
		ImageIcon btnImg = new ImageIcon(btnURL);
		ImageIcon btnPressedImg = new ImageIcon(pressedBtnURL);
		
		setFocusable(false);
		setBorderPainted(false);
		setIcon(btnImg);
		setPressedIcon(btnPressedImg);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				System.out.println("Notifications Btn Clicked");
			}
		});
	}
}