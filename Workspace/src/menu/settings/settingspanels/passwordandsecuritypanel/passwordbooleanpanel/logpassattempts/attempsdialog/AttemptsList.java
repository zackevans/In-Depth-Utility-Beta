package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import file.files.PasswordAttemptsFile;

/**
 * Function: AttemptsList 
 * @author ZackEvans
 *
 * This class holds a list that displays all of the failed password attempts.
 */

public class AttemptsList 
{
	public static JScrollPane listScrollPane = new JScrollPane();
	public static JList list = new JList();
	
	/**
	 * Constructor: AttemptsList()
	 * 
	 * This construcot calls a method to create the list.
	 */
	
	public AttemptsList()
	{
		updateList();
	}
	
	/**
	 * Function: updateList()
	 * 
	 * This function updates the list with attempts stored in a file.
	 * Then it scrolls to the end of the list to show the last failed attempt.
	 */
	
	public static void updateList()
	{
		list.setListData(PasswordAttemptsFile.getAttempts().toArray());
		gotoLastItem();
		list.clearSelection();
	}
	
	/**
	 * Function: createList()
	 * 
	 * This function sets up the list.
	 */
	
	public void createList()
	{
		list.setCellRenderer(new AttemptsListRender());
		
		list.setFont(new Font("Helvetica Neue",Font.PLAIN,20)); // set the text size and font
		
		listScrollPane.getHorizontalScrollBar().setUnitIncrement(0);
		listScrollPane.setViewportView(list);
		listScrollPane.setBorder(null);
		
		gotoLastItem();
	}
	
	/**
	 * Function: gotoLastItem()
	 * 
	 * This function scrolls to the last item in the list ( The newest item)
	 */
	
	public static void gotoLastItem()
	{
		int lastIndex = list.getModel().getSize() -1;
		
		if (lastIndex >= 0) 
		{
			list.ensureIndexIsVisible(lastIndex);
		}
	}
	
	/**
	 * Class: AttemptsListRender
	 * @author ZackEvans
	 *
	 * This function alternates the color of the list indexes.
	 */
	
	class AttemptsListRender extends DefaultListCellRenderer 
	{
	    Font font = new Font("Helvetica Neue",Font.PLAIN,17); // set font for list.

	    @Override
	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	    {
	    	setText(value.toString());
	    	this.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        if(index%2 == 0)
	        	setBackground(new Color(195, 223, 232));
	        else
	        	setBackground(Color.white);
	        
	        return this;
	    }
	}
}
