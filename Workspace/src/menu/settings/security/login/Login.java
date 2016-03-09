package menu.settings.security.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.buffer.BufferPanel;
import sql.system.settings.SystemDatabase;


public class Login extends JPanel 
{
	SystemDatabase systemDB = new SystemDatabase();
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	public static final int btnWidth = 150;
	public static final int btnHeight = 50;
	public static final int btnLn2 = 200;
	public static final int leftRow = -75;
	public static final int rightRow = 125;
	private static JLabel mainTittleLbl;
	private static JLabel companyNameLbl;
	private static JLabel enterPasswordLbl;
	private static JLabel incorrectLbl;
	private static EnterPassField enterPassField;
	private static EnterButton enterBtn;
	private static CancelButton cancelBtn;
	BufferPanel bufferPanel;
	String nextPanel = "MAIN_MENU";
	
	public Login (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize ()
	{
		createComponents();
		layoutComponents();
		initializeComponents();
		setOpaque(false);
	}
	
	public void createComponents()
	{
		mainTittleLbl = new JLabel("Login");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014©");
		enterPasswordLbl = new JLabel("Enter Password: ");
		incorrectLbl = new JLabel("Incorrect Password"); // center label
		enterPassField = new EnterPassField(bufferPanel);
		enterBtn = new EnterButton(bufferPanel);
		cancelBtn = new CancelButton(bufferPanel);
		
		createMainTittleLable();
		createBottomLabel();
		createEnterPassLabel();
		createIncorrectLabel();
		enterPassField.setBounds(235,112,240,25);
		
		enterBtn.setBounds((Window_Height)/2+rightRow, btnLn2, btnWidth, btnHeight);
		cancelBtn.setBounds((Window_Height)/2+leftRow, btnLn2, btnWidth, btnHeight);
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(mainTittleLbl);
		add(companyNameLbl);
		add(enterPasswordLbl);
		add(enterPassField);
		add(enterBtn);
		add(incorrectLbl);
		add(cancelBtn);
	}
	
	public void initializeComponents()
	{
		enterBtn.initialize();
		cancelBtn.initialize();
		enterPassField.addListener();
	}
	
	public String getPassword()
	{
		return enterPassField.getText();
	}
	
	public void clearField()
	{
		enterPassField.setText("");
	}
	
	public void showWarning (boolean tf)
	{
		incorrectLbl.setVisible(tf);
	}
	
	public void setNextPanel(String next)
	{
		nextPanel = next;
	}
	
	public String getNextPanel()
	{
		return nextPanel;
	}
	
	public void setFocusOnField()
	{
		enterPassField.requestFocusInWindow();
	}
	
	public static void  createMainTittleLable() {
		mainTittleLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,30));
	    int x = (int) (Window_Height) / 2;
	    mainTittleLbl.setBounds(0, 15, Window_Width,40);
	    mainTittleLbl.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public static void  createBottomLabel() {
		companyNameLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12));
	    int y = (int) (Window_Width)/2+90;
	    companyNameLbl.setBounds(480, y,225,40);
	}
	
	public static void createEnterPassLabel()
	{
		enterPasswordLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12));
	    enterPasswordLbl.setBounds(239,82,225,40);
	}
	
	public static void createIncorrectLabel(){
		int x = (int) (Window_Height) / 2+30;
		incorrectLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,14));
		incorrectLbl.setBounds(x,140,225,40);
		incorrectLbl.setForeground(Color.red);
		incorrectLbl.setVisible(false);
	}
}
