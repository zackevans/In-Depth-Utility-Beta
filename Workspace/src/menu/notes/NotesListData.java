package menu.notes;

import java.util.ArrayList;

import sql.notes.NotesDataBase;

public class NotesListData 
{
	NotesDataBase notesdb = new NotesDataBase();
	private static ArrayList <String> sortedNames = new ArrayList <String>(); // names of data
	
	public ArrayList<String> getNoteListData()
	{
		sortListNames(); // puts names in correct sequence
		return sortedNames;
	}
	
	public void sortListNames()
	{
		sortedNames.clear();
		
		for (int i = 1; i <= notesdb.countItems(); i++)
		{
			String noteName = notesdb.getNoteNameFromPosition(i);
			sortedNames.add(noteName);
		}
	}
	
	
	
	
	
	

}
