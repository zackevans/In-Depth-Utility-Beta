package menu.notes.mailNotePanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import sql.notes.NotesDataBase;

public class PreviewNote extends JScrollPane
{
	public static JTextArea textArea = new JTextArea();
	NotesDataBase notesdb = new NotesDataBase();
	
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
		TitledBorder titleBorder = new TitledBorder("Preview");
		textArea.setBorder(titleBorder);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		setViewportView(textArea);
	}
	
	public void displayNote(int ID)
	{
		textArea.setText(notesdb.getNotesBody(ID));
		textArea.setCaretPosition(0);
	}
	
	public void clearField()
	{
		textArea.setText("");
	}
}
