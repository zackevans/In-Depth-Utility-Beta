package menu.notes.exportnote;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import sql.notes.NotesDataBase;

public class ExportPreviewNote extends JScrollPane
{
	public static JTextArea textArea = new JTextArea();
	
	public ExportPreviewNote()
	{
		super();
	}
	
	public void initialize()
	{
		createComponents();
	}
	
	public void createComponents()
	{
		TitledBorder titleBorder = new TitledBorder("Preview");
		textArea.setBorder(titleBorder);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		setViewportView(textArea);
	}
	
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
