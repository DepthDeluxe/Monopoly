/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Dec 8, 2013, 9:37:29 PM
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import monopoly.Player;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * @author ajrk001
 *
 */
public class MPlayerProperties extends JDialog implements ActionListener 
{
	Player[] players; // the array of all players
	MMainFrame game; // the main frame that this is coupled to
	JScrollPane properties; // the scroll pane containing another pane with properties
	JPanel btnPanel;
	JPanel propPanel;
	ArrayList<JLabel> propNames;
	
	
	JLabel lblTitle; // title label
	
	public MPlayerProperties(Player[] play, MMainFrame frame)
	{
		super(frame, true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1, 0, 0));
		
		players = play;
		game = frame;
		
		// create all the componenets
		lblTitle = new JLabel("My Properties"); // this is the title label
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER); // aligned center
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 24)); // bold, large font
		this.add(lblTitle); // add at the top
		
		properties = new JScrollPane(); // the scroll pane
		
		propPanel = new JPanel(); // properties panel will lie within this
		
		for(int x = 0; x < players[0].getProperties().size(); x++)
		{
			
		}
		
		btnPanel = new JPanel(); // bottom panel containing the btns
		
		JButton btnLeft = new JButton("Previous Player");
		btnLeft.setEnabled(false);
		btnLeft.setActionCommand("Prev");
		btnPanel.add(btnLeft);
		JButton btnBack = new JButton("Back");
		btnBack.setActionCommand("Back");
		btnPanel.add(btnBack);
		JButton btnRight = new JButton("Next Player");
		btnRight.setActionCommand("Next");
		btnPanel.add(btnRight);		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
	}
}
