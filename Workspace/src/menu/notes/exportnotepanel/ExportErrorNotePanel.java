package menu.notes.exportnotepanel;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import program.util.FileUtil;

public class ExportErrorNotePanel extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	
	public static JLabel selectNoteError = new JLabel();
	public static JLabel fileNameError = new JLabel();
	public static JLabel drictoryError = new JLabel();
	public static JLabel fileNameWarning = new JLabel();
	
	private FileNameField fileNameField = new FileNameField();
	private FileDirectoryField fileDirectoryField = new FileDirectoryField();
	private FileUtil fileUtil = new FileUtil();
	
	
	public ExportErrorNotePanel()
	{
		super();
		setOpaque(false);
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
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		add(selectNoteError);
		add(fileNameError);
		add(drictoryError);
		add(fileNameWarning);
	}
	
	public void hideAllErrors()
	{
		selectNoteError.setVisible(false);
		fileNameError.setVisible(false);
		drictoryError.setVisible(false);
		fileNameWarning.setVisible(false);
	}
	
	public void checkFileWarning()
	{	
		String filePath = FileDirectoryField.fileDirectoryTextField.getText() + "/" + FileNameField.fileNameTextField.getText() + ".txt";
		
		if (fileUtil.doesFileExist(filePath))
		{
			fileNameWarning.setVisible(true);
		}
		
		else
		{
			fileNameWarning.setVisible(false);
		}
	}
}
