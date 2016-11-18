package program.util;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

/**
 * Class: StringUtil 
 * @author ZackEvans
 *
 * This class holds useful methods that contain tools for using textFields
 */

public class StringUtil 
{
	/**
	 * Function: getStringWidth(String text, Font font)
	 * @param text
	 * @param font
	 * @return the width of the string in pix
	 * 
	 * This function returns the width the text would take up on the screen
	 */
	
	public static int getStringWidth(String text, Font font)
	{
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		int textwidth = (int)(font.getStringBounds(text, frc).getWidth()); // get the width of the string 
	
		return textwidth;
	}
}
