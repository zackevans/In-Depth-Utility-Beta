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
	
	
	public MailNotePanel(BufferPanel bufferPanel, Notes notes)
	{
		super();
		setOpaque(false);
		this.bufferPanel = bufferPanel;
		this.notes = notes;
		//setBorder(BorderFactory.createLineBorder(Color.RED));
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
		
		cancleBtn.setBounds(480, 440, 100, 25); 
		enterBtn.setBounds(585, 440, 100, 25);
		
		To.textField.setBounds(30, 15, Window_Width+6, 35);
		toField.toLabel.setBounds(0, 15, 30, 35);
		
		From.textField.setBounds(45, 50, Window_Width+6, 35);
		fromField.fromLabel.setBounds(0, 50,45, 35);
		
	}
	
	public void initializeComponents()
	{
		cancleBtn.initialize();
		enterBtn.initialize();
		toField.initialize();
		fromField.initialize();
	}	
	
	public void addComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		add(toField.toLabel);
		add(fromField.fromLabel);
		add(cancleBtn);
		add(enterBtn);
		add(To.textField);
		add(From.textField);
	
	}
}
