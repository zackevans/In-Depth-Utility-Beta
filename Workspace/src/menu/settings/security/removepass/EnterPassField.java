package menu.settings.security.removepass;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;

import menu.buffer.BufferPanel;
import sql.system.settings.SystemDatabase;

public class EnterPassField extends JPasswordField
{
	BufferPanel bufferPanel;
	private RemovePassword removePass = new RemovePassword(bufferPanel);
	private SystemDatabase sd = new SystemDatabase();
	
	public EnterPassField (BufferPanel bufferPanel) 
    {
        this.bufferPanel = bufferPanel;
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
    
    public void addListeners()
    {
    	addKeyListener(new KeyAdapter() 
		{
			public void keyReleased(KeyEvent e) {}
		    public void keyTyped(KeyEvent e) {}

		    public void keyPressed(KeyEvent e) 
		    {
		    	String pass = removePass.getPassword();
				String dbPass = sd.getPassword();
		    	
		    	if(e.getKeyCode() == KeyEvent.VK_ENTER)
		    	{
		    		if (pass.equals(dbPass))
					{
						sd.updatePassExist(false);
						sd.updatePassword("");
						bufferPanel.showPanel("SECURITY_SETTINGS");
					}
					
					else
					{
						removePass.showWarning(true);
						removePass.setDefaultFocus();
					}
		    	}
		    }
		});
    }
    
    
}
