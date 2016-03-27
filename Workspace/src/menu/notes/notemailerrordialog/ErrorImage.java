package menu.notes.notemailerrordialog;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ErrorImage 
{
	public static JLabel label = new JLabel();
	
	public ErrorImage()
	{
		super();
	}
	
	public void initialize()
	{
		createImage();
	}
	
	public void createImage()
	{
		URL iconURL = ErrorImage.class.getResource("/Button_Images/Notes/NoteMail/NoNetwork.png");
		ImageIcon img = new ImageIcon(iconURL);
		label.setIcon(img);
	}
	
	
	
}
