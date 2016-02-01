package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import launch.app.LaunchApp;
import sql.notes.NotesDataBase;

public class DeleteButton extends JButton
{
	private Notes notes;
	private NotesList notesList = new NotesList(notes);
	private SearchBar searchBar = new SearchBar(notes);
	private NotesDataBase notesdb = new NotesDataBase();
	private LaunchApp launchApp =  new LaunchApp();
	
	public DeleteButton (Notes notes)
	{
		super();
		this.notes = notes;
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
				
				if (searchBar.doesTextExist() == true)
				{
					
				}
				
				else
				{
					int ID = notesList.getLastID();
					 
					if (ID != -1)
					{
						notesdb.pushWholeListUpOne();
						notesdb.deleteNote(ID);
						notesList.loadData();
						notesList.setLastID(-1);
					}
					
					else
					{
						JOptionPane.showMessageDialog(launchApp.getFrame(),
													  "No Item Was Seclected",
													  "Delete Warning",
													  JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}
}
