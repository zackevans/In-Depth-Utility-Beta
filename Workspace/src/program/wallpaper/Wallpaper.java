package program.wallpaper;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

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
