package menu.notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.buffer.BufferPanel;
import menu.notes.sharenotesdialog.ShareList;
import menu.notes.sharenotesdialog.ShareNoteDialog;

public class ShareButton extends JButton
{
	private BufferPanel bufferPanel;
	private Notes notes;
	private ShareNoteDialog shareDialog; 
	private ShareList shareList;
	private static boolean clicked = false;
	
	public ShareButton (BufferPanel bufferPanel,Notes notes)
	{
		super();
		this.bufferPanel = bufferPanel;
		this.notes = notes;
	}
	
	public void initialize()
	{
		createComponents();
		createBtn();
		addListeners();
	}
	
	public void createComponents()
	{
		shareDialog = new ShareNoteDialog(bufferPanel,notes);
		shareList = new ShareList(bufferPanel, notes, shareDialog);
	}
	
	public void createBtn()
	{
		URL url = ShareButton.class.getResource("/Button_Images/Notes/Share.png"); // get button image
		ImageIcon icon = new ImageIcon(url); // create image
		setIcon(icon); // set the image
	}
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("shareBtn");
				
				shareDialog.launchDialog(); // show window next to frame
			}
		});
	}
}