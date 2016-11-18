package menu.notes.addnotedialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.textfield.TextFieldShell;

/**
 * Class: DialogField
 * @author ZackEvans
 * 
 * This class holds the text field that the note name will be entered in
 */

public class NoteNameField
{
	JTextField noteNameTextField = new TextFieldShell(); // text field gets new look from the textfieldshell class
		
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function calls methods to create the textfield
	 */
	
	public void initialize()
	{
		setUpTextField();
		addListeners();
	}
	
	/**
	 * Function: setUpTextField()
	 * @author ZackEvans
	 * 
	 * This function removes the sound when the backspace is pressed on a empty textfield
	 */
	
	public void setUpTextField()
	{
		// TODO get rid of the beep when the backspace is pressed
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * Function adds listeners to the text field
	 */
	
	public void addListeners()
    {
		noteNameTextField.addKeyListener(new KeyAdapter() // add a key listener to the text field
		{
		    @Override
			public void keyPressed(KeyEvent e) 
		    {	
		    	if (e.getKeyCode() == KeyEvent.VK_ENTER) // when the enter key is clicked
		    	{
		    		AddNoteDialog.createNote(); // create a new note in the db
		    	}
		    }
		    
		    @Override
			public void keyReleased(KeyEvent e) {}
		    @Override
			public void keyTyped(KeyEvent e) {}
		});
		
		noteNameTextField.getDocument().addDocumentListener(new DocumentListener()  // add a document listener to the textfield
		{
			
			@Override
			public void removeUpdate(DocumentEvent e)  // when the backspace is pressed
			{
				if(noteNameTextField.getText().length() == 0) // if the textfield is now empty 
				{
					AddNoteDialog.warningLabel.setVisible(true); // show the error
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) // if a key is pressed
			{
				AddNoteDialog.warningLabel.setVisible(false); // hide the warning 
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
    }
}
