package menu.notes.mailNotePanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class To 
{
	public static JTextField textField = new JTextField();
	public JLabel toLabel = new JLabel(" To:");
	
	public To ()
	{
		super();
	}
	
	 public void initialize()
	 {
		 createComponents(); 
	 }
	 
	 public void createComponents()
	 {
		 toLabel.setBackground(Color.WHITE);
		 toLabel.setOpaque(true);
		 
		 textField.setBorder(null);
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
