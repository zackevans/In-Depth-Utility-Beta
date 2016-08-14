package statusbar.addons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class BufferPanelBackButton
{
	public static JButton backButton = new JButton();
	BufferPanel bufferPanel;
	
	public BufferPanelBackButton(BufferPanel bufferPanel)
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
		URL backArrowURL = BufferPanelBackButton.class.getResource("/Button_Images/TopBar/BackButton.png"); // Create URL on the image
		URL backArrowPressedURL = BufferPanelBackButton.class.getResource("/Button_Images/TopBar/BackButtonPressed.png"); // Create URL on the image
		ImageIcon backArrowIcon = new ImageIcon(backArrowURL); // create a Image icon from the URL
		ImageIcon backArrowPressedIcon = new ImageIcon(backArrowPressedURL);
		backButton.setIcon(backArrowIcon); // set button iamge
		backButton.setPressedIcon(backArrowPressedIcon);
		
		backButton.setVisible(false);
		
		//setHorizontalAlignment(JButton.LEFT);
		//setHorizontalTextPosition(JButton.CENTER);
		//..setVerticalTextPosition(JButton.LEFT);
		backButton.setBorder(null); // get rid of the border
		backButton.setFocusable(false); // make the button not have focus
		backButton.setFocusPainted(false); // remove annoying blue border when clicked
		// set the button text in the center
	}
	
	public void addListeners()
	{
		backButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				bufferPanel.showPanel(BufferPanel.lastPanel);
			}
		});
	}	
}
