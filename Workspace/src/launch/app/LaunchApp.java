package launch.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import menu.buffer.BufferPanel;
import program.wallpaper.Wallpaper;
import sql.DataBase;
import sql.system.settings.SystemDatabase;

public class LaunchApp 
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private static JFrame frame = new JFrame();
    private static JLayeredPane layerPane = new JLayeredPane();
    private static Wallpaper wallpaper = new Wallpaper(new ImageIcon("Images/Wallpaper/mavericks_2560.jpg").getImage());
    static BufferPanel bufferPanel = new BufferPanel();
    
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
    	frame.setMinimumSize(new Dimension(Window_Width,Window_Height));
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.getContentPane().add(layerPane);
        
        bufferPanel.initialize();       
        bufferPanel.setSize(frame.getWidth(),frame.getHeight());
      
        layerPane.add(wallpaper, new Integer(0), 0);
        layerPane.add(bufferPanel, new Integer(1), 0);
        
        frame.pack();
        frame.setVisible(true);
        
        dataBaseCalls();
    }
    
    
    
    private static void dataBaseCalls()
    {
    	final DataBase dataBase = new DataBase();
    	final SystemDatabase systemdb = new SystemDatabase();
    	
    	dataBase.createDBLocation();
		dataBase.createDatabase();
		dataBase.checkConnection();
		
		systemdb.createSystemTable();
		
    }
    

}
