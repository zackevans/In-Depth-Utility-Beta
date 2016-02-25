package menu.notes.mailNotePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import menu.notes.NotesListData;
import sql.notes.NotesDataBase;

public class SeclectNote
{
	static JComboBox <String> comboBox = new JComboBox <String>();
	public static ArrayList <String> sortedNames = new ArrayList <String>(); // names of data
	final DefaultComboBoxModel <String> model = new DefaultComboBoxModel <String>();
	private NotesListData notesListData = new NotesListData();
	private NotesDataBase notesdb = new NotesDataBase();
	private PreviewNote previewNote = new PreviewNote();
	
	public SeclectNote()
	{
		super();
	}
	
	public void initialize()
	{
		addListeners();
	}
	
	public void addListeners()
	{
		comboBox.addActionListener(new ActionListener() 
		{
            @Override
			public void actionPerformed(ActionEvent e)
            {
            	int noteNumber = comboBox.getSelectedIndex();
               
            	if (noteNumber != 0)
            	{
            		int ID = notesdb.getID(noteNumber);
            		previewNote.displayNote(ID);
            	}
               
               else if (noteNumber == 0) previewNote.clearField();	   	
            }
        });   
	}
	
	public void updateData()
	{
		sortedNames = notesListData.getNoteListData();
		model.removeAllElements();
		
		model.addElement("- Select a Note - ");
		
		for(int i = 0; i < sortedNames.size();i++)
		{	
			model.addElement(sortedNames.get(i));
		}
		
		comboBox.setModel(model);
	}	
}
