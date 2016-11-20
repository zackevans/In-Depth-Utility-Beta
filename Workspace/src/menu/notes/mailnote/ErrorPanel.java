package menu.notes.mailnote;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.notes.mailnote.recipientviewer.RecipientViewer;

/**
 * Class: ErrorPanel 
 * @author ZackEvans
 *
 * This class is a clear panel that holds and displays error icons over the mail note panel
 */

public class ErrorPanel extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	static JLabel toFieldError = new JLabel(); // create label for toField
	static JLabel fromFieldError = new JLabel(); // create label for fromField
	static JLabel seclectNoteError = new JLabel(); // create label for selectNote
	
	/**
	 * Constructor: ErrorPanel
	 * @author ZackEvans
	 * 
	 * This constructor calls panel hierarchy and makes panel clear
	 */
	
	public ErrorPanel()
	{
		super(); // call panel hierarchy
		setOpaque(false); // make panel clear
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods that create the panel
	 */
	
	public void initialize()
	{
		createComponents();
		addComponents();
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This function creates all labels to be added to the panel
	 */
	
	public void createComponents()
	{
		URL url = ErrorPanel.class.getResource("/Button_Images/Notes/NoteErrors/Error.png"); // create URL for error image
		ImageIcon errorIcon = new ImageIcon(url); // create image icon for error image
		
		// set error icon an all labels
		toFieldError.setIcon(errorIcon);
		fromFieldError.setIcon(errorIcon);
		seclectNoteError.setIcon(errorIcon);
		
		// set tool tip text for each lable
		toFieldError.setToolTipText("Enter a Valid Email");
		fromFieldError.setToolTipText("Enter a name");
		seclectNoteError.setToolTipText("Select a note");
		
		//set position and size of each label
		toFieldError.setBounds(660,18,30,30);
		fromFieldError.setBounds(660,52,30,30);
		seclectNoteError.setBounds(660,79,30,30);
		
		hideAllErrors(); // hide all labels on panel
	}

	/**
	 * Function: addComponents()
	 * @author ZackEvans
	 * 
	 * This function adds all labels to panel
	 */
	
	public void addComponents()
	{
		setLayout(null); // get rid of layout manager
		setPreferredSize(new Dimension(Window_Width,Window_Height)); // set size of the panel
		
		// add all labels to panel
		add(toFieldError);
		add(fromFieldError);
		add(seclectNoteError);
	}
	
	/**
	 * Function: checkErrors()
	 * @author ZackEvans
	 * 
	 * This function checks all fields for errors and if one is found it displays corresponding error icon
	 */
	
	public static void checkErrors()
	{
		hideAllErrors(); // clear all errors
		
		// check all fields for errors
		if(RecipientViewer.listOfEmails.size() == 0)
		{
			toFieldError.setVisible(true);
		}
		
		if (FromField.textField.getText().length() == 0)
		{
			fromFieldError.setVisible(true);
		}
		
		if(SelectNote.comboBox.getSelectedIndex() == 0)
		{
			seclectNoteError.setVisible(true);
		}
	}
	
	/**
	 * Function: hideAllErrors()
	 * @author ZackEvans
	 * 
	 * This function hides all the labels on the panel
	 */
	
	public static void hideAllErrors()
	{
		toFieldError.setVisible(false);
		fromFieldError.setVisible(false);
		seclectNoteError.setVisible(false);
	}
}
