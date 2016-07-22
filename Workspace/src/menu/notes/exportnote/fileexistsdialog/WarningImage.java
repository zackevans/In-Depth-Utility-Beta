package menu.notes.exportnote.fileexistsdialog;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WarningImage extends JLabel
{
	public WarningImage()
	{
		super();
		initialize();
	}
	
	public void initialize()
	{
		createImage();
	}
	
	public void createImage()
	{
		URL iconURL = WarningImage.class.getResource("/Button_Images/Notes/NoteErrors/DialogWarningSign.png");
		ImageIcon img = new ImageIcon(iconURL);
		setIcon(img);
	}
}
