package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import launch.app.LaunchApp;

public class PasswordAttemptsDialog 
{
	public static final int Window_Width = 393; // Standard Dialog size
	public static final int Window_Height = 436; // Standard Dialog size
	public static JFrame customFrame = new JFrame(); // Created JFrame VAR.
	private AttemptsList attemptsList;
	private ClearButton clearButton;
	private OkButton okButton;
	private static boolean clicked = false;
	
	/**
	 * Function: launchDialog()
	 * @author ZackEvans
	 * 
	 * Function is called to launch the dialog window
	 */
	
	public void launchDialog()
	{
		if (clicked == false) // if the dialog has never been launched
		{
			createAndShowGUI(); // set up the window and launch it
			clicked = true; // dialog has been launched
		}
		
		else
		{
			showGUI(); // if the window has been shown before just launch it
		}
	}
	
	public void createAndShowGUI()
	{
		customFrame.setSize(Window_Width, Window_Height); // set the size of the window
		customFrame.setResizable(false); // make the window not be able to resize
		customFrame.getContentPane().setBackground(new Color(192,200,204)); // set color of frame
		
		createComponents(); // call function to create components to be added to panel
		initializeComponents();
		addComponents(); // add components to the panel
		addListeners();
		
		showGUI();
	}
	
	public void showGUI()
	{
		LaunchApp launchApp = new LaunchApp(); // create a var to be able to access the main frame
		// calculate the center of the main JFrame
		int x = launchApp.frameXPosition() + 154; // 154 centers window for x
		int y = launchApp.frameYPosition() + 43; // 167 centers window for y
		
		customFrame.setLocation(x, y); // set frame location
		
		customFrame.setVisible(true); // show the window
	}
	
	public void createComponents()
	{
		attemptsList = new AttemptsList();
		clearButton = new ClearButton();
		okButton = new OkButton();
		
		attemptsList.listScrollPane.setBounds(0, 0, customFrame.getWidth(), 300);
		clearButton.setBounds(197,335,80,25);
		okButton.setBounds(116, 335, 80,25);
	}
	
	public void initializeComponents()
	{
		attemptsList.createList();
		clearButton.initialize();
		okButton.initialize();
	}
	
	public void addComponents()
	{
		customFrame.getContentPane().setLayout(null);
		
		customFrame.getContentPane().add(attemptsList.listScrollPane);
		customFrame.getContentPane().add(clearButton);
		customFrame.getContentPane().add(okButton);
	}
	
	public void addListeners()
	{		
		customFrame.addWindowListener(new WindowListener()
		{
			@Override
			public void windowDeactivated(WindowEvent e) 
			{
				customFrame.setVisible(false);
			}
			
			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {}
			@Override
			public void windowClosed(WindowEvent e) {}
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}
}
