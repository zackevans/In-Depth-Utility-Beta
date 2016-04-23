package menu.notes.exportnotepanel;

import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class ExportNotePanel extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	
	BufferPanel bufferPanel;
	JPanel contentPanel = new JPanel();
	
	private FileNameField fileNameField;
	private ExportErrorNotePanel exportErrorNotePanel;
	private FileDirectoryField fileDirectoryField;
	private ChooseButton chooseButton;
	private ExportButton exportButton;
	private SelectExportNote selectExportNote;
	private ExportPreviewNote exportPreviewNote;
	private ExportCancelButton exportCancelButton;
	
	public ExportNotePanel(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false);
	}
	
	public void initialize()
	{
		createComponents();
		addComponents();
		initializeComponents();
	}
	
	public void createComponents()
	{
		fileNameField = new FileNameField();
		exportErrorNotePanel = new ExportErrorNotePanel();
		fileDirectoryField = new FileDirectoryField();
		chooseButton = new ChooseButton();
		exportButton = new ExportButton(bufferPanel);
		selectExportNote = new SelectExportNote();
		exportPreviewNote = new ExportPreviewNote();
		exportCancelButton = new ExportCancelButton(bufferPanel);
		
		fileNameField.fileNameTextField.setBounds(225,350,225,22);
		fileNameField.fileNameLabel.setBounds(150, 350, 75, 22);
		
		fileDirectoryField.fileDirectoryTextField.setBounds(225, 380, 225, 22);
		fileDirectoryField.fileDirectoryLabel.setBounds(150, 380, 75, 22);
		
		chooseButton.setBounds(455, 380, 100, 25);
		
		exportButton.setBounds(585, 440, 100, 25);
		exportCancelButton.setBounds(480, 440, 100, 25);
		
		selectExportNote.comboBox.setBounds(150,30,400,20);
		exportPreviewNote.setBounds(0, 65, 700, 250);
	}
	
	public void initializeComponents()
	{
		chooseButton.initialize();
		fileDirectoryField.initialize();
		fileNameField.initialize();
		exportErrorNotePanel.initialize();
		exportButton.initialize();
		selectExportNote.initialize();
		exportPreviewNote.initialize();
		exportCancelButton.initialize();
	}
	
	public void addComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		createContentPanel();
		
		JLayeredPane layerPane = new JLayeredPane();
		
		layerPane.add(contentPanel, new Integer(0),0);
		layerPane.add(exportErrorNotePanel, new Integer(1),0);
		
		contentPanel.setBounds(0, 0, Window_Width,Window_Height);
		layerPane.setBounds(0, 0,Window_Width,Window_Height);
		exportErrorNotePanel.setBounds(0, 0, Window_Width,Window_Height);
		
		add(layerPane);
	}
	
	public void createContentPanel()
	{
		contentPanel.setOpaque(false);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(Window_Width,Window_Height-50));
		
		contentPanel.add(fileNameField.fileNameTextField);
		contentPanel.add(fileNameField.fileNameLabel);
		
		contentPanel.add(fileDirectoryField.fileDirectoryTextField);
		contentPanel.add(fileDirectoryField.fileDirectoryLabel);
		
		contentPanel.add(chooseButton);
		
		contentPanel.add(exportButton);
		contentPanel.add(exportCancelButton);
		
		contentPanel.add(selectExportNote.comboBox);
		contentPanel.add(exportPreviewNote);
	}
	
	public void clearAllField()
	{
		fileDirectoryField.fileDirectoryTextField.setText("");
		exportPreviewNote.textArea.setText("");
		fileNameField.fileNameTextField.setText("");
		selectExportNote.comboBox.setSelectedIndex(0);
	}
}
