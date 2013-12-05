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
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import monopoly.Dice;

/**
 * @author ajrk001
 *
 * A class that represents the Board in a monopoly game
 *
 */
public class MBoardPanel extends JPanel
{
	private int[][] positions; // array that will hold the positions on the board where the labels should be moved to
	private int[] playerPos; // where each player is on the board
	JLabel[] players; // array of the jlabels of each player
	
	JLabel diceOne; // image of the first dice
	JLabel diceTwo; // image of second dice
	
	public MBoardPanel()
	{
		this.positions = new int[40][2];
		this.players = new JLabel[4];
		this.playerPos = new int[4];
		this.setConstants(); // function that just sets the large array this.positions with default vals
		
		for(int x = 0; x < 4; x++)
		{
			this.playerPos[x] = 3; // needs to be at a different value than default 0 so it can set positions
		}
		
		this.setSize(650, 650); // set size of panel
		this.setLayout(null); // set by absolute values
		
		this.players[0] = new JLabel("1"); // create images for players
		this.players[0].setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/playerOne.png")));
		this.players[1] = new JLabel("2");
		this.players[1].setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/playerTwo.png")));
		this.players[2] = new JLabel("3");
		this.players[2].setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/playerThree.png")));
		this.players[3] = new JLabel("4");
		this.players[3].setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/playerFour.jpg")));
		for(int x = 0; x < 4; x++) 
		{
			this.players[x].setSize(10, 10); // set their size 
			this.moveCharacter(x, 0); // move to the beginning of board 
			this.players[x].setOpaque(true); // make sure they are drawn on top of everything
		}
		add(this.players[0]);
		add(this.players[1]);
		add(this.players[2]);
		add(this.players[3]);
		
		diceOne = new JLabel(); // creating the dice
		diceOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceOne.jpeg")));
		diceOne.setBounds(150, 250, 53, 53); // setting default position - just the image will be changed
		diceTwo = new JLabel();
		diceTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceTwo.jpeg")));
		diceTwo.setBounds(150, 310, 53, 53);
		add(diceOne);
		add(diceTwo);
		
		Box startBox = Box.createVerticalBox(); // creating boxes that represent each position
		startBox.setBorder(new LineBorder(new Color(0, 0, 0))); // needs border
		startBox.setBounds(550, 550, 100, 100);
		add(startBox);
		
		JLabel goImage = new JLabel(""); // each image will be enclosed within the box
		goImage.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/goPass.jpg")));
		startBox.add(goImage);
		
		Box property1 = Box.createVerticalBox();
		property1.setBorder(new LineBorder(new Color(0, 0, 0)));
		property1.setBounds(500, 550, 50, 100);
		add(property1);
		
		JLabel colorBox1 = new JLabel("");
		colorBox1.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/purple.jpg")));
		property1.add(colorBox1);
		
		JTextArea prop1 = new JTextArea();
		prop1.setEditable(false);
		prop1.setLineWrap(true);
		prop1.setText("Property");
		property1.add(prop1);
		
		Box property2 = Box.createVerticalBox();
		property2.setBorder(new LineBorder(new Color(0, 0, 0)));
		property2.setBounds(450, 550, 50, 100);
		add(property2);
		
		JLabel imageCommOne = new JLabel("");
		imageCommOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/commChest.jpg")));
		property2.add(imageCommOne);
		
		Box property3 = Box.createVerticalBox();
		property3.setBorder(new LineBorder(new Color(0, 0, 0)));
		property3.setBounds(400, 550, 50, 100);
		add(property3);
		
		JLabel colorBox2 = new JLabel("");
		colorBox2.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/purple.jpg")));
		property3.add(colorBox2);
		
		JTextArea prop2 = new JTextArea();
		prop2.setEditable(false);
		prop2.setLineWrap(true);
		prop2.setText("Property");
		property3.add(prop2);
		
		Box property4 = Box.createVerticalBox();
		property4.setBorder(new LineBorder(new Color(0, 0, 0)));
		property4.setBounds(350, 550, 50, 100);
		add(property4);
		
		JLabel imageTaxOne = new JLabel("");
		imageTaxOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/taxOne.png")));
		property4.add(imageTaxOne);
		
		Box property5 = Box.createVerticalBox();
		property5.setBorder(new LineBorder(new Color(0, 0, 0)));
		property5.setBounds(300, 550, 50, 100);
		add(property5);
		
		JLabel railroadOne = new JLabel("");
		railroadOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/railroadOne.jpg")));
		property5.add(railroadOne);
		
		Box property6 = Box.createVerticalBox();
		property6.setBorder(new LineBorder(new Color(0, 0, 0)));
		property6.setBounds(250, 550, 50, 100);
		add(property6);
		
		JLabel colorBox3 = new JLabel("");
		colorBox3.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/blue.jpg")));
		property6.add(colorBox3);
		
		JTextArea prop3 = new JTextArea();
		prop3.setEditable(false);
		prop3.setLineWrap(true);
		prop3.setText("Property");
		property6.add(prop3);
		
		Box property7 = Box.createVerticalBox();
		property7.setBorder(new LineBorder(new Color(0, 0, 0)));
		property7.setBounds(200, 550, 50, 100);
		add(property7);
		
		JLabel imageChanceOne = new JLabel("");
		imageChanceOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/chance.jpg")));
		imageChanceOne.setAlignmentX(0.5f);
		property7.add(imageChanceOne);
		
		Box property8 = Box.createVerticalBox();
		property8.setBorder(new LineBorder(new Color(0, 0, 0)));
		property8.setBounds(150, 550, 50, 100);
		add(property8);
		
		JLabel colorBox4 = new JLabel("");
		colorBox4.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/blue.jpg")));
		property8.add(colorBox4);
		
		JTextArea prop4 = new JTextArea();
		prop4.setEditable(false);
		prop4.setLineWrap(true);
		prop4.setText("Property");
		property8.add(prop4);
		
		Box property9 = Box.createVerticalBox();
		property9.setBorder(new LineBorder(new Color(0, 0, 0)));
		property9.setBounds(100, 550, 50, 100);
		add(property9);
		
		JLabel colorBox5 = new JLabel("");
		colorBox5.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/blue.jpg")));
		property9.add(colorBox5);
		
		JTextArea prop5 = new JTextArea();
		prop5.setEditable(false);
		prop5.setLineWrap(true);
		prop5.setText("Property");
		property9.add(prop5);
		
		Box jailBox = Box.createVerticalBox();
		jailBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		jailBox.setBounds(0, 550, 100, 100);
		add(jailBox);
		
		JLabel imageJail = new JLabel("");
		imageJail.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/jail.jpg")));
		jailBox.add(imageJail);
		
		Box property10 = Box.createHorizontalBox();
		property10.setBorder(new LineBorder(new Color(0, 0, 0)));
		property10.setBounds(0, 500, 100, 50);
		add(property10);
		
		JTextArea prop6 = new JTextArea();
		prop6.setEditable(false);
		prop6.setText("Property");
		property10.add(prop6);
		
		JLabel colorBox6 = new JLabel("");
		colorBox6.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/magenta.jpg")));
		property10.add(colorBox6);
		
		Box property11 = Box.createHorizontalBox();
		property11.setBorder(new LineBorder(new Color(0, 0, 0)));
		property11.setBounds(0, 450, 100, 50);
		add(property11);
		
		JLabel imageUtilityOne = new JLabel("");
		property11.add(imageUtilityOne);
		imageUtilityOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/utilityGold.png")));
		
		Box property12 = Box.createHorizontalBox();
		property12.setBorder(new LineBorder(new Color(0, 0, 0)));
		property12.setBounds(0, 400, 100, 50);
		add(property12);
		
		JTextArea prop7 = new JTextArea();
		prop7.setEditable(false);
		prop7.setLineWrap(true);
		prop7.setText("Property");
		property12.add(prop7);
		
		JLabel colorBox7 = new JLabel("");
		colorBox7.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/magenta.jpg")));
		property12.add(colorBox7);
		
		Box property13 = Box.createHorizontalBox();
		property13.setBorder(new LineBorder(new Color(0, 0, 0)));
		property13.setBounds(0, 350, 100, 50);
		add(property13);
		
		JTextArea prop8 = new JTextArea();
		prop8.setEditable(false);
		prop8.setText("Property");
		property13.add(prop8);
		
		JLabel colorBox8 = new JLabel("");
		colorBox8.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/magenta.jpg")));
		property13.add(colorBox8);
		
		Box property14 = Box.createHorizontalBox();
		property14.setBorder(new LineBorder(new Color(0, 0, 0)));
		property14.setBounds(0, 300, 100, 50);
		add(property14);
		
		JLabel railroadTwo = new JLabel("");
		railroadTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/railroadTwo.jpg")));
		property14.add(railroadTwo);
		
		Box property15 = Box.createHorizontalBox();
		property15.setBorder(new LineBorder(new Color(0, 0, 0)));
		property15.setBounds(0, 250, 100, 50);
		add(property15);
		
		JTextArea prop9 = new JTextArea();
		prop9.setEditable(false);
		prop9.setLineWrap(true);
		prop9.setText("Property");
		property15.add(prop9);
		
		JLabel colorBox9 = new JLabel("");
		colorBox9.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/orange.jpg")));
		property15.add(colorBox9);
		
		Box property16 = Box.createHorizontalBox();
		property16.setBorder(new LineBorder(new Color(0, 0, 0)));
		property16.setBounds(0, 200, 100, 50);
		add(property16);
		
		JLabel imageCommTwo = new JLabel("");
		imageCommTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/commChest.jpg")));
		property16.add(imageCommTwo);
		
		Box property17 = Box.createHorizontalBox();
		property17.setBorder(new LineBorder(new Color(0, 0, 0)));
		property17.setBounds(0, 150, 100, 50);
		add(property17);
		
		JTextArea prop10 = new JTextArea();
		prop10.setEditable(false);
		prop10.setLineWrap(true);
		prop10.setText("Property");
		property17.add(prop10);
		
		JLabel colorBox10 = new JLabel("");
		colorBox10.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/orange.jpg")));
		property17.add(colorBox10);
		
		Box property18 = Box.createHorizontalBox();
		property18.setBorder(new LineBorder(new Color(0, 0, 0)));
		property18.setBounds(0, 100, 100, 50);
		add(property18);
		
		JTextArea prop11 = new JTextArea();
		prop11.setEditable(false);
		prop11.setLineWrap(true);
		prop11.setText("Property");
		property18.add(prop11);
		
		JLabel colorBox11 = new JLabel("");
		colorBox11.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/orange.jpg")));
		property18.add(colorBox11);
		
		Box freeParking = Box.createHorizontalBox();
		freeParking.setBorder(new LineBorder(new Color(0, 0, 0)));
		freeParking.setBounds(0, 0, 100, 100);
		add(freeParking);
		
		JLabel imageFreeParking = new JLabel("");
		imageFreeParking.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/freeParking.png")));
		freeParking.add(imageFreeParking);
		
		Box property19 = Box.createVerticalBox();
		property19.setBorder(new LineBorder(new Color(0, 0, 0)));
		property19.setBounds(100, 0, 50, 100);
		add(property19);
		
		JTextArea prop12 = new JTextArea();
		prop12.setEditable(false);
		prop12.setLineWrap(true);
		prop12.setText("Property");
		property19.add(prop12);
		
		JLabel colorBox12 = new JLabel("");
		colorBox12.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/red.jpg")));
		property19.add(colorBox12);
		
		Box property20 = Box.createVerticalBox();
		property20.setBorder(new LineBorder(new Color(0, 0, 0)));
		property20.setBounds(150, 0, 50, 100);
		add(property20);
		
		JLabel imageChanceTwo = new JLabel("");
		imageChanceTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/chance.jpg")));
		imageChanceTwo.setAlignmentX(0.5f);
		property20.add(imageChanceTwo);
		
		Box property21 = Box.createVerticalBox();
		property21.setBorder(new LineBorder(new Color(0, 0, 0)));
		property21.setBounds(200, 0, 50, 100);
		add(property21);
		
		JTextArea prop13 = new JTextArea();
		prop13.setEditable(false);
		property21.add(prop13);
		prop13.setLineWrap(true);
		prop13.setText("Property");
		
		JLabel colorBox13 = new JLabel("");
		colorBox13.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/red.jpg")));
		property21.add(colorBox13);
		
		Box property22 = Box.createVerticalBox();
		property22.setBorder(new LineBorder(new Color(0, 0, 0)));
		property22.setBounds(250, 0, 50, 100);
		add(property22);
		
		JTextArea prop14 = new JTextArea();
		prop14.setEditable(false);
		prop14.setLineWrap(true);
		prop14.setText("Property");
		property22.add(prop14);
		
		JLabel colorBox14 = new JLabel("");
		colorBox14.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/red.jpg")));
		property22.add(colorBox14);
		
		Box property23 = Box.createVerticalBox();
		property23.setBorder(new LineBorder(new Color(0, 0, 0)));
		property23.setBounds(300, 0, 50, 100);
		add(property23);
		
		JLabel railroadThree = new JLabel("");
		railroadThree.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/railroadThree.jpg")));
		property23.add(railroadThree);
		
		Box property24 = Box.createVerticalBox();
		property24.setBorder(new LineBorder(new Color(0, 0, 0)));
		property24.setBounds(350, 0, 50, 100);
		add(property24);
		
		JTextArea prop15 = new JTextArea();
		prop15.setEditable(false);
		prop15.setLineWrap(true);
		prop15.setText("Property");
		property24.add(prop15);
		
		JLabel colorBox15 = new JLabel("");
		colorBox15.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/yellow.jpg")));
		property24.add(colorBox15);
		
		Box property25 = Box.createVerticalBox();
		property25.setBorder(new LineBorder(new Color(0, 0, 0)));
		property25.setBounds(400, 0, 50, 100);
		add(property25);
		
		JLabel imageUtilityTwo = new JLabel("");
		property25.add(imageUtilityTwo);
		imageUtilityTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/utilityEnhancment.png")));
		
		Box property26 = Box.createVerticalBox();
		property26.setBorder(new LineBorder(new Color(0, 0, 0)));
		property26.setBounds(450, 0, 50, 100);
		add(property26);
		
		JTextArea prop16 = new JTextArea();
		prop16.setEditable(false);
		prop16.setLineWrap(true);
		prop16.setText("Property");
		property26.add(prop16);
		
		JLabel colorBox16 = new JLabel("");
		colorBox16.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/yellow.jpg")));
		property26.add(colorBox16);
		
		Box property27 = Box.createVerticalBox();
		property27.setBorder(new LineBorder(new Color(0, 0, 0)));
		property27.setBounds(500, 0, 50, 100);
		add(property27);
		
		JTextArea prop17 = new JTextArea();
		prop17.setEditable(false);
		prop17.setLineWrap(true);
		prop17.setText("Property");
		property27.add(prop17);
		
		JLabel colorBox17 = new JLabel("");
		colorBox17.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/yellow.jpg")));
		property27.add(colorBox17);
		
		Box goToJail = Box.createHorizontalBox();
		goToJail.setBorder(new LineBorder(new Color(0, 0, 0)));
		goToJail.setBounds(550, 0, 100, 100);
		add(goToJail);
		
		JLabel imageGoToJail = new JLabel("");
		imageGoToJail.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/goToJail.jpg")));
		goToJail.add(imageGoToJail);
		
		Box property28 = Box.createHorizontalBox();
		property28.setBorder(new LineBorder(new Color(0, 0, 0)));
		property28.setBounds(550, 100, 100, 50);
		add(property28);
		
		JLabel colorBox18 = new JLabel("");
		colorBox18.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/green.jpg")));
		property28.add(colorBox18);
		
		JTextArea prop18 = new JTextArea();
		prop18.setEditable(false);
		prop18.setLineWrap(true);
		prop18.setText("Property");
		property28.add(prop18);
		
		Box property29 = Box.createHorizontalBox();
		property29.setBorder(new LineBorder(new Color(0, 0, 0)));
		property29.setBounds(550, 150, 100, 50);
		add(property29);
		
		JLabel colorBox19 = new JLabel("");
		colorBox19.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/green.jpg")));
		property29.add(colorBox19);
		
		JTextArea prop19 = new JTextArea();
		prop19.setEditable(false);
		prop19.setLineWrap(true);
		prop19.setText("Property");
		property29.add(prop19);
		
		Box property30 = Box.createHorizontalBox();
		property30.setBorder(new LineBorder(new Color(0, 0, 0)));
		property30.setBounds(550, 200, 100, 50);
		add(property30);
		
		JLabel imageCommThree = new JLabel("");
		imageCommThree.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/commChest.jpg")));
		property30.add(imageCommThree);
		
		Box property31 = Box.createHorizontalBox();
		property31.setBorder(new LineBorder(new Color(0, 0, 0)));
		property31.setBounds(550, 250, 100, 50);
		add(property31);
		
		JLabel colorBox20 = new JLabel("");
		colorBox20.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/green.jpg")));
		property31.add(colorBox20);
		
		JTextArea prop20 = new JTextArea();
		prop20.setEditable(false);
		prop20.setLineWrap(true);
		prop20.setText("Property");
		property31.add(prop20);
		
		Box property32 = Box.createHorizontalBox();
		property32.setBorder(new LineBorder(new Color(0, 0, 0)));
		property32.setBounds(550, 300, 100, 50);
		add(property32);
		
		JLabel railroadFour = new JLabel("");
		railroadFour.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/railroadFour.jpg")));
		property32.add(railroadFour);
		
		Box property33 = Box.createHorizontalBox();
		property33.setBorder(new LineBorder(new Color(0, 0, 0)));
		property33.setBounds(550, 350, 100, 50);
		add(property33);
		
		JLabel imageChanceThree = new JLabel("");
		imageChanceThree.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/chance.jpg")));
		property33.add(imageChanceThree);
		
		Box property34 = Box.createHorizontalBox();
		property34.setBorder(new LineBorder(new Color(0, 0, 0)));
		property34.setBounds(550, 400, 100, 50);
		add(property34);
		
		JLabel colorBox21 = new JLabel("");
		colorBox21.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/darkBlue.jpg")));
		property34.add(colorBox21);
		
		JTextArea prop21 = new JTextArea();
		prop21.setEditable(false);
		prop21.setLineWrap(true);
		prop21.setText("Property");
		property34.add(prop21);
		
		Box property35 = Box.createHorizontalBox();
		property35.setBorder(new LineBorder(new Color(0, 0, 0)));
		property35.setBounds(550, 450, 100, 50);
		add(property35);
		
		JLabel imageTaxTwo = new JLabel("");
		imageTaxTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/taxTwo.jpg")));
		property35.add(imageTaxTwo);
		
		Box property36 = Box.createHorizontalBox();
		property36.setBorder(new LineBorder(new Color(0, 0, 0)));
		property36.setBounds(550, 500, 100, 50);
		add(property36);
		
		JLabel colorBox22 = new JLabel("");
		colorBox22.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/darkBlue.jpg")));
		property36.add(colorBox22);
		
		JTextArea prop22 = new JTextArea();
		prop22.setEditable(false);
		prop22.setLineWrap(true);
		prop22.setText("Property");
		property36.add(prop22);
		
		JLabel lblMonopoly = new JLabel("MONOPOLY");
		lblMonopoly.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblMonopoly.setBounds(232, 273, 211, 52);
		add(lblMonopoly);
		
		JLabel lblRedditEdition = new JLabel("Reddit Edition");
		lblRedditEdition.setBounds(300, 331, 66, 14);
		lblRedditEdition.setSize(95, 12);
		add(lblRedditEdition);
		
		Box chanceBox = Box.createHorizontalBox();
		chanceBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		chanceBox.setBounds(411, 442, 100, 58);
		add(chanceBox);
		
		JLabel lblChance = new JLabel("Chance");
		chanceBox.add(lblChance);
		
		JLabel mainLabelChance = new JLabel("");
		mainLabelChance.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/chance.jpg")));
		chanceBox.add(mainLabelChance);
		
		Box ccBox = Box.createHorizontalBox();
		ccBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		ccBox.setBounds(150, 150, 156, 58);
		add(ccBox);
		
		JLabel lblCommChst = new JLabel("Community Chest");
		lblCommChst.setAlignmentX(0.5f);
		ccBox.add(lblCommChst);
		
		JLabel mainLabelCommChest = new JLabel("");
		mainLabelCommChest.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Images/commChest.jpg")));
		ccBox.add(mainLabelCommChest);
	}
	
