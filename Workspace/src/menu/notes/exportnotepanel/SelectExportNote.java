package menu.notes.exportnotepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import menu.notes.NotesListData;
import menu.notes.mailnotepanel.ErrorPanel;
import sql.notes.NotesDataBase;

public class SelectExportNote
{
	static JComboBox <String> comboBox = new JComboBox <String>();
	public static ArrayList <String> sortedNames = new ArrayList <String>(); // names of data
	final DefaultComboBoxModel <String> model = new DefaultComboBoxModel <String>();
	private NotesListData notesListData = new NotesListData();
	private NotesDataBase notesdb = new NotesDataBase();
	private PreviewExportNote previewExportNote = new PreviewExportNote();
	ErrorPanel errorPanel = new ErrorPanel();
	public static int lastID = -1;
	
	public SelectExportNote()
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
            		lastID = notesdb.getID(noteNumber);
            		previewExportNote.displayNote(lastID);
            	}
               
               else if (noteNumber == 0) 
               {
            	   previewExportNote.clearField();
            	   lastID = -1;
               }
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
	
	public int getLastID ()
	{
		return lastID;
	}
	
	public String getNoteName()
	{
		return comboBox.getItemAt(comboBox.getSelectedIndex());
	}	
}
