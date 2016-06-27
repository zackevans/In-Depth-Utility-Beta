package menu.notes.mailnote.recipientviewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import menu.notes.mailnote.RecipientButton;

public class RemoveButton extends JButton
{
	RecipientPanel recipientPanel;
	
	public RemoveButton(RecipientPanel recipientPanel)
	{
		super();
		this.recipientPanel = recipientPanel;
	}
	
	public void initialize()
	{
		createBtn();
		addListeners();
	}
	
	/**
	 * Function: createBtn()
	 * @author ZackEvans
	 * 
	 * This function set icons for the button
	 */
	
	public void createBtn()
	{
		URL removeBtnUrl = RemoveButton.class.getResource("/Button_Images/Notes/NotesPanel/Clear.png"); // create a URL for the button image
		URL pressedRemoveBtnUrl = RemoveButton.class.getResource("/Button_Images/Notes/NotesPanel/ClearPressed.png"); // create a URL for the image when the button is pressed
		
		// make the button clear except for the image on the button
		setOpaque(false); 
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		
		setIcon(new ImageIcon(removeBtnUrl)); // set icon for the button
		setPressedIcon(new ImageIcon(pressedRemoveBtnUrl)); // set the icon for when the button is pressed
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a action listener to the button that when clicked clears the search bar text.
	 */
	
	public void addListeners()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				RecipientHolder.removePanel(getParent(),recipientPanel.emailText);
				RecipientButton.updateButtonNumber();
			}
		});
		
		
		addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				RecipientViewer.recipientViewerPanel.setVisible(true);
			}
		});
	}
}
