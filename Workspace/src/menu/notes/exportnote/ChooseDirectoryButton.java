package menu.notes.exportnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import launch.app.LaunchApp;

public class ChooseDirectoryButton extends JButton
{
	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	public void createComponents()
	{
		setText("Choose ...");
		setFocusPainted(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// create JFile chooser
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select File Location");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new java.io.File("/Desktop"));
				
				int status = fileChooser.showOpenDialog(LaunchApp.frame);
			
				if (status == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = fileChooser.getSelectedFile();
					String directory = selectedFile.getParent() + "/" + selectedFile.getName();
					
					FileDirectoryField.fileDirectoryTextField.setText(directory);
					
					ExportErrorNotePanel.checkFileWarning();
				} 
			}
		});
	}
}
