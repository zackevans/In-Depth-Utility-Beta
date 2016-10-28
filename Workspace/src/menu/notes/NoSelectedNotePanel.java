package menu.notes;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NoSelectedNotePanel extends JPanel
{	
	JLabel imageLabel = new JLabel();
	JLabel textLabel = new JLabel("No Note Selected");
	
	public NoSelectedNotePanel()
	{
		super();
		createPanel();
	}
	
	public void createPanel()
	{
		setLayout(null);
		setBackground(Color.white);
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,Color.BLACK));
		
		createImage();
		addText();
		
		addComponents();
	}
	
	
	public void addComponents()
	{
		imageLabel.setBounds(0, -40, 450,423);
		textLabel.setBounds(0,260, 450, 35);
		
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
		textLabel.setFont(new Font("Helvetica Neue",Font.PLAIN,25));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
