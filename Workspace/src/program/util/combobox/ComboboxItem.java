package program.util.combobox;

/**
 * Class: ComboboxItem 
 * @author ZackEvans
 *
 * This class allows items in a combobox to be treated as seprate even if the items names are identical.
 */

public class ComboboxItem 
{
	private String text; // create var to hold name of item in combobox
	
	/**
	 * Constructor: ComboboxItem(String text)
	 * @param text
	 * 
	 * Constructor takes in a string and saves it
	 */
	
	public ComboboxItem(String text)
	{
		this.text = text; // save text passes into constructor
	}
	
	@Override
	public String toString() // override method to return passes in text
	{
		return text;
	}
}
