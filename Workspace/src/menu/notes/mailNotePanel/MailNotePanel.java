package menu.notes.mailNotePanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import menu.buffer.BufferPanel;
import menu.notes.Notes;

public class MailNotePanel extends JPanel
{
	public static final int Window_Width = 700;
	public static final int Window_Height = 500;
	BufferPanel bufferPanel;
	Notes notes;
	private CancelButton cancleBtn;
	private EnterButton enterBtn;
	private To toField;
	private From fromField;
	private SeclectNote seclectNote;
	private PreviewNote previewNote;
	private AdditionalComments addComments;
	
	public MailNotePanel(BufferPanel bufferPanel, Notes notes)
	{
		super();
		setOpaque(false);
		this.bufferPanel = bufferPanel;
		this.notes = notes;
	}
	
	public void initialize()
	{
		createComponents();
		initializeComponents();
		addComponents();
	}
	
	public void createComponents()
	{
		cancleBtn = new CancelButton(bufferPanel);
		enterBtn = new EnterButton(bufferPanel);
		toField = new To();
		fromField = new From();
		seclectNote = new SeclectNote();
		previewNote = new PreviewNote();
		addComments = new AdditionalComments();
		
		cancleBtn.setBounds(480, 440, 100, 25); 
		enterBtn.setBounds(585, 440, 100, 25);
		
		To.textField.setBounds(30, 15, Window_Width-30, 35); // - 30 bc of the label that takes space
		toField.toLabel.setBounds(0, 15, 30, 35);
		
		From.textField.setBounds(45, 50, Window_Width-30, 35);
		fromField.fromLabel.setBounds(0, 50,45, 35);
		
		seclectNote.comboBox.setBounds(-5, 85, Window_Width + 10, 20); 
		
		previewNote.label.setBounds(0, 104, 75, 15);
		previewNote.setBounds(0, 119, Window_Width, 200); // 104
		
		addComments.setBounds(0, 319, Window_Width, 115);
		addComments.commentLabel.setBounds(0, 320, 200, 15);
	}

	public void initializeComponents()
	{
		cancleBtn.initialize();
		enterBtn.initialize();
		toField.initialize();
		fromField.initialize();
		seclectNote.initialize();
		previewNote.initialize();
		addComments.initialize();
	}	
	
	public void addComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		add(toField.toLabel);
		add(fromField.fromLabel);
		add(addComments.commentLabel);
		add(cancleBtn);
		add(enterBtn);
		add(To.textField);
		add(From.textField);
		add(seclectNote.comboBox);
		add(previewNote.label);
		add(previewNote);
		add(addComments);
	}
	
	public void clearAllFields()
	{
		To.textField.setText("");
		From.textField.setText("");
		PreviewNote.textArea.setText("");
		AdditionalComments.textArea.setText("");
	}
}