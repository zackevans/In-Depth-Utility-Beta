package menu.settings.security.enterpassword;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import menu.buffer.BufferPanel;
import menu.settings.security.passconfirm.PasswordConfirm;
import sql.system.settings.SystemDatabase;


/**
 * @author ZackEVans
 * Class: EnterPassField
 * 
 * password field added to enterPassword
 */

public class EnterPassField extends JPasswordField
{
	BufferPanel bufferPanel; // create bufferPanel object
	private EnterPassword ep = new EnterPassword(bufferPanel); // Enter password class object
	private SystemDatabase sd = new SystemDatabase(); // system database object
	private PasswordConfirm pc = new PasswordConfirm(bufferPanel);

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
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6); // create a round rectangle underField
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
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 6,6); // create and draw border around field
    }
    
    public void addListener()
    {
    	addKeyListener(new KeyAdapter() 
		{
			public void keyReleased(KeyEvent e) {}
		    public void keyTyped(KeyEvent e) {}

		    public void keyPressed(KeyEvent e) 
		    {
		    	String pass = ep.getPassword(); // get pass word from textfield 
				String dbPass = sd.getPassword(); // get pass from database 
		    	
		    	if (e.getKeyCode() == KeyEvent.VK_ENTER)
		    	{
		    		if (pass.equals(dbPass)) // check if passwords match
					{
						bufferPanel.showPanel("PASSWORD_CONFIRM"); // show password confirm panel
						pc.setDefaultFocus();
					}
					
					else
					{
						ep.showWarning(true); // show warrning
						ep.clearFields();
						ep.setDefaultFocus();
					}
		    	}

		    }
		});
	}
}
