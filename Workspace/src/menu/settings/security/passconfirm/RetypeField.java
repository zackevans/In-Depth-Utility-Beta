package menu.settings.security.passconfirm;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPasswordField;

import menu.buffer.BufferPanel;

public class RetypeField extends JPasswordField
{
	BufferPanel bufferPanel;
	private Shape shape;
	
	public RetypeField (BufferPanel bufferPanel) 
    {
        setOpaque(false); 
        this.bufferPanel = bufferPanel;
    }
	
 	protected void paintComponent(Graphics g) 
    {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         super.paintComponent(g);
    }
    
    protected void paintBorder(Graphics g) 
    {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    
    public boolean contains(int x, int y) 
    {
         if (shape == null || !shape.getBounds().equals(getBounds())) 
         {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         
         return shape.contains(x, y);
    }
	
}
