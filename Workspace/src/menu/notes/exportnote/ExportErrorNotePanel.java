package menu.notes.exportnote;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import program.util.FileUtil;

/**
 * Class: ExportErrorNotePanel
 * @author ZackEvans
 *
 * This class holds multiple JLabels that display errors and warning images  on components.
 */

public class ExportErrorNotePanel extends JPanel
{
	// create all labels
	public static JLabel selectNoteError = new JLabel();
	public static JLabel fileNameError = new JLabel();
	public static JLabel drictoryError = new JLabel();
	public static JLabel fileNameWarning = new JLabel();
	
	/**
	 * Constructor: ExportErrorNotePanel ()
	 * 
	 * This constructor calls panel hierarchy and calls a method to create the button.
	 */
	
	public ExportErrorNotePanel ()
	{
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to set up the panel and error labels
	 */
	
	public void initialize()
	{
		createPanel();
		createComponents();
		addComponents();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function makes the panel clear
	 */
	
	public void createPanel()
	{
		setOpaque(false);
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This method creates all the images and tooltip text. This method also sets the size and location of the labels.
	 */
	
	public void createComponents()
	{
		// create URLs for images and create image icon for the warrnings.
		URL errorUrl = ExportErrorNotePanel.class.getResource("/Button_Images/Notes/NoteErrors/Error.png");
		URL warningUrl = ExportErrorNotePanel.class.getResource("/Button_Images/Notes/NoteErrors/WarningSign.png");
		ImageIcon errorIcon = new ImageIcon(errorUrl);
		ImageIcon warningIcon = new ImageIcon(warningUrl);
		
		// set labels icons.
		selectNoteError.setIcon(errorIcon);
		fileNameError.setIcon(errorIcon);
		drictoryError.setIcon(errorIcon);
		fileNameWarning.setIcon(warningIcon);
		
		// set the tiiltip text for all of the errors.
		selectNoteError.setToolTipText("Select a note");
		fileNameError.setToolTipText("Enter a file name");
		drictoryError.setToolTipText("Add location");
		fileNameWarning.setToolTipText("This file already exists.");
		
		// set the size and location of the errors.
		selectNoteError.setBounds(505,30,30,20);
		fileNameError.setBounds(430,350,30,22);
		drictoryError.setBounds(430,380,30,22);
		fileNameWarning.setBounds(450,350,30,22);
		
		hideAllErrors(); // hide all the label errors
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function removes the layout manager from the panel and the size of the panel
	 * Then it adds the errors to the panel.
	 */
	
	public void addComponents()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		setLayout(null); // remove layout manager
		setPreferredSize(new Dimension(Window_Width,Window_Height)); // set the size of the panel
		
		// add all labels to the panel
		add(selectNoteError);
		add(fileNameError);
		add(drictoryError);
		add(fileNameWarning);
	}
	
	/**
	 * Function: hideAllErrors()
	 * 
	 * This method hides all of the labels on the panel
	 */
	
	public static void hideAllErrors()
	{
		selectNoteError.setVisible(false);
		fileNameError.setVisible(false);
		drictoryError.setVisible(false);
		fileNameWarning.setVisible(false);
	}
	
	/**
	 * Function: checkAllErrors()
	 * 
	 * This function hides all the errors on the panel then checks all of the components on the export panel for errors.
	 */
	
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
		
		// check if there is text in the file directory field.
		if (FileDirectoryField.fileDirectoryTextField.getText().length() == 0) 
		{
			ExportErrorNotePanel.drictoryError.setVisible(true);
		}
	}
	
	/**
	 * Function: checkFileWarning()
	 * 
	 * This function checks if the file already exists and if it does then it shows the fileNameWarning.
	 */
	
	public static void checkFileWarning()
	{	
		String filePath = FileDirectoryField.fileDirectoryTextField.getText() + "/" + FileNameField.fileNameTextField.getText() + ".txt";
		
		// check if the file exists
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
