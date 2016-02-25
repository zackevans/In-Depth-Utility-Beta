package menu.notes.mailNotePanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import sql.notes.NotesDataBase;

public class PreviewNote extends JScrollPane
{
	public static JTextArea textArea = new JTextArea();
	NotesDataBase notesdb = new NotesDataBase();
	public static JLabel label = new JLabel("Preview: ");
	
	public PreviewNote()
	{
		super();
	}
	
	public void initialize()
	{
		createComponents();
	}
	
	
	public void createComponents()
	{
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		setViewportView(textArea);
	}
	
	public void displayNote(int ID)
	{
		textArea.setText(notesdb.getNotesBody(ID));
	}
	
	public void clearField()
	{
		textArea.setText("");
	}
	
}
