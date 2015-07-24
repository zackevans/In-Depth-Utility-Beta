package menu.settings.security;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.settings.security.enterpassword.EnterPassword;
import menu.settings.security.passconfirm.PasswordConfirm;
import menu.settings.security.removepass.RemovePassword;
import sql.system.settings.SystemDatabase;

public class SecuritySettings extends JPanel
{
	private SystemDatabase systemdb = new SystemDatabase();
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final int btnLn1 = 50+55;
	public static final int btnLn2 = 150+55;
	public static final int btnLn3 = 250;
	public static final int leftRow = -75;
	public static final int rightRow = 125;
	public static final int btnWidth = 150;
	public static final int btnHeight = 50;
	public static final int btnPadding = 25;
	private static JLabel companyNameLbl;
	private static JLabel mainTittleLbl;
	private JButton createPswBtn;
	private JButton resetPswBtn;
	private JButton removePswBtn;
	private JButton returnBtn;
	private PasswordConfirm passConfirm;
	private EnterPassword enterPass;
	private RemovePassword removePass;
	BufferPanel bufferPanel;
	
	public SecuritySettings (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setButtons();
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
		mainTittleLbl = new JLabel("Security Settings");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014©");
		createPswBtn = new JButton ("Create Password");
		removePswBtn = new JButton ("Remove Password");
		resetPswBtn = new JButton ("Reset Password");
		returnBtn = new JButton ("Return");
		passConfirm =  new PasswordConfirm(bufferPanel);
		enterPass = new EnterPassword(bufferPanel);
		removePass = new RemovePassword(bufferPanel);
		
		createMainTittleLable();
		createBottomLabel();
		createPswButton();
		createResetButton();
		createRemoveButton();
		createReturnButton();
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(mainTittleLbl);
		add(companyNameLbl);
		add(createPswBtn);
		add(resetPswBtn);
		add(removePswBtn);
		add(returnBtn);
	}
	
	public void addListeners()
	{
		createPswBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Create Passowrd Btn");
				bufferPanel.showPanel("PASSWORD_CONFIRM");
				passConfirm.hideWarning();
				passConfirm.clearTxtFields();
			}
		});
		
		resetPswBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Reset Pass Btn");
				bufferPanel.showPanel("ENTER_PASSWORD");
				enterPass.clearFields();
				enterPass.showWarning(false);
			}
		});
		
		removePswBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Remove Pass Btn");
				bufferPanel.showPanel("REMOVE_PASSWORD");
				removePass.clearFields();
				removePass.showWarning(false);
			}
		});
		
		returnBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				bufferPanel.showPanel("SETTINGS_MENU");
			}
		});
	}
	
	public static void  createMainTittleLable() {
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30));
	    int x = (int) (Window_Height) / 2;
	    mainTittleLbl.setBounds(x, 5, 250,40);
	}
	
	public static void  createBottomLabel() {
		companyNameLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12));
	    int y = (int) (Window_Width)/2+90;
	    companyNameLbl.setBounds(480, y,225,40);
	}

	
	public void createPswButton() {   
		int x = (Window_Height)/2+leftRow;;
		createPswBtn.setBounds(x,btnLn1,btnWidth,btnHeight);
		createPswBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
		//createPswBtn.setEnabled(false);
	}
	
	public void createResetButton() {
		int x = (Window_Height)/2+rightRow;
		resetPswBtn.setBounds(x,btnLn1,btnWidth,btnHeight);
		resetPswBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	}
	
	public void createRemoveButton() {
		int x = (Window_Height)/2+leftRow;
	    removePswBtn.setBounds(x,btnLn2,btnWidth,btnHeight);
	    removePswBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	}
	
	public void createReturnButton() {
		int x = (Window_Height)/2+rightRow;
	    returnBtn.setBounds(x,btnLn2,btnWidth,btnHeight);
	    returnBtn.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
	}
	
	public void setButtons()
	{
		boolean passExist = systemdb.getPassExist();
		
		if (passExist == true)
		{
			createPswBtn.setEnabled(false);
			resetPswBtn.setEnabled(true);
			removePswBtn.setEnabled(true);
		}
		
		else
		{
			createPswBtn.setEnabled(true);
			resetPswBtn.setEnabled(false);
			removePswBtn.setEnabled(false);
		}
	}
	
}
