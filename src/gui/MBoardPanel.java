/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Nov 15, 2013, 10:13:56 AM
 */
package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author ajrk001
 *
 */
public class MBoardPanel extends JPanel
{
	private int[] positions;
	
	JLabel board;
	JLabel[] players;
	
	JLabel diceOne;
	JLabel diceTwo;
	
	JLabel chanceCard;
	JLabel communityChestCard;
	
	public MBoardPanel()
	{
		this.setLayout(null); // doesn't need a layout because will reperesent the 
		this.positions = new int[40];
		
		this.players = new JLabel[4];
		
	}
	
	/**
	 * this function will update this panel with the correct image for the dice as assigned by the parameters passed, and will also
	 * assign a random location on the board to them
	 * @param one - the value that the first dice should have
	 * @param two - the value that the second dice should have
	 */
	public void rollDice(int one, int two)
	{
		// will set a random location to each dice, and set the image to a the value represented by one and two for each dice
	}
	
	/**
	 * this function will move a characters image to a assigned position as it has been moved in the model
	 * @param player - the player to be moved on the board
	 * @param position - the position on the board they have been moved to
	 */
	public void moveCharacter(int player, int position)
	{
		// will move the player indicated by the 
	}
	
	/**
	 * This function will reset the board to a "new board" state, where everything is placed as it should be
	 */
	public void resetBoard()
	{
		
	}
}
