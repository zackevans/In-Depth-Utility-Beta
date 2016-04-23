package menu.notes.samenamedialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.exportnotepanel.FileNameField;

public class RenameButton extends JButton
{
	BufferPanel bufferPanel;
	
	public RenameButton(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	public void createBtn()
	{
		setText("Rename");
		setFocusable(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SameNameDialog sameNameDialog = new SameNameDialog(bufferPanel);
				FileNameField fileNameField = new FileNameField();
				
				fileNameField.fileNameTextField.requestFocus();
				fileNameField.fileNameTextField.selectAll();
				sameNameDialog.sameNameDialogFrame.setVisible(false);
			}
		});
	}
}
