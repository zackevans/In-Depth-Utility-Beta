package menu.notes;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

import menu.buffer.BufferPanel;
import sql.notes.NotesDataBase;

public class SearchBar extends JTextField implements FocusListener
{
	BufferPanel bufferPanel;
	private NotesDataBase notesdb = new NotesDataBase();
	private Shape shape;
	private static String hint = " Search";
	private boolean showingHint;
	
	
    public SearchBar (BufferPanel bufferPanel) 
    {
        super(hint);
        setOpaque(false); // As suggested by @AVD in comment.
        this.bufferPanel = bufferPanel;
        this.showingHint = true;
        super.addFocusListener(this);
    }
    
    public void initialize()
    {
    	
    }
   
    protected void paintComponent(Graphics g) 
    {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
         super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) 
    {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
    }
   
    public boolean contains(int x, int y) 
    {
         if (shape == null || !shape.getBounds().equals(getBounds())) 
         {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         return shape.contains(x, y);
    }
    
	public void focusGained(FocusEvent arg0) 
    {
    	if(this.getText().isEmpty()) 
    	{
            super.setText("");
            showingHint = false;
    	}
	}

	@Override
	public void focusLost(FocusEvent arg0) 
	{
		if(this.getText().isEmpty()) 
		{
			super.setText(hint);
			showingHint = true;
		}
	}
	
    @Override
    public String getText() 
    {
        return showingHint ? "" : super.getText();
    }	
    
   public boolean doesTextExist()
   {
	   String text = getText();
	   
	   if (text.length() > 0)
	   {
		   return true;
	   }
	   
	   else return false;
   }
}
