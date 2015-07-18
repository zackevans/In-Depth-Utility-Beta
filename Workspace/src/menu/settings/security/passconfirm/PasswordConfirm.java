package menu.settings.security.passconfirm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class PasswordConfirm extends JPanel
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
	private static JLabel enterPassword;
	private static JLabel reEnterPassLbl;
	private static JLabel invalidLbl;
	private static InitialField initialField;
	private static RetypeField retypeField;
	private static EnterButton enterBtn;
	private static ReturnButton returnBtn;
	public String passwordText;
	BufferPanel bufferPanel;
	
	public PasswordConfirm (BufferPanel bufferPanel)
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
		mainTittleLbl = new JLabel("Set Password");
		companyNameLbl = new JLabel("Created By Appended Karma Ltd. 2014©");
		enterPassword = new JLabel("Enter Password:");
		reEnterPassLbl = new JLabel ("Re-Enter Password:");
		invalidLbl = new JLabel ("Invalid Input: Passwords Empty/Do not Match");
		initialField = new InitialField (bufferPanel);
		retypeField = new RetypeField(bufferPanel);
		enterBtn = new EnterButton(bufferPanel);
		returnBtn = new ReturnButton(bufferPanel);
		
		createMainTittleLable();
		createBottomLabel();
		createEnterLabel();
		createReEnterLabel();
		createInvalidLabel();
		initialField.setBounds(225, 75, 240, 25);
		retypeField.setBounds(225, 150, 240, 25);
		returnBtn.setBounds((Window_Height)/2+leftRow, btnLn2, btnWidth, btnHeight);
		enterBtn.setBounds((Window_Height)/2+rightRow, btnLn2, btnWidth, btnHeight);
	}
	
	public void layoutComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height-20));
		
		add(mainTittleLbl);
		add(companyNameLbl);
		add(enterPassword);
		add(reEnterPassLbl);
		add(invalidLbl);
		add(initialField);
		add(retypeField);
		add(enterBtn);
		add(returnBtn);
	}	
	
	public void initializeComponents()
	{
		enterBtn.initialize();
		returnBtn.initialize();
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
	
	public static void createEnterLabel(){
		enterPassword.setFont(new Font("Helvetica Neue",Font.PLAIN,12));
	    enterPassword.setBounds(227,45,225,40);
	}
	
	public static void createReEnterLabel() {
		reEnterPassLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,12));
		reEnterPassLbl.setBounds(227,120,225,40);
	}
	
	public static void createInvalidLabel()
	{
		invalidLbl.setFont(new Font("Helvetica Neue",Font.PLAIN,16));
		invalidLbl.setBounds(360,120,350,40);
		invalidLbl.setForeground(Color.RED);
		invalidLbl.setVisible(false);
	}
	
	public boolean comparePass()
	{
		String iniText = initialField.getText();
		String reText = retypeField.getText();
		boolean r = false;
		
		if (iniText.length() > 0 && reText.length() > 0) {
			if (iniText.equals(reText)){
				r = true;
			}
		}
		return r; 
	}
	
	public void clearTxtFields()
	{
		initialField.setText("");
		retypeField.setText("");
	}
	
	public String getPass()
	{
		passwordText = initialField.getText();
		return passwordText;
	}
	
	public void showWarning()
	{
		invalidLbl.setVisible(true);
	}
	
	public void	hideWarning()
	{
		invalidLbl.setVisible(false);
	}
}
