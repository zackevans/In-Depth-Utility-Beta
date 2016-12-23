package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;

/**
 * Class: CancelButton
 * @author ZackEvans
 * 
 * This class is a button that when clicked hides the mail note panel and displays the Notes panel
 */

public class CancelButton extends JButton
{
	BufferPanel bufferPanel; // create bufferPanel object to access showPanel() method
	
	/**
	 * Constructor: Cancel Button
	 * 
	 * This constructor calls panel hierarchy and inherits the bufferPanel object
	 */
	
	public CancelButton (BufferPanel bufferPanel)
	{
		super(); // call panel 
		this.bufferPanel = bufferPanel; // inherit bufferPanel obejct
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create button
	 */
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * 
	 * This function sets the text of the button and removes the blue border
	 */
	
	public void createBtn()
	{
		setText("Cancel");
		setFocusPainted(false); // remove blue focus border
	}
	
	/**
	 * Function: addListeners()
	 * @see NOTES panel
	 * 
	 * This function adds an action listener to the button
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add action listener
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // override function
			{
				bufferPanel.showPanel("NOTES"); // show notes panel
			}
		});
	}
}
