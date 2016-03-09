package menu.notes.mailNotePanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class From 
{
	public static JTextField textField = new JTextField();
	public JLabel fromLabel = new JLabel(" From:");
	
	public From()
	{
		super();
	}
	
	public void initialize()
	{
		fromLabel.setBackground(Color.WHITE);
		fromLabel.setOpaque(true);
		fromLabel.setVisible(true);
		fromLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK)); // set only the top of border black
		textField.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK)); 
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
