package menu.notes.sharenotesdialog;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import launch.app.LaunchApp;
import menu.notes.Notes;

public class ShareNoteDialog 
{
	public static final int Window_Width = 150; // Standard Dialog size
	public static final int Window_Height = 167; // Standard Dialog size
	private LaunchApp launchApp = new LaunchApp();
	private ShareList shareList;
	public static JFrame customFrame = new JFrame(); // Created JFrame VAR.
	private Notes notes;
	
	
	public ShareNoteDialog(Notes notes)
	{
		super();
		this.notes = notes;
		customFrame.setUndecorated(true); // removes status bar from frame
	}
	
	public void launchDialog()
	{
		createAndShowGUI();
	}
	
	public void createAndShowGUI()
	{
		int x = launchApp.frameXPosition()+700 ; //  centers window for x
		int y = launchApp.frameYPosition()+ 42 ; // centers window for y
		
		customFrame.setSize(Window_Width, Window_Height); 
		customFrame.setMinimumSize(new Dimension(Window_Width,Window_Height));
		customFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		customFrame.setResizable(false);
		customFrame.setLocation(x, y); // set frame in center of main frame 
		customFrame.setBackground(Color.WHITE);
		customFrame.setOpacity(.45f);
		
		createComponents();
		
		//customFrame.getContentPane().setLayout(null);
		
		addComponents();
		addListeners();
		
		customFrame.setVisible(true);
	}
	
	public void createComponents()
	{
		shareList = new ShareList (this);
		
		shareList.initialize();
	}
	
	public void addComponents()
	{
		customFrame.add(ShareList.list);
	}
	
	public void addListeners()
	{
//		customFrame.addFocusListener(new FocusListener() 
//    	{
//            @Override
//            public void focusLost(FocusEvent e) // when the frame is unslected it goes away
//            {
//           	 	customFrame.setVisible(false); 
//            }
//            
//            @Override
//            public void focusGained(FocusEvent e) {} // not needed currently
//        });
	}
}