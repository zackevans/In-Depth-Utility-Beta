package menu.notes.notemailerrordialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;

public class DiscardButton extends JButton
{
	BufferPanel bufferPanel;
	Notes notes = new Notes(bufferPanel);
	MailNoteErrorDialog mailNoteErrorDialog = new MailNoteErrorDialog(bufferPanel);
	
	public DiscardButton(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		setText("Discard");
		setForeground(Color.RED);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("Discard Button");
				mailNoteErrorDialog.errorFrame.setVisible(false); 
				notes.infoButton.requestFocusInWindow(); // Set focous on button so it is not the searchBar
				bufferPanel.showPanel("NOTES");
			}
		});
	}
}
