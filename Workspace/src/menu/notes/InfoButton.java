package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class InfoButton extends JButton
{
	private BufferPanel bufferPanel;
	
	public InfoButton (BufferPanel bufferPanel)
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
		URL url = InfoButton.class.getResource("/Button_Images/Notes/info.png");
		ImageIcon icon = new ImageIcon(url);
		setIcon(icon);	
	}

	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("InfoBtn");
			}
		});
	}
}
