package menu.notes;

import java.awt.Graphics;

import javax.swing.JTextField;

public class SearchBar 
{
	static JTextField textField = new JTextField();
	
	Notes notes;
	
    public SearchBar (Notes notes) 
    {
       super();
       this.notes = notes;
    }
    
    public void initialize()
    {
    	
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
    