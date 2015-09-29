package panel.customnotedialog;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import launch.app.LaunchApp;

public class NoteDialog 
{
	public static final int Window_Width = 393; // Standard Dialog size
	public static final int Window_Height = 167; // Standard Dialog size
	private static JFrame customFrame = new JFrame(); // Created JFrame VAR.
	private static JLabel enterLabel = new JLabel("Note Name: ");
	private LaunchApp launchApp = new LaunchApp(); // created to acess frame location
	private DialogField dialogField = new DialogField();
	private CancelBtn cancelBtn = new CancelBtn(this);
	private EnterButton enterBtn = new EnterButton(this);
	
	public NoteDialog()
	{
		
	}
	
	public void launchDialog()
	{
		createAndShowGUI();
	}
	
	public void createAndShowGUI()
	{
		int x = launchApp.frameXPosition() + 154; // 154 centers window for x
		int y = launchApp.frameYPosition() + 167; // 167 centers window for y
		
		customFrame.setSize(Window_Width, Window_Height); 
		customFrame.setMinimumSize(new Dimension(Window_Width,Window_Height));
		customFrame.setResizable(false);
		customFrame.setLocation(x, y); // set frame in center of main frame 
		
		createComponents();
		
		customFrame.getContentPane().setLayout(null);
		
		addComponents();
		
		dialogField.setText("");
		dialogField.requestFocus();
	
		customFrame.setVisible(true);
	}
	
	public void createComponents()
	{
		dialogField.setBounds(75, 30, 243, 30);
		cancelBtn.setBounds(75,80,100,45);
		enterBtn.setBounds(223, 80, 100, 45);
		createEnterLabel();
	}
	
	public void addComponents()
	{
		customFrame.getContentPane().add(dialogField);
		customFrame.getContentPane().add(enterLabel);
		customFrame.getContentPane().add(cancelBtn);
		customFrame.getContentPane().add(enterBtn);
	}
	
	public static void  createEnterLabel()
	{
		enterLabel.setFont(new Font("Helvetica Neue",Font.PLAIN,12)); // set font and set text size = 12
	    enterLabel.setBounds(77,3,225,40); // set postion and width and height of label
	}
	
	public void showDialog(boolean tf)
	{
		customFrame.setVisible(tf);
	}
	
	public String getFieldText()
	{
		return dialogField.getText();
	}
	
	public void showDialogError()
	{
		dialogField.setText("Note MUST Contain a Name");
		dialogField.requestFocus(); // setFocous on textfield
		dialogField.selectAll(); // hilight all text in text field
	}
	
}
