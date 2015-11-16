package menu.calendar;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import menu.buffer.BufferPanel;


public class Grid extends JTable
{
	private BufferPanel bufferPanel;
	
	public Grid (int r, int c)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	//public Grid (int rows, int cols)
	
		//super();
	
	
	public void initialize()
	{
		createGrid();
		addListeners();
	}
	
	public void createGrid()
	{
		setModel(new DefaultTableModel(6,7));
		
		for (int i = 0; i <= 5; i++)
		{
			setRowHeight(i, 63);
		}
	
		for (int i = 0; i <= 6; i++)
		{
			//getColumnModel().getColumn(i).setPreferredWidth(100);
		}
	}
	
	
	public void addListeners()
	{
		
	}
}
