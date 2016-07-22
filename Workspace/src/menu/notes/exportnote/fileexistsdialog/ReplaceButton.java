package menu.notes.exportnote.fileexistsdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.Notes;
import menu.notes.exportnote.ExportPreviewNote;
import menu.notes.exportnote.FileDirectoryField;
import menu.notes.exportnote.FileNameField;
import program.util.FileUtil;

public class ReplaceButton extends JButton
{
	BufferPanel bufferPanel;
	
	public ReplaceButton (BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
		initialize();
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
				String fileLocation = FileDirectoryField.fileDirectoryTextField.getText() + "/" + FileNameField.fileNameTextField.getText() + ".txt"; // put together fileLocation
				
				FileUtil.overWriteFile(fileLocation, ExportPreviewNote.textArea.getText()); // overwrite text in the file
				
				FileExistsDialog.sameNameDialogFrame.setVisible(false);
				
				Notes.shareButton.requestFocusInWindow();
				bufferPanel.showPanel("NOTES");
			}
		});
	}
}
