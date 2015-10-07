package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class DeleteButton extends JButton
{
	private BufferPanel bufferPanel;
	private NotesList notesList = new NotesList(bufferPanel);
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
				
			}
		});
	}

}
