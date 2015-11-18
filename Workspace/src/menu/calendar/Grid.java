package menu.calendar;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import menu.buffer.BufferPanel;


public class Grid extends JTable
{
	private BufferPanel bufferPanel;
	
	public Grid (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
	
	public void initialize()
	{
		createGrid();
		addListeners();
	}
	
	public void createGrid()
	{	
		DefaultTableModel model = new DefaultTableModel(7,7);
		model.fireTableDataChanged();
		setModel(model);
		setGridColor(Color.black);
		
		setRowHeight(0, 23);
		
		for (int i = 1; i <= 6; i++)
		{
			setRowHeight(i, 63);
		}
	
		for (int i = 0; i <= 6; i++)
		{
			getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		repaint();
	}
	
	
	public void addListeners()
	{
		
	}
}
