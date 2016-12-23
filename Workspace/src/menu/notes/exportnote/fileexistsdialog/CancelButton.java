package menu.notes.exportnote.fileexistsdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Class : CancelButton
 * @author ZackEvans
 *
 * This class is a button that when clicked hides the dialog window
 */

public class CancelButton extends JButton
{
	/**
	 * Constructor: CancelButton()
	 * 
	 * This constructor calls the panel hierarchy and a method to create the button.
	 */
	
	public CancelButton()
	{	
		super();
		initialize();
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the button
	 */
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * 
	 * This function sets the text and doesent allow focous on the button
	 */
	
	public void createBtn()
	{
		setText("Cancel");
		setFocusable(false);
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This function adds an action listener to the button
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add action listener
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				FileExistsDialog.sameNameDialogFrame.setVisible(false); // hide the dialog window
			}
		});
	}
}