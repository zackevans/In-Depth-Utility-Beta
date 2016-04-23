package menu.notes.exportnotepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import launch.app.LaunchApp;

public class ChooseButton extends JButton
{
	LaunchApp launchApp = new LaunchApp();
	private FileDirectoryField fileDirectoryField = new FileDirectoryField();
	private ExportErrorNotePanel exportErrorNotePanel = new ExportErrorNotePanel();
	
	public ChooseButton()
	{
		
	}
	
	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	public void createComponents()
	{
		setText("Choose ...");
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// create jfile chooser
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select File Location");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new java.io.File("/Desktop"));
				
				int status = fileChooser.showOpenDialog(launchApp.frame);
			
				if (status == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = fileChooser.getSelectedFile();
					String directory = selectedFile.getParent() + "/" + selectedFile.getName();
					
					fileDirectoryField.fileDirectoryTextField.setText(directory);
					
					exportErrorNotePanel.checkFileWarning();
				} 
				
				else if (status == JFileChooser.CANCEL_OPTION) 
				{
				    	  
				}
			}
		});
	}
}
