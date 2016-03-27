package menu.notes.notemailerrordialog;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;

public class MailNoteErrorDialog 
{
	public static JFrame errorFrame = new JFrame();
	public static final int Window_Width = 393; // Standard Dialog size
	public static final int Window_Height = 167;
	private BufferPanel bufferPanel;
	private LaunchApp launchApp = new LaunchApp();
	private JTextArea errorLabel;
	public static JCheckBox checkBox;
	private ErrorImage errorImage;
	private SaveAndSendButton saveAndSendButton;
	private DiscardButton discardButton;
	private boolean clicked = false;
	public static String[] to;
	public static String subject;
	public static String body;
	
	public MailNoteErrorDialog(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void launchDialog (String[] to, String subject, String body)
	{
		this.to = to;
		this.subject = subject;
		this.body = body;
		
		if (clicked == false)
		{
			createAndShowGUI();
			clicked = true;
		}
		
		else
		{
			showWindow();
		}
	}
	
	private void createAndShowGUI()
	{
		int x = launchApp.frameXPosition() + 154; 
		int y = launchApp.frameYPosition() + 167;
		
		errorFrame.setSize(Window_Width, Window_Height); 
		errorFrame.setMinimumSize(new Dimension(Window_Width,Window_Height));
		errorFrame.setResizable(false);
		errorFrame.setLocation(x, y); // set frame in center of main frame 
		
		createComponents();
		
		errorFrame.getContentPane().setLayout(null);
		
		addComponents();
		
		errorFrame.setVisible(true);
	}
	
	private void showWindow()
	{
		int x = launchApp.frameXPosition() + 154; 
		int y = launchApp.frameYPosition() + 167;
		errorFrame.setLocation(x, y); // set frame in center of main frame
		errorFrame.setVisible(true);
	}
	
	public void createComponents()
	{
		createErrorImage();
		createErrorLabel();
		createCheckbox();
		createSaveAndSendButton();
		createDiscardButton();
	}
	
	public void addComponents()
	{
		errorFrame.add(errorLabel);
		errorFrame.add(checkBox);
		errorFrame.add(errorImage.label);
		errorFrame.add(saveAndSendButton);
		errorFrame.add(discardButton);
	}
	
	public void createErrorImage()
	{
		errorImage = new ErrorImage();
		errorImage.label.setBounds(10,16,75,55);
		//errorImage.label.setBorder(BorderFactory.createLineBorder(Color.red));
		errorImage.initialize();
	}
	
	public void createErrorLabel()
	{
		errorLabel = new JTextArea();
		errorLabel.setWrapStyleWord(true);
		errorLabel.setLineWrap(true);
		errorLabel.setOpaque(false);
		errorLabel.setEditable(false);
		errorLabel.setFocusable(false);
		errorLabel.setText("You are not currently connected to the internet. Would you like to send this message when you come back online.");
		errorLabel.setBounds(95, 16, 298,50 );
		//errorLabel.setBorder(BorderFactory.createLineBorder(Color.red));
	}	
	
	public void createCheckbox()
	{
		checkBox = new JCheckBox("Always Save & Send");
		checkBox.setBounds(89, 70, 200, 25);
		checkBox.setFocusPainted(false); // removed blue outline when clicked
		checkBox.setBorderPainted(false);
		//checkBox.setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	public void createSaveAndSendButton()
	{
		saveAndSendButton = new SaveAndSendButton(bufferPanel);
		saveAndSendButton.initialize();
		saveAndSendButton.setFocusPainted(false);
		saveAndSendButton.setBounds(253, 110, 120, 25);
	}
	
	public void createDiscardButton()
	{
		discardButton = new DiscardButton(bufferPanel);
		discardButton.initialize();
		discardButton.setFocusPainted(false);
		discardButton.setBounds(148,110, 100, 25);
	}
}