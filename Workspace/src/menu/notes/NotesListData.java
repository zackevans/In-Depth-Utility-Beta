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
	
	public ArrayList<String> getSearcNoteListData(String searchText)
	{
		sortSearchListNames(searchText);
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
	
	public void sortSearchListNames(String searchText)
	{
		sortedNames.clear();
		
		for (int i = 1; i <= notesdb.countItems(); i++)
		{
			String noteName = notesdb.getNoteNameFromPosition(i);
			
			if (noteName.contains(searchText)) // checks to see if the searchbar text is in the note
			{
				sortedNames.add(noteName);
			}
		}
	}
}
