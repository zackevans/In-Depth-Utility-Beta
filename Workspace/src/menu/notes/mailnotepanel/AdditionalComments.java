package menu.notes.mailnotepanel;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AdditionalComments extends JScrollPane 
{
	public static JTextArea textArea = new JTextArea();
	public static JLabel commentLabel = new JLabel("  Additional Comments:");
	
	public AdditionalComments()
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
		setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setLineWrap(true);
		commentLabel.setVisible(true);
		commentLabel.setForeground(Color.black);
		setViewportView(textArea);
		//commentLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void addListeners()
	{
		textArea.addFocusListener(new FocusListener() 
		{
			@Override
			public void focusLost(FocusEvent e) 
			{
				if (doesTextExist() == false)
				{
					commentLabel.setVisible(true);
				}	
			}
			
			@Override
			public void focusGained(FocusEvent e) 
			{
				commentLabel.setVisible(false);
			}
		});
	}
	
	public boolean doesTextExist()
	 {
		 String text = textArea.getText();
		 
		 if (text.length() > 0)
		 {
			 return true;
		 }
		 
		 else return false;
	 }
}