package panel.wallpaper;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Class: Wallpaper
 * @author ZackEvans
 *
 *	This class is the wallpaper image panel
 */

public class Wallpaper extends JPanel
{
	private Image img; // create object that holds wallpaper image

	/**
	 * Function: Wallpaper(String img)
	 * @author ZackEvans
	 * @param img
	 * 
	 * Constructor takes in string of image and converts it to a ImageIcon
	 */
	
	public Wallpaper(String img) 
	{
		this(new ImageIcon(img).getImage()); // turn image into Imageicon and set on panel
	}
	
	/**
	 * Function: Wallpaper(Image img)
	 * @author ZackEvans
	 * @param img
	 * 
	 * Constructor takes in image and converts it to a ImageIcon
	 */

	public Wallpaper(Image img) 
	{
		this.img = img; // assign the image
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); // create dimention for image panel
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null); // set Layout null
	}

	/**
	 * Function: paintComponent(Graphics g) 
	 * @author ZackEvans
	 * @param Graphics g
	 * 
	 * draw image on panel
	 */
	
	@Override
	public void paintComponent(Graphics g) 
	{
		g.drawImage(img, 0, 0, null);
	}
}
