package menu.notes;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import program.searchbar.SearchBarShell;

public class SearchBar
{
	static JTextField textField = new SearchBarShell();
	JLabel searchLabel = new JLabel("Search");
	Notes notes;
	
    public SearchBar (Notes notes) 
    {
       super();
       this.notes = notes;
    }
    
    public void initialize()
    {
    	 textField.addFocusListener(new FocusListener() {

             @Override
             public void focusGained(FocusEvent e) 
             {
            	 searchLabel.setVisible(false);
             }

             @Override
             public void focusLost(FocusEvent e) 
             {
            	 if (doesTextExist() ==  false)
            	 {
            		 searchLabel.setVisible(true);
            	 }
             }
         });
    	 
    	searchLabel.setOpaque(false);
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
}
    