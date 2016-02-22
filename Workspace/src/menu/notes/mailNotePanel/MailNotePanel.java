package menu.notes.mailNotePanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
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
	
	
	public MailNotePanel(BufferPanel bufferPanel, Notes notes)
	{
		super();
		setOpaque(false);
		this.bufferPanel = bufferPanel;
		this.notes = notes;
		setBorder(BorderFactory.createLineBorder(Color.RED));
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
		
		cancleBtn.setBounds(480, 440, 100, 25); 
		enterBtn.setBounds(585, 440, 100, 25);
		To.textField.setBounds(-2, 13, Window_Width+6, 35);
	}
	
	public void initializeComponents()
	{
		cancleBtn.initialize();
		enterBtn.initialize();
		toField.initialize();
	}	
	
	public void addComponents()
	{
		setLayout(null);
		setPreferredSize(new Dimension(Window_Width,Window_Height));
		
		add(cancleBtn);
		add(enterBtn);
		add(To.textField);
	}
	
	
}
