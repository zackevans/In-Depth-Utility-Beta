package menu.notes.samenamedialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;
import menu.notes.exportnotepanel.ExportPreviewNote;
import menu.notes.exportnotepanel.FileDirectoryField;
import menu.notes.exportnotepanel.FileNameField;
import program.util.FileUtil;

public class ReplaceButton extends JButton
{	
	BufferPanel bufferPanel;
	
	public ReplaceButton (BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	public void createBtn()
	{
		setText("Replace");
		setFocusable(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// create objects needed
				FileUtil fileUtil = new FileUtil();
				FileDirectoryField fileDirectoryField = new FileDirectoryField();
				FileNameField fileNameField = new FileNameField();
				ExportPreviewNote exportPreviewNote = new ExportPreviewNote();
				SameNameDialog sameNameDialog = new SameNameDialog(bufferPanel);
				Notes notes = new Notes(bufferPanel);
				
				String fileLocation = fileDirectoryField.fileDirectoryTextField.getText() + "/" + fileNameField.fileNameTextField.getText() + ".txt"; // pice together fileLocation
				fileUtil.overWriteFile(fileLocation, exportPreviewNote.textArea.getText()); // overwrite text in the file
				
				sameNameDialog.sameNameDialogFrame.setVisible(false);
				notes.infoButton.requestFocusInWindow();
				bufferPanel.showPanel("NOTES");
				
			}
		});
	}
}
