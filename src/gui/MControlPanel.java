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
	GridLayout layout = new GridLayout(0, 1, 5, 5);
	Border etchedB;
	
	JButton rollDice;
	JButton newGame;
	JLabel currMoney;
	JLabel curPlayer;
	JLabel myWins;
	JLabel myLosses;

	
	public MControlPanel() 
	{
		etchedB = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		this.setLayout(layout);
		this.setBorder(etchedB);
		
		JLabel currPlayerLab = createLabel("Next Player");
		
		curPlayer = createLabel("Horsie Dude Guy");
		Font temp = curPlayer.getFont();
		curPlayer.setFont(new Font(temp.getName(), Font.BOLD, temp.getSize()));
		
		JLabel myWinLab = createLabel("My Win Count");
		
		myWins = createLabel("0");
		myWins.setBorder(new LineBorder(Color.BLACK));
		
		JLabel myLossLab = createLabel("My Loss Count");
		
		myLosses = createLabel("0");
		myLosses.setBorder(new LineBorder(Color.BLACK));
		
		this.add(currPlayerLab);
		this.add(curPlayer);
		this.add(myWinLab);
		this.add(myWins);
		this.add(myLossLab);
		this.add(myLosses);
	}
	
	public JLabel createLabel(String text)
	{
		JLabel temp = new JLabel(text);
		temp.setHorizontalAlignment(SwingConstants.CENTER);
		return temp;
	}
}
