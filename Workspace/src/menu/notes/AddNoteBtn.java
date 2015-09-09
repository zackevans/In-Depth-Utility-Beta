package menu.notes;


import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;

public class AddNoteBtn extends JButton
{
	private BufferPanel bufferPanel;
	private LaunchApp launchApp = new LaunchApp();
	
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
		int x = launchApp.frameXPosition() + 154; // 154 centers window for x
		int y = launchApp.frameYPosition() + 167; // 167 centers window for y
		
		
		JOptionPane optionPane = new JOptionPane("Enter Note Title:"
                , JOptionPane.QUESTION_MESSAGE
                , JOptionPane.CANCEL_OPTION
                , null, null, "");
		
		optionPane.setWantsInput(true);    
		
		JDialog dialog = optionPane.createDialog(null, "Create Note Name");
		
		dialog.setLocation(x, y);
		dialog.setVisible(true);
		
	}
}
