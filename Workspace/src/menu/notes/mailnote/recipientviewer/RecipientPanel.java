package menu.notes.mailnote.recipientviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import program.util.StringUtil;

/**
 * Class: RecipientPanel
 * @author ZackEvans
 *
 * This class is a panel that holds a label containing a email of a recipient and a button to remove the panel
 */

public class RecipientPanel extends JPanel
{
	String emailText = "";
	JLabel emailLabel; // label that holds recipient email
	RemoveButton removeButton; // button that will delete the panel
	
	/**
	 * Constructor: RecipientPanel (String email)
	 * @author ZackEvans
	 * @param email of recipient
	 * 
	 * This constructor calls panel hierarchy and saves the email passed to the method.
	 * This constructor calls method to create and setup the panel
	 */
	
	public RecipientPanel (String email)
	{
		super(); // call hierarchy
		emailText = email; // save email text
		createPanel(email); // create the panel
	}
	
	/**
	 * Function: paintComponent(Graphics g) *
	 * @author ZackEvans
	 * @param Graphics g
	 * 
	 * round border of text field
	 */
	
	@Override
	protected void paintComponent(Graphics g) 
    {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
         super.paintComponent(g);
    }
	
	/**
	 * Function: paintBorder(Graphics g)*
	 * @author ZackEvans
	 * @param Graphics g
	 * 
	 * round border of text field
	 */
	
    @Override
	protected void paintBorder(Graphics g) 
    {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
    }
	
    /**
     * Function: createPanel(String email)
     * @author ZackEvans
     * @param email
     * 
     * This function sets up a new panel
     */
    
	public void createPanel(String email)
	{
		Font font = new Font("Helvetica Neue",Font.PLAIN,12); // create font for text added to panel
		
		setLayout(null); // remove layout of panel
		setPreferredSize(new Dimension(StringUtil.getStringWidth(email,font) + 30, 24)); // set the size of panel (calculate by getting pix width of text and adding space for the button) 
		setBackground(new Color(163, 164, 168)); // set background of the panel to a gray
		
		emailLabel = new JLabel(email); // create new label to add to the panel
		emailLabel.setFont(font); // set the font of the text
		emailLabel.setBounds(4, 1,(StringUtil.getStringWidth(email,font)) + 2, 23); // set size and location of the label
		
		removeButton = new RemoveButton(this); // create a new button to discard the panel
		removeButton.setBounds(StringUtil.getStringWidth(email,font) + 8 ,1, 20,20); // set size and location of the button
		
		// add components to the panel
		add(emailLabel);
		add(removeButton);
	}
	
	/**
	 * Function: getEmailText()
	 * @author ZackEvans
	 * @return recipient email
	 * 
	 * This function returns the email recipient shown on the panel
	 */
	public String getEmailText()
	{
		return emailText; // return the recipients email
	}
}
