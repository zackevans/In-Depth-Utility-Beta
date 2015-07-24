package menu.settings.security.enterpassword;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class EnterPassword extends JPanel
{
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
	private static ReturnButton returnBtn;
	BufferPanel bufferPanel;
	
	public EnterPassword(BufferPanel bufferPanel)
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
		mainTittleLbl = new JLabel("Reset Password");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014©");
		enterPasswordLbl = new JLabel("Enter Password: ");
		enterPassField = new EnterPassField(bufferPanel);
		enterBtn = new EnterButton(bufferPanel);
		returnBtn = new ReturnButton(bufferPanel);
		incorrectLbl = new JLabel("Incorrect Password"); // center label
		
		createMainTittleLable();
		createBottomLabel();
		createEnterPassLabel();
		createIncorrectLabel();
		enterPassField.setBounds(235,112,240,25);
		enterBtn.setBounds((Window_Height)/2+rightRow, btnLn2, btnWidth, btnHeight);
		returnBtn.setBounds((Window_Height)/2+leftRow, btnLn2, btnWidth, btnHeight);
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
		add(returnBtn);
		add(incorrectLbl);
	}
	
	public void initializeComponents()
	{
		enterBtn.initialize();
		returnBtn.initialize();
	}
	
	public String getPassword()
	{
		return enterPassField.getText();
	}
	
	public void clearFields()
	{
		enterPassField.setText("");
	}
	
	public void showWarning (boolean tf)
	{
		incorrectLbl.setVisible(tf);
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