	/**
	 * this function will update this panel with the correct image for the dice as assigned by the parameters passed, and will also
	 * assign a static location on the board to them
	 * @param one - the value that the first dice should have
	 * @param two - the value that the second dice should have
	 */
	public void rollDice(Dice dice)
	{
		switch (dice.getFirstValue()) // will reset the image using switch to set the file path
		{
			case 1: diceOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceOne.jpeg"))); break;
			case 2: diceOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceTwo.jpeg"))); break;
			case 3: diceOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceThree.jpeg"))); break;
			case 4: diceOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceFour.jpeg"))); break;
			case 5: diceOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceFive.jpeg"))); break;
			case 6: diceOne.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceSix.jpeg"))); break;
		}
		switch (dice.getSecondValue())
		{
			case 1: diceTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceOne.jpeg"))); break;
			case 2: diceTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceTwo.jpeg"))); break;
			case 3: diceTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceThree.jpeg"))); break;
			case 4: diceTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceFour.jpeg"))); break;
			case 5: diceTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceFive.jpeg"))); break;
			case 6: diceTwo.setIcon(new ImageIcon(MBoardPanel.class.getResource("/gui/Dice/diceSix.jpeg"))); break;
		}
	}
	
	/**
	 * this function will move a characters image to a assigned position as it has been moved in the model
	 * @param player - the player to be moved on the board
	 * @param position - the position on the board they have been moved to
	 */
	public void moveCharacter(int player, int position)
	{
		int temp = 0;
		for(int x = 0; x < 4; x++)
		{
			if(x != player)
			{
				if(this.playerPos[x] == position)
				{
					temp += 1;
				}
			}
		}
		System.out.println(temp);
		temp *= 15;
		if(position < 11 || (position > 19 && position < 31))
		{
			this.players[player].setBounds(this.positions[position][0], this.positions[position][1] + temp, 10, 10);
		}
		else
		{
			this.players[player].setBounds(this.positions[position][0] + temp, this.positions[position][1], 10, 10);
		}
		this.playerPos[player] = position;
	}
	
