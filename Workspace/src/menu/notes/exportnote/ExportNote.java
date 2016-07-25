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
	
	/**
	 * Constructor:  ExportNote(BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This method calls the panel hierarchy and inherits the bufferPanel object.
	 */
	
	public ExportNote(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods setup the panel and its components.
	 */
	
	public void initialize()
	{
		createComponents();
		addComponents();
		initializeComponents();
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This function creates panel components and sets the size an location of them.
	 */
	
	public void createComponents()
	{
		// create the components
		selectNote = new SelectNote();
		errorNotePanel = new ExportErrorNotePanel();
		previewNote = new ExportPreviewNote();
		fileNameField = new FileNameField();
		fileDirectoryField = new FileDirectoryField();
		chooseDirectoryButton = new ChooseDirectoryButton();
		cancelButton = new ExportCancelButton(bufferPanel);
		exportButton = new ExportButton(bufferPanel);
		
		// set the size and location of the components
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
	
	/**
	 * Function: addComponents()
	 * @author ZackEvans
	 * 
	 * This function sets up the panel and adds the components to it.
	 */
	
	public void addComponents()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		setLayout(null); // remove layout manager from the panel 
		setPreferredSize(new Dimension(Window_Width,Window_Height)); // set the size of the panel
		setOpaque(false); // make the panel clear
		
		createContentPanel(); // create the panel to hold all of the main panel components.
		
		JLayeredPane layerPane = new JLayeredPane(); // errorPanel sits on TOP of the content panel
		
		// add components to the panel
		layerPane.add(contentPanel, new Integer(0),0); 
		layerPane.add(errorNotePanel, new Integer(1),0);
		
		// set the location of the panel and layers
		contentPanel.setBounds(0, 0, Window_Width,Window_Height);
		layerPane.setBounds(0, 0,Window_Width,Window_Height);
		errorNotePanel.setBounds(0, 0, Window_Width,Window_Height);
		
		add(layerPane); // add the layerPanel to the panel
	}
	
	/**
	 * Function: initializeComponents()
	 * @author ZackEvans
	 * 
	 * This function calls the methods to create the components.
	 */
	
	public void initializeComponents()
	{
		selectNote.initialize();
		previewNote.initialize();
		fileNameField.initialize();
		fileDirectoryField.initialize();
		cancelButton.initialize();
		exportButton.initialize();
	}
	
	/**
	 * Function: createContentPanel()
	 * @author ZackEvans
	 * 
	 * This function creates the content panel and adds the components to it.
	 */
	
	public void createContentPanel()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		contentPanel.setOpaque(false); // make the panel clear
		contentPanel.setLayout(null); // remove the layout manager
		contentPanel.setPreferredSize(new Dimension(Window_Width,Window_Height-50)); // set the size of panel
		
		// add components to the content panel
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
	
	/**
	 * Function: clearPanel()
	 * @author ZackEvans
	 * 
	 * This function clears all of the components on the panel
	 */
	
	public static void clearPanel()
	{
		SelectNote.comboBox.setSelectedIndex(0);
		PreviewNote.textArea.setText("");
		FileNameField.fileNameTextField.setText("");
		FileDirectoryField.fileDirectoryTextField.setText("");
		ExportErrorNotePanel.hideAllErrors();
	}
	
	/**
	 * Function: autoFill()
	 * @author ZackEvans
	 * 
	 * This function fills the selected note with the note selected on the note panel
	 */
	
	public static void autoFill()
	{
		SelectNote selectNote = new SelectNote();
		selectNote.updateData();
		
		if(NotesList.list.getSelectedIndex() != -1) // if there is a note selecetd
		{
			SelectNote.comboBox.setSelectedIndex(NotesList.list.getSelectedIndex() +1); // set the index in the index panel
		}
	}
	
}