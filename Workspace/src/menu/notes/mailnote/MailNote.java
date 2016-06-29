package menu.notes.mailnote;

import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.notes.NotesList;
import menu.notes.mailnote.recipientviewer.RecipientHolder;
import menu.notes.mailnote.recipientviewer.RecipientViewer;

public class MailNote extends JPanel
{
	JPanel contentPanel = new JPanel();
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
	BufferPanel bufferPanel;
	
	
	public MailNote (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false);
	}
	
	public void initialize()
	{
		createComponents();
		initializeComponents();
		addComponents();
	}
	
	public void createComponents()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
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
		
		ToField.textField.setBounds(30, 15, 670, 35);
		toField.toLabel.setBounds(0, 15, 30, 35);
		
		FromField.textField.setBounds(45, 50, 670, 35);
		fromField.fromLabel.setBounds(0, 50,45, 35);
		
		addRecipientButton.setBounds(605, 18, 30, 30);
		recipientButton.button.setBounds(631, 18, 30, 30);
		recipientViewer.recipientViewerPanel.setBounds(0,50,Window_Width,35);
		errorPanel.setBounds(0, 0, Window_Width,Window_Height);
		
		SelectNote.comboBox.setBounds(-5, 85, Window_Width + 10, 20);
		
		previewNote.setBounds(0, 119, Window_Width, 200);
		
		addComments.setBounds(0, 319, Window_Width, 115);
		addComments.commentLabel.setBounds(0, 320, 200, 15);
		
		cancelButton.setBounds(480, 440, 100, 25); 
		sendButton.setBounds(585, 440, 100, 25);
	}
	
	public void initializeComponents()
	{
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

	public void addComponents()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		createContentPanel();
		
		JLayeredPane layerPane = new JLayeredPane();
		layerPane.add(contentPanel, new Integer(0),0);
		layerPane.add(errorPanel, new Integer(1),0);
		layerPane.add(RecipientViewer.recipientViewerPanel,new Integer(2),0);
		
		layerPane.setBounds(0, 0,Window_Width,Window_Height);
		
		add(layerPane);
	}
	
	public void createContentPanel()
	{
		final int Window_Width = 700;
		final int Window_Height = 500;
		
		contentPanel.setOpaque(false);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(Window_Width,Window_Height-50));
		contentPanel.setBounds(0, 0, Window_Width,Window_Height);
		
		contentPanel.add(addRecipientButton);
		contentPanel.add(RecipientButton.button);
		contentPanel.add(toField.toLabel);
		contentPanel.add(ToField.textField);
		contentPanel.add(fromField.fromLabel);
		contentPanel.add(FromField.textField);
		contentPanel.add(SelectNote.comboBox);
		contentPanel.add(previewNote);
		contentPanel.add(addComments.commentLabel);
		contentPanel.add(addComments);
		contentPanel.add(cancelButton);
		contentPanel.add(sendButton);
	}
	
	public static void clearPanel()
	{
		ToField.textField.setText("");
		RecipientHolder.clearPanels();
		FromField.textField.setText("");
		SelectNote.comboBox.setSelectedIndex(0);
		AdditionalComments.textArea.setText("");
		AdditionalComments.commentLabel.setVisible(true);
		ErrorPanel.hideAllErrors();
	}
	
	
	public static void autoFill()
	{
		SelectNote selectNote = new SelectNote();
		selectNote.updateData();
		
		if(NotesList.list.getSelectedIndex() != -1)
		{
			selectNote.comboBox.setSelectedIndex(NotesList.list.getSelectedIndex() + 1);
		}
	}
}