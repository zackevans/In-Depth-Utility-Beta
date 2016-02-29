package menu.notes.mailNotePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import program.mail.SendMail;
import sql.notes.NotesDataBase;

public class SendButton extends JButton
{
	BufferPanel bufferPanel;
	private To to = new To();
	private SeclectNote seclectNote = new SeclectNote();
	private SendMail mail = new SendMail();
	private NotesDataBase notesdb = new NotesDataBase();

	public SendButton (BufferPanel bufferPanel)
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
		setText("Send");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Send button");
				int id = seclectNote.getLastID();
				
				System.out.println("ID = " + id);
				
				if (id != -1)
				{
					String s = To.textField.getText();
					String[] to = {s};
				
					String subject = seclectNote.getNoteName();
					String body = notesdb.getNotesBody(id);
					
					bufferPanel.showPanel("NOTES");
					mail.sendNoteEMail(to, subject, body);
				}
			}
		});
	}
}
