package menu.notes.exportnote;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import program.util.FileUtil;

public class ExportErrorNotePanel extends JPanel
{
	public static JLabel selectNoteError = new JLabel();
	public static JLabel fileNameError = new JLabel();
	public static JLabel drictoryError = new JLabel();
	public static JLabel fileNameWarning = new JLabel();
	
	public ExportErrorNotePanel ()
	{
		super();
		setOpaque(false);
		initialize();
	}
	
	public void initialize()
	{
		createComponents();
		addComponents();
	}
	
	public void createComponents()
	{
		URL errorUrl = ExportErrorNotePanel.class.getResource("/Button_Images/Notes/NoteErrors/Error.png");
		URL warningUrl = ExportErrorNotePanel.class.getResource("/Button_Images/Notes/NoteErrors/WarningSign.png");
		ImageIcon errorIcon = new ImageIcon(errorUrl);
		ImageIcon warningIcon = new ImageIcon(warningUrl);
		
		
		selectNoteError.setIcon(errorIcon);
		fileNameError.setIcon(errorIcon);
		drictoryError.setIcon(errorIcon);
		fileNameWarning.setIcon(warningIcon);
		
		selectNoteError.setToolTipText("Select a note");
		fileNameError.setToolTipText("Enter a file name");
		drictoryError.setToolTipText("Add location");
		fileNameWarning.setToolTipText("This file already exists.");
		
		selectNoteError.setBounds(505,30,30,20);
		fileNameError.setBounds(430,350,30,22);
		drictoryError.setBounds(430,380,30,22);
		fileNameWarning.setBounds(450,350,30,22);
		
		hideAllErrors();
	}
	
	public void addComponents()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		add(selectNoteError);
		add(fileNameError);
		add(drictoryError);
		add(fileNameWarning);
	}
	
	public static void hideAllErrors()
	{
		selectNoteError.setVisible(false);
		fileNameError.setVisible(false);
		drictoryError.setVisible(false);
		fileNameWarning.setVisible(false);
	}
	
	public static void checkAllErrors()
	{
		hideAllErrors(); // clear everything
		
		checkFileWarning(); // check if the file name is already taken
		
		// check if a note is not selected in the combobox.
		if (SelectNote.comboBox.getSelectedIndex() ==0) 
		{
			ExportErrorNotePanel.selectNoteError.setVisible(true);
		}
		
		// check if the file name field is empty
		if (FileNameField.fileNameTextField.getText().length() == 0)
		{
			ExportErrorNotePanel.fileNameError.setVisible(true);	
		}
		
		if (FileDirectoryField.fileDirectoryTextField.getText().length() == 0) 
		{
			ExportErrorNotePanel.drictoryError.setVisible(true);
		}
	}
	
	public static void checkFileWarning()
	{	
		String filePath = FileDirectoryField.fileDirectoryTextField.getText() + "/" + FileNameField.fileNameTextField.getText() + ".txt";
		
		if (FileUtil.doesFileExist(filePath))
		{
			fileNameWarning.setVisible(true);
		}
		
		else
		{
			fileNameWarning.setVisible(false);
		}
	}
}
