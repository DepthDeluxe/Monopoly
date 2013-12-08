/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 7, 2013, 2:50:10 PM
 */
package gui;

import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import monopoly.Player;
import monopoly.tiles.Property;

public class MMortgagePanel extends JPanel
{
	LinkedList<Property> playProp;
	int numProps;
	JCheckBox[] boxes;
	JButton confirm;
	
	public MMortgagePanel(Player play)
	{
		playProp = play.getProperties(); // get properties owned by player
		numProps = playProp.size(); // get number
		boxes = new JCheckBox[numProps]; // get the right number of checkboxes
		
		this.setLayout(new GridLayout(numProps + 1, 1, 0, 0)); // set layout to +1 size for button
		
		for(int x = 0; x < numProps; x++)
		{
			boxes[x] = new JCheckBox(propToString(playProp.get(x)));
			this.add(boxes[x]);
		}
		
		confirm = new JButton("Mortgage Selected Values");
		this.add(confirm);
	}
	
	private String propToString(Property x)
	{
		String s = "Name: ";
		s += x.getName() + ". Mortgaged Value: " + x.getMortgagedValue();
		return s;
	}
}
