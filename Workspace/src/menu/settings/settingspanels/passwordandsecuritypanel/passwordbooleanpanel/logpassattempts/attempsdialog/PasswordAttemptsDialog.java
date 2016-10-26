package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import file.files.PasswordAttemptsFile;
import launch.app.LaunchApp;

public class PasswordAttemptsDialog 
{
	public static final int Window_Width = 393; // Standard Dialog size
	public static final int Window_Height = 436; // Standard Dialog size
	public static JFrame customFrame = new JFrame(); // Created JFrame VAR.
	private AttemptsList attemptsList;
	private ClearButton clearButton;
	private OkButton okButton;
	private JLabel titleLabel;
	public static EmptyListIconPanel emptyListIconPanel;
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
		
		if( PasswordAttemptsFile.isEmpty())
		{
			emptyListIconPanel.setVisible(true);
		}
		
		else
		{
			emptyListIconPanel.setVisible(false);
		}
		
		customFrame.setVisible(true); // show the window
	}
	
	public void createComponents()
	{
		attemptsList = new AttemptsList();
		clearButton = new ClearButton();
		okButton = new OkButton();
		titleLabel = new TitleLabel();
		emptyListIconPanel = new EmptyListIconPanel();
		
		titleLabel.setBounds(0, 0,customFrame.getWidth(), 50);
		attemptsList.listScrollPane.setBounds(0, 50, customFrame.getWidth(), 300);
		clearButton.setBounds(116,370,80,25);
		okButton.setBounds(197,370,80,25);
		emptyListIconPanel.setBounds(0, 50, customFrame.getWidth(), 300);
	}
	
	public void initializeComponents()
	{
		attemptsList.createList();
		clearButton.initialize();
		okButton.initialize();
	}
	
	public void addComponents()
	{
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.add(titleLabel);
		contentPanel.add(attemptsList.listScrollPane);
		contentPanel.add(clearButton);
		contentPanel.add(okButton);
		contentPanel.setBounds(0, 0, Window_Width,Window_Height);
		
		JLayeredPane layerPane = new JLayeredPane();
		layerPane.add(contentPanel, new Integer(0), 0);
        layerPane.add(emptyListIconPanel, new Integer(1), 0);	
        
        customFrame.getContentPane().add(layerPane);
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
