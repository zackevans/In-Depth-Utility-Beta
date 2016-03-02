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
	private From from = new From();
	private SeclectNote seclectNote = new SeclectNote();
	private SendMail mail = new SendMail();
	private NotesDataBase notesdb = new NotesDataBase();
	private AdditionalComments additionalNotes = new AdditionalComments();

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
				
				if (id != -1)
				{
					String[] to = {To.textField.getText()};
					String fromField = From.textField.getText();
					String subject = seclectNote.getNoteName();
					String body = notesdb.getNotesBody(id);
					String additionalComments = additionalNotes.textArea.getText();
					
					String finalBody = body + "\n\n" + additionalComments + "\n\n" + "-" + fromField;
					
					bufferPanel.showPanel("NOTES");
					
					Thread sendMail = new Thread(new PushEmail(to, subject, finalBody)); // create new thred to send email	
					sendMail.start(); // start execution of the thred
				}
			}
		});
	}
}

class PushEmail implements Runnable 
{
	private SendMail mail = new SendMail();
	String[] to;
	String subject; 
	String body;
	
	public PushEmail(String[] to, String subject, String body)
	{
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	@Override
	public void run() 
	{
		mail.sendNoteEMail(to, subject, body);
	}
}