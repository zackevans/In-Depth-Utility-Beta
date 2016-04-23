package menu.notes.samenamedialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;

public class CancelButton extends JButton
{
	BufferPanel bufferPanel;
	
	public CancelButton(BufferPanel bufferPanel)
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
		setFocusable(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				SameNameDialog sameNameDialog = new SameNameDialog(bufferPanel);
				
				sameNameDialog.sameNameDialogFrame.setVisible(false);
			}
		});
	}
}
