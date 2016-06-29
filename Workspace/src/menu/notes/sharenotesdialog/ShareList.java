package menu.notes.sharenotesdialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import menu.buffer.BufferPanel;
import menu.notes.mailnote.MailNote;

/**
 * Class: ShareList 
 * @author ZackEvans
 *
 * This class is a list that shows share opptions
 */

public class ShareList 
{
	public static  JList list = new JList(); // create 
	private final Map<String, ImageIcon> imageMap = new HashMap<String, ImageIcon>(); // create a hashmap with the name and image
	BufferPanel bufferPanel; // create bufferPanel obejct used to show the different Share panels
	
	/**
	 * Constructor: ShareList (BufferPanel bufferPanel)
	 * @author ZackEvans
	 * @param bufferPanel
	 * 
	 * This constructor inherit the bufferPanel object and calls panel hierarchy.
	 */
	
	public ShareList (BufferPanel bufferPanel)
	{
		super(); // call panel hierarchy.
		this.bufferPanel = bufferPanel; // inherit the bufferPanel object
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to create the list.
	 */
	
	public void initialize()
	{
		createList();
		createIcons();
		addListeners();
	}
	
	/**
	 * Function: createList()
	 * @author ZackEvans
	 * 
	 * This function adds the names into the list and sets the color of it.
	 * This function also sets a custom cell render on the list
	 */
	
	public void createList()
	{
		String[] listElemets = {"Mail", "Export"};
		list.setListData(listElemets); // put the list data in the list
		list.setBackground(new Color(225,225,225,0x99)); // set color to clear white
		list.setCellRenderer(new ShareListRender()); // add custom cell render to list
	}
	
	/**
	 * Function: createIcons()
	 * @author ZackEvans
	 * 
	 * This function creates images that will be added to the list
	 * Images should be 32 x 32 pix
	 */
	
	public void createIcons()
	{
		URL iconURL = ShareList.class.getResource("/Button_Images/Notes/ShareNoteList/Mail.png"); // create URL to store mail image
		URL exportURL = ShareList.class.getResource("/Button_Images/Notes/ShareNoteList/Export.png"); // create URL to store export image
		
		ImageIcon mailImg = new ImageIcon(iconURL); // create an icon image from the URL
		ImageIcon exportImg = new ImageIcon(exportURL); 
		
		// add text and image to hash map
		imageMap.put("Mail", mailImg);
		imageMap.put("Export", exportImg);
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * @see MailNotePanel
	 * 
	 * Function adds a focus listener and a mouse listener to the panel
	 */
	
	public void addListeners()
	{
		list.addFocusListener(new FocusListener()  // add focous listener to list
    	{
            @Override
            public void focusLost(FocusEvent e) // when the frame no longer has focous
            {
            	list.clearSelection(); // clear the list of selections
           	 	ShareNotesDialog.customFrame.setVisible(false); // hide dialog window	
            }
            
            @Override
            public void focusGained(FocusEvent e) {} // not needed currently
        });	
		
		
		list.addMouseListener(new MouseListener() // add mouse listener to the panel
		{
	        @Override
	        public void mouseClicked(MouseEvent e)  // over write what happens when a items is clicked
	        {  
	        	if (e.getClickCount() == 1)
	        	{	
	        		int indexClicked = list.getSelectedIndex(); // get the index that was clicked
	        		
	        		if(indexClicked == 0) 
	        		{
	        			bufferPanel.showPanel("MAIL_NOTES"); // show mail note panel
	        			MailNote.clearPanel(); // reset all components on the panel
	        			MailNote.autoFill();
	        		}
	        		
	        		if(indexClicked == 1)
	        		{
	        			
	        		}
	        		
	        		ShareNotesDialog.customFrame.setVisible(false);	// hide the window
	        	}
	        }
	        
	        @Override
	        public void mouseReleased(MouseEvent e) {} // note needed
	        @Override
	        public void mousePressed(MouseEvent e) {}
	        @Override
	        public void mouseExited(MouseEvent e) {}
	        @Override
	        public void mouseEntered(MouseEvent e) {}
	    });
	}
	
	/**
	 * Class: ShareListRender
	 * @author ZackEvans
	 *
	 * This class creates custom list cells.
	 * This class Overrides the cell render method to add an image and text to the list.
	 */
	
	class ShareListRender extends DefaultListCellRenderer 
	{
	    Font font = new Font("Helvetica Neue",Font.PLAIN,17); // set font for list.

	    @Override
	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	    {
	        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // create new label
	        
	        label.setIcon(imageMap.get(value)); // add image to label
	        label.setHorizontalTextPosition(JLabel.RIGHT); // add text to the right of the Label
	        label.setFont(font); // set the label font
	        label.setBorder(null); // get rid of the border that surrounds the individual items in the list.
	        return label; // return the custom jlabel
	    }
	}
}