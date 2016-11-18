package menu.notes.exportnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import menu.buffer.BufferPanel;

/**
 * Class: ExportCancelButton 
 * @author ZackEvans
 * 
 * This class is a button that hides the export note panel
 */

public class ExportCancelButton extends JButton
{
	BufferPanel bufferPanel;
	
	/**
	 * Constructor: ExportCancelButton(BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This constructor calls button hierarchy and inherits the bufferPanel object
	 */
	
	public ExportCancelButton(BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans 
	 * 
	 * This method calls function to setup the button
	 */
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * @author ZackEvans
	 * 
	 * This function sets the button text and removes the selected border.
	 */
	
	public void createBtn()
	{
		setText("Cancel");
		setFocusPainted(false);
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a action listener to the button.
	 * The action listener shows the notes panel
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				bufferPanel.showPanel("NOTES"); // show notes panel
			}
		});
	}
}
