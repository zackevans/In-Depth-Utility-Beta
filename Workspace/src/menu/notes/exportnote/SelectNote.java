package menu.notes.exportnote;

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
 * This class holds a combobox that is used to select the note the user wants to export
 */

public class SelectNote 
{
	public static JComboBox <String> comboBox = new JComboBox <String>(); // create the combobox that will hold the note options
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
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
	 * @author ZackEvans
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
	 * @author ZackEvans
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
	 * @author ZackEvans
	 * 
	 * This method adds a action listener to the combobox
	 */
	
	public void addListeners()
	{
		comboBox.addActionListener(new ActionListener()  // add action listener to combobox 
		{
			@Override
			public void actionPerformed(ActionEvent e)  // override what happens when the button is pressed.
			{
				ExportPreviewNote exportPreviewNote = new ExportPreviewNote();
				
				exportPreviewNote.displayNote(SelectNote.comboBox.getSelectedIndex()); // show the note body in the preview window.
				
				if (SelectNote.comboBox.getSelectedIndex() !=0) // if the combo box is not empty.
				{
					ExportErrorNotePanel.selectNoteError.setVisible(false); // hide the warning
				}
				
				else
				{
					ExportErrorNotePanel.selectNoteError.setVisible(true); // show the warning.
				}
			}
		});
	}
}
