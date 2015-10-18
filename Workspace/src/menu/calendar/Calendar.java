package menu.calendar;
import javax.swing.JPanel;
import menu.buffer.BufferPanel;

public class Calendar extends JPanel
{
	BufferPanel bufferPanel; // create bufferPanel object

	/**
	 * Constructor: Notes
	 * @param bufferPanel
	 * 
	 * Call super Run panel hierarchy 
	 * Inherent bufferPanel object
	 * 
	 */
	
	public Calendar (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
		setOpaque(false);
	}
	
	/**
	 * Function: paintComponent*
	 * 
	 */
	
//	public void paintComponent(Graphics g)
//	{
//		super.paintComponent(g); 
//		
//	}
	
	/**
	 * Function: initialize
	 * 
	 * call function to create panel
	 * 
	 */
	
	public void initialize()
	{
		createComponents();
		initializeComponents();
		layoutComponents();
	}
	
	public void createComponents()
	{
		
	}
	
	public void initializeComponents()
	{
		
	}
	
	public void layoutComponents()
	{
		
	}
	
}
