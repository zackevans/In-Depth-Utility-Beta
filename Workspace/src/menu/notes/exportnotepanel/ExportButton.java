package menu.notes.exportnotepanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.samenamedialog.ExportTextLabel;
import menu.notes.samenamedialog.SameNameDialog;
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
	}

	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				ExportErrorNotePanel exportErrorNotePanel = new ExportErrorNotePanel();
				SelectExportNote selectExportNote = new SelectExportNote();
				FileNameField fileNameField = new FileNameField();
				FileDirectoryField fileDirectoryField = new FileDirectoryField();
				FileUtil fileUtil = new FileUtil();
				ExportPreviewNote exportPreviewNote = new ExportPreviewNote();
				SameNameDialog sameNameDialog = new SameNameDialog(bufferPanel);
				ExportTextLabel exportTextLabel = new ExportTextLabel(bufferPanel);
				// check seclection box
				
				String fileLocation = fileDirectoryField.fileDirectoryTextField.getText() + "/" + fileNameField.fileNameTextField.getText() + ".txt";
				
				if(selectExportNote.comboBox.getSelectedIndex() !=0 && fileNameField.fileNameTextField.getText().length() !=0 && fileDirectoryField.fileDirectoryTextField.getText().length() !=0)
				{
					if (!fileUtil.doesFileExist(fileLocation)) // check if file doesent exit.
					{				
						fileUtil.createNewFile(fileLocation, exportPreviewNote.textArea.getText());	
					}
					
					else
					{
						
						exportErrorNotePanel.checkFileWarning();
						sameNameDialog.launchDialog();
						exportTextLabel.createErrorText();
						// show popup that there is already the same file
					}
				}
				
				else
				{
					exportErrorNotePanel.checkFileWarning();
					
					exportErrorNotePanel.hideAllErrors();
					
					if (selectExportNote.comboBox.getSelectedIndex() ==0)
					{
						exportErrorNotePanel.selectNoteError.setVisible(true);
					}
					
					if (fileNameField.fileNameTextField.getText().length() == 0)
					{
						exportErrorNotePanel.fileNameError.setVisible(true);
					}
					
					if (fileDirectoryField.fileDirectoryTextField.getText().length() == 0)
					{
						exportErrorNotePanel.drictoryError.setVisible(true);
					}
				}
			}
		});
	}
}
