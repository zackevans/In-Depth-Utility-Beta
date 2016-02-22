package menu.notes.mailNotePanel;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class To 
{
	public static JTextField textField = new JTextField();
	JLabel toLabel = new JLabel("To:");
	
	public To ()
	{
		super();
	}
	
	 public void initialize()
	 {
		 addListeners();
		 toLabel.setOpaque(false);
	 }
	 
	 public void addListeners()
	 {
		 textField.addFocusListener(new FocusListener() 
	    	{
	            @Override
	            public void focusGained(FocusEvent e) 
	            {
	            	toLabel.setVisible(false);
	            }

	            @Override
	            public void focusLost(FocusEvent e) 
	            {
	           	 	if (doesTextExist() == false)
	           	 	{
	           	 		toLabel.setVisible(true);
	           	 	}
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
}
