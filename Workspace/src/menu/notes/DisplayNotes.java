package menu.notes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sql.notes.NotesDataBase;

public class DisplayNotes extends JScrollPane
{
	private static JTextArea textArea;
	Notes notes;
	NotesDataBase notesdb = new NotesDataBase();
	NotesList notesList = new NotesList(notes);
	SearchBar searchBar = new SearchBar(notes);	
	
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
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		setViewportView(textArea);
	}
	
	public void displayNote(int ID)
	{
		textArea.setText(notesdb.getNotesBody(ID));
	}
	
	
	public void documentChange()
	{
		textArea.getDocument().addDocumentListener(new DocumentListener()
	    {	
			@Override
			public void changedUpdate(DocumentEvent arg0) 
			{
	        	   
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) 
			{
				updateChange();
				
			}
	
			@Override
			public void removeUpdate(DocumentEvent arg0) 
			{
				updateChange();
			}
	    });
	}

	public void updateChange()
	{
			int id = notesList.getLastID();
			
			if (id != -1)
			{
				String currentText = textArea.getText();
				notesdb.updateNotesBody(id, currentText);
			}		
	}
	
	public void clearDisplay()
	{
		textArea.setText("");
	}
}