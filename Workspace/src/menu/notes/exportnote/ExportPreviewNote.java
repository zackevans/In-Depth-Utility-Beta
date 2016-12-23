package menu.notes.exportnote;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import sql.notes.NotesDataBase;

/**
 * Class: ExportPreviewNote
 * @author ZackEvans
 *
 * This class is a scroll pane that holds a text area that shows a preview of the notes body to the user.
 */

public class ExportPreviewNote extends JScrollPane
{
	public static JTextArea textArea = new JTextArea(); // create text area to sit in scroll pane
	
	/**
	 * Constructor: ExportPreviewNote()
	 * 
	 * This function calls the scroll pane hierarchy.
	 */
	
	public ExportPreviewNote()
	{
		super(); 
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls a method to setup the scrollpane
	 */
	
	public void initialize()
	{
		createComponents();
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function creates the scrollpane
	 */
	
	public void createComponents()
	{
		TitledBorder titleBorder = new TitledBorder("Preview"); // create a title border
		textArea.setBorder(titleBorder); // set the textareas border.
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK)); // creates a black border all around the scroll panel
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Don't let the panel scroll right to left
		// make the text return for each word.
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false); // make the user not be able to edit the text in the panel
		
		setViewportView(textArea); // set the panel in the scrollpanel
	}
	
	/**
	 * Function: displayNote(int index)
	 * @param index
	 * 
	 * This function displays a note in the textarea.
	 */
	
	public void displayNote(int index)
	{
		NotesDataBase notesdb = new NotesDataBase();
		
		if (index != 0) // if the index of the note is not 0
		{
			textArea.setText(notesdb.getNotesBody(notesdb.getID(index))); // get ID from index instead of list position because combo box has a placeholder in index 0
			textArea.setCaretPosition(0); // set the caret to the top of the textarea.
		}
		
		else // if the index is 0
		{
			textArea.setText(""); // clear the text area
		}
	}
}
