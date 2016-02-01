package menu.notes;


import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;
import panel.customnotedialog.NoteDialog;

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
		setIcon(new ImageIcon("Images/Button_Images/Notes/Add.png"));
		validate();
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


