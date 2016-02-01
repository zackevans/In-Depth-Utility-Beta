package menu.notes;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import program.searchbar.SearchBarShell;

public class SearchBar
{
	Notes notes;
	public static JTextField textField = new SearchBarShell();
	JLabel searchLabel = new JLabel("Search");
	NotesList notesList = new NotesList(notes);
	NotesListData notesData = new NotesListData();
	
    public SearchBar (Notes notes) 
    {
       super();
       this.notes = notes;
    }
    
    public void initialize()
    {
    	addListeners();
    	searchLabel.setOpaque(false);
    }
    
    public void addListeners()
    {
    	textField.addFocusListener(new FocusListener() 
    	{
            @Override
            public void focusGained(FocusEvent e) 
            {
           	 	searchLabel.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) 
            {
           	 	if (doesTextExist() == false)
           	 	{
           	 		searchLabel.setVisible(true);
           	 	}
            }
        });
    	
    	textField.getDocument().addDocumentListener(new DocumentListener() 
    	{
    		public void changedUpdate(DocumentEvent arg0) {}
    		
    		public void insertUpdate(DocumentEvent arg0) 
    		{
    			notesList.loadSearchData(textField.getText());
    			notesData.loadOldListPositions(textField.getText());
    			
    		}
    		
    		public void removeUpdate(DocumentEvent arg0) 
    		{
    			notesList.loadSearchData(textField.getText());
    			notesData.loadOldListPositions(textField.getText());
    		}
    	});
    }
    
    
    public boolean doesTextExist()
    {
 	   String text = textField.getText();
 	   
 	   if (text.length() > 0)
 	   {
 		   return true;
 	   }
 	   
 	   else return false;
    }
    
    public void clearTextField()
    {
    	textField.setText("");
    }
    
    public void showSearchLabel()
    {
    	searchLabel.setVisible(true);
    }
}
    