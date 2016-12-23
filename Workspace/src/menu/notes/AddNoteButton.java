package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.notes.addnotedialog.AddNoteDialog;

/**
 * Class: AddNoteButton
 * @author ZackEvans
 *
 * This class is a button. When the button is clicked it will launch a pop up window
 */

public class AddNoteButton extends JButton
{
	/**
	 * Constructor: AddNoteButton ()
	 * 
	 * This constructor calls button hierarchy
	 */
	
	public AddNoteButton ()
	{
		super(); // call hierarchy
	}
	
	/**
	 * Function: initialize ()
	 * 
	 * Function calls methods to setup button and and a listener to it.
	 */
	
	public void initialize ()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * 
	 * Function adds an image to the button
	 */
	
	public void createButton()
	{
		URL url = AddNoteButton.class.getResource("/Button_Images/Notes/NotesPanel/Add.png"); // add resource to the project
		ImageIcon icon = new ImageIcon(url); // create a image icon from the URL
		setIcon(icon); // set image on the button
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * Function adds a listener to the button
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add listener to the button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // preform this action when the button is clicked
			{
				AddNoteDialog addNoteDialog = new AddNoteDialog(); // create object to launch the dialog window
				
				System.out.println("Add Button");
				addNoteDialog.launchDialog(); // call method to launch window
			}
		});
	}
}
