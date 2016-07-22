package menu.notes.exportnote.fileexistsdialog;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;

public class FileExistsDialog 
{
	public static JFrame sameNameDialogFrame= new JFrame(); 
	BufferPanel bufferPanel;
	DialogMessage dialogMessage;
	JLabel warningImage;
	JButton replaceButton;
	JButton renameButton;
	JButton cancelButton;
	private static boolean clicked = false;
	
	public FileExistsDialog (BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
	}
	
	public void launchDialog()
	{	
		if (clicked == false)
		{
			System.out.println("False");
			createAndShowGUI();
			clicked = true;
		}
		
		else
		{
			System.out.println("True");
			showWindow();
		}
	}
	
	
	public void createAndShowGUI()
	{
		createComponents();
		
		sameNameDialogFrame.setSize(393, 167); 
		sameNameDialogFrame.setResizable(false);
		
		addComponents();
		
		showWindow();
	}
	
	public void showWindow()
	{
		LaunchApp launchApp = new LaunchApp();
		
		int x = launchApp.frameXPosition() + 154; 
		int y = launchApp.frameYPosition() + 167; // centers window for y
		sameNameDialogFrame.setLocation(x, y); // set frame in center of main frame 
		
		//ShareList.list.clearSelection();
		sameNameDialogFrame.setVisible(true);
	}
	
	public void createComponents()
	{
		dialogMessage = new DialogMessage();
		warningImage = new WarningImage();
		replaceButton = new ReplaceButton(bufferPanel);
		renameButton = new RenameButton();
		cancelButton = new CancelButton();
		
		DialogMessage.errorTextArea.setBounds(100, 30, 293, 50);
		warningImage.setBounds(-10,16,120,75);
	}
	
	public void addComponents()
	{
		sameNameDialogFrame.getContentPane().setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 100, 393, 30);
		
		buttonPanel.add(cancelButton);
		buttonPanel.add(renameButton);
		buttonPanel.add(replaceButton);
		
		sameNameDialogFrame.getContentPane().add(buttonPanel);
		sameNameDialogFrame.getContentPane().add(warningImage);
		sameNameDialogFrame.getContentPane().add(DialogMessage.errorTextArea);
	}
}
