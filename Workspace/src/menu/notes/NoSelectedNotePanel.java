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

/**
 * Function: NoSelectedNotePanel
 * @author ZackEvans
 * 
 * This class is a panel that is shown when no note is selected
 */

public class NoSelectedNotePanel extends JPanel
{	
	JLabel imageLabel = new JLabel();
	static JLabel textLabel = new JLabel();
	
	/**
	 * Constructor: NoSelectedNotePanel()
	 * 
	 * This constructor calls panel hierarchy and calls a method to create the panel
	 */
	
	public NoSelectedNotePanel()
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
		setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,Color.BLACK));
		
		updateText();
		createImage();
		addText();
		
		addComponents();
	}
	
	/**
	 * Function: addComponents()
	 * 
	 * This function sets the size and location of the components and adds them to the panel.
	 */
	
	public void addComponents()
	{
		imageLabel.setBounds(0, -40, 450,423);
		textLabel.setBounds(0,260, 450, 35);
		
		add(imageLabel);
		add(textLabel);
	}
	
	/**
	 * Function: updateText()
	 * 
	 * This function updates the text based on if there are notes in the list.
	 */
	
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
	
	/**
	 * Function: createImage()
	 * 
	 * This function creates the image to add to the panel
	 */
	
	public void createImage()
	{
		URL url = NoSelectedNotePanel.class.getResource("/Button_Images/Settings/AttemptsDialog/Document.png"); // add resource to the project
		ImageIcon icon = new ImageIcon(url); // create a image icon from the URL
		imageLabel.setIcon(icon);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * Function: addText()
	 * 
	 * This function sets up the text label
	 */
	
	public void addText()
	{
		textLabel.setFont(new Font("Helvetica Neue",Font.PLAIN,25));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
