package menu.notes.notemailerrordialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;
import sql.saveandsend.SaveAndSendDataBase;
import sql.saveandsend.SaveAndSendSettingsDataBase;

public class SaveAndSendButton extends JButton
{
	BufferPanel bufferPanel;
	Notes notes = new Notes(bufferPanel);
	private MailNoteErrorDialog mailNoteErrorDialog = new MailNoteErrorDialog(bufferPanel);
	private SaveAndSendDataBase saveAndSendDatabase = new SaveAndSendDataBase();
	private SaveAndSendSettingsDataBase saveAndSendSettingsDataBase = new SaveAndSendSettingsDataBase();
	
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
					saveAndSendSettingsDataBase.updateNeverShow(true);
				}
				
				saveAndSendDatabase.createSavedEmail(MailNoteErrorDialog.to, MailNoteErrorDialog.subject ,MailNoteErrorDialog.body);
				mailNoteErrorDialog.errorFrame.setVisible(false);
				notes.infoButton.requestFocusInWindow();
				bufferPanel.showPanel("NOTES");
			}
		});	
	}
}
