package toolbar.topbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.buffer.BufferPanel;

public class TopBar extends JPanel
{
	BufferPanel bufferPanel;
	
	public TopBar (BufferPanel bufferPanel)
	{
		super();
		this.bufferPanel = bufferPanel;
	}
}
