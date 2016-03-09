package menu.settings.security.login;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPasswordField;

import menu.buffer.BufferPanel;
import panel.screensaver.ScreenSaver;
import sql.system.settings.SystemDatabase;

public class EnterPassField extends JPasswordField 
{
	BufferPanel bufferPanel;
	private EnterButton enterBtn = new EnterButton(bufferPanel);
	SystemDatabase systemDB = new SystemDatabase();
	Login login = new Login(bufferPanel);
	ScreenSaver ss = new ScreenSaver(bufferPanel);
	
	public EnterPassField (BufferPanel bufferPanel) 
    {
        this.bufferPanel = bufferPanel;
        setFocusable(true);
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
		    		System.out.println("Enter key pressed");
		    		
		    		if (login.getPassword().equals(systemDB.getPassword()))
					{
						bufferPanel.showPanel(login.getNextPanel());
						login.showWarning(false);
					}
					
					else
					{
						login.showWarning(true);
						login.clearField();
					}
		    	}
		    	
		    	if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		    	{
		    		bufferPanel.showPanel("SCREEN_SAVER");
		    		ss.requestFocusInWindow();
		    	}
		    	
		    }
		});
	}
}
