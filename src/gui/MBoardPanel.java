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
import javax.swing.Box;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Component;

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
		this.positions = new int[40];
		this.players = new JLabel[4];
		this.setSize(500, 500);
		setLayout(null);
		
		Box startBox = Box.createVerticalBox();
		startBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		startBox.setBackground(Color.BLACK);
		startBox.setBounds(421, 421, 79, 79);
		add(startBox);
		
		Box property1 = Box.createVerticalBox();
		property1.setBorder(new LineBorder(new Color(0, 0, 0)));
		property1.setBounds(383, 421, 38, 79);
		add(property1);
		
		Box property2 = Box.createVerticalBox();
		property2.setBorder(new LineBorder(new Color(0, 0, 0)));
		property2.setBounds(345, 421, 38, 79);
		add(property2);
		
		Box property3 = Box.createVerticalBox();
		property3.setBorder(new LineBorder(new Color(0, 0, 0)));
		property3.setBounds(307, 421, 38, 79);
		add(property3);
		
		Box property4 = Box.createVerticalBox();
		property4.setBorder(new LineBorder(new Color(0, 0, 0)));
		property4.setBounds(269, 421, 38, 79);
		add(property4);
		
		Box property5 = Box.createVerticalBox();
		property5.setBorder(new LineBorder(new Color(0, 0, 0)));
		property5.setBounds(231, 421, 38, 79);
		add(property5);
		
		Box property6 = Box.createVerticalBox();
		property6.setBorder(new LineBorder(new Color(0, 0, 0)));
		property6.setBounds(193, 421, 38, 79);
		add(property6);
		
		Box property7 = Box.createVerticalBox();
		property7.setBorder(new LineBorder(new Color(0, 0, 0)));
		property7.setBounds(155, 421, 38, 79);
		add(property7);
		
		Box property8 = Box.createVerticalBox();
		property8.setBorder(new LineBorder(new Color(0, 0, 0)));
		property8.setBounds(117, 421, 38, 79);
		add(property8);
		
		Box property9 = Box.createVerticalBox();
		property9.setBorder(new LineBorder(new Color(0, 0, 0)));
		property9.setBounds(79, 421, 38, 79);
		add(property9);
		
		Box jailBox = Box.createVerticalBox();
		jailBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		jailBox.setBounds(0, 421, 79, 79);
		add(jailBox);
		
		Box property10 = Box.createHorizontalBox();
		property10.setBorder(new LineBorder(new Color(0, 0, 0)));
		property10.setBounds(0, 383, 79, 38);
		add(property10);
		
		Box property11 = Box.createHorizontalBox();
		property11.setBorder(new LineBorder(new Color(0, 0, 0)));
		property11.setBounds(0, 345, 79, 38);
		add(property11);
		
		Box property12 = Box.createHorizontalBox();
		property12.setBorder(new LineBorder(new Color(0, 0, 0)));
		property12.setBounds(0, 307, 79, 38);
		add(property12);
		
		Box property13 = Box.createHorizontalBox();
		property13.setBorder(new LineBorder(new Color(0, 0, 0)));
		property13.setBounds(0, 269, 79, 38);
		add(property13);
		
		Box property14 = Box.createHorizontalBox();
		property14.setBorder(new LineBorder(new Color(0, 0, 0)));
		property14.setBounds(0, 231, 79, 38);
		add(property14);
		
		Box property15 = Box.createHorizontalBox();
		property15.setBorder(new LineBorder(new Color(0, 0, 0)));
		property15.setBounds(0, 193, 79, 38);
		add(property15);
		
		Box property16 = Box.createHorizontalBox();
		property16.setBorder(new LineBorder(new Color(0, 0, 0)));
		property16.setBounds(0, 155, 79, 38);
		add(property16);
		
		Box property17 = Box.createHorizontalBox();
		property17.setBorder(new LineBorder(new Color(0, 0, 0)));
		property17.setBounds(0, 117, 79, 38);
		add(property17);
		
		Box property18 = Box.createHorizontalBox();
		property18.setBorder(new LineBorder(new Color(0, 0, 0)));
		property18.setBounds(0, 79, 79, 38);
		add(property18);
		
		Box freeParking = Box.createHorizontalBox();
		freeParking.setBorder(new LineBorder(new Color(0, 0, 0)));
		freeParking.setBounds(0, 0, 79, 79);
		add(freeParking);
		
		Box property19 = Box.createVerticalBox();
		property19.setBorder(new LineBorder(new Color(0, 0, 0)));
		property19.setBounds(79, 0, 38, 79);
		add(property19);
		
		Box property20 = Box.createHorizontalBox();
		property20.setBorder(new LineBorder(new Color(0, 0, 0)));
		property20.setBounds(117, 0, 38, 79);
		add(property20);
		
		Box property21 = Box.createHorizontalBox();
		property21.setBorder(new LineBorder(new Color(0, 0, 0)));
		property21.setBounds(155, 0, 38, 79);
		add(property21);
		
		Box property22 = Box.createHorizontalBox();
		property22.setBorder(new LineBorder(new Color(0, 0, 0)));
		property22.setBounds(193, 0, 38, 79);
		add(property22);
		
		Box property23 = Box.createHorizontalBox();
		property23.setBorder(new LineBorder(new Color(0, 0, 0)));
		property23.setBounds(231, 0, 38, 79);
		add(property23);
		
		Box property24 = Box.createHorizontalBox();
		property24.setBorder(new LineBorder(new Color(0, 0, 0)));
		property24.setBounds(269, 0, 38, 79);
		add(property24);
		
		Box property25 = Box.createHorizontalBox();
		property25.setBorder(new LineBorder(new Color(0, 0, 0)));
		property25.setBounds(307, 0, 38, 79);
		add(property25);
		
		Box property26 = Box.createHorizontalBox();
		property26.setBorder(new LineBorder(new Color(0, 0, 0)));
		property26.setBounds(345, 0, 38, 79);
		add(property26);
		
		Box property27 = Box.createHorizontalBox();
		property27.setBorder(new LineBorder(new Color(0, 0, 0)));
		property27.setBounds(383, 0, 38, 79);
		add(property27);
		
		Box goToJail = Box.createHorizontalBox();
		goToJail.setBorder(new LineBorder(new Color(0, 0, 0)));
		goToJail.setBounds(421, 0, 79, 79);
		add(goToJail);
		
		Box property28 = Box.createHorizontalBox();
		property28.setBorder(new LineBorder(new Color(0, 0, 0)));
		property28.setBounds(421, 79, 79, 38);
		add(property28);
		
		Box property29 = Box.createHorizontalBox();
		property29.setBorder(new LineBorder(new Color(0, 0, 0)));
		property29.setBounds(421, 117, 79, 38);
		add(property29);
		
		Box property30 = Box.createHorizontalBox();
		property30.setBorder(new LineBorder(new Color(0, 0, 0)));
		property30.setBounds(421, 155, 79, 38);
		add(property30);
		
		Box property31 = Box.createHorizontalBox();
		property31.setBorder(new LineBorder(new Color(0, 0, 0)));
		property31.setBounds(421, 193, 79, 38);
		add(property31);
		
		Box property32 = Box.createHorizontalBox();
		property32.setBorder(new LineBorder(new Color(0, 0, 0)));
		property32.setBounds(421, 231, 79, 38);
		add(property32);
		
		Box property33 = Box.createHorizontalBox();
		property33.setBorder(new LineBorder(new Color(0, 0, 0)));
		property33.setBounds(421, 269, 79, 38);
		add(property33);
		
		Box property34 = Box.createHorizontalBox();
		property34.setBorder(new LineBorder(new Color(0, 0, 0)));
		property34.setBounds(421, 307, 79, 38);
		add(property34);
		
		Box property35 = Box.createHorizontalBox();
		property35.setBorder(new LineBorder(new Color(0, 0, 0)));
		property35.setBounds(421, 345, 79, 38);
		add(property35);
		
		Box property36 = Box.createHorizontalBox();
		property36.setBorder(new LineBorder(new Color(0, 0, 0)));
		property36.setBounds(421, 383, 79, 38);
		add(property36);
		
		JLabel lblMonopoly = new JLabel("MONOPOLY");
		lblMonopoly.setFont(new Font("Manteka", Font.BOLD, 34));
		lblMonopoly.setBounds(154, 204, 191, 52);
		add(lblMonopoly);
		
		JLabel lblRedditEdition = new JLabel("Reddit Edition");
		lblRedditEdition.setBounds(219, 255, 66, 14);
		add(lblRedditEdition);
		
		Box chanceBox = Box.createHorizontalBox();
		chanceBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		chanceBox.setBounds(294, 323, 89, 58);
		add(chanceBox);
		
		JLabel lblChance = new JLabel("Chance");
		chanceBox.add(lblChance);
		
		Box ccBox = Box.createHorizontalBox();
		ccBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		ccBox.setBounds(117, 106, 89, 58);
		add(ccBox);
		
		JLabel lblNewLabel = new JLabel("Community Chest");
		lblNewLabel.setAlignmentX(0.5f);
		ccBox.add(lblNewLabel);
		
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
