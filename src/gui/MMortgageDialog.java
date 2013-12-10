/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 2:50:10 PM
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import monopoly.Player;
import monopoly.tiles.Property;

/**
 * A custom dialog panel to add mortgage capabilities
 * It must contain the action listener itself, as it is a modal 
 * dialog, and it must have some action before it closes.
 * @author ajrk001
 *
 */
public class MMortgageDialog extends JDialog implements ActionListener
{
	LinkedList<Property> playProp;
	int numProps;
	JCheckBox[] boxes;
	int[] indexes;
	JButton confirm;
	
	Player player;
	MMainFrame game;

	public MMortgageDialog(MMainFrame frame, Player play)
	{
		super(frame, true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		game = frame;
		player = play;
		
		playProp = player.getProperties(); // get properties owned by player
		numProps = playProp.size() - player.getMortgagedProperties(); // get number
		boxes = new JCheckBox[numProps]; // get the right number of checkboxes
		indexes = new int[numProps];
		
		this.setLayout(new GridLayout(numProps + 1, 1, 0, 0)); // set layout to +1 size for button
		
		int y = 0;
		for(int x = 0; x < playProp.size(); x++)
		{
			if(!playProp.get(x).isMortgaged())
			{
				boxes[y] = new JCheckBox(propToString(playProp.get(x)));
				indexes[y] = x;
				this.add(boxes[y]);
				y++;
			}
		}
		
		confirm = new JButton("Mortgage Selected Values");
		confirm.addActionListener(this);
		this.add(confirm);
		this.pack();
	}
	
	private String propToString(Property x)
	{
		String s = "Name: ";
		s += x.getName() + ". Mortgaged Value: " + x.getMortgagedValue();
		return s;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int x = 0; x < boxes.length; x++)
		{
			if(boxes[x].isSelected())
			{
				playProp.get(indexes[x]).mortgage();
				
			}
		}
		game.getControl().setPlayerMoneyVal(0, player.getMoney());
		this.dispose();
	}
}
