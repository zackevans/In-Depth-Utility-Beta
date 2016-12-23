package menu.notes.mailnote;

import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.notes.NotesList;
import menu.notes.mailnote.recipientviewer.RecipientHolder;
import menu.notes.mailnote.recipientviewer.RecipientViewer;

/**
 * Class: MailNote
 * @author ZackEvans
 *
 * This class is the panel that holds all of the mail note components.
 */

public class MailNote extends JPanel
{
	JPanel contentPanel = new JPanel(); // create panel to hold all main components. 
	// create components to add the the panel
	private ToField toField;
	private FromField fromField;
	private RecipientButton recipientButton;
	private AddRecipientButton addRecipientButton;
	private RecipientViewer recipientViewer;
	private ErrorPanel errorPanel;
	private SelectNote selectnote;
	private PreviewNote previewNote;
	private AdditionalComments addComments;
	private CancelButton cancelButton;
	private SendButton sendButton;
	BufferPanel bufferPanel; // create bufferPanel object to pass down to panel components
	
	/**
	 * Constructor: MailNote (BufferPanel bufferPanel)
	 * @param bufferPanel
	 * 
	 * This constructor calls panel hierarchy, inherits bufferPanel and makes the panel clear
	 */
	
	public MailNote (BufferPanel bufferPanel)
	{
		super(); // calls panel hierarchy
		this.bufferPanel = bufferPanel; // inherits bufferPanel
		setOpaque(false); // make panel clear
	}
	
	/**
	 * Function: initialize()
	 * 
	 * This function calls methods to create the panel
	 */
	
	public void initialize()
	{
		createComponents();
		initializeComponents();
		addComponents();
	}
	
	/**
	 * Function: createComponents()
	 * 
	 * This function creates components to be added to the panel.
	 * Each component is initialized and bounds are set
	 */
	
	public void createComponents()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		// initialize components
		toField = new ToField();
		fromField = new FromField();
		addRecipientButton = new AddRecipientButton();
		recipientButton = new RecipientButton();
		recipientViewer = new RecipientViewer();
		errorPanel = new ErrorPanel();
		selectnote = new SelectNote();
		previewNote = new PreviewNote();
		addComments = new AdditionalComments();
		cancelButton = new CancelButton(bufferPanel);
		sendButton = new SendButton(bufferPanel);
		
		// set size and location for components
		ToField.textField.setBounds(30, 15, 570, 35);
		toField.toLabel.setBounds(0, 15, 30, 35);
		toField.addEmailButtonBackground.setBounds(600, 15, 100, 35);
		
		FromField.textField.setBounds(45, 50, 670, 35);
		fromField.fromLabel.setBounds(0, 50,45, 35);
		
		addRecipientButton.setBounds(605, 18, 30, 30);
		RecipientButton.button.setBounds(631, 18, 30, 30);
		RecipientViewer.recipientViewerPanel.setBounds(0,50,Window_Width,35);
		errorPanel.setBounds(0, 0, Window_Width,Window_Height);
		
		SelectNote.comboBox.setBounds(-5, 85, Window_Width + 10, 20);
		
		previewNote.setBounds(0, 119, Window_Width, 200);
		
		addComments.setBounds(0, 319, Window_Width, 115);
		AdditionalComments.commentLabel.setBounds(0, 320, 200, 15);
		
		cancelButton.setBounds(480, 440, 100, 25); 
		sendButton.setBounds(585, 440, 100, 25);
	}
	
	/**
	 * Function: initializeComponents()
	 * 
	 * This function call methods to initialize components
	 */
	
	public void initializeComponents()
	{
		// components initialize methods called
		toField.initialize();
		fromField.initialize();
		addRecipientButton.initialize();
		recipientButton.initialize();
		recipientViewer.initialize();
		errorPanel.initialize();
		selectnote.initialize();
		previewNote.initialize();
		addComments.initialize();
		cancelButton.initialize();
		sendButton.initialize();
	}

	/**
	 * Function: addComponents()
	 * 
	 * Function sets layout of the panel and adds components to it.
	 */
	
	public void addComponents()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		setLayout(null); // remove layout
		setPreferredSize(new Dimension(Window_Width,Window_Height)); // sey size of panel
		
		createContentPanel(); // call method to create the content panel
		
		// LayerPanel Bottom to Top: contentPanel, errorPanel, RecpientViewer
		JLayeredPane layerPane = new JLayeredPane(); // create a layerPane
		layerPane.add(contentPanel, new Integer(0),0);
		layerPane.add(errorPanel, new Integer(1),0);
		layerPane.add(RecipientViewer.recipientViewerPanel,new Integer(2),0);
		
		layerPane.setBounds(0, 0,Window_Width,Window_Height); // set location and size of the layerpane
		
		add(layerPane); // add layerPane to mail panel
	}
	
	/**
	 * Function: createContentPanel()
	 * 
	 * This function creates the content panel and then adds components to it.
	 */
	
	public void createContentPanel()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		contentPanel.setOpaque(false); // set panel clear
		contentPanel.setLayout(null); // get rid of layout
		contentPanel.setPreferredSize(new Dimension(Window_Width,Window_Height-50)); // set size of the panel
		contentPanel.setBounds(0, 0, Window_Width,Window_Height); // set the location and size of the panel
		
		// add components to panel
		contentPanel.add(addRecipientButton);
		contentPanel.add(RecipientButton.button);
		contentPanel.add(toField.toLabel);
		contentPanel.add(toField.addEmailButtonBackground);
		contentPanel.add(ToField.textField);
		contentPanel.add(fromField.fromLabel);
		contentPanel.add(FromField.textField);
		contentPanel.add(SelectNote.comboBox);
		contentPanel.add(previewNote);
		contentPanel.add(AdditionalComments.commentLabel);
		contentPanel.add(addComments);
		contentPanel.add(cancelButton);
		contentPanel.add(sendButton);
	}
	
	/**
	 * Function: clearPanel()
	 * 
	 * This function clears all components on the MailNote panel
	 */
	
	public static void clearPanel()
	{
		ToField.textField.setText(""); 
		RecipientHolder.clearAllPanels();
		FromField.textField.setText("");
		SelectNote.comboBox.setSelectedIndex(0);
		AdditionalComments.textArea.setText("");
		AdditionalComments.commentLabel.setVisible(true);
		ErrorPanel.hideAllErrors();
	}
	
	/**
	 * Function: autoFill()
	 * 
	 * This function autofills fields in mail note panel 
	 */
	
	public static void autoFill()
	{
		SelectNote selectNote = new SelectNote();
		selectNote.updateData();
		
		if(NotesList.list.getSelectedIndex() != -1) // if a note is selected on the NOTES panel
		{
			SelectNote.comboBox.setSelectedIndex(NotesList.list.getSelectedIndex() + 1); // set selected index to be the same as the one on the NOTES panel
		}
	}
}