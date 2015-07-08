package launch.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import menu.buffer.BufferPanel;
import program.walpaper.Wallpaper;

public class LaunchApp 
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	private JFrame frame = new JFrame();
    private JLayeredPane layerPane = new JLayeredPane();
    private JPanel panelGreen = new JPanel();
    private static Wallpaper wallpaper = new Wallpaper(new ImageIcon("Images/Wallpaper/mavericks_2560.jpg").getImage());
    BufferPanel bufferPanel = new BufferPanel();
    
    public LaunchApp()
    {
    	frame.setSize(Window_Width, Window_Height); 
    	frame.setMinimumSize(new Dimension(Window_Width,Window_Height));
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	
        frame.getContentPane().add(layerPane);
        
        wallpaper.setOpaque(false);
        
        bufferPanel.initialize();
        bufferPanel.setOpaque(false);
        
        bufferPanel.setSize(Window_Width, Window_Height);
        
        
        
        layerPane.add(wallpaper, new Integer(0), 0);
        layerPane.add(bufferPanel, new Integer(1), 0);
        
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LaunchApp();
    }

}
