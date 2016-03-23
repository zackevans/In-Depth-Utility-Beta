package menu.notes.mailnotepanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class From 
{
	private ErrorPanel errorPanel = new ErrorPanel();
	public static JTextField textField = new JTextField();
	public JLabel fromLabel = new JLabel(" From:");
	
	public From()
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
		fromLabel.setBackground(Color.WHITE);
		fromLabel.setOpaque(true);
		fromLabel.setVisible(true);
		fromLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK)); // set only the top of border black
		textField.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK)); 
	}
	
	public void addListeners()
	{
		textField.getDocument().addDocumentListener(new DocumentListener()
	    {	
			@Override
			public void changedUpdate(DocumentEvent arg0) {}
			@Override
			public void insertUpdate(DocumentEvent arg0) 
			{
				errorPanel.fromFieldError.setVisible(false);
			}
	
			@Override
			public void removeUpdate(DocumentEvent arg0) 
			{
				if (textField.getText().length() == 0)
				{
					errorPanel.fromFieldError.setVisible(true);
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
