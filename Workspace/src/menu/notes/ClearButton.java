package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class ClearButton extends JButton
{
	BufferPanel bufferPanel;
	private SearchBar searchBar;
	
	public ClearButton (BufferPanel bufferPanel,SearchBar searchBar)
	{
		super();
		this.bufferPanel = bufferPanel;
		this.searchBar = searchBar;
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	public void createBtn()
	{
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setIcon(new ImageIcon("Images/Button_Images/Notes/Clear.png"));
		setPressedIcon(new ImageIcon("Images/Button_Images/TopBar/PressedClear.png"));
		validate();
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Clear Button");
				
				searchBar.setText("");
				searchBar.requestFocusInWindow();
			}
		});
	}
}
