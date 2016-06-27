package menu.notes.mailnote;

import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.notes.mailnote.recipientviewer.RecipientViewer;

public class MailNote extends JPanel
{
	JPanel contentPanel = new JPanel();
	private ToField toField;
	private FromField fromField;
	private RecipientButton recipientButton;
	private AddRecipientButton addRecipientButton;
	private RecipientViewer recipientViewer;
	private BufferPanel bufferPanel;
	
	
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
		toField = new ToField();
		fromField = new FromField();
		addRecipientButton = new AddRecipientButton();
		recipientButton = new RecipientButton();
		recipientViewer = new RecipientViewer();
		
		ToField.textField.setBounds(30, 15, 670, 35);
		toField.toLabel.setBounds(0, 15, 30, 35);
		
		FromField.textField.setBounds(45, 50, 670, 35);
		fromField.fromLabel.setBounds(0, 50,45, 35);
		
		addRecipientButton.setBounds(625, 18, 30, 30);
		recipientButton.button.setBounds(650, 18, 30, 30);
		recipientViewer.recipientViewerPanel.setBounds(0,50,700,35);
	}
	
	public void initializeComponents()
	{
		toField.initialize();
		fromField.initialize();
		addRecipientButton.initialize();
		recipientButton.initialize();
		recipientViewer.initialize();
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
		layerPane.add(RecipientViewer.recipientViewerPanel,new Integer(1),0);
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
	}
}