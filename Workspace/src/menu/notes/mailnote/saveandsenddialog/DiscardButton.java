package menu.notes.mailnote.saveandsenddialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;

/**
 * Class: DiscardButton
 * @author ZackEvans
 *
 * This class is a button that discards the note email that is going to be sent.
 */

public class DiscardButton extends JButton
{
	BufferPanel bufferPanel; // bufferPanel object to show the Notes panel
	
	/**
	 * Constructor: DiscardButton(BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This constructor inherits the bufferPanel object, calls button hierarchy and calls a method to setup the button.
	 */
	
	public DiscardButton(BufferPanel bufferPanel)
	{
		super(); // calls button hierarchy
		this.bufferPanel = bufferPanel; // Inherit bufferPanel
		initialize(); // call method to setup button
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
	 * This method sets the text and color of the button.
	 * This method also removes the blue selected border.
	 */
	
	public void createButton()
	{
		setText("Discard");
		setForeground(Color.RED); // set button text red
		setFocusPainted(false); // remove selected border
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This methods adds a action listener to the button
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add action listener to the button
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SaveAndSendDialog.saveAndSendDialogFrame.setVisible(false); // hide the dialog
				bufferPanel.showPanel(bufferPanel.lastPanel); // show the NOTES panel
				Notes.shareButton.requestFocusInWindow(); // make share button have focus so it it doesen't focus on the search bar.
			} 
		});
	}
}