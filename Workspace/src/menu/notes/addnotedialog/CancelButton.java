package menu.notes.addnotedialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class: CancelButton
 * @author ZackEvans
 *
 * This class is a button that when clicked hides the dialog frame
 */

public class CancelButton extends JButton
{
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * Function calls methods to set up the button 
	 */
	
	public CancelButton()
	{
		super();
	}
	
	public void initialize()
	{
		createButton();
		addListeners();
	}
	
	/**
	 * Function: createButton()
	 * @author ZackEvans
	 * 
	 * This function sets up the button
	 */
	
	public void createButton()
	{
		setText("Cancel"); // put text on button
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a action listener to the button to perform a action
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add action listener to the class
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // when button is clicked
			{
				AddNoteDialog.customFrame.setVisible(false); // hide the main frame
			}
		});
	}
}
