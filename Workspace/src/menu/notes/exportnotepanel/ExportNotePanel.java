package menu.notes.exportnotepanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class ExportNotePanel extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	BufferPanel bufferPanel;
	private SelectExportNote selectExportNote;
	private PreviewExportNote previewExportNote;
	private FileNameField fileNameField;
	private LocationField locationField;
	private ChooseLocationButton chooseButton;
	private CancelButton cancelButton;
	private ExportButton exportButton;
	
	public ExportNotePanel(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false);
	}
	
	public void initialize()
	{
		createComponents();
		initializeComponents();
		addComponents();
	}
	
	public void createComponents()
	{
		selectExportNote = new SelectExportNote();
		previewExportNote = new PreviewExportNote();
		fileNameField = new FileNameField();
		locationField = new LocationField();
		chooseButton = new ChooseLocationButton();
		cancelButton = new CancelButton(bufferPanel);
		exportButton = new ExportButton(bufferPanel);
		
		selectExportNote.comboBox.setBounds(150,30,400,20);
		previewExportNote.setBounds(0, 65, 700, 250);
		
		fileNameField.fileNameLabel.setBounds(150, 350, 75, 22);
		fileNameField.fileNameField.setBounds(225,350,225,22);
		
		locationField.locationLabel.setBounds(150, 380, 75, 22);
		locationField.locationField.setBounds(225, 380, 225, 22);
		
		chooseButton.setBounds(455, 380, 100, 25);
		
		cancelButton.setBounds(480, 440, 100, 25); 
		exportButton.setBounds(585, 440, 100, 25);
	}
	
	public void initializeComponents()
	{
		selectExportNote.initialize();
		previewExportNote.initialize();
		fileNameField.initialize();
		locationField.initialize();
		chooseButton.initialize();
		cancelButton.initialize();
		exportButton.initialize();
	}
	
	public void addComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		add(selectExportNote.comboBox);
		add(previewExportNote);
		add(fileNameField.fileNameLabel);
		add(fileNameField.fileNameField);
		add(locationField.locationLabel);
		add(locationField.locationField);
		add(chooseButton);
		add(cancelButton);
		add(exportButton);
	}
}
