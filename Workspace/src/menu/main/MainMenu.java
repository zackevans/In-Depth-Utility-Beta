package menu.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.buffer.BufferPanel;


public class MainMenu extends JPanel 
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final int btnLn1 = 75;
	public static final int btnLn2 = 175;
	public static final int btnLn3 = 275;
	public static final int leftRow = -75;
	public static final int rightRow = 125;
	public static final int btnWidth = 150;
	public static final int btnHeight = 50;
	public static final int btnPadding = 25;
	private BufferPanel bufferPanel;
	private static JLabel mainTittleLbl;
	private static JLabel companyNameLbl;
	private JButton notesBtn; 
	private JButton remindersBtn; 
	private JButton mailBtn;
	private JButton settingsBtn; 
	private JButton aboutBtn;
	
	
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
		//setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	public void createComponents() 
	{
		mainTittleLbl = new JLabel("In Depth Utility");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014©");
		notesBtn = new JButton("Notes");
		remindersBtn = new JButton ("Reminders");
		mailBtn = new JButton ("Mail");
		settingsBtn = new JButton ("Settings Menu");
		aboutBtn = new JButton ("About IDU");
		
		createMainTittleLable();
		createBottomLabel();
		createPersonalButton();
		createSchoolButton();
		createComputerButton();
		createSettingsButton();
		createAboutMenu ();
		//mainTittleLbl.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//companyNameLbl.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	public void layoutComponents() 
	{	
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));//-20 
		add(mainTittleLbl);
		add(companyNameLbl);
		add(notesBtn);
        add(remindersBtn);
		add(mailBtn);
		add(settingsBtn);
		add(aboutBtn);
	}
	
	public void addListeners()
	{
		notesBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("notesBtn");
				//bufferPanel.showPanel("PERSONAL_MENU");
			}
		});
		
		remindersBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("remindersBtn");
				//bufferPanel.showPanel("SCHOOL_MENU");
			}
		});
		
		mailBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("mailBtn");
			}
		});
		
		settingsBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("SettignsBtn");
				bufferPanel.showPanel("SETTINGS_MENU");
			}
		});
		
		aboutBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("AboutBtn");
			}
		});
		
	}
	
	public static void  createMainTittleLable() 
	{
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30));
	    int x = (int) (Window_Height) / 2;
	    mainTittleLbl.setBounds(0, 15, Window_Width,40);
	    mainTittleLbl.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public static void  createBottomLabel()
	{
		companyNameLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12));
	    int y = (int) (Window_Width)/2+90;
	    companyNameLbl.setBounds(480, y,225,40);
	}
	public void createPersonalButton()
	{   
		int x = (Window_Height)/2+leftRow;;
		notesBtn.setBounds(x,btnLn1,btnWidth,btnHeight);
		notesBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
		//notesBtn.setOpaque(false);
		//notesBtn.setContentAreaFilled(false);
		//notesBtn.setBorderPainted(false);
		//notesBtn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	public void createSchoolButton()
	{
		int x = (Window_Height)/2+rightRow;
		remindersBtn.setBounds(x,btnLn1,btnWidth,btnHeight);
		remindersBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
		//remindersBtn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	public void createComputerButton()
	{
		int x = (Window_Height)/2+leftRow;
	    mailBtn.setBounds(x,btnLn2,btnWidth,btnHeight);
	    mailBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	    //mailBtn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	public void createSettingsButton()
	{
		int x = (Window_Height)/2+rightRow;
	    settingsBtn.setBounds(x,btnLn2,btnWidth,btnHeight);
	    settingsBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	    //settingsBtn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
	
	public void createAboutMenu ()
	{
		int x = (Window_Height)/2+btnPadding;
	    aboutBtn.setBounds(x,btnLn3,btnWidth,btnHeight);
	    aboutBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,16));
	    //aboutBtn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}
}
