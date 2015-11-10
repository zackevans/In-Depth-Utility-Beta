package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class ClearButton extends JButton
{
	BufferPanel bufferPanel;
	SearchBar searchBar;
	
	public ClearButton (BufferPanel bufferPanel)
	{
		super();
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
		initializeComponents();
	}
	
	public void createBtn()
	{
		setIcon(new ImageIcon("Images/Button_Images/Notes/Clear.png"));
		setPressedIcon(new ImageIcon("Images/Button_Images/TopBar/PressedClear.png"));
		validate();
	}
	
	public void initializeComponents()
    {
    	searchBar = new SearchBar(bufferPanel);
    }
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Clear Button");
			}
		});
	}
}
