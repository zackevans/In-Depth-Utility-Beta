package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.notes.mailnote.recipientviewer.RecipientViewer;

public class RecipientButton 
{
	public static JButton button = new JButton();	
	
	public RecipientButton ()
	{
		
	}
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		URL recipentDecal = RecipientButton.class.getResource("/Button_Images/Notes/MailNote/RecipientButton.png");
		ImageIcon decalImage = new ImageIcon(recipentDecal);
		button.setIcon(decalImage);
		
		button.setText("0");
		button.setBorder(null);
		button.setFocusable(false);
		button.setFocusPainted(false);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setHorizontalTextPosition(JButton.CENTER);
	}
	
	public void addListeners()
	{
		button.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				RecipientViewer.recipientViewerPanel.setVisible(true);
			}
		});
	}
	
	public static void updateButtonNumber()
	{
		button.setText((new Integer(RecipientViewer.listOfEmails.size()).toString()));
		button.setBorder(null);
	}
}
	