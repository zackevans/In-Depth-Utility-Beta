package menu.notes.samenamedialog;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import launch.app.LaunchApp;
import menu.buffer.BufferPanel;
import menu.notes.sharenotesdialog.ShareList;

public class SameNameDialog 
{
	public static JFrame sameNameDialogFrame= new JFrame(); 
	
	BufferPanel bufferPanel;
	public static final int Window_Width = 393; // Standard Dialog size
	public static final int Window_Height = 167; // Standard Dialog size
	private LaunchApp launchApp = new LaunchApp(); 
	private ExportTextLabel exportTextArea;
	private CancelButton cancelButton;
	private RenameButton renameButton;
	private ReplaceButton replaceButton;
	private WarningImage warningImage;
	private boolean clicked = false;
	
	public SameNameDialog(BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
	}
	
	public void launchDialog()
	{	
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
	
	public void createAndShowGUI()
	{
		createComponents(); // create components first before using launch app
		
		int x = launchApp.frameXPosition() + 154; 
		int y = launchApp.frameYPosition() + 167; // centers window for y
		sameNameDialogFrame.setLocation(x, y); // set frame in center of main frame 
		
		sameNameDialogFrame.setSize(Window_Width, Window_Height); 
		sameNameDialogFrame.setMinimumSize(new Dimension(Window_Width,Window_Height));
		sameNameDialogFrame.setResizable(false);
		
		addComponents();
		
		sameNameDialogFrame.setVisible(true);
	}
	
	public void showWindow()
	{
		int x = launchApp.frameXPosition() + 154; 
		int y = launchApp.frameYPosition() + 167; // centers window for y
		sameNameDialogFrame.setLocation(x, y); // set frame in center of main frame 
		
		ShareList.list.clearSelection();
		sameNameDialogFrame.setVisible(true);
	}
	
	public void createComponents()
	{
		exportTextArea = new ExportTextLabel(bufferPanel);
		cancelButton = new CancelButton(bufferPanel);
		renameButton = new RenameButton(bufferPanel);
		replaceButton = new ReplaceButton(bufferPanel);
		warningImage = new WarningImage();
		
		cancelButton.initialize();
		renameButton.initialize();
		replaceButton.initialize();
		warningImage.initialize();
		
		//exportTextArea.errorTextArea.setBorder(BorderFactory.createLineBorder(Color.RED));
		exportTextArea.errorTextArea.setBounds(100, 30, 293, 50);
		
		warningImage.warningLabel.setBounds(-10,16,120,75);
		//warningImage.warningLabel.setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	
	public void addComponents()
	{
		sameNameDialogFrame.getContentPane().setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 100, Window_Width, 30);
		//buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		buttonPanel.add(cancelButton);
		buttonPanel.add(renameButton);
		buttonPanel.add(replaceButton);
		
		sameNameDialogFrame.getContentPane().add(exportTextArea.errorTextArea);	
		sameNameDialogFrame.getContentPane().add(warningImage.warningLabel);
		sameNameDialogFrame.getContentPane().add(buttonPanel);
	}
}
