package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import program.util.combobox.ComboboxItem;
import sql.notes.NotesDataBase;

/**
 * Class: SelectNote
 * @author ZackEvans
 *
 * This class holds a combobox that is used to select the note the user wants to email
 */

public class SelectNote 
{
	public static JComboBox <String> comboBox = new JComboBox <>(); // create the combobox that will hold the note options
	
	/**
	 * Function: initialize()
	 * 
	 * This method calls functions to create the combobox
	 */
	
	public void initialize()
	{
		createCombobox();
		updateData();
		addListeners();
	}
	
	/**
	 * Function: createCombobox()
	 * 
	 * This method make the comobox not focus and removes the border.
	 */
	
	public void createCombobox()
	{
		comboBox.setFocusable(false); // make the combobox not be able to focous
		comboBox.setBorder(null); // remove the border
	}
	
	/**
	 * Function: updateData()
	 * 
	 * This method updates the note options in the combo box
	 */
	
	@SuppressWarnings("unchecked")
	public void updateData()
	{
		NotesDataBase notesdb = new NotesDataBase();
		
		ArrayList<String> noteNames = notesdb.getListNamesData(); // get array of note names
		noteNames.add(0, "- Select Note -"); // add select at the front
		
		DefaultComboBoxModel defultModel = new DefaultComboBoxModel();
		
		for (int i = 0; i< noteNames.size(); i++)
		{
			defultModel.addElement(new ComboboxItem(noteNames.get(i))); // add names from db into the combo box.
		}
		
		comboBox.setModel(defultModel); // set the combo box model
	}
	
	/**
	 * Function: addListeners()
	 * 
	 * This method adds a action listener to the combobox
	 */
	
	public void addListeners()
	{
		comboBox.addActionListener(new ActionListener()  // add action listener to combobox 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PreviewNote previewNote = new PreviewNote();
				
				previewNote.displayNote(comboBox.getSelectedIndex()); // show note body in preview window
				
				if (comboBox.getSelectedIndex() == 0) // if the first index is selected (The one that sats SELECT NOTE)
				{
					ErrorPanel.seclectNoteError.setVisible(true); // show the error
				}
				
				else // if the first index is note selected
				{
					ErrorPanel.seclectNoteError.setVisible(false); // hide the errorpanel
				}
			}
		});
	}
}
