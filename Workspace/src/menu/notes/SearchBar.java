package menu.notes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.textfield.TextFieldShell;

/**
 * Class: SearchBar 
 * @author ZackEvans
 *
 * This class is a text field that searches through notes
 */

public class SearchBar 
{
	public static JTextField textField = new TextFieldShell(); // create textfield to use as searchbar
	public JLabel searchLabel = new JLabel("Search"); // create label to go over textfield
	
	/**
	 * Function: initialize()
	 * 
	 * Calls methods to create searchbar components
	 */
	
	public void initialize()
    {
		createSearchbar();
    	addListeners();
    }
	
	/**
	 * Function: createSearchbar()
	 * 
	 * customize label
	 */
	
	public void createSearchbar()
	{
		searchLabel.setOpaque(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * Function adds focous and action listener to the textfield
	 */
	
	public void addListeners()
	{
		// add a document listener to the textField
		textField.getDocument().addDocumentListener(new DocumentListener() 
		{	
			@Override
			public void insertUpdate(DocumentEvent e)
			{
				updateNotesList(); // update the data in the notes list when the text in the searchbar changes
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				updateNotesList(); // update the data in the notes list when the text in the searchbar changes
			}
		
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		// Add a focus listener to the textfield
		textField.addFocusListener(new FocusListener() 
		{	
			@Override
			public void focusLost(FocusEvent e) // when componet is clicked off of 
			{
				if (doesTextExist() == false)  // if there is no text in the field
				{
					searchLabel.setVisible(true); // show the search label
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) // when the textfield is clicked on
			{
				searchLabel.setVisible(false);	// hide the search label
			}
		});
	}
	
	/**
	 * Function: updateNotesList()
	 * 
	 * This function updates the Jlist that displays the note names. 
	 */
	
	public void updateNotesList()
	{
		NotesList notesList = new NotesList();
		DisplayNotes displayNotes = new DisplayNotes();
		
		notesList.loadSearchData(textField.getText()); // load load search data into the list
		displayNotes.clearDisplay(); // reset display
	}
	
	/**
	 * Function: doesTextExist()
	 * @return if there is text in the text field
	 * 
	 * This function return true or false based on if there is text in the textfield
	 */
	
	public boolean doesTextExist()
    {
    	String text = textField.getText(); // get text from textfield
    	
    	if (text.length() > 0) // check to see if text exists
    	{
    		return true; // if it does return true
    	}
    	
    	else return false; // if there is not text return false
    }
}
