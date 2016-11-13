package menu.notes.mailnote.saveandsenddialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;
import menu.notes.mailnote.SendButton;
import sql.saveandsend.SaveAndSendDataBase;
import sql.saveandsend.SaveAndSendSettingsDataBase;

/**
 * Class: SaveAndSendButton
 * @author ZackEvans
 *
 * This class is a button that when clicked saves the note email in the db to be sent later.
 */

public class SaveAndSendButton extends JButton
{
	BufferPanel bufferPanel;
	
	/**
	 * Constructor: SaveAndSendButton(BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This constructor calls button hierarchy and inherits the bufferPanel object.
	 * This constructor calls a method to setup the button.
	 */
	
	public SaveAndSendButton(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to create the button
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * @author ZackEvans
	 * 
	 * This function sets the text and removes the selected border of the button
	 */
	
	public void createButton()
	{
		setText("Save & Send");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This method adds a action listener that when fired saves the note email in the data base
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SaveAndSendDataBase saveAndSendDatabase = new SaveAndSendDataBase();
				SaveAndSendSettingsDataBase saveAndSendSettingsDatabase = new SaveAndSendSettingsDataBase();
				
				if (SaveAndSendDialog.checkBox.isSelected()) // if the check box is selected
				{
					saveAndSendSettingsDatabase.updateNeverShow(true); // mark that is was slected in the db
				}
				
				saveAndSendDatabase.createSavedEmail(SendButton.to, SendButton.fromField, SendButton.finalBody); // save the note email in the databse
				
				Notes.shareButton.requestFocusInWindow(); // request focus in the window so that it doesn't default to the search bar
				bufferPanel.showPanel(bufferPanel.lastPanel); // show note panel
				SaveAndSendDialog.saveAndSendDialogFrame.setVisible(false);	 // hide dialog
			}
		});	
	}
}
