package menu.notes.sharenotesdialog;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;
import menu.notes.Notes;

public class ShareNoteDialog 
{
	BufferPanel bufferPanel;
	public static final int Window_Width = 150; // Standard Dialog size
	public static final int Window_Height = 167; // Standard Dialog size
	public static JFrame customFrame = new JFrame(); // Created JFrame VAR.
	private LaunchApp launchApp; 
	private Notes notes;
	private ShareList shareList;
	private boolean clicked = false;
	
	public ShareNoteDialog(BufferPanel bufferPanel, Notes notes)
	{
		super();
		this.notes = notes;
		this.bufferPanel = bufferPanel;
		customFrame.setUndecorated(true); // removes status bar from frame
	}
	
	public void launchDialog()
	{
		if (clicked == false)
		{
			createAndShowGUI();
			clicked = true;
		}
		
		else
		{
			showWindow();
			
		}
	}
	
	public void createAndShowGUI()
	{
		createComponents(); // create components first before using launch app
		
		int x = launchApp.frameXPosition()+700 ; //  centers window for x
		int y = launchApp.frameYPosition()+ 42 ; // centers window for y
		
		customFrame.setSize(Window_Width, Window_Height); 
		customFrame.setMinimumSize(new Dimension(Window_Width,Window_Height));
		customFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		customFrame.setResizable(false);
		customFrame.setLocation(x, y); // set frame in center of main frame 
		//customFrame.setBackground(Color.WHITE);
		//customFrame.setBackground(new Color(0,0,0,64));
		
		addComponents();
		
		customFrame.setVisible(true);
	}
	
	public void showWindow()
	{
		int x = launchApp.frameXPosition()+700 ; //  centers window for x
		int y = launchApp.frameYPosition()+ 42 ; // centers window for y
		customFrame.setLocation(x, y); // set frame in center of main frame 
		ShareList.list.clearSelection();
		customFrame.setVisible(true);
	}
	
	public void createComponents()
	{
		shareList = new ShareList (bufferPanel, notes, this);
		launchApp = new LaunchApp();
		
		shareList.initialize();
	}
	
	public void addComponents()
	{
		customFrame.getContentPane().add(ShareList.list);
	}
}