package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.JScrollPane;

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
	}
	
	
	public void createList()
	{
		list.setFont(new Font("Helvetica Neue",Font.PLAIN,17)); // set the text size and font
		
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
}
