package menu.notes.mailnotepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;
import menu.notes.notemailerrordialog.MailNoteErrorDialog;
import program.mail.SendMail;
import program.util.NetworkUtil;
import sql.notes.NotesDataBase;
import sql.saveandsend.SaveAndSendDataBase;
import sql.saveandsend.SaveAndSendSettingsDataBase;

public class SendButton extends JButton
{
	BufferPanel bufferPanel;
	private To to;
	private From from;
	private SelectNote selectNote;
	private SendMail mail;
	private Notes notes;
	private NotesDataBase notesdb;
	private AdditionalComments additionalNotes;
	private ErrorPanel errorPanel;
	private SaveAndSendDataBase saveAndSendDataBase;
	private SaveAndSendSettingsDataBase saveAndSendSettingsDataBase;
	private MailNoteErrorDialog mailNoteErrorDialog;
	private NetworkUtil networkUtil;

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
		notes = new Notes(bufferPanel);
		selectNote = new SelectNote();
		notesdb = new NotesDataBase();
		additionalNotes = new AdditionalComments();
		errorPanel = new ErrorPanel();
		saveAndSendDataBase = new SaveAndSendDataBase();
		saveAndSendSettingsDataBase = new SaveAndSendSettingsDataBase();
		mailNoteErrorDialog = new MailNoteErrorDialog(bufferPanel);
		networkUtil = new NetworkUtil();
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
						
				if(to.textField.getText().length() != 0 && from.textField.getText().length() !=0 && selectNote.comboBox.getSelectedIndex() != 0)
				{
					if (networkUtil.isNetworkAvailable())
					{
						int id = selectNote.getLastID();
						
						if (id != -1)
						{
							String[] to = {To.textField.getText()};
							String fromField = From.textField.getText();
							String subject = selectNote.getNoteName();
							
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
						int id = selectNote.getLastID();
						
						String[] to = {To.textField.getText()};
						String fromField = From.textField.getText();
						String subject = selectNote.getNoteName();
						String body = notesdb.getNotesBody(id);
						String additionalComments = additionalNotes.textArea.getText();
						
						String finalBody = body + "\n\n" + additionalComments + "\n\n" + "-" + fromField;
						
						if(saveAndSendSettingsDataBase.getNeverShow() == true)
						{
							saveAndSendDataBase.createSavedEmail(to, subject ,finalBody);
							mailNoteErrorDialog.errorFrame.setVisible(false);
							notes.infoButton.requestFocusInWindow();
							bufferPanel.showPanel("NOTES");
						}
						
						else
						{
							mailNoteErrorDialog.launchDialog(to, subject, finalBody);
						}
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
					
					if (selectNote.comboBox.getSelectedIndex() == 0)
					{
						errorPanel.seclectNoteErrror.setVisible(true);
					}
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