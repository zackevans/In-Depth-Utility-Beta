package menu.notes.exportnotepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChooseLocationButton extends JButton
{
	public ChooseLocationButton ()
	{
		super();
	}
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		setText("Choose ...");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("Choose Button");
				
			}
		});
	}
}
