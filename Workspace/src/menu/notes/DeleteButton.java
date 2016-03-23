package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

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
	private DisplayNotes displayNotes = new DisplayNotes(notes);
	private NotesListData notesData = new NotesListData();
	
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
		URL iconURL = DeleteButton.class.getResource("/Button_Images/Notes/NotesPanel/Delete.png");
		ImageIcon img = new ImageIcon(iconURL);
		setIcon(img);
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
					int ID = notesList.getLastID();
					
					if (ID != -1)
					{
						notesdb.pushWholeListUpOne();
						notesdb.deleteNote(ID);
						notesData.removeNameElement(0);
						notesList.setLastID(-1);
						displayNotes.clearDisplay();
						notesList.loadRawData();
					}
					
					else
					{
						JOptionPane.showMessageDialog(launchApp.getFrame(),
								  "No Item Was Seclected",
								  "Delete Warning",
								  JOptionPane.WARNING_MESSAGE);
					}
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
						displayNotes.clearDisplay();
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
