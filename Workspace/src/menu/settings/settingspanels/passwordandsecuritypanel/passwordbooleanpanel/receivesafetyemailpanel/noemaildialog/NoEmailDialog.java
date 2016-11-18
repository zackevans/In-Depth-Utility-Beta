package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.receivesafetyemailpanel.noemaildialog;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import launch.app.LaunchApp;
import menu.notes.exportnote.fileexistsdialog.WarningImage;
import menu.settings.settingsbufferpanel.SettingsBufferPanel;

public class NoEmailDialog 
{
	public static JFrame noEmailDialogFrame= new JFrame();
	private static boolean clicked = false;
	JLabel warningText;
	JLabel warningImage;
	JButton cancelButton;
	JButton addEmailButton;
	SettingsBufferPanel settingsBufferPanel;
	
	public NoEmailDialog (SettingsBufferPanel settingsBufferPanel)
	{
		this.settingsBufferPanel = settingsBufferPanel;
	}
		
	public void launchDialog()
	{	
		if (clicked == false) // if the panel has not been clicked
		{
			createAndShowGUI(); // creaet the panel and show it.
			clicked = true; // set the obejct to be marked as shown
		}
		
		else // if the window has been shown
		{
			showWindow(); // show the dialog window.
		}
	}
	
	public void createAndShowGUI()
	{
		createComponents(); // call method to create the components on the panel
		
		noEmailDialogFrame.setSize(393, 167);  // set the size of the dialog frame.
		noEmailDialogFrame.setResizable(false); // make the frame note resizeable
		
		addComponents(); // add the components to the frame
		
		showWindow(); // display the window.
	}
	
	public void showWindow()
	{
		LaunchApp launchApp = new LaunchApp();
		
		// set location of the dialog
		int x = launchApp.frameXPosition() + 154; 
		int y = launchApp.frameYPosition() + 167; // centers window for y
		noEmailDialogFrame.setLocation(x, y); // set frame in center of main frame 
		
		//ShareList.list.clearSelection();
		noEmailDialogFrame.setVisible(true);
	}
	
	public void createComponents()
	{
		warningText = new JLabel("An Email Doesent Exist");
		warningImage = new WarningImage();
		cancelButton = new CancelButton();
		addEmailButton = new AddEmailButton(settingsBufferPanel);
		
		warningText.setBounds(130, 40, 293, 25);
		warningText.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		
		warningImage.setBounds(-10,16,120,75);
		
		cancelButton.setBounds(170, 105, 100,25);
		addEmailButton.setBounds(275, 105, 100,25);
	}
	
	public void addComponents()
	{
		noEmailDialogFrame.getContentPane().setLayout(null);
		
		noEmailDialogFrame.getContentPane().add(warningText);
		noEmailDialogFrame.getContentPane().add(warningImage);
		noEmailDialogFrame.getContentPane().add(cancelButton);
		noEmailDialogFrame.getContentPane().add(addEmailButton);
	}
}
