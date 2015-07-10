package menu.settings;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class SettingsMenu extends JPanel
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
	private static JLabel mainTittleLbl;
	private static JLabel companyNameLbl;
	private BufferPanel bufferPanel;
	private JButton generalBtn;
	private JButton securityBtn;
	private JButton returnBtn;
	
	//SETTINGS
	//general (Sounds,Mail,
	//security
	//Wallpaper
	//Notifacations
	//RETURN
	
	public SettingsMenu (BufferPanel bufferPanel)
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
	}
	
	public void createComponents() 
	{
		mainTittleLbl = new JLabel("Settings Menu");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014");
		generalBtn = new JButton("General");
		securityBtn = new JButton("Security");
		returnBtn = new JButton("Return");
		
		createMainTittleLable();
		createBottomLabel();
		createGeneralButton();
		createSecurityButton();
		createReturnButton();
	}
	
	public void layoutComponents() 
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));//-20 
		add(mainTittleLbl);
		add(companyNameLbl);
		add(generalBtn);
		add(securityBtn);
		add(returnBtn);
	}
	
	public void addListeners() 
	{
		generalBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("GeneralBtn");
			}
		});

		securityBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("SecurityBtn");
				bufferPanel.showPanel("SECURITY_SETTINGS");
			}
		});

		returnBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("ReturnBtn");
				bufferPanel.showPanel("MAIN_MENU");
			}
		});

	}
	
	public static void  createMainTittleLable() {
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30));
	    int x = (int) (Window_Height) / 2;
	    mainTittleLbl.setBounds(x, 5, 200,40);
	}

	public static void  createBottomLabel() {
		companyNameLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12));
	    int y = (int) (Window_Width)/2+85;
	    companyNameLbl.setBounds(480, y,225,40);
	}
	
	public void createGeneralButton() {   
		int x = (Window_Height)/2+leftRow;;
		generalBtn.setBounds(x,btnLn1,btnWidth,btnHeight);
		generalBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	}

	public void createSecurityButton() {
		int x = (Window_Height)/2+rightRow;
		securityBtn.setBounds(x,btnLn1,btnWidth,btnHeight);
		securityBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	}

	public void createReturnButton() {
		int x = (Window_Height)/2+btnPadding;
	    returnBtn.setBounds(x,btnLn3,btnWidth,btnHeight);
	    returnBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,16));
	}

}
