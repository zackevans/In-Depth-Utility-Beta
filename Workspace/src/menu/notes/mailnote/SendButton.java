package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.mailnote.recipientviewer.RecipientViewer;
import program.util.NetworkUtil;
import program.util.email.PushEmail;
import sql.notes.NotesDataBase;

public class SendButton extends JButton
{
	BufferPanel bufferPanel;
	
	public SendButton (BufferPanel bufferPanel)
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
		setText("Send");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(RecipientViewer.listOfEmails.size() !=0 && FromField.textField.getText().length() !=0 && SelectNote.comboBox.getSelectedIndex() !=0)
				{
					NotesDataBase notesdb = new NotesDataBase();
					ArrayList <String> list = notesdb.getListNamesData();
					int index = SelectNote.comboBox.getSelectedIndex();
					String[] to = RecipientViewer.listOfEmails.toArray(new String[0]);
					String fromField = FromField.textField.getText();
					String subject = list.get(index-1);
					String body = notesdb.getNotesBody(notesdb.getID(SelectNote.comboBox.getSelectedIndex()));
					String additionalComments = AdditionalComments.textArea.getText();
					String finalBody = body + "\n\n" + additionalComments + "\n\n" + "-" + fromField;
					
					if(NetworkUtil.isNetworkAvailable()) // if network is available
					{
						bufferPanel.showPanel("NOTES");
						
						Thread sendMail = new Thread(new PushEmail(to, subject, finalBody)); // create new thred to send email	
						sendMail.start(); // start execution of the thred
					}
					
					else
					{
						
					}
				}
				
				else
				{
					ErrorPanel errorPanel = new ErrorPanel();
					
					errorPanel.hideAllErrors();
					
					if(RecipientViewer.listOfEmails.size() == 0)
					{
						ErrorPanel.toFieldError.setVisible(true);
					}
					
					if (FromField.textField.getText().length() == 0)
					{
						ErrorPanel.fromFieldError.setVisible(true);
					}
					
					if(SelectNote.comboBox.getSelectedIndex() == 0)
					{
						ErrorPanel.seclectNoteError.setVisible(true);
					}
				}
			}
		});
	}
}



