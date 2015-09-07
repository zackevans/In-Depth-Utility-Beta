package menu.notes;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import menu.buffer.BufferPanel;

public class AddNoteBtn extends JButton
{
	private BufferPanel bufferPanel;
	
	public AddNoteBtn (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize ()
	{
		createButton();
		addListeners();
	}
	
	public void createButton()
	{
		ImageIcon start = new ImageIcon(getClass().getResource("Images/Add.png"));
		setIcon(start);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Add Button");
				createInputWindow();
			}
		});
	}
	
	public void createInputWindow()
	{
		JOptionPane optionPane = new JOptionPane("Enter Note Title:"
                , JOptionPane.QUESTION_MESSAGE
                , JOptionPane.CANCEL_OPTION
                , null, null, "");
		
		
		
		optionPane.setWantsInput(true);             
		JDialog dialog = optionPane.createDialog(null, "Create Note Name");
		dialog.setLocation(10, 20);
		dialog.setVisible(true);
	}
}
