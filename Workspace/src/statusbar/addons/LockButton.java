package statusbar.addons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class LockButton 
{
	BufferPanel bufferPanel;
	public static JButton lockButton = new JButton();

	public LockButton(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
		initialize();
	}

	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		URL lockButtonURL = LockButton.class.getResource("/Button_Images/TopBar/LockButton.png"); // Create URL on the image
		URL lockButtonPressedURL = LockButton.class.getResource("/Button_Images/TopBar/LockButtonPressed.png");
		ImageIcon lockButtonIcon = new ImageIcon(lockButtonURL); // create a Image icon from the URL
		ImageIcon lockbuttonPressedIcon = new ImageIcon(lockButtonPressedURL);
		lockButton.setIcon(lockButtonIcon); // set button iamge
		lockButton.setPressedIcon(lockbuttonPressedIcon);
		
		lockButton.setVisible(false);
		
		lockButton.setBorder(null); // get rid of the border
		lockButton.setFocusable(false); // make the button not have focus
		lockButton.setFocusPainted(false); // remove annoying blue border when clicked
	}
	
	public void addListeners()
	{
		lockButton.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				bufferPanel.showRawPanel("LOGIN_PANEL");
			}
		});
	}
}
