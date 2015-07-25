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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import menu.buffer.BufferPanel;
import program.wallpaper.Wallpaper;
import sql.DataBase;
import sql.system.settings.SystemDatabase;
import statusbar.topbar.TopBar;

public class LaunchApp 
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private static JFrame frame = new JFrame();
    private static JLayeredPane layerPane = new JLayeredPane();
    private static Wallpaper wallpaper; 
    static BufferPanel bufferPanel = new BufferPanel();
    public static TopBar topBar;
    
    public static void main(String[] args) 
    {
    	SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				createAndShowGUI();
			}
		});
    	
    	dataBaseCalls();
    }
    
    private static void createAndShowGUI()
    {
    	frame.setSize(Window_Width, Window_Height); 
    	frame.setMinimumSize(new Dimension(Window_Width,Window_Height));
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	
        bufferPanel.initialize();    
        bufferPanel.setSize(frame.getWidth(),frame.getHeight());
        
        wallpaper = new Wallpaper("/Library/Desktop Pictures/Underwater.jpg");
        
        topBar = new TopBar(bufferPanel);
        topBar.setSize(frame.getWidth(), frame.getHeight());
        topBar.initialize();
        
        layerPane.add(wallpaper, new Integer(0), 0);
        layerPane.add(bufferPanel, new Integer(1), 0);
        layerPane.add(topBar, new Integer(2), 0);
        
        frame.getContentPane().add(layerPane);
        frame.pack();
        frame.setVisible(true);
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
