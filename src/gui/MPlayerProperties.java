/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 8, 2013, 9:37:29 PM
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import monopoly.Player;
import monopoly.tiles.Property;



/**
 * @author ajrk001
 *
 */
public class MPlayerProperties extends JDialog implements ActionListener 
{
	Player[] players; // the array of all players
	MMainFrame game; // the main frame that this is coupled to
	JPanel btnPanel; // panel with btns
	JPanel propPanel; // pane that will contain the table of data or lbl saying no props
	JLabel lblTitle; // title label
	int curPlayerDisplayed; // the current player displayed
	
	public MPlayerProperties(Player[] play, MMainFrame frame)
	{
		super(frame, true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		players = play;
		game = frame;
		curPlayerDisplayed = 0;
		
		JPanel tempPanel = new JPanel();
		// create all the componenets
		lblTitle = new JLabel("My Properties"); // this is the title label
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER); // aligned center
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 24)); // bold, large font
		tempPanel.add(lblTitle); // add at the top
		tempPanel.setPreferredSize(new Dimension(400, 40));
		
		propPanel = new JPanel(); // the panel with the table or lbl saying no props
		propPanel.setLayout(new GridLayout(1, 1, 0, 0));
		setPropPanel(curPlayerDisplayed);
		
		btnPanel = new JPanel(); // bottom panel containing the btns
		btnPanel.setPreferredSize(new Dimension(400, 40));
		
		JButton btnLeft = new JButton("Previous Player");
		btnLeft.setEnabled(false); // can't go back from 0
		btnLeft.setActionCommand("Prev");
		btnPanel.add(btnLeft);
		JButton btnBack = new JButton("Back"); // btn to go back to game
		btnBack.setActionCommand("Back");
		btnPanel.add(btnBack);
		JButton btnRight = new JButton("Next Player");
		btnRight.setActionCommand("Next");
		btnPanel.add(btnRight);		
		
		this.add(tempPanel, BorderLayout.NORTH);
		this.add(propPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		
		this.pack();
	}

	private void setPropPanel(int player)
	{
		propPanel.removeAll(); // make sure clean panel
		if(players[player].getProperties().size() == 0) // if there are no properties, just put a label
		{
			JLabel noProps = new JLabel("No Properties Owned!");
			propPanel.add(noProps);
		}
		else
		{
			String[] columnNames = new String[] { "Name", "Price", "Rent", "Is Mortgaged" }; // default column names
			LinkedList<Property> props = players[player].getProperties(); // get the properties the player owns
			Object[][] data = new Object[props.size()][4]; // the data to be added to the panel

			// get the data, then add to table
			for(int x = 0; x < props.size(); x++)
			{
				Property theProp = props.get(x); // get the property
				data[x][0] = theProp.getName(); // name
				data[x][1] = theProp.getPrice(); // price
				data[x][2] = theProp.getRent(); // rent
				data[x][3] = theProp.isMortgaged(); // ismortgaged?
			}
			
			JScrollPane scrollPane = new JScrollPane(); // scrollpanel for table 
			
			JTable table = new JTable(data, columnNames); // table with the correct data, and column names
			table.setFillsViewportHeight(true);
			table.setEnabled(false);
				
			scrollPane.getViewport().add(table); // add table to scroll panel
			propPanel.add(scrollPane); // add scrollpanel to prop panel
			propPanel.setPreferredSize(new Dimension(400, 100));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
