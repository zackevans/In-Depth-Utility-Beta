package menu.notes.mailnote.recipientviewer;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RecipientViewer 
{
	public static JPanel recipientViewerPanel  = new JPanel();
	public static JLabel noRecipientsWarrning = new JLabel("No Recipients Exist");
	public static ArrayList<String> listOfEmails = new ArrayList<String>();
	
	public RecipientViewer ()
	{
		
	}
	
	public void initialize()
	{
		createPanel();
	}
	
	public void createPanel()
	{
		createComponents(); // create components first before launching the app
		
		recipientViewerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black)); // remove border from window
		recipientViewerPanel.setBackground(Color.white);  
		
		recipientViewerPanel.setVisible(false); 
	}
	
	public void createComponents()
	{
		RecipientHolder recipientHolder = new RecipientHolder();
		recipientHolder.initialize();
		recipientHolder.setBounds(0,1,700,34); // set at 1 so border is not overlapped
		
		noRecipientsWarrning.setFont(new Font("Helvetica Neue",Font.PLAIN,17));
		noRecipientsWarrning.setBounds(0,0,700,34);
		noRecipientsWarrning.setHorizontalAlignment(SwingConstants.CENTER);
		
		recipientViewerPanel.setLayout(null);
		recipientViewerPanel.add(noRecipientsWarrning);
		recipientViewerPanel.add(recipientHolder);
	}	
	
	public static int getEmailIndex(String email)
	{
		int returnVal = -1;
		
		for(int i = 0; i < listOfEmails.size(); i++)
		{
			if(listOfEmails.get(i).contains(email))
			{
				returnVal = i;
			}
		}
		
		return returnVal;
	}
}
