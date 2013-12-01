/** 
* CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Nov 15, 2013, 10:13:56 AM
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 * @author ajrk001
 *
 */
public class MControlPanel extends JPanel
{
	
	JLabel curPlayer;
	JLabel myWins;
	JLabel myLoss;
	
	Font boldFont = new Font("Tahoma", Font.BOLD, 11);
	public MControlPanel() 
	{
		this.setLayout(new GridLayout(8, 1, 0, 0));
		
		JLabel curPlayerLab = createLabel("Next Player");
		
		curPlayer = createLabel("Horsie Dude Guy");
		curPlayer.setFont(boldFont);
		
		JLabel myWinsLab = createLabel("My Win Count:");
		
		myWins = createLabel("0");
		myWins.setFont(boldFont);
		
		JLabel myLossLab = createLabel("My Loss Count:");
		
		myLoss = createLabel("0");
		myLoss.setFont(boldFont);
		
		JButton btnRollDice = new JButton("Roll Dice!");
		btnRollDice.setSize(50, 20);

		JButton btnNewGame = new JButton("New Game!");
		btnNewGame.setSize(50, 20);
		
		this.add(curPlayerLab);
		this.add(curPlayer);
		this.add(myWinsLab);
		this.add(myWins);
		this.add(myLossLab);
		this.add(myLoss);
		this.add(btnRollDice);
		this.add(btnNewGame);
	}
	
	public JLabel createLabel(String text)
	{
		JLabel temp = new JLabel(text);
		temp.setHorizontalAlignment(SwingConstants.CENTER);
		return temp;
	}
}
