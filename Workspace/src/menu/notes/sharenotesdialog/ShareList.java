package menu.notes.sharenotesdialog;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import menu.buffer.BufferPanel;
import menu.notes.Notes;
import menu.notes.mailNotePanel.MailNotePanel;

public class ShareList 
{
	BufferPanel bufferPanel;
	private Notes notes;
	private ShareNoteDialog shareNoteDialog;
	private MailNotePanel mailNotePanel;
	public static  JList list = new JList();
	String[] listElemets = {"Mail", "Export"};
	
	public ShareList(BufferPanel bufferPanel,Notes notes, ShareNoteDialog shareNoteDialog)
	{
		super();
		this.bufferPanel = bufferPanel;
		this.notes = notes;
		this.shareNoteDialog = shareNoteDialog;
	}
	
	public void initialize()
	{
		createComponents();
		createList();
		addListeners();
	}
	
	public void createComponents()
	{
		mailNotePanel = new MailNotePanel(bufferPanel,notes);
	}

	public void createList()
	{
		//list.setOpaque(true);
		//list.setBackground(new Color(128,128,128,0x33));
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
           	 	list.clearSelection();
           	 	//list.repaint();
            }
            
            @Override
            public void focusGained(FocusEvent e) {} // not needed currently
        });	
		
		
		list.addMouseListener(new MouseListener() 
		{
	        @Override
	        public void mouseReleased(MouseEvent e) {}
	        @Override
	        public void mousePressed(MouseEvent e) {}
	        @Override
	        public void mouseExited(MouseEvent e) {}
	        @Override
	        public void mouseEntered(MouseEvent e) {}
	        
	        @Override
	        public void mouseClicked(MouseEvent e) 
	        {  
	        	if (e.getClickCount() == 1)
	        	{	
	        		int indexClicked = list.getSelectedIndex();
	        		
	        		if(indexClicked == 0)
	        		{
	        			// show mail panel
	        			bufferPanel.showPanel("MAIL_NOTES_PANEL");
	        			
	        			
	        			
	        			System.out.println("asfdsfa");
	        		}
	        		
	        		if(indexClicked == 1)
	        		{
	        			// show export menu
	        		}
	        		
	        		shareNoteDialog.customFrame.setVisible(false);	
	        	}
	        }
	    });
	}
}