package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import launch.app.LaunchApp;
import menu.notes.addnotedialog.NoteDialog;

public class AddNoteButton extends JButton
{
	private Notes notes;
	private LaunchApp launchApp = new LaunchApp();
	private NotesList notesList = new NotesList(notes);
	private NoteDialog noteDialog = new NoteDialog(notes,notesList);
	
	public AddNoteButton (Notes notes)
	{
		super();
		this.notes = notes;
	}
	
	public void initialize ()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		URL url = AddNoteButton.class.getResource("/Button_Images/Notes/NotesPanel/Add.png");
		ImageIcon icon = new ImageIcon(url);
		setIcon(icon);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Add Button");
				noteDialog.createAndShowGUI();
			}
		});
	}
}
