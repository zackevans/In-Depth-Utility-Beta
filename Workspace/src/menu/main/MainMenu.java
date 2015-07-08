package menu.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;


public class MainMenu extends JPanel 
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final int btnLn1 = 50;
	public static final int btnLn2 = 150;
	public static final int btnLn3 = 250;
	public static final int leftRow = -75;
	public static final int rightRow = 125;
	public static final int btnWidth = 150;
	public static final int btnHeight = 50;
	public static final int btnPadding = 25;
	private BufferPanel bufferPanel;
	private static JLabel mainTittleLbl;
	
	public MainMenu (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize ()
	{
		createComponents();
		layoutComponents();
		addListeners();
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	public void createComponents()
	{
		mainTittleLbl = new JLabel("In Depth Utility");
		
		createMainTittleLable();
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));//-20 
		
		add(mainTittleLbl);
	}
	
	public void addListeners()
	{
		
	}
	
	public static void  createMainTittleLable() 
	{
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30));
	    int x = (int) (Window_Height) / 2;
	    mainTittleLbl.setBounds(x, 5, 200,40);
	    //mainTittleLbl.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	
}