	/**
	 * This function will reset the board to a "new board" state, where everything is placed as it should be
	 */
	public void resetBoard()
	{
		for(int x = 0; x < 4; x++)
		{
			this.moveCharacter(x, 0);
		}
	}
	
	/**
	 * Very inefficient function that sets some of the constants required for the gui functions that set positions
	 * and images
	 */
	private void setConstants()
	{
		this.positions[0][0] = 600;
		this.positions[1][0] = 525;
		this.positions[2][0] = 475;
		this.positions[3][0] = 425;
		this.positions[4][0] = 375;
		this.positions[5][0] = 325;
		this.positions[6][0] = 275;
		this.positions[7][0] = 225;
		this.positions[8][0] = 175;
		this.positions[9][0] = 125;
		this.positions[10][0] = 50;
		for(int x = 0; x < 11; x++) { this.positions[x][1] = 575; }		
		this.positions[11][1] = 525;
		this.positions[12][1] = 475;
		this.positions[13][1] = 425;
		this.positions[14][1] = 375;
		this.positions[15][1] = 325;
		this.positions[16][1] = 275;
		this.positions[17][1] = 225;
		this.positions[18][1] = 175;
		this.positions[19][1] = 125;
		this.positions[20][1] = 50;
		for(int x = 11; x < 21; x++) { this.positions[x][0] = 25; }
		this.positions[30][0] = 600;
		this.positions[29][0] = 525;
		this.positions[28][0] = 475;
		this.positions[27][0] = 425;
		this.positions[26][0] = 375;
		this.positions[25][0] = 325;
		this.positions[24][0] = 275;
		this.positions[23][0] = 225;
		this.positions[22][0] = 175;
		this.positions[21][0] = 125;
		for(int x = 21; x < 31; x++) { this.positions[x][1] = 25; }
		this.positions[39][1] = 525;
		this.positions[38][1] = 475;
		this.positions[37][1] = 425;
		this.positions[36][1] = 375;
		this.positions[35][1] = 325;
		this.positions[34][1] = 275;
		this.positions[33][1] = 225;
		this.positions[32][1] = 175;
		this.positions[31][1] = 125;
		for(int x = 31; x < 40; x++) { this.positions[x][0] = 575; }
	}
}
