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
import java.awt.event.ActionListener;

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
	
	JLabel[] moneyLabels;
	JLabel[] nameLabels;
	
	JButton btnRollDice;
	JButton btnMortgage;
	
	Font boldFont = new Font("Tahoma", Font.BOLD, 11);
	public MControlPanel() 
	{
		this.moneyLabels = new JLabel[4];
		this.nameLabels = new JLabel[4];
		this.setLayout(new GridLayout(10, 1, 0, 0));		
		
		nameLabels[0] = createLabel("Your Money!:");
		moneyLabels[0] = createLabel("0");
		moneyLabels[0].setFont(boldFont);
		
		nameLabels[1] = createLabel("Player Two Money!:");
		moneyLabels[1] = createLabel("0");
		moneyLabels[1].setFont(boldFont);
		
		nameLabels[2] = createLabel("Player Three Money!:");
		moneyLabels[2] = createLabel("0");
		moneyLabels[2].setFont(boldFont);
		
		nameLabels[3] = createLabel("Player Four Money!:");
		moneyLabels[3] = createLabel("0");
		moneyLabels[3].setFont(boldFont);
		
		btnRollDice = new JButton("Roll Dice!");
		btnRollDice.setSize(50, 20);
		
		btnMortgage = new JButton("Mortgage!");
		btnMortgage.setSize(50, 20);
		
		for(int x = 0; x < 4; x++)
		{
			this.add(nameLabels[x]);
			this.add(moneyLabels[x]);
		}
		this.add(btnRollDice);
		this.add(btnMortgage);
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
		for(int x = 0; x < 4; x++)
		{
			moneyLabels[x].setText(Double.toString(money[x]));
		}
	}
	
	/**
	 * This function will take in a array that represents four players name,
	 * and set the labels as needed 
	 * @param names - names represented as string in array form
	 */
	public void setNames(String[] names)
	{
		for(int x = 0; x < 4; x++)
		{
			nameLabels[x].setText(names + " money!");
		}
	}
	
	public void setRollDiceAction(ActionListener al) {
		btnRollDice.addActionListener(al);
	}
	
	public void setMortgageAction(ActionListener al) {
		btnMortgage.addActionListener(al);
	}
	
	public JButton getBtnRollDice()
	{
		return btnRollDice;
	}
}
