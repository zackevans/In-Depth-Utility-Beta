package menu.notes.mailnote;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import sql.notes.NotesDataBase;

/**
 * Class: PreviewNote
 * @author ZackEvans
 *
 * This class is a window where the user can preview the note they are sharing
 */

public class PreviewNote extends JScrollPane
{
	public static JTextArea textArea = new JTextArea(); // create textArea to hold preview
	
	/**
	 * Constructor: PreviewNote
	 * @author ZackEvans
	 * 
	 * This constructor calls panel hierarchy
	 */
	
	public PreviewNote()
	{
		super(); //call panel hierarchy
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to create the scroll pane.
	 */
	
	public void initialize()
	{
		createPreviewArea();
	}

	/**
	 * Function: createPreviewArea()
	 * @author ZackEvans
	 * 
	 * This function creates the text area for the preview window
	 */
	
	public void createPreviewArea()
	{
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK)); // set border of the scroll pane to black
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // turn off scrolling from left to right
		
		textArea.setBorder(new TitledBorder("Preview")); // set a title border on panel
		
		// return words if they do not fit on the screen
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textArea.setEditable(false); //  make user not able to use textarea
		
		setViewportView(textArea); // set the textarea in the scrollpane
	}
	
	/**
	 * Function: displayNote(int index)
	 * @author ZackEvans
	 * @param index
	 * 
	 * This function gets the note body from the db and shows it in the text area
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
			textArea.setText(""); // clear the textarea
		}
	}
}
