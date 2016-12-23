package menu.notes.exportnote.fileexistsdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.notes.exportnote.FileNameField;

/**
 * Class: RenameButton
 * @author ZackEvans
 *
 * This class is a button that when pressed highlights the name text field and the hides the dialog frame
 */

public class RenameButton extends JButton
{
	/**
	 * Constructor: RenameButton()
	 * 
	 * This constructor calls the button heirarchy and calls a method to setup the button.
	 */
	
	public RenameButton()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the button
	 */
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * 
	 * This function sets the text of the button and makes it so the button cannot gain focus
	 */
	
	public void createBtn()
	{
		setText("Rename");
		setFocusable(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds a action listener to the button 
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add action listener to the button
		{
			@Override
			public void actionPerformed(ActionEvent e) // override what happends when the button is pressed
			{
				FileNameField.fileNameTextField.requestFocus(); // make the text field with the filename be in focus.
				FileNameField.fileNameTextField.selectAll(); // select all the text in the textfield.
				FileExistsDialog.sameNameDialogFrame.setVisible(false); // hide the dialog
			}
		});
	}	
}
