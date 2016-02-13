package menu.notes.sharenotesdialog;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JList;

public class ShareList 
{
	private ShareNoteDialog shareNoteDialog;
	public static JList list = new JList();
	String[] listElemets = {"Mail", "Export"};
	
	public ShareList(ShareNoteDialog shareNoteDialog)
	{
		super();
		this.shareNoteDialog = shareNoteDialog;
	}
	
	public void initialize()
	{
		createList();
		addListeners();
	}
	
	
	public void createList()
	{
		list.setFont(new Font("Helvetica Neue",Font.PLAIN,17)); // set special font
		list.setListData(listElemets); // put the list data in the list
	}
	
	public void addListeners()
	{
		list.addFocusListener(new FocusListener() 
    	{
            @Override
            public void focusLost(FocusEvent e) // when the frame is unslected it goes away
            {
           	 	shareNoteDialog.customFrame.setVisible(false); // hide dialog window
            }
            
            @Override
            public void focusGained(FocusEvent e) {} // not needed currently
        });	
	}
}
