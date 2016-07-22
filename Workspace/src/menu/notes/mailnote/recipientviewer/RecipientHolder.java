package menu.notes.mailnote.recipientviewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import menu.notes.mailnote.RecipientButton;

/**
 * Class: RecipientHolder
 * @author ZackEvans
 *
 * This class is a scroll pane that holds the panels that show the emails the user will send to
 */

public class RecipientHolder extends JScrollPane
{
	public static JPanel panelHolder = new JPanel(); // this panel sits in the scroll pane and is the container that holds the other panels
	
	/**
	 * Constructor: RecipientHolder ()
	 * @author ZackEvans
	 * 
	 * This constructor calls scroll pane hierarchy and makes the pane clear.
	 */
	
	public RecipientHolder ()
	{
		super(); // call hierarchy
		setOpaque(false); // make clear
	}

	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to create the scroll pane
	 */
	
	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	/**
	 * Function: createComponents()
	 * @author ZackEvans
	 * 
	 * This method sets how the scroll pane will scroll.
	 * This method hides the scroll bar from the scroll pane.
	 * This method sets properties for how the panel holder will work.
	 * This method sets the size of the scroll pane viewport and the content that goes in it.
	 */
	
	private void createComponents()
	{
		// set how the scroll bar will function
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black)); // remove the border except for the bottom
		
		getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0)); // hide the scroll bar from the scroll pane
		getHorizontalScrollBar().setUnitIncrement(30); // make scrolling more fluid. 
		
		panelHolder.setOpaque(false); // make panel clear
		panelHolder.setLayout(new FlowLayout(FlowLayout.LEFT)); // set the layout manager for the panel
		panelHolder.setBorder(null); // remove the border of the panel
		
		getViewport().setSize(new Dimension(20, 20)); // set the size of the viewport
		
		setViewportView(panelHolder);	 // put the panel holder in the viewport
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This method adds a mouse listener to the holder panel
	 */
	
	public void addListeners()
	{	
		panelHolder.addMouseListener(new MouseAdapter()  // add mouse listener
		{
			@Override
			public void mouseExited(MouseEvent e) // overide method that fires when mouse leaves object
			{
				RecipientViewer.recipientViewerPanel.setVisible(false); // hide the panel with the fill recpient listener
			}
		});
	}
	
	/**
	 * Function: createNewPanel(String email)
	 * @author ZackEvans
	 * @param email to be sent to
	 * 
	 * This method creates a new panel to sit in the panel holder
	 */
	
	public static void createNewPanel(String email)
	{
		panelHolder.add(new RecipientPanel(email)); // create a new panel to sit in the panel holder
	}
	
	/**
	 * Function: removePanel(Component clickedPanel,String email)
	 * @author ZackEvans
	 * @param clickedPanel
	 * @param email
	 * 
	 * This method removes a panel from the panel holder
	 */
	
	public static void removePanel(Component clickedPanel,String email)
	{
		panelHolder.remove(clickedPanel); // remove component from panel
		panelHolder.repaint(); // re-show panel holder
		panelHolder.revalidate(); // remove child show new
		
		RecipientViewer.listOfEmails.remove(RecipientViewer.getEmailIndex(email)); // remove email from arrayList
		
		if(RecipientViewer.listOfEmails.size() == 0) // of there are no more items in the panel 
		{
			RecipientViewer.noRecipientsWarrning.setVisible(true); // show warning that no emails exist
		}
	}
	
	/**
	 * Function: clearAllPanels()
	 * @author ZackEvans
	 * 
	 * This method clears all the panels in the panel holder
	 */
	
	public static void clearAllPanels()
	{
		panelHolder.removeAll(); // remove all components on panel
		panelHolder.repaint(); // re-show panel
		panelHolder.revalidate(); // trash old child show new child
		RecipientViewer.listOfEmails.clear(); // clear array containing all emails to send to
		RecipientButton.updateButtonNumber(); // call method to update the number on the recipient button
		RecipientViewer.noRecipientsWarrning.setVisible(true); // show warning that no emails exist in the panel
	}
}
