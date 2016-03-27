package menu.notes.mailnotepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.notemailerrordialog.MailNoteErrorDialog;
import program.mail.SendMail;
import sql.notes.NotesDataBase;

public class SendButton extends JButton
{
	BufferPanel bufferPanel;
	private To to;
	private From from;
	private SeclectNote seclectNote;
	private SendMail mail;
	private NotesDataBase notesdb;
	private AdditionalComments additionalNotes;
	private MailNoteErrorDialog mailNoteErrorDialog; 
	private ErrorPanel errorPanel;

	public SendButton (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		initializeComponents();
		createBtn();
		addListeners();
	}
	
	public void initializeComponents()
	{
		to = new To();
		from = new From();
		mail = new SendMail();
		seclectNote = new SeclectNote();
		notesdb = new NotesDataBase();
		additionalNotes = new AdditionalComments();
		errorPanel = new ErrorPanel();
		mailNoteErrorDialog = new MailNoteErrorDialog(bufferPanel);
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
						
				if(to.textField.getText().length() != 0 && from.textField.getText().length() !=0 && seclectNote.comboBox.getSelectedIndex() != 0)
				{
					if (isNetworkAvailable())
					{
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
					
					else
					{
						int id = seclectNote.getLastID();
						
						String[] to = {To.textField.getText()};
						String fromField = From.textField.getText();
						String subject = seclectNote.getNoteName();
						String body = notesdb.getNotesBody(id);
						String additionalComments = additionalNotes.textArea.getText();
						
						String finalBody = body + "\n\n" + additionalComments + "\n\n" + "-" + fromField;
						
						mailNoteErrorDialog.launchDialog(to,subject,finalBody);
					}
					
					errorPanel.hideAllErrors();
				}
				
				else
				{	
					errorPanel.hideAllErrors();
					
					if(to.isValidEmailAddress(to.textField.getText()) == false)
					{						
						errorPanel.toFieldError.setVisible(true);
					}
					
					if(from.textField.getText().length() == 0)
					{
						errorPanel.fromFieldError.setVisible(true);
					}
					
					if (seclectNote.comboBox.getSelectedIndex() == 0)
					{
						errorPanel.seclectNoteErrror.setVisible(true);
					}
				}
			}
		});
	}
	
	
	private static boolean isNetworkAvailable() 
	{                                                                                                                                                                                                 
	    try 
	    {                                                                                                                                                                                                                                 
	        final URL url = new URL("http://www.google.com");                                                                                                                                                                                 
	        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
	        conn.connect();                                                                                                                                                                                                                   
	        return true;                                                                                                                                                                                                                      
	    } 
	    catch (MalformedURLException e) 
	    {                                                                                                                                                                                                   
	        throw new RuntimeException(e);                                                                                                                                                                                                    
	    } 
	    catch (IOException e) 
	    {                                                                                                                                                                                                             
	        return false;                                                                                                                                                                                                                     
	    }                                                                                                                                                                                                                                     
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