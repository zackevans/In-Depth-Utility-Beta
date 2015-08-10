package menu.settings.security.enterpassword;

import java.awt.Graphics;
import javax.swing.JPasswordField;
import menu.buffer.BufferPanel;


/**
 * @author ZackEVans
 * Class: EnterPassField
 * 
 * password field added to enterPassword
 */

public class EnterPassField extends JPasswordField
{
	BufferPanel bufferPanel; // create bufferPanel object
	
	/**
	 * Constructor: EnterPassField 
	 * @param bufferPanel
	 * 
	 * Inharet bufferPanel object
	 */
	
	public EnterPassField (BufferPanel bufferPanel) 
    {
        this.bufferPanel = bufferPanel; // Inharet object
    }
	
	/**
	 * Function: PaintComponent*
	 * @param g
	 *  
	 *  set color
	 *  create rectangle around textfield
	 *  paint rectange
	 */

 	protected void paintComponent(Graphics g) 
    {
         g.setColor(getBackground()); // set color same as backround
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15); // create a round rectangle underField
         super.paintComponent(g); // paint all hierarchy
    }
    
 	/**
 	 * Function: PaintBorder*
 	 * @param g
 	 * 
 	 * set color
 	 * draw border around passfield
 	 */
 	
    protected void paintBorder(Graphics g) 
    {
         g.setColor(getForeground()); // set color same as backround
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15,15); // create and draw border around field
    }
}
