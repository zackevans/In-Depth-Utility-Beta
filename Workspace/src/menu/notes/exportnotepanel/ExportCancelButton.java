package menu.notes.exportnotepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class ExportCancelButton extends JButton
{
	BufferPanel bufferPanel;
	
	public ExportCancelButton(BufferPanel bufferPanel)
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
		setText("Cancel");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				bufferPanel.showPanel("NOTES");
			}
		});
	}
}