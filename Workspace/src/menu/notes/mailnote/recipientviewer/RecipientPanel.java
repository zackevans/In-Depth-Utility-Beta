package menu.notes.mailnote.recipientviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import program.util.TextfieldUtil;

public class RecipientPanel extends JPanel
{
	String emailText = "";
	JLabel emailLabel;
	RemoveButton removeButton;
	
	public RecipientPanel (String email)
	{
		super();
		emailText = email;
		createPanel(email);
	}
	
	@Override
	protected void paintComponent(Graphics g) 
    {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
         super.paintComponent(g);
    }
	
	/**
	 * Function: paintBorder(Graphics g)
	 * @author ZackEvans
	 * @param Graphics g
	 * 
	 * round border of textfield
	 */
	
    @Override
	protected void paintBorder(Graphics g) 
    {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
    }
	
	public void createPanel(String email)
	{
		Font font = new Font("Helvetica Neue",Font.PLAIN,12);
		
		setLayout(null);
		setPreferredSize(new Dimension(TextfieldUtil.getStringWidth(email,font) + 30, 24));
		setBackground(new Color(163, 164, 168));
		
		
		emailLabel = new JLabel(email);
		emailLabel.setFont(font);
		emailLabel.setBounds(4, 1,(TextfieldUtil.getStringWidth(email,font)) + 2, 23);
		
		removeButton = new RemoveButton(this);
		removeButton.initialize();
		removeButton.setBounds(TextfieldUtil.getStringWidth(email,font) + 8 ,1, 20,20);
		
		add(emailLabel);
		add(removeButton);
	}
	
	public String getEmailText()
	{
		return emailText;
	}
}
