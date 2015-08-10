package menu.settings.security.enterpassword;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;

/**
 * @author ZackEvans
 * Constructor: ReturnButton
 * 
 * return button for enterPass Panel
 *
 */

public class ReturnButton extends JButton 
{
	BufferPanel bufferPanel; // bufferPanel object
	
	/**
	 * Constructor: ReturnButton
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Inharet bufferPanel object
	 */
	
	public ReturnButton (BufferPanel bufferPanel)
	{
		super(); // call hierachy 
		this.bufferPanel = bufferPanel; // inharet bufferPanel object
	}
	
	/**
	 * Function: initialize
	 * 
	 * call functions to create button
	 */
	
	public void initialize()
	{
		createBtn(); 
		addListeners();
	}
	
	/**
	 * Function: createBtn
	 * 
	 * set button text
	 */
	
	public void createBtn()
	{
		setText("Return"); // set text to return
	}
	
	/**
	 * Function: addListeners
	 * 
	 * add listeners and function for it
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() // add listener to button
		{
			@Override
			public void actionPerformed(ActionEvent arg0) // when action is proformed
			{
				bufferPanel.showPanel("SECURITY_SETTINGS"); // show security setting panel
			}
		});
	}
}
