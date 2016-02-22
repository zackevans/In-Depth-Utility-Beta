package menu.notes.mailNotePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class CancelButton extends JButton
{
	BufferPanel bufferPanel;

	public CancelButton (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		initialize();
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	public void createBtn()
	{
		setText("Cancel");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Cancel button");
				
				bufferPanel.showPanel("NOTES");
			}
		});
	}
}
