package menu.notes.exportnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.exportnote.fileexistsdialog.DialogMessage;
import menu.notes.exportnote.fileexistsdialog.FileExistsDialog;
import program.util.FileUtil;

public class ExportButton extends JButton
{
	BufferPanel bufferPanel;
	
	public ExportButton (BufferPanel bufferPanel)
	{
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	public void createButton ()
	{
		setText("Export");
		setFocusPainted(false);
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String fileLocation = FileDirectoryField.fileDirectoryTextField.getText() + "/" + FileNameField.fileNameTextField.getText() + ".txt";
			
				if(SelectNote.comboBox.getSelectedIndex() !=0 && FileNameField.fileNameTextField.getText().length() !=0 && FileDirectoryField.fileDirectoryTextField.getText().length() !=0)
				{
					if (!FileUtil.doesFileExist(fileLocation)) // check if file doesen't exit.
					{				
						FileUtil.createNewFile(fileLocation, ExportPreviewNote.textArea.getText());	
						bufferPanel.showPanel("NOTES");
					}
					
					else
					{
						FileExistsDialog  fileExistsDialog = new FileExistsDialog(bufferPanel);
						
						fileExistsDialog.launchDialog();
						DialogMessage.updateErrorText();
					}
				}
				
				else
				{
					ExportErrorNotePanel.checkAllErrors();
				}
			}
		});
	}
}