package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

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
		URL clearBtnUrl = ClearButton.class.getResource("/Button_Images/Notes/Clear.png");
		URL pressedClearBtnUrl = ClearButton.class.getResource("/Button_Images/Notes/ClearPressed.png");
		
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		setIcon(new ImageIcon(clearBtnUrl));
		setPressedIcon(new ImageIcon(pressedClearBtnUrl));
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Clear Button");
				
				searchBar.textField.setText("");
				searchBar.textField.requestFocusInWindow();
			}
		});
	}
}
