package menu.notes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import menu.buffer.BufferPanel;
import sql.notes.NotesDataBase;

public class DisplayNotes extends JScrollPane
{
	Notes notes;
	NotesDataBase notesdb = new NotesDataBase();
	NotesList notesList = new NotesList(notes);
	private static JTextArea textArea;
	
	
	public DisplayNotes(Notes notes)
	{
		super();
		this.notes = notes;
	}
	
	
	public void initialize()
	{
		createComponents();
		documentChange();
	}
	
	public void createComponents()
	{
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		setViewportView(textArea);
	}
	
	public void displayNote()
	{
		int id = notesList.getDBLocation();
		
		System.out.println("ID: " + id);
	
		if (id != -1)
		{
			String noteText = notesdb.getNotesBody(id);
			textArea.setText(noteText);
		}
	}
	
	
	public void documentChange()
	{
		textArea.getDocument().addDocumentListener(new DocumentListener()
	    {
	
			public void changedUpdate(DocumentEvent arg0) 
			{
	        	   
			}
			public void insertUpdate(DocumentEvent arg0) 
			{
				updateChange();
				
			}
	
			public void removeUpdate(DocumentEvent arg0) 
			{
				updateChange();
			}
	    });
	}
	
	public void updateChange()
	{
			int id = notesList.getDBLocation();
			String currentText = textArea.getText();
			notesdb.updateNotesBody(id, currentText);
	 }

}
