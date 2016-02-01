package menu.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sql.notes.NotesDataBase;

public class NotesListData 
{
	NotesDataBase notesdb = new NotesDataBase();
	private static ArrayList <String> sortedNames = new ArrayList <String>(); // names of data
	private static ArrayList<Integer> oldListPositions = new ArrayList <Integer>();
	private static Map <Integer,Integer> newOldListPosition = new HashMap <Integer,Integer> (); // KEY: current list position VALUE: corresponding old key 
	
	public ArrayList<String> getRawNoteListData()
	{
		return sortedNames;
	}
	
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
	
	public void moveListItemUp(int index)
	{
		String name = sortedNames.get(index);
		sortedNames.remove(index);
		sortedNames.add(0, name);
		
		int position = oldListPositions.get(index);
		oldListPositions.remove(index);
		oldListPositions.add(0,position);
	}
	
	
	public void loadOldListPositions(String searchText)
	{
		int listPosition = 0;
		oldListPositions.clear();
		
		for(int i = 0; i <= notesdb.countItems(); i++) 
		{	
			String noteName = notesdb.getNoteNameFromPosition(i);
			
			if(noteName.contains(searchText)) 
			{
				oldListPositions.add(listPosition);		
			}
			
			listPosition++;
		}
	}
	
	public int getOldEquivalentPosition(int currentPosition)
	{
		return oldListPositions.get(currentPosition);
	}
}
