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
	
	JLabel myMoney;
	JLabel playerTwoMoney;
	JLabel playerThreeMoney;
	JLabel playerFourMoney;
	
	Font boldFont = new Font("Tahoma", Font.BOLD, 11);
	public MControlPanel() 
	{
		this.setLayout(new GridLayout(10, 1, 0, 0));		
		
		JLabel myMoneyLab = createLabel("Your Money!:");
		myMoney = createLabel("0");
		myMoney.setFont(boldFont);
		
		JLabel playerTwoMoneyLab = createLabel("Your Money!:");
		playerTwoMoney = createLabel("0");
		playerTwoMoney.setFont(boldFont);
		
		JLabel playerThreeMoneyLab = createLabel("Your Money!:");
		playerThreeMoney = createLabel("0");
		playerThreeMoney.setFont(boldFont);
		
		JLabel playerFourMoneyLab = createLabel("Your Money!:");
		playerFourMoney = createLabel("0");
		playerFourMoney.setFont(boldFont);
		
		JButton btnRollDice = new JButton("Roll Dice!");
		btnRollDice.setSize(50, 20);

		JButton btnNewGame = new JButton("New Game!");
		btnNewGame.setSize(50, 20);
		
		this.add(myMoneyLab);
		this.add(myMoney);
		this.add(playerTwoMoneyLab);
		this.add(playerTwoMoney);
		this.add(playerThreeMoneyLab);
		this.add(playerThreeMoney);
		this.add(playerFourMoneyLab);
		this.add(playerFourMoney);
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
