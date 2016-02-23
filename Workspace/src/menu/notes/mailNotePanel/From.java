package menu.notes.mailNotePanel;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
		fromLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		//textField.setBorder(null);
		textField.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
	}
	 
	 public void addListeners()
	 {
		textField.addFocusListener(new FocusListener() 
    	{
            @Override
            public void focusGained(FocusEvent e) 
            {
            	System.out.println("gained");
            	
            	fromLabel.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) 
            {
           	 	if (doesTextExist() == false)
           	 	{
           	 		System.out.println("lost");
           	 		
           	 		fromLabel.setVisible(true);
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
