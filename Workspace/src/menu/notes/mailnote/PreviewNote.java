package menu.notes.mailnote;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import sql.notes.NotesDataBase;

public class PreviewNote extends JScrollPane
{
	public static JTextArea textArea = new JTextArea();
	
	public PreviewNote()
	{
		super();
	}
	
	public void initialize()
	{
		createPreviewArea();
	}

	public void createPreviewArea()
	{
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		textArea.setBorder(new TitledBorder("Preview"));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		setViewportView(textArea);
	}
	
	public void displayNote(int index)
	{
		NotesDataBase notesdb = new NotesDataBase();
		
		if (index!=0)
		{
			textArea.setText(notesdb.getNotesBody(notesdb.getID(index))); // get ID from index instead of list position because combo box has a placeholder in index 0
			textArea.setCaretPosition(0);
		}
		
		else
		{
			textArea.setText("");
		}
	}
}
