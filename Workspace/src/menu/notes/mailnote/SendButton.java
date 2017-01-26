package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.mailnote.recipientviewer.RecipientViewer;
import program.util.email.PushEmail;
import sql.notes.NotesDataBase;

/**
 * Class: SendButton
 * @author ZackEvans
 *
 * This class is a button that when clicked sends note to recpients.
 */

public class SendButton extends JButton
{
	BufferPanel bufferPanel;
	public static String[] to;
	public static String fromField;
	public static String finalBody;
	
	/**
	 * Constructor: SendButton (BufferPanel bufferPanel)
	 * 
	 * This constructor calls panel hierarchy and inherits the bufferPanel object
	 */
	
	public SendButton (BufferPanel bufferPanel)
	{
		super(); // call hierarchy
		this.bufferPanel = bufferPanel; // inherits bufferPanel
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This method call functions to create the button
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * This function sets the button text and removes the focus border.
	 */
	
	public void createButton()
	{
		setText("Send"); // set button text to send
		setFocusPainted(false); // remove blue focus border
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This method adds a action listener to the button.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add action listener to button
		{	
			@Override
			public void actionPerformed(ActionEvent e) // override action listener with my logic
			{
				if(RecipientViewer.listOfEmails.size() !=0 && FromField.textField.getText().length() !=0 && SelectNote.comboBox.getSelectedIndex() !=0) // if all the fields are not empty
				{
					NotesDataBase notesdb = new NotesDataBase();
					ArrayList <String> namesList = notesdb.getListNamesData(); // get array with all note names
					int index = SelectNote.comboBox.getSelectedIndex(); // get selected index in the combo box
					String subject = namesList.get(index-1); // subtract 1 bc first item in combo box is "Select Note"
					String body = notesdb.getNotesBody(notesdb.getID(SelectNote.comboBox.getSelectedIndex())); // get body from db based on selected index
					String additionalComments = AdditionalComments.textArea.getText(); // get comments user added in text area
					
					to = RecipientViewer.listOfEmails.toArray(new String[0]); // convert array list to string array
					fromField = FromField.textField.getText(); // get text from the FromField
					finalBody = body + "\n\n" + additionalComments + "\n\n" + "-" + fromField; // add all fields together to create email body
					
					PushEmail.sendEmail(to, subject, body);
					bufferPanel.showPanel(BufferPanel.lastPanel);
				}
				
				else // if a field is empty
				{			
					ErrorPanel.hideAllErrors(); // clear all errors
					ErrorPanel.checkErrors(); // check if there are errors
				}
			}
		});
	}
}