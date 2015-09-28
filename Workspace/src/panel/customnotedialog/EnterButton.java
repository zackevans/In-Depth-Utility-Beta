package panel.customnotedialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EnterButton extends JButton
{
	NoteDialog dialog;
	
	public EnterButton (NoteDialog dialog)
	{
		this.dialog = dialog;
		setText("Enter");
		addListeners();
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("EnterButton");
				
			}
		});
	}

}
