package menu.notes.exportnote;

import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.notes.NotesList;
import menu.notes.mailnote.PreviewNote;


/**
 * Class: ExportNote
 * @author ZackEvans
 *
 * This class is a panel that holds all of the export note panels (Content Panel, ErrorNote Panel)
 */

public class ExportNote extends JPanel
{
	BufferPanel bufferPanel;
	JPanel contentPanel = new JPanel(); // this panel holds all of the components used to export the a note
	SelectNote selectNote;
	ExportErrorNotePanel errorNotePanel; 
	ExportPreviewNote previewNote;
	FileNameField fileNameField;
	FileDirectoryField fileDirectoryField;
	ChooseDirectoryButton chooseDirectoryButton;
	ExportCancelButton cancelButton;
	ExportButton exportButton;
	
	public ExportNote(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createComponents();
		addComponents();
		initializeComponents();
	}
	
	public void createComponents()
	{
		selectNote = new SelectNote();
		errorNotePanel = new ExportErrorNotePanel();
		previewNote = new ExportPreviewNote();
		fileNameField = new FileNameField();
		fileDirectoryField = new FileDirectoryField();
		chooseDirectoryButton = new ChooseDirectoryButton();
		cancelButton = new ExportCancelButton(bufferPanel);
		exportButton = new ExportButton(bufferPanel);
		
		SelectNote.comboBox.setBounds(150,30,400,20);
		previewNote.setBounds(0, 65, 700, 250);
		
		FileNameField.fileNameTextField.setBounds(225,350,225,22);
		fileNameField.fileNameLabel.setBounds(150, 350, 75, 22);
		
		FileDirectoryField.fileDirectoryTextField.setBounds(225, 380, 225, 22);
		fileDirectoryField.fileDirectoryLabel.setBounds(150, 380, 75, 22);
		
		chooseDirectoryButton.setBounds(455, 380, 100, 25);
		
		cancelButton.setBounds(480, 440, 100, 25);
		exportButton.setBounds(585, 440, 100, 25);
	}
	
	public void addComponents()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		setOpaque(false);
		
		createContentPanel();
		
		JLayeredPane layerPane = new JLayeredPane();
		
		layerPane.add(contentPanel, new Integer(0),0); 
		layerPane.add(errorNotePanel, new Integer(1),0);
		
		contentPanel.setBounds(0, 0, Window_Width,Window_Height);
		layerPane.setBounds(0, 0,Window_Width,Window_Height);
		errorNotePanel.setBounds(0, 0, Window_Width,Window_Height);
		
		add(layerPane);
	}
	
	public void initializeComponents()
	{
		selectNote.initialize();
		previewNote.initialize();
		fileNameField.initialize();
		fileDirectoryField.initialize();
		chooseDirectoryButton.initialize();
		cancelButton.initialize();
		exportButton.initialize();
	}
	
	public void createContentPanel()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		contentPanel.setOpaque(false);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(Window_Width,Window_Height-50));
		
		contentPanel.add(SelectNote.comboBox);
		contentPanel.add(previewNote);
		
		contentPanel.add(FileNameField.fileNameTextField);
		contentPanel.add(fileNameField.fileNameLabel);

		contentPanel.add(FileDirectoryField.fileDirectoryTextField);
		contentPanel.add(fileDirectoryField.fileDirectoryLabel);
		
		contentPanel.add(chooseDirectoryButton);
		
		contentPanel.add(cancelButton);
		contentPanel.add(exportButton);
		
	}
	
	public static void clearPanel()
	{
		SelectNote.comboBox.setSelectedIndex(0);
		PreviewNote.textArea.setText("");
		FileNameField.fileNameTextField.setText("");
		FileDirectoryField.fileDirectoryTextField.setText("");
		ExportErrorNotePanel.hideAllErrors();
	}
	
	public static void autoFill()
	{
		SelectNote selectNote = new SelectNote();
		selectNote.updateData();
		
		if(NotesList.list.getSelectedIndex() != -1)
		{
			SelectNote.comboBox.setSelectedIndex(NotesList.list.getSelectedIndex() +1);
		}
	}
	
}