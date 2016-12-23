package menu.settings.settingspanels.passwordandsecuritypanel.passwordbooleanpanel.logpassattempts.attempsdialog;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.notes.AddNoteButton;

/**
 * Class: EmptyListIconPanel
 * @author ZackEvans
 *
 * This class is a panel that displays when there are no saved failed attemtps.
 */

public class EmptyListIconPanel extends JPanel
{
	JLabel imageLabel = new JLabel();
	JLabel textLabel = new JLabel("No Attempts");
	
	/**
	 * Constructor: EmptyListIconPanel ()
	 * 
	 * This constructor calls the panel hierarchy and calls a method to create the panel.
	 */
	
	public EmptyListIconPanel ()
	{
		super();
		createPanel();
	}
	
	/**
	 * Function: createPanel()
	 * 
	 * This function sets up the panel
	 */
	
	public void createPanel()
	{
		setLayout(null);
		setBackground(Color.white);
		
		createImage();
		addText();
		
		addComponents();
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function sets the size and location of the components and adds them to the panel
	 */
	
	public void addComponents()
	{
		imageLabel.setBounds(0, -40, 393,300);
		textLabel.setBounds(0,225, 393, 35);
		
		add(imageLabel);
		add(textLabel);
	}
	
	/**
	 * Function: createImage()
	 * 
	 * This function creates the image to be added to the panel
	 */
	
	public void createImage()
	{
		URL url = AddNoteButton.class.getResource("/Button_Images/Settings/AttemptsDialog/Document.png"); // add resource to the project
		ImageIcon icon = new ImageIcon(url); // create a image icon from the URL
		imageLabel.setIcon(icon);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * Function: addText()
	 * 
	 * This function creates the text label for the panel.
	 */
	
	public void addText()
	{
		textLabel.setFont(new Font("Helvetica Neue",Font.PLAIN,30));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
}