package menu.settings.security.passconfirm;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPasswordField;

import menu.buffer.BufferPanel;
import sql.system.settings.SystemDatabase;

public class RetypeField extends JPasswordField
{
	BufferPanel bufferPanel;
	private Shape shape;
	private PasswordConfirm passConfirm = new PasswordConfirm(bufferPanel);
	private SystemDatabase systemdb = new SystemDatabase();
	
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
    
   public void addListener()
   {
	   addKeyListener(new KeyAdapter() 
		{
			public void keyReleased(KeyEvent e) {}
		    public void keyTyped(KeyEvent e) {}

		    public void keyPressed(KeyEvent e) 
		    {
		    	if (e.getKeyCode() == KeyEvent.VK_ENTER)
		    	{
		    		boolean matchPass = passConfirm.comparePass();
		    		
		    		if (matchPass == true)
					{
						if (passConfirm.getPass().length() >0)
						{
							boolean row = systemdb.checkRow();
							
							if(row == true)
							{
								systemdb.updatePassword(passConfirm.getPass());
								systemdb.updatePassExist(true);
							}
							
							else
							{
								systemdb.createPassword(passConfirm.getPass());
								systemdb.updatePassExist(true);
							}
							
							// TODO tip move to first launch
							passConfirm.hideWarning();
							passConfirm.clearTxtFields();
							bufferPanel.showPanel("SECURITY_SETTINGS");
						}
					}
					
					else
					{
						passConfirm.showWarning();
						passConfirm.clearTxtFields();
						passConfirm.setDefaultFocus();
					}
		    		
		    	}
		    }
		});
   }
	
}
