package menu.notes.mailnotepanel;

import java.awt.Color;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class To 
{
	public static JTextField textField = new JTextField();
	public JLabel toLabel = new JLabel(" To:");
	private ErrorPanel errorPanel = new ErrorPanel();
	
	public To ()
	{
		super();
	}
	
	 public void initialize()
	 {
		 createComponents();
		 addListeners();
	 }
	 
	 public void createComponents()
	 {
		 toLabel.setBackground(Color.WHITE);
		 toLabel.setOpaque(true);
		 
		 textField.setBorder(null);
	 }
	 
	 public void addListeners()
	 {
		 
		 textField.getDocument().addDocumentListener(new DocumentListener() 
		 {		 
			 @Override
			 public void changedUpdate(DocumentEvent e) {}
			 
			 @Override
			 public void insertUpdate(DocumentEvent e) 
			 {
				 if (isValidEmailAddress(textField.getText()) == true)
				 {
					 errorPanel.toFieldError.setVisible(false);
				 }
				 
				 else
				 {
					 errorPanel.toFieldError.setVisible(true);
				 }
			 }
			 
			 @Override
			 public void removeUpdate(DocumentEvent e) 
			 {
				if (textField.getText().length() == 0)
				{
					errorPanel.toFieldError.setVisible(true);
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
	 
	 public static boolean isValidEmailAddress(String email) 
	 {
		 boolean result = true;
		
		 try 
		 {
	     	 InternetAddress emailAddr = new InternetAddress(email);
	     	 emailAddr.validate();
		 } 
		 
		 catch (AddressException ex) 
		 {
			 result = false;
		 }
		 
	  	 return result;
	}
}
