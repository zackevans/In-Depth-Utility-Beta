package menu.notes.addnotedialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class: EnterButton
 * @author ZackEvans
 * 
 * This class is a button that when clicked will create a new note in the database
 */

public class EnterButton extends JButton
{
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function calls methods to create button.
	 */
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * @author ZackEvans
	 * 
	 * This function sets button properties.
	 * Function adds text to button
	 */
	
	public void createButton()
	{
		setText("Enter"); // put enter on the button
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * Function adds an action listener to the button.
	 * When the Button is clicked it will create a new note in the database
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add action listener
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // when the button is clicked
			{
				System.out.println("EnterButton");
				
				AddNoteDialog.createNote();
			}
		});
	}
}
