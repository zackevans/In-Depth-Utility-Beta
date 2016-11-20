package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.notes.AddNoteButton;

public class EmptyListIconPanel extends JPanel
{
	JLabel imageLabel = new JLabel();
	JLabel textLabel = new JLabel("No Attempts");
	
	public EmptyListIconPanel ()
	{
		super();
		createPanel();
	}
	
	public void createPanel()
	{
		setLayout(null);
		setBackground(Color.white);
		
		createImage();
		addText();
		
		addComponents();
	}
	
	public void addComponents()
	{
		imageLabel.setBounds(0, -40, 393,300);
		textLabel.setBounds(0,225, 393, 35);
		
		add(imageLabel);
		add(textLabel);
	}
	
	public void createImage()
	{
		URL url = AddNoteButton.class.getResource("/Button_Images/Settings/AttemptsDialog/Document.png"); // add resource to the project
		ImageIcon icon = new ImageIcon(url); // create a image icon from the URL
		imageLabel.setIcon(icon);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void addText()
	{
		textLabel.setFont(new Font("Helvetica Neue",Font.PLAIN,30));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
}