package panel.wallpaper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.notes.AddNoteButton;

public class Wallpaper extends JPanel
{
	private Image img;

	public Wallpaper(String img) 
	{
		this(new ImageIcon(img).getImage());
	}

	public Wallpaper(Image img) 
	{
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) 
	{
		g.drawImage(img, 0, 0, null);
	}
}
