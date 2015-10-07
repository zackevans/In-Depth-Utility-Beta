package panel.customnotedialog;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import sql.notes.NotesDataBase;

public class DialogField extends JTextField
{
	NotesDataBase notesdb = new NotesDataBase();
	NoteDialog dialog;
	
	public DialogField(NoteDialog dialog)
	{
		this.dialog = dialog;
		addListeners();
	}
	
	public void addListeners()
    {
    	addKeyListener(new KeyAdapter() 
		{
			public void keyReleased(KeyEvent e) {}
		    public void keyTyped(KeyEvent e) {}

		    public void keyPressed(KeyEvent e) 
		    {
		    	if (e.getKeyCode() == KeyEvent.VK_ENTER)
		    	{
		    		dialog.enterFunction();
		    	}
		    }
		});
    }
	
}