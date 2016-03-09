package menu.notes.addnotedialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CancelBtn extends JButton
{
	NoteDialog noteDialog;
	
	public CancelBtn (NoteDialog dialog)
	{
		this.noteDialog = dialog;
		setText("Cancel");
		addListeners();
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Cancel Button");
				noteDialog.showDialog(false);
			}
		});
	}
	

}
