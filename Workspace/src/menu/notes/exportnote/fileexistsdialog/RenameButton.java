package menu.notes.exportnote.fileexistsdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.notes.exportnote.FileNameField;

public class RenameButton extends JButton
{
	public RenameButton()
	{
		initialize();
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
				FileNameField.fileNameTextField.requestFocus();
				FileNameField.fileNameTextField.selectAll();
				FileExistsDialog.sameNameDialogFrame.setVisible(false);
			}
		});
	}	
}
