package panel.customnotedialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import sql.notes.NotesDataBase;

public class EnterButton extends JButton
{
	NotesDataBase notesdb = new NotesDataBase();
	
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
				
				String noteName = dialog.getFieldText();
				
				if (noteName.length() <= 0|| noteName.contains("Note MUST Contain a Name"))
				{
					dialog.showDialogError();
					
				}
				
				else
				{
					notesdb.createPersonalNote(noteName);
					dialog.showDialog(false);
				}
				
			}
		});
	}

}
