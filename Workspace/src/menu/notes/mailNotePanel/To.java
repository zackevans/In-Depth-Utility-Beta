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
		 addListeners();
		 createComponents(); 
	 }
	 
	 public void createComponents()
	 {
		 toLabel.setBackground(Color.WHITE);
		 toLabel.setOpaque(true);
		 
		 textField.setBorder(null);
	 }
	 
	 public void addListeners()
	 {
//		textField.addFocusListener(new FocusListener() 
//    	{
//            @Override
//            public void focusGained(FocusEvent e) 
//            {
//            	toLabel.setVisible(false);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) 
//            {
//           	 	if (doesTextExist() == false)
//           	 	{
//           	 		toLabel.setVisible(true);
//           	 	}
//            }
//        });
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
