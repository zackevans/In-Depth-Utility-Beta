package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import file.files.PasswordAttemptsFile;

public class AttemptsList 
{
	public static JScrollPane listScrollPane = new JScrollPane();
	public static JList list = new JList();
	
	public AttemptsList()
	{
		updateList();
	}
	
	public static void updateList()
	{
		list.setListData(PasswordAttemptsFile.getAttempts().toArray());
		gotoLastItem();
		list.clearSelection();
	}
	
	
	public void createList()
	{
		list.setCellRenderer(new AttemptsListRender());
		
		list.setFont(new Font("Helvetica Neue",Font.PLAIN,20)); // set the text size and font
		
		listScrollPane.getHorizontalScrollBar().setUnitIncrement(0);
		listScrollPane.setViewportView(list);
		listScrollPane.setBorder(null);
		
		gotoLastItem();
	}
	
	public static void gotoLastItem()
	{
		int lastIndex = list.getModel().getSize() -1;
		
		if (lastIndex >= 0) 
		{
			list.ensureIndexIsVisible(lastIndex);
		}
	}
	
	class AttemptsListRender extends DefaultListCellRenderer 
	{
	    Font font = new Font("Helvetica Neue",Font.PLAIN,17); // set font for list.

	    @Override
	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	    {
	    	setText(value.toString());
	    	
	        this.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        if(index%2 == 0)
	        {
	        	setBackground(new Color(195, 223, 232));
	        }
	        
	        else
	        {
	        	setBackground(Color.white);
	        }
	        
	        return this;
	    }
	}
}
