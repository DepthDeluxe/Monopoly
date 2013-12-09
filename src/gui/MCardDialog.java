/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 9:48:14 PM
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import monopoly.Card;
import monopoly.tiles.TileType;

/**
 * A custom JDialog implementation that will act similarly to 
 * @author ajrk001
 *
 */
public class MCardDialog extends JDialog implements ActionListener
{
	Card card; // the card that you have to display
	MMainFrame game;
	
	private final String fileChance = "Images/chance.jpg";
	private final String fileCommChest = "Images/commChest.jpg";
	
	public MCardDialog(Card chosen, MMainFrame frame, TileType type)
	{
		super(frame, true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(new GridLayout(2, 1, 0, 0));
		game = frame;		
		card = chosen;
		
		JPanel tempPane = new JPanel(); // get temp pane to seperate things
		
		JLabel image = new JLabel(); // image representing chance/commchest
		if(type == TileType.CHANCE)
		{
			image.setIcon(new ImageIcon(MCardDialog.class.getResource(fileChance)));
		}
		else
		{
			image.setIcon(new ImageIcon(MCardDialog.class.getResource(fileCommChest)));
		}
		tempPane.add(image);
		
		JLabel description = new JLabel(card.getDescription());
		tempPane.add(description);
		
		this.add(tempPane);
		
		JButton confirm = new JButton("This message has been recieved and ignored"); // button to continue the game
		confirm.addActionListener(this);
		this.add(confirm);
	}
	
	public void actionPerformed(ActionEvent e1)
	{
		
	}
}
