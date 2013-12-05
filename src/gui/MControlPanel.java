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
 * A JPanel that will contain properties of a game of monopoly
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
		
		JLabel playerTwoMoneyLab = createLabel("Player Two Money!:");
		playerTwoMoney = createLabel("0");
		playerTwoMoney.setFont(boldFont);
		
		JLabel playerThreeMoneyLab = createLabel("Player Three Money!:");
		playerThreeMoney = createLabel("0");
		playerThreeMoney.setFont(boldFont);
		
		JLabel playerFourMoneyLab = createLabel("Player Four Money!:");
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
	
	/**
	 * This function will create a new JLabel that has a horizontal alignment set so that
	 * repeated lines of code are not needed
	 * @param text - a String representing the text the JLabel should contain
	 * @return a new JLabel with a central alignment, with the text being the parameter passed
	 */
	public JLabel createLabel(String text)
	{
		JLabel temp = new JLabel(text);
		temp.setHorizontalAlignment(SwingConstants.CENTER);
		return temp;
	}
	
	/**
	 * This function will take in a array that represents each players money, and set the labels as needed.
	 * @param money - a double array representating four players money
	 */
	public void setMoneyVals(double[] money)
	{
		myMoney.setText(Double.toString(money[0]));
		playerTwoMoney.setText(Double.toString(money[1]));
		playerThreeMoney.setText(Double.toString(money[2]));
		playerFourMoney.setText(Double.toString(money[3]));
	}
}
