package menu.notes.mailnote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import sql.notes.NotesDataBase;

public class SelectNote 
{
	public static JComboBox <String> comboBox = new JComboBox <String>();

	public SelectNote()
	{
		super();
	}
	
	public void initialize()
	{
		createCombobox();
		updateData();
		addListeners();
	}
	
	public void createCombobox()
	{
		comboBox.setFocusable(false);
		comboBox.setBorder(null);
	}
	
	@SuppressWarnings("unchecked")
	public void updateData()
	{
		NotesDataBase notesdb = new NotesDataBase();
		
		ArrayList<String> noteNames = notesdb.getListNamesData();
		noteNames.add(0, "- Select Note -");
		
		DefaultComboBoxModel defultModel = new DefaultComboBoxModel();
		
		for (int i = 0; i< noteNames.size(); i++)
		{
			defultModel.addElement(new CreateComboboxItem(noteNames.get(i)));
		}
		
		comboBox.setModel(defultModel);
	}
	
	public void addListeners()
	{
		comboBox.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				PreviewNote previewNote = new PreviewNote();
				
				previewNote.displayNote(comboBox.getSelectedIndex());
				
				if (comboBox.getSelectedIndex() == 0)
				{
					ErrorPanel.seclectNoteError.setVisible(true);
				}
				
				else
				{
					ErrorPanel.seclectNoteError.setVisible(false);
				}
			}
		});
	}
	
	public class CreateComboboxItem
	{
		private String text;
		
		public CreateComboboxItem(String text)
		{
			this.text = text;
		}
		
		@Override
		public String toString()
		{
			return text;
		}
	}
}
