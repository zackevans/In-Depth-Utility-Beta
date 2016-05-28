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
	 * Constructor: NoteNameField()
	 * @author ZackEvans
	 * 
	 * 
	 */
	
	public NoteNameField()
	{
		
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function calls methods to create the textfield
	 */
	
	public void initialize()
	{
		addListeners();
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * Function adds listeners to the textfield
	 */
	
	public void addListeners()
    {
		// add a key listener to the textfield
		noteNameTextField.addKeyListener(new KeyAdapter() 
		{
		    @Override
			public void keyPressed(KeyEvent e) 
		    {	
		    	if (e.getKeyCode() == KeyEvent.VK_ENTER) // when the enter key is clicked
		    	{
		    		AddNoteDialog addNoteDialog = new AddNoteDialog();
		    		addNoteDialog.createNote();
		    		//dialog.enterFunction();
		    	}
		    }
		    
		    @Override
			public void keyReleased(KeyEvent e) {}
		    @Override
			public void keyTyped(KeyEvent e) {}
		});
		
		noteNameTextField.getDocument().addDocumentListener(new DocumentListener() 
		{
			
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				if(noteNameTextField.getText().length() == 0)
				{
					AddNoteDialog.warningLabel.setVisible(true);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				AddNoteDialog.warningLabel.setVisible(false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				if (noteNameTextField.getText().length() ==0)
				{
					AddNoteDialog.warningLabel.setVisible(true);
				}
			}
		});
    }
}
