package launch.app;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LaunchApp 
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final String progName = "In Depth Utility";
	final static JFrame frame = new JFrame(progName); 
	
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI()
	{	
		frame.setSize(Window_Width, Window_Height); 
		frame.setBackground(Color.CYAN); //clear 0x33
		frame.setMinimumSize(new Dimension(Window_Width,Window_Height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);
	}
	

}
