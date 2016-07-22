package menu.notes.exportnote.fileexistsdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CancelButton extends JButton
{
	
	
	public CancelButton()
	{	
		super();
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
		setFocusable(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				FileExistsDialog.sameNameDialogFrame.setVisible(false);
			}
		});
	}
	

}
