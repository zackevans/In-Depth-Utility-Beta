package panel.customnotedialog;

import java.awt.Dimension;

import javax.swing.JFrame;

import launch.app.LaunchApp;

public class NoteDialog extends JFrame
{
	public static final int Window_Width = 393; 
	public static final int Window_Height = 167;
	private static JFrame customFrame = new JFrame(); // Created JFrame VAR. 
	private LaunchApp launchApp = new LaunchApp();
	
	
	public NoteDialog()
	{
		
	}
	
	
	public void launchDialog()
	{
		createFrame();
	}
	
	
	public void createFrame()
	{
		int x = launchApp.frameXPosition() + 154; // 154 centers window for x
		int y = launchApp.frameYPosition() + 167; // 167 centers window for y
		
		customFrame.setSize(Window_Width, Window_Height); 
		customFrame.setMinimumSize(new Dimension(Window_Width,Window_Height));
    	
		customFrame.setLocation(x, y); // set frame in center of main frame 
		
    	// Set Frame nonResizable prefrence
		customFrame.setResizable(false);
		
		customFrame.setVisible(true);
	}
	
	
	
	
	
	
	
}
