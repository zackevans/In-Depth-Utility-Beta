package menu.notes.mailnotepanel;

import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.notes.Notes;

public class MailNotePanel extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	JLayeredPane layerPane = new JLayeredPane();
	JPanel contentPanel = new JPanel();
	BufferPanel bufferPanel;
	Notes notes;
	private CancelButton cancleBtn;
	private SendButton sendBtn;
	private To toField;
	private From fromField;
	private SeclectNote seclectNote;
	private PreviewNote previewNote;
	private AdditionalComments addComments;
	private ErrorPanel errorPanel;
	
	public MailNotePanel(BufferPanel bufferPanel, Notes notes)
	{
		super();
		this.bufferPanel = bufferPanel;
		this.notes = notes;
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
		toField = new To();
		fromField = new From();
		errorPanel = new ErrorPanel();
		seclectNote = new SeclectNote();
		previewNote = new PreviewNote();
		sendBtn = new SendButton(bufferPanel);
		addComments = new AdditionalComments();
		cancleBtn = new CancelButton(bufferPanel);

		cancleBtn.setBounds(480, 440, 100, 25); 
		sendBtn.setBounds(585, 440, 100, 25);
		
		To.textField.setBounds(30, 15, Window_Width-30, 35); // - 30 bc of the label that takes space
		toField.toLabel.setBounds(0, 15, 30, 35);
		
		From.textField.setBounds(45, 50, Window_Width-30, 35);
		fromField.fromLabel.setBounds(0, 50,45, 35);
		
		seclectNote.comboBox.setBounds(-5, 85, Window_Width + 10, 20); 
		
		previewNote.setBounds(0, 119, Window_Width, 200); // 104
		
		addComments.setBounds(0, 319, Window_Width, 115);
		addComments.commentLabel.setBounds(0, 320, 200, 15);
	}	

	public void initializeComponents()
	{
		sendBtn.initialize();
		toField.initialize();
		cancleBtn.initialize();
		fromField.initialize();
		errorPanel.initialize();
		seclectNote.initialize();
		previewNote.initialize();
		addComments.initialize();
	}	
	
	public void addComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		createContentPanel();
	
		contentPanel.setBounds(0, 0, Window_Width,Window_Height);
		errorPanel.setBounds(0, 0, Window_Width,Window_Height);
		
		layerPane.add(contentPanel, new Integer(0),0);
		layerPane.add(errorPanel, new Integer(1),0);
		
		layerPane.setBounds(0, 0,Window_Width,Window_Height);
		
		add(layerPane);
	}
	
	
	public void createContentPanel()
	{
		contentPanel.setOpaque(false);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(Window_Width,Window_Height-50));
		//contentPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		contentPanel.add(toField.toLabel);
		contentPanel.add(fromField.fromLabel);
		contentPanel.add(addComments.commentLabel);
		contentPanel.add(cancleBtn);
		contentPanel.add(sendBtn);
		contentPanel.add(To.textField);
		contentPanel.add(From.textField);
		contentPanel.add(SeclectNote.comboBox);
		contentPanel.add(previewNote);
		contentPanel.add(addComments);
	}
	
	public void clearAllFields()
	{
		To.textField.setText("");
		From.textField.setText("");
		PreviewNote.textArea.setText("");
		AdditionalComments.textArea.setText("");
		AdditionalComments.commentLabel.setVisible(true);
	}
}