package menu.notes.mailnote;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class: AdditionalComments
 * @author ZackEvans
 *
 * This class is a text area for users to enter an additional comment to the end of the email.
 */

public class AdditionalComments extends JScrollPane
{
	public static JTextArea textArea = new JTextArea(); // create text area for user to type in.
	public static JLabel commentLabel = new JLabel("  Additional Comments:"); // create label to add over textarea
	
	/**
	 * Constructor: AdditionalComments()
	 * @author ZackEvans
	 * 
	 * The constructor calls the scroll panes hierarchy
	 */
	
	public AdditionalComments()
	{
		super(); // call panel hierarchy
	}
	
	/**
	 * Function: initialize()
	 * @author ZackEvans
	 * 
	 * This function calls methods to create the scroll pane.
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
	 * This function creates and sets up the text area.
	 */
	
	public void createComponents()
	{
		setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK)); // creates a border on all sides except the top
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // never let panel scroll left and right

		// set words to return to the next line if they don't fit in the text area
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		commentLabel.setVisible(true);// show comment ontop of the 
		commentLabel.setForeground(Color.black); // set text of label to black
		
		setViewportView(textArea); // set the text area in scrollpane
	}
	
	/**
	 * Function: addListeners()
	 * @author ZackEvans
	 * 
	 * This function adds a focous listener to the text area.
	 */
	
	public void addListeners()
	{
		textArea.addFocusListener(new FocusListener() // add focous listener
		{
			@Override
			public void focusLost(FocusEvent e) // over ride what happend when the textarea is clicked off of
			{
				if (textArea.getText().length() <= 0) // if no text exists then show text
				{
					commentLabel.setVisible(true);
				}	
			}
			
			@Override
			public void focusGained(FocusEvent e) // when the text area is clicked on
			{
				commentLabel.setVisible(false); // hide the text.
			}
		});
	}
}