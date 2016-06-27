package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.notes.mailnote.recipientviewer.RecipientHolder;
import menu.notes.mailnote.recipientviewer.RecipientViewer;
import program.util.EmailUtil;

public class AddRecipientButton extends JButton
{
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		URL recipentDecal = RecipientButton.class.getResource("/Button_Images/Notes/MailNote/AddRecipient.png");
		ImageIcon decalImage = new ImageIcon(recipentDecal);
		setIcon(decalImage);
		
		setBorder(null);
		setFocusable(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				addEmail();
			}	
		});
	}
	
	public void addEmail()
	{
		if (EmailUtil.validateEmailAddress(ToField.textField.getText())) // if there is a valid email in the textfield 
		{		
			if(! (RecipientViewer.listOfEmails.contains(ToField.textField.getText()))) // if the email has not already been added
			{
				RecipientViewer.listOfEmails.add(ToField.textField.getText());
				RecipientButton.updateButtonNumber();
				RecipientViewer.noRecipientsWarrning.setVisible(false);
				ErrorPanel.toFieldError.setVisible(false);
				RecipientHolder.createNewPanel();
				ToField.textField.setText("");
				
			}
			
			else // if the email still exists
			{
				ToField.textField.setText(""); // clear the text
				ErrorPanel.toFieldError.setVisible(false);
			}
		}
		
		else
		{
			ErrorPanel.toFieldError.setVisible(true);
		}
	}
}
