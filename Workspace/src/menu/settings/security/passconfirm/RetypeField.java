package menu.settings.security.passconfirm;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;

import menu.buffer.BufferPanel;
import sql.systemsettings.SystemDatabase;

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
	
	@Override
	protected void paintComponent(Graphics g) 
    {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
         super.paintComponent(g);
    }

    @Override
	protected void paintBorder(Graphics g) 
    {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
    }
    
   public void addListener()
   {
	   addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) {}
		    @Override
			public void keyTyped(KeyEvent e) {}

		    @Override
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
