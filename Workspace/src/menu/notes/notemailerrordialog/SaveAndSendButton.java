package menu.notes.notemailerrordialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;
import sql.saveandsend.SaveAndSendDataBase;

public class SaveAndSendButton extends JButton
{
	BufferPanel bufferPanel;
	Notes notes = new Notes(bufferPanel);
	private MailNoteErrorDialog mailNoteErrorDialog = new MailNoteErrorDialog(bufferPanel);
	SaveAndSendDataBase saveAndSendDatabase = new SaveAndSendDataBase();
	
	public SaveAndSendButton(BufferPanel bufferPanel)
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
		setText("Save & Send");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("Save And Send button");
				
				if(mailNoteErrorDialog.checkBox.isSelected())
				{
					
				}
				
				else
				{	
					saveAndSendDatabase.createSavedEmail(MailNoteErrorDialog.to, MailNoteErrorDialog.subject ,MailNoteErrorDialog.body);
					mailNoteErrorDialog.errorFrame.setVisible(false);
					notes.infoButton.requestFocusInWindow();
					bufferPanel.showPanel("NOTES");
				}
			}
		});	
	}
}
