package menu.notes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;
import sql.notes.NotesDataBase;

public class DeleteButton extends JButton
{
	private BufferPanel bufferPanel;
	private NotesList notesList = new NotesList(bufferPanel);
	private NotesDataBase notesdb = new NotesDataBase();
	//private LaunchApp launchApp = new LaunchApp();
	//private DisplayNotes displayNotes = new DisplayNotes(bufferPanel);
	
	public DeleteButton (BufferPanel bufferPanel)
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
		setIcon(new ImageIcon("Images/Button_Images/Notes/Delete.png"));
		validate();
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Delete Button");
				int lastIndex = notesList.getLastIndex() +1;
				int ID = notesdb.getID(lastIndex);
				
				System.out.println(ID);
				
				if (lastIndex != -1)
				{
					notesdb.pushWholeListUpOne();
					notesdb.deleteNote(ID);
				}
				
				else
				{
					System.out.println("There was not a last index ");
				}

			}
		});
	}

}
