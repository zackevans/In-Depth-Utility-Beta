package menu.notes;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sql.util.DatabaseUtil;

public class NoSelectedNotePanel extends JPanel
{	
	JLabel imageLabel = new JLabel();
	static JLabel textLabel = new JLabel();
	
	public NoSelectedNotePanel()
	{
		super();
		createPanel();
	}
	
	public void createPanel()
	{
		setLayout(null);
		setBackground(Color.white);
		setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,Color.BLACK));
		
		updateText();
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
	
	public static void updateText()
	{
		if(DatabaseUtil.countItems("USER_NOTES") == 0)
		{
			textLabel.setText("Create a Note");
		}
		
		else
		{
			textLabel.setText("Select a Note");
		}
	}
	
	public void createImage()
	{
		URL url = NoSelectedNotePanel.class.getResource("/Button_Images/Settings/AttemptsDialog/Document.png"); // add resource to the project
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
