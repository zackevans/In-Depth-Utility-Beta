package menu.notes.mailnote;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FromField 
{
	public static JTextField textField = new JTextField();
	public JLabel fromLabel = new JLabel(" From:");
	
	public FromField ()
	{
		
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
			public void removeUpdate(DocumentEvent e) 
			{
				if(FromField.textField.getText().length() == 0)
				{
					ErrorPanel.fromFieldError.setVisible(true);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				ErrorPanel.fromFieldError.setVisible(false);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
}
