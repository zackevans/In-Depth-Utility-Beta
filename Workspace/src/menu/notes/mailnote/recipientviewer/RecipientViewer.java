package menu.notes.mailnote.recipientviewer;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Class:  RecipientViewer 
 * @author ZackEvans
 *
 * This class contains the panel that holds all of the recipient Viewer components
 */

public class RecipientViewer 
{
	public static JPanel recipientViewerPanel  = new JPanel(); // create the panel to hold all of the components
	public static JLabel noRecipientsWarrning = new JLabel("No Recipients Exist"); // create a label to show when there is nothing in the pamel
	public static ArrayList<String> listOfEmails = new ArrayList<String>(); // create array list to hold all of the recipients emails
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to create the panel
	 */
	
	public void initialize()
	{
		createPanel();
	}
	
	/**
	 * Function: createPanel()
	 * @author ZackEvans
	 * 
	 * This method creates the components to be added to the panel and sets a custom border.
	 * By default this panel is hidden.
	 */
	
	public void createPanel()
	{
		createComponents(); // create components first before launching the app
		
		recipientViewerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black)); // border for top and bottom only
		recipientViewerPanel.setBackground(Color.white); // set the color of the panel to white
		
		recipientViewerPanel.setVisible(false); // hide the panel
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This function creates the components to be added to the panel
	 */
	
	public void createComponents()
	{
		RecipientHolder recipientHolder = new RecipientHolder();
		recipientHolder.initialize(); 
		recipientHolder.setBounds(0,1,700,34); // set at 1 so border is not overlapped (it looks like shit if it does)
		
		// set font,location,size, and position of the warning
		noRecipientsWarrning.setFont(new Font("Helvetica Neue",Font.PLAIN,17));
		noRecipientsWarrning.setBounds(0,0,700,34);
		noRecipientsWarrning.setHorizontalAlignment(SwingConstants.CENTER);
		
		recipientViewerPanel.setLayout(null); // remove layout manager for panel
		
		// add components to the panel 
		recipientViewerPanel.add(noRecipientsWarrning);
		recipientViewerPanel.add(recipientHolder);
	}	
	
	/**
	 * Function: getEmailIndex(String email)
	 * @author ZackEvans
	 * @param email of recipient
	 * @return the index of the email in the arraylist
	 * 
	 * This method returns the position of the email in the arraylist.
	 * TODO make this more efficient. add hash to make lookup faster
	 */
	
	public static int getEmailIndex(String email)
	{
		int returnVal = -1;
		
		for(int i = 0; i < listOfEmails.size(); i++) // look throuh all items in arraylist
		{
			if(listOfEmails.get(i).contains(email)) // if items match
			{
				returnVal = i; // return value
			}
		}
		
		return returnVal;
	}
}
