package menu.notes.addnotedialog;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import launch.app.LaunchApp;
import menu.notes.DisplayNotes;
import menu.notes.NotesList;
import sql.notes.NotesDataBase;

/**
 * Class: AddNoteDialog
 * @author ZackEvans
 *
 * Class that launches a dialog to prompt the user to create a note
 */

public class AddNoteDialog 
{
	public static final int Window_Width = 393; // Standard Dialog size
	public static final int Window_Height = 167; // Standard Dialog size
	public static JFrame customFrame = new JFrame(); // Created JFrame VAR.
	private static NoteNameField noteNameField;
	private EnterButton enterButton;
	private CancelButton cancelButton;
	private static JLabel enterLabel; 
	public static JLabel warningLabel;
	private static boolean clicked = false;
	
	/**
	 * Function: launchDialog()
	 * @author ZackEvans
	 * 
	 * Function is called to launch the dialog window
	 */
	
	public void launchDialog()
	{
		if (clicked == false) // if the dialog has never been launched
		{
			createAndShowGUI(); // set up the window and launch it
			clicked = true; // dialog has been launched
		}
		
		else
		{
			showGUI(); // if the window has been shown before just launch it
		}
	}
	
	/**
	 * Function : 
	 * @author ZackEvans
	 * 
	 * This function creates the dialog window and displays it.
	 */
	
	public void createAndShowGUI()
	{	
		customFrame.setSize(Window_Width, Window_Height); // set the size of the window
		customFrame.setResizable(false); // make the window not be able to resize
		customFrame.getContentPane().setBackground(new Color(192,200,204)); // set color of frame
		
		createComponents(); // call function to create components to be added to panel
		initializeComponents();
		addComponents(); // add components to the panel
		
		showGUI();
	}
	
	/**
	 * Function: showGUI()
	 * @author ZackEvans
	 * 
	 * This function sets the location of the window then displays it
	 */
	
	public void showGUI()
	{
		LaunchApp launchApp = new LaunchApp(); // create a var to be able to access the main frame
		// calculate the center of the main JFrame
		int x = launchApp.frameXPosition() + 154; // 154 centers window for x
		int y = launchApp.frameYPosition() + 167; // 167 centers window for y
		
		customFrame.setLocation(x, y); // set frame location
		
		clearWindow(); // reset all components in panel
		
		customFrame.setVisible(true); // show the window
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This function creates the components that will be added to the dialog frame
	 */
	
	public void createComponents()
	{
		// create obejcts to be added
		noteNameField = new NoteNameField();
		enterButton = new EnterButton();
		cancelButton = new CancelButton();
		enterLabel = new JLabel("Note Name: ");
		warningLabel = new DialogWarningLabel();
		
		// set the location and size of the object
		noteNameField.noteNameTextField.setBounds(75, 30, 243, 24);
		enterButton.setBounds(223, 80, 100, 25);
		cancelButton.setBounds(123,80,100,25);
		enterLabel.setBounds(77,3,225,40);
		warningLabel.setBounds(318,30,30,22);
	}
	
	/**
	 * Function: initializeComponents()
	 * @author ZackEvans
	 * 
	 * This function calls methods that will initialize the objects that are going to be added to the frame.
	 */
	
	public void initializeComponents()
	{
		// initialize components
		noteNameField.initialize();
		enterButton.initialize();
		cancelButton.initialize();
	}
	
	/**
	 * Function: addComponents()
	 * @author ZackEvans
	 * 
	 * Function adds the components to the dialog frame
	 */
	
	public void addComponents()
	{
		customFrame.getContentPane().setLayout(null); // dont have a layout manager for this panel
		
		// add components to the content panel of the frame
		customFrame.getContentPane().add(noteNameField.noteNameTextField); 
		customFrame.getContentPane().add(enterButton);
		customFrame.getContentPane().add(cancelButton);
		customFrame.getContentPane().add(enterLabel);
		customFrame.getContentPane().add(warningLabel);
	}
	
	/**
	 * Function: clearWindow()
	 * @author ZackEvans
	 * 
	 * Function resets the dialogs GUI
	 */
	
	public void clearWindow()
	{
		noteNameField.noteNameTextField.setText(""); // clear the name textfield
		noteNameField.noteNameTextField.requestFocus(); // set focous on the window
		warningLabel.setVisible(false);
	}
	
	/**
	 * Function: showDialogWarning
	 * @author ZackEvans
	 * @see warning message
	 * 
	 * This function shows a warning message in the text field and then highlights the text in the field.
	 */
	
	public static void showDialogWarningMessage() 
	{
		noteNameField.noteNameTextField.setText("Note MUST Contain a Name"); // show warning in the text field
		noteNameField.noteNameTextField.requestFocus(); // setFocous on text field
		noteNameField.noteNameTextField.selectAll(); // Highlight all text in text field
	}
	
	/**
	 * Function: createNote()
	 * @author ZackEvans
	 * @see warning text when
	 * 
	 * This function checks if a new note can be created. If so it creates it and adds it to the list
	 */
	
	public static void createNote()
	{
		NotesList notesList = new NotesList();
		NotesDataBase notesDataBase = new NotesDataBase(); // create object to access method to create a new note
		
		String noteName = noteNameField.noteNameTextField.getText(); // get name user entered 
		
		if (!(noteName.length() <= 0 || noteName.contains("Note MUST Contain a Name"))) // if there is not a error with the entered note name
		{
			notesDataBase.addNewNoteToList(noteName); // create new note in database
			notesList.loadData(); // load data from db
			NotesList.list.setSelectedIndex(0); // select the first index in the list
			
			DisplayNotes.textArea.setText("");
			DisplayNotes.textArea.requestFocusInWindow();
			DisplayNotes.textArea.setEditable(true);
			
			customFrame.setVisible(false); // hide window
		}
		
		else // if the text is valid
		{
			showDialogWarningMessage(); // show warning message in textfield
			warningLabel.setVisible(true); // hide window
		}
	}
}