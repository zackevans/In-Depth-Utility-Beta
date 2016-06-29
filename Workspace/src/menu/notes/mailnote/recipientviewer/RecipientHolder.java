package menu.notes.mailnote.recipientviewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import menu.notes.mailnote.RecipientButton;
import menu.notes.mailnote.ToField;

public class RecipientHolder extends JScrollPane
{
	public static JPanel panelHolder = new JPanel();
	
	public RecipientHolder ()
	{
		super();
		setOpaque(false);
	}

	public void initialize()
	{
		createComponents();
		addListeners();
	}
	
	private void createComponents()
	{
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
		getHorizontalScrollBar().setUnitIncrement(30);
		
		panelHolder.setOpaque(false);
		panelHolder.setMinimumSize(new Dimension(10,20));
		panelHolder.setMaximumSize(new Dimension(100000000, 5));
		panelHolder.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelHolder.setBorder(null);
		
		getViewport().setSize(new Dimension(20, 20));
		
		setViewportView(panelHolder);	
	}
	
	public void addListeners()
	{	
		panelHolder.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseExited(MouseEvent e) // overide method that fires when mouse leaves object
			{
				RecipientViewer.recipientViewerPanel.setVisible(false);
			}
		});
	}
	
	public static void createNewPanel()
	{
		panelHolder.add(new RecipientPanel(ToField.textField.getText()));
	}
	
	public static void removePanel(Component clickedPanel,String email)
	{
		panelHolder.remove(clickedPanel);
		panelHolder.repaint();
		panelHolder.revalidate();
		
		RecipientViewer.getEmailIndex(email);
		RecipientViewer.listOfEmails.remove(RecipientViewer.getEmailIndex(email));
		
		if(RecipientViewer.listOfEmails.size() == 0)
		{
			RecipientViewer.noRecipientsWarrning.setVisible(true);
		}
	}
	
	public static void clearPanels()
	{
		panelHolder.removeAll();
		panelHolder.repaint();
		panelHolder.revalidate();
		RecipientViewer.listOfEmails.clear();
		RecipientButton.updateButtonNumber();
		RecipientViewer.noRecipientsWarrning.setVisible(true);
	}
}
