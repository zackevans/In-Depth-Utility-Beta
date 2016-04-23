package menu.notes.samenamedialog;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WarningImage 
{
	public static JLabel warningLabel = new JLabel();
	
	public WarningImage()
	{
		super();
	}
	
	public void initialize()
	{
		createImage();
	}
	
	public void createImage()
	{
		URL iconURL = WarningImage.class.getResource("/Button_Images/Notes/NoteErrors/DialogWarningSign.png");
		ImageIcon img = new ImageIcon(iconURL);
		warningLabel.setIcon(img);
	}
}
